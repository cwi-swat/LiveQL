package nl.cwi.swat.liveql.ast.types;

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

}
