package nl.cwi.swat.liveql.patch;

import nl.cwi.swat.liveql.ast.stat.Conditional;

public class ConditionalPatch extends StatPatch {
	// NB: cannot make IfThen/IfThenElse subclasses because of invariant collections.
	
	private final Conditional source;
	private StatPatch thenPatch;
	private StatPatch elsePatch;

	public ConditionalPatch(Conditional source) {
		super();
		this.source = source;
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
	
	public Conditional getSource() {
		return source;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
