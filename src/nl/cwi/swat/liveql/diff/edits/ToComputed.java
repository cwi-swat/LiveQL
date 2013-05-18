package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;

public class ToComputed extends QLEdit {

	private final Expr expr;

	public ToComputed(Expr expr) {
		this.expr = expr;
	}
	
	public Expr getExpr() {
		return expr;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
