package nl.cwi.swat.liveql.render;

import java.util.Observable;


public abstract class Handler extends Observable {
	public abstract void handleEvent();

	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
}
