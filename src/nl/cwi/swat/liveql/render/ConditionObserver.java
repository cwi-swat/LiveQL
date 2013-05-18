package nl.cwi.swat.liveql.render;

import java.awt.Component;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.eval.Bool;
import nl.cwi.swat.liveql.eval.Eval;
import nl.cwi.swat.liveql.eval.Value;

public class ConditionObserver implements Observer {
	
	private final List<Expr> conds;
	private final State state;
	private final JFrame window;
	private final Component label;
	private final Component widget;

	public ConditionObserver(List<Expr> conds, Component label, Component widget, State state, JFrame window) {
		this.conds = conds;
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
		boolean visible = isEnabled();
		label.setVisible(visible);
		widget.setVisible(visible);
	}

	private boolean isEnabled() {
		for (Expr cond: conds) {
			Value value = cond.accept(new Eval(state.getEnv()));
			boolean enabled = value.isDefined() && ((Bool)value).getValue();
			if (!enabled) {
				return false;
			}
		}
		return true;
	}
	
}
