package nl.cwi.swat.liveql.ast.expr;

import java.util.Map;

import nl.cwi.swat.liveql.ast.types.Int;
import nl.cwi.swat.liveql.ast.types.Type;

public class Div extends Binary {

	public Div(Expr lhs, Expr rhs) {
		super(lhs, rhs);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Type typeOf(Map<Ident, Type> typeEnv) {
		return new Int();
	}

}
