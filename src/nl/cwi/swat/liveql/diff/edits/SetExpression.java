package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Computed;

public class SetExpression extends QLEdit {

	private final Expr expr;
	private final Expr old;

	public SetExpression(Expr expr, Expr old) {
		this.expr = expr;
		this.old = old;
	}
	
	public Expr getExpr() {
		return expr;
	}
	
	public Expr getOld() {
		return old;
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

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
