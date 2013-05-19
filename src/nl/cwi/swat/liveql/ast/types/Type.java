package nl.cwi.swat.liveql.ast.types;

import nl.cwi.swat.liveql.eval.Value;

public abstract class Type {
	
	private final int line;

	public Type(int line) {
		this.line = line;
	}
	
	public Type() {
		this(-1);
	}
	
	public int getLine() {
		return line;
	}
	
	public boolean isError() {
		return false;
	}
	
	public abstract <T, U> T accept(Visitor<T, U> visitor, U arg);
	
	public abstract boolean isCompatibleTo(Type t);
	
	public boolean isCompatibleToInt() {
		return false;
	}

	public boolean isCompatibleToNumeric() {
		return false;
	}
	
	public boolean isCompatibleToStr() {
		return false;
	}
	
	public boolean isCompatibleToBool() {
		return false;
	}
	
	public boolean isCompatibleToMoney() {
		return false;
	}

	public Value convertFromStr(nl.cwi.swat.liveql.eval.Str str) {
		throw new AssertionError("subclasses should override this method");
	}

	public Value convertFromInt(nl.cwi.swat.liveql.eval.Int n) {
		throw new AssertionError("subclasses should override this method");
	}

	public Value convertFromBool(nl.cwi.swat.liveql.eval.Bool bool) {
		throw new AssertionError("subclasses should override this method");
	}
}
