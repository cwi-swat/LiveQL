package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;
import nl.cwi.swat.liveql.patch.BlockPatch;

public abstract class  BlockEdit implements Edit<Stat> {

	private final int position;
	private final Stat statement;

	
	public BlockEdit(int position, Stat statement) {
		this.position = position;
		this.statement = statement;
	}
	
	public int getPosition() {
		return position;
	}
	
	public Stat getStatement() {
		return statement;
	}
	
	public void register(BlockPatch patch) {
		patch.addEdit(this);
	}

}