package nl.cwi.swat.liveql.render;

import static nl.cwi.swat.liveql.check.CheckStat.checkStat;
import static nl.cwi.swat.liveql.parser.antlr.FormParser.parse;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;

import net.miginfocom.swing.MigLayout;
import nl.cwi.swat.liveql.ast.form.Form;
import nl.cwi.swat.liveql.check.Message;
import nl.cwi.swat.liveql.parser.test.ParseError;
import nl.cwi.swat.liveql.render.Util.Pair;

import org.antlr.runtime.RecognitionException;


public class App implements Runnable {
	private final List<Message> theErrors;
	private final JFrame renderFrame;
	private final JFrame editorFrame;
	private final JTextArea editor;
	private Form form;
	private final JTable errorMessages;
	private final String source;
	private State state;
	
	public App(String resourcePath) throws IOException, ParseError {
		this.theErrors = new ArrayList<Message>();
		this.errorMessages = new JTable(new ErrorTableModel());
		InputStream stream = App.class.getResourceAsStream(resourcePath);
		this.source = Util.readStream(stream);
        this.form = parse(source);
        this.state = new State();
		this.renderFrame = new JFrame(form.getName().getName());
		this.editorFrame = new JFrame(form.getName().getName());
		this.editor = new JTextArea(40, 60);
		state = new State();
	}
	
	public static void main(String[] args) {
		try {
			EventQueue.invokeLater(new App(args[0]));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseError e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		setup();
	}
	
	private void setup() {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setupRenderFrame();
		setupEditorFrame();
	}

	private void setupEditorFrame() {
		JScrollPane editorPane = new JScrollPane(editor);
		Font font = new Font("Monaco", Font.PLAIN, 12);
		editor.setFont(font);
		editor.setText(source);
		
		errorMessages.setPreferredScrollableViewportSize(new Dimension(
				errorMessages.getPreferredScrollableViewportSize().width, 
				2 * errorMessages.getRowHeight())); 

		JScrollPane errorPane = new JScrollPane(errorMessages);
		JPanel cp = new JPanel(new MigLayout("wrap 1"));

		editor.getDocument().addDocumentListener(new SourceDocumentListener());

		cp.add(editorPane, "w 100%-10, h 80%-10,span,wrap");
		
		cp.add(errorPane, "w 100%-10, h 20%-10,span,wrap");
		
		errorMessages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		errorMessages.getSelectionModel().addListSelectionListener(new ErrorListListener());
		
		editorFrame.setPreferredSize(new Dimension(600, 500));
		editorFrame.setContentPane(cp);
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorFrame.pack();
		editorFrame.setLocationRelativeTo(null);
		editorFrame.setLocation(80, 100);
		editorFrame.setVisible(true);
	}
	
	private void setupRenderFrame() {
		renderFrame.setLayout(new FlowLayout());
		Container panel = renderFrame.getContentPane();
		panel.setLayout(new MigLayout("wrap 2", "[grow][grow]"));
		Renderer.render(form, state, panel, editor, renderFrame);
		//renderFrame.getContentPane().add(theGui.getPanel());
		renderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		renderFrame.pack();
		renderFrame.setLocationRelativeTo(null);
		renderFrame.setLocation(700, 100);
		renderFrame.setVisible(true);		
	}

		
	@SuppressWarnings("serial")
	private class ErrorTableModel extends AbstractTableModel {

		@Override
		public int getColumnCount() {
			return 2;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return theErrors.size();
		}
		
		public String getColumnName(int column) {
			switch (column) {
			case 0: return "Line";
			case 1: return "Message";
			}
			return null;
		};

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 0: return theErrors.get(row).getLine();
			case 1: return theErrors.get(row).getMessage();
			}
			return null;
		}
	}
	
	private class SourceDocumentListener implements DocumentListener {
		private void reparseEtc(Document doc) {
			editor.getHighlighter().removeAllHighlights();
			System.out.println("Reparsing");
			try {
				String text = doc.getText(0, doc.getLength());
				try {
					form = parse(text);
				}
				catch (RuntimeException e) {
					if (e.getCause() instanceof RecognitionException) {
						RecognitionException pe = (RecognitionException)e.getCause();
						int line = pe.line;
						System.err.println("Parse error at line: " + line + " and column " + pe.charPositionInLine);
						return;
					}
					throw e;
				}
				theErrors.clear();
				checkStat(form.getBody(), theErrors);
				((ErrorTableModel)errorMessages.getModel()).fireTableDataChanged();
				if (!theErrors.isEmpty()) {
					return;
				}

				renderFrame.getContentPane().removeAll();
				State newState = new State(state.getEnv());
				Renderer.render(form, newState, 
						renderFrame.getContentPane(), editor, renderFrame);
				newState.merge(state);
				state = newState;
				//theGui.setState(gui.getState());
				//renderFrame.getContentPane().add(gui.getPanel());
				renderFrame.pack();
//				renderFrame.getContentPane().repaint();
//				renderFrame.repaint();
			}
			catch (ParseError e) {
				System.err.println("Parse error: " + e.getMessage());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void removeUpdate(DocumentEvent event) {
			reparseEtc((Document)event.getDocument());
		}
		
		@Override
		public void insertUpdate(DocumentEvent event) {
			reparseEtc((Document)event.getDocument());
		}
		
		@Override
		public void changedUpdate(DocumentEvent event) {
			reparseEtc((Document)event.getDocument());
		}
	}
	
	private class ErrorListListener implements ListSelectionListener {

		 public void valueChanged(ListSelectionEvent e) {
             //Ignore extra messages.
			 if (e.getValueIsAdjusting()) return;
			 editor.getHighlighter().removeAllHighlights();
			 ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			 if (lsm.isSelectionEmpty()) {
				 return;
			 }
			 int selectedRow = lsm.getMinSelectionIndex();
			 System.out.println("Row " + selectedRow
					 + " is now selected.");

			 Document doc = editor.getDocument();
			 try {
				 String txt = doc.getText(0, doc.getLength());
				 Pair pair = Util.getPositionForLine(txt, theErrors.get(selectedRow).getLine());
				 editor.getHighlighter().addHighlight(pair.start, pair.end, 
						 new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY));

			 } 
			 catch (BadLocationException e1) {
				 return;
			 }
			 editor.grabFocus();
         }
	}
}
