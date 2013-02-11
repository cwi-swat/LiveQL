package nl.cwi.swat.liveql.ast.types;

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
}