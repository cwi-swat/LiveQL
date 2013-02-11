package nl.cwi.swat.liveql.ast.types;

public class Int extends Numeric {
	public Int() {
		super();
	}
	
	public Int(int line) {
		super(line);
	}
	
	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToInt();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Int;
	}
	
	@Override
	public String toString() {
		return "integer";
	}
	
	public <T, U> T accept(Visitor<T,U> visitor, U arg) {
		return visitor.visit(this, arg);
	}
}
