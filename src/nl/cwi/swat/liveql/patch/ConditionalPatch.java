package nl.cwi.swat.liveql.patch;

import nl.cwi.swat.liveql.ast.expr.Expr;


public abstract class ConditionalPatch extends StatPatch {
	private StatPatch bodyPatch;
	private Expr cond;

	public ConditionalPatch(Expr cond) {
		super();
		this.cond = cond;
		this.bodyPatch = null;
	}
	
	
	public Expr getCond() {
		return cond;
	}
	
	public StatPatch getBodyPatch() {
		return bodyPatch;
	}
	
	
	public void setBodyPatch(StatPatch bodyPatch) {
		this.bodyPatch = bodyPatch;
	}
	
	@Override
	public boolean isIdentity() {
		if (!super.isIdentity()) {
			return false;
		}
		return getBodyPatch().isIdentity();
	}
	
}
