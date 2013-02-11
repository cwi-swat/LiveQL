package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Conditional;


public class SetCondition extends ConditionalEdit {

	private final Expr condition;

	public SetCondition(Conditional statement, Expr condition) {
		super(statement);
		this.condition = condition;
	}

	public Expr getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return "cond(" + getConditional().getCond() + " -> " + getCondition() + ")";
	}
	
}
