package nl.cwi.swat.liveql.diff.edits;

import java.util.List;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class Block implements Edit<Stat> {
	
	private final nl.cwi.swat.liveql.ast.stat.Block block;
	private final List<Edit<Stat>> edits;

	public Block(nl.cwi.swat.liveql.ast.stat.Block block, List<Edit<Stat>> edits) {
		this.block = block;
		this.edits = edits;
	}
	
	public nl.cwi.swat.liveql.ast.stat.Block getBlock() {
		return block;
	}
	
	public List<Edit<Stat>> getEdits() {
		return edits;
	}
	
	@Override
	public String toString() {
		return edits.toString();
	}

}
