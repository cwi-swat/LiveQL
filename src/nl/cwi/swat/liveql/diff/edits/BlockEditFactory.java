package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.EditFactory;

public class BlockEditFactory implements EditFactory<Stat> {

	@Override
	public InsertStat insert(int i, Stat x) {
		return new InsertStat(i, x);
	}

	@Override
	public RemoveStat remove(int i, Stat removed) {
		return new RemoveStat(i, removed);
	}

	@Override
	public NoChange noChange(int i, Stat old, Stat nw) {
		return new NoChange(i, old, nw);
	}

}
