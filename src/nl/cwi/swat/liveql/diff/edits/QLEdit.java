package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public abstract class QLEdit implements Edit<Stat> {
	public abstract void accept(Visitor visitor);

}
