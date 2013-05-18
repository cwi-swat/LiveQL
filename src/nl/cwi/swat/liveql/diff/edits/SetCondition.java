package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;


public class SetCondition implements Edit<Stat> {
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
	
	
	
}
