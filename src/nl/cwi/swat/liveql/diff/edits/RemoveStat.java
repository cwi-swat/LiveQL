package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;

public class RemoveStat extends BlockEdit {

	public RemoveStat(int position, Stat removed) {
		super(position, removed);
	}
	
	public Stat getRemoved() {
		return getStatement();
	}

	@Override
	public String toString() {
		return "-(" + getPosition() + ", " + getRemoved() + ")";
	}
}
