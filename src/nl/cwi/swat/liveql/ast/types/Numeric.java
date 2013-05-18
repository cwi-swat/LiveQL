package nl.cwi.swat.liveql.ast.types;

import nl.cwi.swat.liveql.eval.Bool;
import nl.cwi.swat.liveql.eval.Int;
import nl.cwi.swat.liveql.eval.Str;
import nl.cwi.swat.liveql.eval.Value;

public class Numeric extends Type {
	
	public Numeric() {
		super();
	}
	
	public Numeric(int line) {
		super(line);
	}
	
	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToNumeric();
	}
	
	@Override
	public boolean isCompatibleToInt() {
		return true;
	}
	
	@Override
	public boolean isCompatibleToMoney() {
		return true;
	}
	
	@Override
	public boolean isCompatibleToNumeric() {
		return true;
	}
	
	@Override
	public String toString() {
		return "numeric";
	}

	@Override
	public <T, U> T accept(Visitor<T, U> visitor, U arg) {
		return visitor.visit(this, arg);
	}

	@Override
	public Value convertFromStr(Str str) {
		throw new AssertionError("numeric does not have values");
	}

	@Override
	public Value convertFromInt(Int n) {
		throw new AssertionError("numeric does not have values");
	}

	@Override
	public Value convertFromBool(Bool bool) {
		throw new AssertionError("numeric does not have values");
	}
}
