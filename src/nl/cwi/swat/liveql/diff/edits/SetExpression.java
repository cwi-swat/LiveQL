package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Computed;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class SetExpression implements Edit<Stat> {

	private final Expr expr;
	private final Expr old;

	public SetExpression(Expr expr, Expr old) {
		this.expr = expr;
		this.old = old;
	}
	
	public Expr getExpr() {
		return expr;
	}
	
	@Override
	public String toString() {
		return "expr(" + old + " -> " + getExpr() + ")";
	}
	
	public Computed apply(Computed c) {
		return new Computed(c.getLabel(), c.getName(), c.getType(), expr);
	}
	
	public Computed unapply(Computed c) {
		return new Computed(c.getLabel(), c.getName(), c.getType(), old);
	}

}
