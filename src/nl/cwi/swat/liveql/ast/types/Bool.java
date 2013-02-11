package nl.cwi.swat.liveql.ast.types;

public class Bool extends Type {
	public Bool(int line) {
		super(line);
	}
	
	public Bool() {
		super();
	}

	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToBool();
	}
	
	@Override
	public boolean isCompatibleToBool() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Bool;
	}
	
	@Override
	public String toString() {
		return "boolean";
	}

	@Override
	public <T, U> T accept(Visitor<T, U> visitor, U arg) {
		return visitor.visit(this, arg);
	}
}
