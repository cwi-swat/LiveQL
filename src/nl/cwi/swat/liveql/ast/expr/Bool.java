package nl.cwi.swat.liveql.ast.expr;

import java.util.Map;

import nl.cwi.swat.liveql.ast.types.Type;

public class Bool extends Literal {
	private final Boolean value;
	
	public Bool(Boolean value, int line) {
		super(line);
		this.value = value;
	}
	
	
	@Override
	public Type typeOf(Map<Ident, Type> typeEnv) {
		return new nl.cwi.swat.liveql.ast.types.Bool();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	public Boolean getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Bool)) {
			return false;
		}
		return getValue().equals(((Bool)obj).getValue());
	}

}
