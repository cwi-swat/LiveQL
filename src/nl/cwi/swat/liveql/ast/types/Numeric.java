package nl.cwi.swat.liveql.ast.types;

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
}
