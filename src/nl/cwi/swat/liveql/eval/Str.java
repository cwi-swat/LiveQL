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

}
