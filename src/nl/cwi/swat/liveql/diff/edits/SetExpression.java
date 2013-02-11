package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Computed;

public class SetExpression extends ComputedEdit {

	private final Expr expr;

	public SetExpression(Computed question, Expr expr) {
		super(question);
		this.expr = expr;
	}
	
	public Expr getExpr() {
		return expr;
	}
	
	@Override
	public String toString() {
		return "expr(" + ((Computed)getQuestion()).getExpr() + " -> " + getExpr() + ")";
	}
}
