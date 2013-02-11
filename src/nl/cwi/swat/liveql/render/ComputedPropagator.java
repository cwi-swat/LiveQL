package nl.cwi.swat.liveql.render;

import java.util.Observable;

import nl.cwi.swat.liveql.ast.expr.Ident;

public class ComputedPropagator extends Observable {
	
	private Ident name;

	public ComputedPropagator(Ident name) {
		this.name = name;
	}
	
	@Override
	public void notifyObservers() {
		System.out.println("Propagating " + name);
		setChanged();
		super.notifyObservers();
	}
}
