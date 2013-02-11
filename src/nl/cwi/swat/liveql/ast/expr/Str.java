package nl.cwi.swat.liveql.ast.expr;

import java.util.Map;

import nl.cwi.swat.liveql.ast.types.Type;

public class Str extends Literal {
	private final String value;

	public Str(String value, int line) {
		super(line);
		this.value = value.substring(1, value.length() - 1);
	}
	
	public String getValue() {
		return value;
	}
	

	@Override
	public Type typeOf(Map<Ident, Type> typeEnv) {
		return new nl.cwi.swat.liveql.ast.types.Str();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
