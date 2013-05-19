package nl.cwi.swat.liveql.render;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.eval.Env;
import nl.cwi.swat.liveql.eval.Undefined;
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
	
	public List<QState> getStates(Ident name) {
		List<QState> qs = new ArrayList<QState>();
		for (QState q: this) {
			if (q.getName().equals(name)) {
				qs.add(q);
			}
		}
		return qs;
	}
	
	@Deprecated
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
		for (QState q: getStates(var)) {
			if (q.isVisible()) {
				return q.getValue();
			}
		}
		return Undefined.UNDEF;
	}
	

	
	public void trigger(Ident x) {
		boolean change = true;
		while (change) {
			change = false;
			List<Ident> todo = new ArrayList<Ident>();
			todo.add(x);
			while (!todo.isEmpty()) {
				Ident y = todo.remove(0);
				for (QState q: this) {
					boolean vis = false, val = false;
					if (q.hasControlDependencyOn(y)) {
						vis = q.updateVisibility(this);
						val = q.recompute(this);
					}
					if (q.hasDataDependencyOn(y)) {
						val = q.recompute(this);
					}
					if (vis || val) {
						change = true;
						todo.add(q.getName());				
					}
				}
			}

		}
//		Stack<Ident> todo = new Stack<Ident>();
//		todo.push(x);
//		while (!todo.isEmpty()) {
//			Ident y = todo.pop();
//			System.out.println("Y = " + y);
//			for (QState q: this) {
//				boolean vis = false, val = false;
//				if (q.hasControlDependencyOn(y)) {
//					vis = q.updateVisibility(this);
//				}
//				if (q.hasDataDependencyOn(y)) {
//					val = q.recompute(this);
//				}
//				if (vis || val) {
//					System.out.println("vis = " + vis + " val = " + val);
//					todo.push(q.getName());				
//				}
//			}
//		}
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
