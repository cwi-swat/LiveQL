package nl.cwi.swat.liveql.ast.stat;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.diff.edits.SetCondition;
import nl.cwi.swat.liveql.patch.ConditionalPatch;

public abstract class Conditional extends Stat {
	private final Expr cond;
	private final Stat body;

	public Conditional(Expr cond, Stat body) {
		this.cond = cond;
		this.body = body;
	}
	
	public Expr getCond() {
		return cond;
	}
	
	public Stat getBody() {
		return body;
	}

	@Override
	public boolean match(Stat other) {
		return other.matchConditional(this);
	}
	
	@Override
	public boolean matchConditional(Conditional other) {
		return true;
	}

	protected void diffCond(Conditional me, ConditionalPatch patch) {
		if (!getCond().equals(me.getCond())) {
			patch.addEdit(new SetCondition(me, getCond()));
		}
	}
	
	@Override
	public int getLine() {
		return getCond().getLine();
	}
	

}
