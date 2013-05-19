package nl.cwi.swat.liveql.render;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.eval.Env;
import nl.cwi.swat.liveql.eval.Value;

public class State implements Iterable<QState>, Env {
	private List<QState> qStates;
	private List<Widget> widgets;
	
	public State() {
		qStates = new ArrayList<QState>();
		widgets = new ArrayList<Widget>();
	}
	
	public void remove(int i) {
		qStates.remove(i);
		widgets.remove(i);
	}

	@Override
	public Iterator<QState> iterator() {
		return qStates.iterator();
	}
	
	public Widget getWidget(int i) {
		return widgets.get(i);
	}
	
	public QState getState(int i) {
		return qStates.get(i);
	}
	
	public QState getState(Ident name) {
		for (QState q: this) {
			if (q.getName().equals(name)) {
				return q;
			}
		}
		return null;
	}

	// Slow, but could be optimized.
	@Override
	public boolean containsKey(Ident var) {
		for (QState q: this) {
			if (q.getName().equals(var)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Value get(Ident var) {
		return getState(var).getValue();
	}
	

	
	public void trigger(Ident x) {
		Set<Ident> todo = new HashSet<Ident>();
		todo.add(x);
		while (!todo.isEmpty()) {
			x = todo.iterator().next();
			todo.remove(x);
			for (QState q: this) {
				if (q.hasDataDependencyOn(x)) {
					boolean changed = q.recompute(this);
					if (changed) {
						todo.add(q.getName());
					}
				}
				if (q.hasControlDependencyOn(x)) {
					q.updateVisibility(this);
				}
			}
		}
	}

	public void add(int index, QState q, Widget w) {
		qStates.add(index, q);
		widgets.add(index, w);
	}

	public void replace(int index, QState q, Widget w) {
		qStates.remove(index);
		widgets.remove(index);
		add(index, q, w);
	}
	
}
