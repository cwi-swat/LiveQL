package nl.cwi.swat.liveql.ast.types;

public class Str extends Type {
	public Str() {
		super();
	}
	
	public Str(int line) {
		super(line);
	}
	
	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToStr();
	}
	
	@Override
	public boolean isCompatibleToStr() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Str;
	}

	@Override
	public String toString() {
		return "string";
	}

	@Override
	public <T, U> T accept(Visitor<T, U> visitor, U arg) {
		return visitor.visit(this, arg);
	}
}
