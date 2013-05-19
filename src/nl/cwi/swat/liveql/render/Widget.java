package nl.cwi.swat.liveql.render;

import javax.swing.JComponent;

import nl.cwi.swat.liveql.eval.Value;

public abstract class Widget {
	private final JComponent component;
	private final State theState;

	public Widget(JComponent component, State theState) {
		this.component = component;
		this.theState = theState;
	}
	
	
	
	public State getTheState() {
		return theState;
	}

	public JComponent getComponent() {
		return component;
	}

	public abstract void setValue(Value value);
	public abstract Value getValue();

	public void setVisible(boolean b) {
		getComponent().setVisible(b);
	}

	public abstract void setEditable(boolean editable);

}
