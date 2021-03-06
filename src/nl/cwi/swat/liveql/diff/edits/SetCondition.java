package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;


public class SetCondition extends QLEdit {
	private final Expr condition;
	private final Expr old;

	public SetCondition(Expr condition, Expr old) {
		this.condition = condition;
		this.old = old;
	}

	public Expr getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return "cond(" + old + " -> " + getCondition() + ")";
	}
	

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	
	
}
