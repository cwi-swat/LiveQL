package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.patch.BlockPatch;

public class NoChange extends BlockEdit  {
	private final Stat old;

	public NoChange(int position, Stat old, Stat nw) {
		super(position, nw);
		this.old = old;
	}
	
	public Stat getOld() {
		return old;
	}
	
	public Stat getNew() {
		return getStatement();
	}
	
	@Override
	public String toString() {
		return "=(" + getPosition() + ", " + getOld() + ", " + getNew() + ")";
	}
	
	@Override
	public void register(BlockPatch patch) {
		patch.addKid(getOld().diff(getNew()));
	}

}
