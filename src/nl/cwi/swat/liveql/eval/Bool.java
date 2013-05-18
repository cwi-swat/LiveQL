package nl.cwi.swat.liveql.eval;

import nl.cwi.swat.liveql.ast.types.Type;

public class Bool extends Value {
	private final boolean value;

	public Bool(boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return this.value;
	}
	
	@Override
	public Value andBool(Bool arg) {
		return new Bool(arg.getValue() && getValue());
	}
	
	@Override
	public Value orBool(Bool arg) {
		return new Bool(arg.getValue() || getValue());
	}
	
	@Override
	public Value and(Value arg) {
		return arg.andBool(this);
	}
	
	@Override
	public Value or(Value arg) {
		return arg.orBool(this);
	}
	
	@Override
	public Value not() {
		return new Bool(!getValue());
	}

	@Override
	public Value convert(Type type) {
		return type.convertFromBool(this);
	}
	
}
