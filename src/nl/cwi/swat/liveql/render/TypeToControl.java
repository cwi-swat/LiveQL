package nl.cwi.swat.liveql.render;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.cwi.swat.liveql.ast.types.Bool;
import nl.cwi.swat.liveql.ast.types.Int;
import nl.cwi.swat.liveql.ast.types.Money;
import nl.cwi.swat.liveql.ast.types.Numeric;
import nl.cwi.swat.liveql.ast.types.Str;
import nl.cwi.swat.liveql.ast.types.Visitor;
import nl.cwi.swat.liveql.check.Error;
import nl.cwi.swat.liveql.eval.Undefined;
import nl.cwi.swat.liveql.eval.Value;

public class TypeToControl implements Visitor<Control, Boolean> {
	
	public static Control typeToWidget(nl.cwi.swat.liveql.ast.types.Type type, boolean editable) {
		Visitor<Control, Boolean> v = new TypeToControl();
		return type.accept(v, editable);
	}
	
	private TypeToControl() {
	}

	@Override
	public Control visit(Bool type, Boolean editable) {
		final JCheckBox cb = new JCheckBox();
		cb.setEnabled(editable);
		return new Control(cb) {
			public void setValue(nl.cwi.swat.liveql.eval.Value value) {
				boolean b = value.isDefined() && ((nl.cwi.swat.liveql.eval.Bool)value).getValue(); 
				cb.setSelected(b);
			}
			
			@Override
			public Value getValue() {
				return new nl.cwi.swat.liveql.eval.Bool(cb.isSelected());
			}

			@Override
			public void setHandler(final Handler handler) {
				cb.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						handler.handleEvent();
					}
				});
			}
		};
	}

	
	abstract class TextFieldWidget extends Control {
		public TextFieldWidget(JTextField component, int line) {
			super(component);
		}
		
		@Override
		public void setValue(nl.cwi.swat.liveql.eval.Value value) {
			textField().setText(value.toString());
		}

		@Override
		public void setHandler(final Handler handler) {
			textField().addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					handler.handleEvent();
				}
				
				@Override
				public void focusGained(FocusEvent e) {
				}
			});
		}

		protected JTextField textField() {
			return (JTextField)getComponent();
		}
	}
	
	
	@Override
	public Control visit(Int type, Boolean editable) {
		final JTextField tf = new JTextField(20);
		tf.setEditable(editable);
		return new TextFieldWidget(tf, type.getLine()) {
			@Override
			public Value getValue() {
				String txt = textField().getText();
				try {
					int n = Integer.parseInt(txt);
					return new nl.cwi.swat.liveql.eval.Int(n);
				}
				catch (NumberFormatException e) {
					return Undefined.UNDEF;
				}
			}
		};
	}

	@Override
	public Control visit(Money type, Boolean editable) {
		throw new UnsupportedOperationException("money is currently not implemented");
	}

	@Override
	public Control visit(Str type, Boolean editable) {
		final JTextField tf = new JTextField(20);
		tf.setEditable(editable);
		return new TextFieldWidget(tf, type.getLine()) {
			@Override
			public Value getValue() {
				return new nl.cwi.swat.liveql.eval.Str(textField().getText());
			}
		};
	}

	@Override
	public Control visit(Numeric type, Boolean editable) {
		throw new UnsupportedOperationException("no widgets for inferred generic types");
	}

	@Override
	public Control visit(Error type, Boolean editable) {
		throw new UnsupportedOperationException("no widgets for error types");
	}

}
