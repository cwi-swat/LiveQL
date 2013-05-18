package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.IfThen;
import nl.cwi.swat.liveql.ast.stat.IfThenElse;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class AddElse implements Edit<Stat> {

	private final Stat elseBody;

	public AddElse(Stat elseBody) {
		this.elseBody = elseBody;
	}
	
	public Stat getElseBody() {
		return elseBody;
	}
	
	@Override
	public String toString() {
		return "+else";
	}
	
	public IfThenElse apply(IfThen it) {
		return new IfThenElse(it.getCond(), it.getBody(), elseBody);
	}
	
	public IfThen unapply(IfThenElse ite) {
		return new IfThen(ite.getCond(), ite.getElseBody());
	}
	
	

}
