package nl.cwi.swat.liveql.render;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.eval.Undefined;
import nl.cwi.swat.liveql.eval.Value;

public class State {
	private final Map<Ident, Value> env;
	private final Map<Ident, Observable> observables;
	private final HashMap<Ident, Control> controls;
	
	public State(Map<Ident, Value> env) {
		this.env = env;
		this.observables = new HashMap<Ident, Observable>();
		this.controls = new HashMap<Ident, Control>();
	}

	public State() {
		this(new HashMap<Ident, Value>());
	}

	public void addObserver(Ident x, Observer obs) {
		observables.get(x).addObserver(obs);
	}
	
	public void putObservable(Ident x, Observable obs) {
		observables.put(x,  obs);
	}

	public void putValue(Ident name, Value value) {
		env.put(name, value);
	}
	
	public Value getValue(Ident name) {
		if (hasValue(name)) {
			return env.get(name);
		}
		return Undefined.UNDEF;
	}
	
	public Map<Ident, Value> getEnv() {
		return env;
	}

	public void notify(Ident name) {
		observables.get(name).notifyObservers();		
	}

	public boolean hasValue(Ident name) {
		return env.containsKey(name);
	}

	public void merge(State old) {
		// TODO: deal with type conversions!
		//getEnv().putAll(old.getEnv());
		Map<Ident, Value> oldEnv = old.getEnv();
		for (Ident var: oldEnv.keySet()) {
			System.out.println("OLD: " + var + " = " + oldEnv.get(var));
			if (!controls.containsKey(var)) {
				System.out.println("I don't have a " + var);
				// if I do not have it, it is deleted
				continue;
			}
			
			if (oldEnv.get(var) != Undefined.UNDEF) {
				putValue(var, oldEnv.get(var));
				// Hmm, wouldn't want to do this for computed 
				// fields. It will now be overriden when triggering.
				controls.get(var).setValue(oldEnv.get(var));
			}
		}
		for (Ident var: observables.keySet()) {
//			if (hasValue(var)) {
//				// only update non-computed controls.
//				controls.get(var).setValue(getValue(var));
//			}
			observables.get(var).notifyObservers();
		}
	}

	public void putControl(Ident name, Control ctl) {
		controls.put(name, ctl);
	}
	
	
}
