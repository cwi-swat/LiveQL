package nl.cwi.swat.liveql.render;

import javax.swing.JComponent;

import nl.cwi.swat.liveql.eval.Value;

public abstract class Control {
	private final JComponent component;
	
	public Control(JComponent component) {
		this.component = component;
	}
	
	public JComponent getComponent() {
		return component;
	}

	public abstract void setValue(Value value);
	public abstract Value getValue();
	public abstract void setHandler(Handler handler);

	public void setVisible(boolean b) {
		getComponent().setVisible(b);
	}
	
}

