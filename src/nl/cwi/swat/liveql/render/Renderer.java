package nl.cwi.swat.liveql.render;

import static nl.cwi.swat.liveql.ast.expr.UsedVars.usedVars;
import static nl.cwi.swat.liveql.render.TypeToControl.typeToWidget;
import static nl.cwi.swat.liveql.render.Util.getPositionForLine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;

import nl.cwi.swat.liveql.ast.expr.And;
import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.expr.Not;
import nl.cwi.swat.liveql.ast.form.Form;
import nl.cwi.swat.liveql.ast.stat.Answerable;
import nl.cwi.swat.liveql.ast.stat.Block;
import nl.cwi.swat.liveql.ast.stat.Computed;
import nl.cwi.swat.liveql.ast.stat.IfThen;
import nl.cwi.swat.liveql.ast.stat.IfThenElse;
import nl.cwi.swat.liveql.ast.stat.Label;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.ast.stat.Visitor;
import nl.cwi.swat.liveql.eval.Eval;
import nl.cwi.swat.liveql.eval.Value;
import nl.cwi.swat.liveql.render.Util.Pair;

public class Renderer implements Visitor {
	private final Container panel;
	private final State state;
	private final JTextArea editor;
	private final JFrame window;
	private Expr cond;
	
	private static void render(Expr cond, Stat stat, State state, Container panel, JTextArea editor, JFrame window) {
		Renderer r = new Renderer(cond, state, panel, editor, window);
		stat.accept(r);
	}
	
	private Renderer(Expr cond, State state, Container panel, JTextArea editor, JFrame window) {
		this.cond = cond;
		this.state = state;
		this.editor = editor;
		this.window = window;
		this.panel = panel; //new Container(new MigLayout("wrap 2", "[grow][grow]"));
	}
	
	public static void render(Form form, State state, Container panel, JTextArea editor, JFrame window) {
		render(new nl.cwi.swat.liveql.ast.expr.Bool(true, -1), form.getBody(), state, panel, editor, window);
	}
	
	private void add(Component comp) {
		panel.add(comp, "hidemode 3");
	}

	private JLabel addLabel(final Label label) {
		JLabel jlabel = new JLabel(label.getValue());
		jlabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent event) {
				Document doc = editor.getDocument();
				try {
					String txt = doc.getText(0, doc.getLength());
					Pair pair = getPositionForLine(txt, label.getLine());
					editor.getHighlighter().removeAllHighlights();
					editor.getHighlighter().addHighlight(pair.start, pair.end, 
							new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY));
			        editor.grabFocus();

				} catch (BadLocationException e1) {
					return;
				}
			}
		});
		add(jlabel);		
		return jlabel;
	}
	
	private void registerDeps(Expr expr, Observer obs) {
		// NB: this requires there be no cyclic deps.
		for (Ident x: usedVars(expr)) {
			state.addObserver(x, obs);
		}
	}
	
	private void registerComputed(Computed stat, Control comp) {
		Value value = stat.getExpr().accept(new Eval(state.getEnv()));
		comp.setValue(value);
		state.putValue(stat.getName(), value);
		state.putControl(stat.getName(), comp);
		state.putObservable(stat.getName(), new ComputedPropagator(stat.getName()));
	}
	
	private void registerAnswerable(Answerable stat, Control comp) {
		Handler handler = new AnswerHandler(stat, comp, state);
		comp.setHandler(handler);
		state.putObservable(stat.getName(), handler);
		state.putControl(stat.getName(), comp);
	}

	@Override
	public void visit(Computed stat) {
		JLabel label = addLabel(stat.getLabel());
		Control ctl = typeToWidget(stat.getType(), false);
		registerDeps(stat.getExpr(), new ComputedObserver(stat, ctl, state));
		registerDeps(cond, new ConditionObserver(cond, label, ctl.getComponent(), state, window)); 
		registerComputed(stat, ctl);
		add(ctl.getComponent());
	}

	@Override
	public void visit(Answerable stat) {
		JLabel label = addLabel(stat.getLabel());
		Control ctl = typeToWidget(stat.getType(), true);
		registerAnswerable(stat, ctl);
		registerDeps(cond, new ConditionObserver(cond, label, ctl.getComponent(), state, window)); 
		add(ctl.getComponent());
	}
	
	@Override
	public void visit(IfThen stat) {
		render(new And(cond, stat.getCond()), stat.getBody(), state, panel, editor, window);
	}

	@Override
	public void visit(IfThenElse stat) {
		render(new And(cond, stat.getCond()), stat.getBody(), state, panel, editor, window);
		render(new And(cond, new Not(stat.getCond())), stat.getElseBody(), state, panel, editor, window);
	}

	@Override
	public void visit(Block stat) {
		for (Stat s: stat.getStats()) {
			s.accept(this);
		}
	}
}
