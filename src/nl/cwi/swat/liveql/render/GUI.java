package nl.cwi.swat.liveql.render;

import javax.swing.JPanel;

public class GUI {

	private State state;
	private final JPanel panel;

	public GUI(State state, JPanel panel) {
		this.state = state;
		this.panel = panel;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
