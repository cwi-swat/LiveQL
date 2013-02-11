package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Conditional;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public abstract class ConditionalEdit implements Edit<Stat> {

	private final Conditional statement;

	public ConditionalEdit(Conditional statement) {
		this.statement = statement;
	}
	
	public Conditional getConditional() {
		return statement;
	}
}
