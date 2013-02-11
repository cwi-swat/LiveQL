package nl.cwi.swat.liveql.render;

import nl.cwi.swat.liveql.ast.stat.Answerable;

public class AnswerHandler extends Handler {
	
	private final Answerable stat;
	private final State state;
	private final Control control;

	public AnswerHandler(Answerable stat, Control control, State state) {
		this.stat = stat;
		this.control = control;
		this.state = state;
	}

	@Override
	public void handleEvent() {
		state.putValue(stat.getName(), control.getValue());
		setChanged();
		notifyObservers();
	}

}
