package nl.cwi.swat.liveql.ast.expr;

import java.util.Map;

import nl.cwi.swat.liveql.ast.types.Type;

public class Int extends Literal {

	private final int value;

	public Int(int n, int line) {
		super(line);
		this.value = n;
	}

	public int getValue() {
		return value;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Type typeOf(Map<Ident, Type> typeEnv) {
		return new nl.cwi.swat.liveql.ast.types.Int();
	}

}
