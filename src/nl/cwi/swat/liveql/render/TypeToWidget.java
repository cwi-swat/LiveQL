package nl.cwi.swat.liveql.render;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.types.Bool;
import nl.cwi.swat.liveql.ast.types.Int;
import nl.cwi.swat.liveql.ast.types.Money;
import nl.cwi.swat.liveql.ast.types.Numeric;
import nl.cwi.swat.liveql.ast.types.Str;
import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.ast.types.Visitor;
import nl.cwi.swat.liveql.check.Error;
import nl.cwi.swat.liveql.eval.Undefined;
import nl.cwi.swat.liveql.eval.Value;

public class TypeToWidget implements Visitor<Widget, Ident> {
	
	public static Widget typeToWidget(State theState, Type type, Ident name) {
		Visitor<Widget,Ident> v = new TypeToWidget(theState);
		return type.accept(v, name);
	}

	private State theState;
	
	private TypeToWidget(State theState) {
		this.theState = theState;
	}

	@Override
	public Widget visit(Bool type, final Ident name) {
		final JCheckBox cb = new JCheckBox();
		cb.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent _) {
				theState.getState(name).setValue(new nl.cwi.swat.liveql.eval.Bool(cb.isSelected()));
				theState.trigger(name);
			}
			
		});
		
		return new Widget(cb, theState) {
			
			
			public void setValue(nl.cwi.swat.liveql.eval.Value value) {
				boolean b = value.isDefined() && ((nl.cwi.swat.liveql.eval.Bool)value).getValue(); 
				cb.setSelected(b);
			}
			
			@Override
			public Value getValue() {
				return new nl.cwi.swat.liveql.eval.Bool(cb.isSelected());
			}
			
			@Override
			public void setEditable(boolean editable) {
				cb.setEnabled(editable);
			}

		};
	}

	
	
	abstract class TextFieldWidget extends Widget {
		public TextFieldWidget(JTextField component, final Ident name, final State theState) {
			super(component, theState);
			component.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					theState.getState(name).setValue(getValue());
					theState.trigger(name);
				}
				
				@Override
				public void focusGained(FocusEvent e) {
				}
			});
		}
		
		@Override
		public void setValue(nl.cwi.swat.liveql.eval.Value value) {
			textField().setText(value.toString());
		}

		protected JTextField textField() {
			return (JTextField)getComponent();
		}
		
		@Override
		public void setEditable(boolean editable) {
			textField().setEditable(editable);
		}
	}
	
	
	@Override
	public Widget visit(Int type, Ident name) {
		final JTextField tf = new JTextField(3);
		return new TextFieldWidget(tf, name, theState) {
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
	public Widget visit(Money type, Ident name) {
		throw new UnsupportedOperationException("money is currently not implemented");
	}

	@Override
	public Widget visit(Str type, Ident name) {
		final JTextField tf = new JTextField(20);
		return new TextFieldWidget(tf, name, theState) {
			@Override
			public Value getValue() {
				return new nl.cwi.swat.liveql.eval.Str(textField().getText());
			}
		};
	}

	@Override
	public Widget visit(Numeric type, Ident name) {
		throw new UnsupportedOperationException("no widgets for inferred generic types");
	}

	@Override
	public Widget visit(Error type, Ident name) {
		throw new UnsupportedOperationException("no widgets for error types");
	}

}
