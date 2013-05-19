package nl.cwi.swat.liveql.eval;

import nl.cwi.swat.liveql.ast.types.Type;

public class Int extends Value {
	private final Integer value;
	
	public Int(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	
	@Override
	public Value add(Value arg) {
		return arg.addInt(this);
	}
	
	@Override
	public Value sub(Value arg) {
		return arg.subInt(this);
	}
	
	@Override
	public Value div(Value arg) {
		return arg.divInt(this);
	}
	
	@Override
	public Value mul(Value arg) {
		return arg.mulInt(this);
	}
	
	@Override
	public Value lt(Value arg) {
		return arg.ltInt(this);
	}
	
	@Override
	public Value gt(Value arg) {
		return arg.gtInt(this);
	}
	
	@Override
	public Value leq(Value arg) {
		return arg.leqInt(this);
	}
	
	@Override
	public Value geq(Value arg) {
		return arg.geqInt(this);
	}
	
	
	
	/*
	 * NB: below the arguments are reversed because of double dispatch.
	 */
	
	@Override
	protected Value addInt(Int arg) {
		return new Int(arg.getValue() + getValue());
	}

	@Override
	protected Value subInt(Int arg) {
		return new Int(arg.getValue() - getValue());
	}

	@Override
	protected Value mulInt(Int arg) {
		return new Int(arg.getValue() * getValue());
	}

	@Override
	protected Value divInt(Int arg) {
		return new Int(arg.getValue() / getValue());
	}
	
	@Override
	public Value pos() {
		return this;
	}
	
	@Override
	public Value neg() {
		return new Int(- getValue());
	}
	
	@Override
	protected Value gtInt(Int arg) {
		return new Bool(arg.getValue() > getValue());
	}

	@Override
	protected Value ltInt(Int arg) {
		return new Bool(arg.getValue() < getValue());
	}
	
	@Override
	protected Value leqInt(Int arg) {
		return new Bool(arg.getValue() <= getValue());
	}

	@Override
	protected Value geqInt(Int arg) {
		return new Bool(arg.getValue() >= getValue());
	}

	@Override
	public Value convert(Type type) {
		return type.convertFromInt(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Int)) {
			return false;
		}
		return getValue().equals(((Int)obj).getValue());
	}
}
