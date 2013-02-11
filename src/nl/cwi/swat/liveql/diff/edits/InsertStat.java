package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;

public class InsertStat extends BlockEdit {

	public InsertStat(int position, Stat statement) {
		super(position, statement);
	}
	
	@Override
	public String toString() {
		return "+(" + getPosition() + ", " + getStatement() + ")";
	}

}
