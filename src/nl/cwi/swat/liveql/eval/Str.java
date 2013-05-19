package nl.cwi.swat.liveql.eval;

import nl.cwi.swat.liveql.ast.types.Type;

public class Str extends Value {
	private final String value;
	
	public Str(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public Value convert(Type type) {
		return type.convertFromStr(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Str)) {
			return false;
		}
		return getValue().equals(((Str)obj).getValue());
	}
}
