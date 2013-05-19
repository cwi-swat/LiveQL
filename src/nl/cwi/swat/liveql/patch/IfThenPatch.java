package nl.cwi.swat.liveql.patch;

import nl.cwi.swat.liveql.ast.expr.Expr;

public class IfThenPatch extends ConditionalPatch {

	public IfThenPatch(Expr cond) {
		super(cond);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
