package nl.cwi.swat.liveql.patch;


public class ConditionalPatch extends StatPatch {
	// NB: cannot make IfThen/IfThenElse subclasses because of invariant collections.
	
	private StatPatch thenPatch;
	private StatPatch elsePatch;

	public ConditionalPatch() {
		super();
		this.thenPatch = null;
		this.elsePatch = new BlockPatch();
	}
	
	public StatPatch getElsePatch() {
		return elsePatch;
	}
	
	public StatPatch getThenPatch() {
		return thenPatch;
	}
	
	public void setElsePatch(StatPatch elsePatch) {
		this.elsePatch = elsePatch;
	}
	
	public void setThenPatch(StatPatch thenPatch) {
		this.thenPatch = thenPatch;
	}
	
	@Override
	public boolean isIdentity() {
		if (!super.isIdentity()) {
			return false;
		}
		return getThenPatch().isIdentity() && getElsePatch().isIdentity();
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
