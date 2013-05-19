package nl.cwi.swat.liveql.patch;

import nl.cwi.swat.liveql.ast.expr.Expr;

public class IfThenElsePatch extends ConditionalPatch {
	private StatPatch elsePatch;

	public IfThenElsePatch(Expr cond) {
		super(cond);
		this.elsePatch = new BlockPatch(); // !!!
	}
	
	public StatPatch getElsePatch() {
		return elsePatch;
	}
	
	public void setElsePatch(StatPatch elsePatch) {
		this.elsePatch = elsePatch;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	

	@Override
	public boolean isIdentity() {
		if (!super.isIdentity()) {
			return false;
		}
		return elsePatch.isIdentity();
	}

}
