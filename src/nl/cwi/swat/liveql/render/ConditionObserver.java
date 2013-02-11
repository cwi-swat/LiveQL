package nl.cwi.swat.liveql.render;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.eval.Bool;
import nl.cwi.swat.liveql.eval.Eval;
import nl.cwi.swat.liveql.eval.Value;

public class ConditionObserver implements Observer {
	
	private final Expr cond;
	private final State state;
	private final JFrame window;
	private final Component label;
	private final Component widget;

	public ConditionObserver(Expr cond, Component label, Component widget, State state, JFrame window) {
		this.cond = cond;
		this.label = label;
		this.widget = widget;
		this.state = state;
		this.window = window;
		updateVisibility();
	}

	@Override
	public void update(Observable o, Object arg) {
		updateVisibility();
		window.pack();
	}

	private void updateVisibility() {
		Value value = cond.accept(new Eval(state.getEnv()));
		boolean visible = value.isDefined() && ((Bool)value).getValue();
		label.setVisible(visible);
		widget.setVisible(visible);
	}

}
