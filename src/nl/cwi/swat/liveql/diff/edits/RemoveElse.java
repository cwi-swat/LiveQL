package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Conditional;

public class RemoveElse extends IfThenElseEdit {

	public RemoveElse(Conditional statement) {
		super(statement);
	}
	
	@Override
	public String toString() {
		return "-else()";
	}

}
