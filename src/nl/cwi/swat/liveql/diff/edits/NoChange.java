package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.patch.BlockPatch;
import nl.cwi.swat.liveql.patch.StatPatch;

public class NoChange extends BlockEdit  {
	private StatPatch patch;

	public NoChange(int position, Stat old, Stat nw) {
		super(position, nw);
		this.patch = old.diff(nw);
	}
	
	
	public StatPatch getPatch() {
		return patch;
	}
	
	@Override
	public String toString() {
		return "=(" +patch + ")";
	}
	
	@Override
	public void register(BlockPatch patch) {
		patch.addEdit(this);
	}


	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
