package nl.cwi.swat.liveql.ast.types;

import nl.cwi.swat.liveql.eval.Value;

public class Money extends Numeric {
	public Money() {
		super();
	}
	
	public Money(int line) {
		super(line);
	}

	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToMoney();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Money;
	}
	
	@Override
	public String toString() {
		return "money";
	}
	
	@Override
	public Value defaultValue() {
		throw new AssertionError("not yet implemeted");
	}

}
