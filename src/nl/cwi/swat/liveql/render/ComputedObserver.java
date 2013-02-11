package nl.cwi.swat.liveql.render;

import java.util.Observable;
import java.util.Observer;

import nl.cwi.swat.liveql.ast.stat.Computed;
import nl.cwi.swat.liveql.eval.Eval;
import nl.cwi.swat.liveql.eval.Value;

public class ComputedObserver implements Observer {
	private final Control control;
	private final State state;
	private final Computed stat;

	public ComputedObserver(Computed stat, Control control, State state) {
		this.stat = stat;
		this.control = control;
		this.state = state;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Value value = stat.getExpr().accept(new Eval(state.getEnv()));
		state.putValue(stat.getName(), value);
		state.notify(stat.getName());
		control.setValue(value);
	}
}
