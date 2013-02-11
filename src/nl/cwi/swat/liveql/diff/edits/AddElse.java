package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.IfThen;
import nl.cwi.swat.liveql.ast.stat.Stat;

public class AddElse extends IfThenEdit {

	private final Stat elseBody;

	public AddElse(IfThen statement, Stat elseBody) {
		super(statement);
		this.elseBody = elseBody;
	}
	
	public Stat getElseBody() {
		return elseBody;
	}
	
	@Override
	public String toString() {
		return "+else";
	}

}
