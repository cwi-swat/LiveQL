package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.IfThen;
import nl.cwi.swat.liveql.ast.stat.IfThenElse;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class RemoveElse implements Edit<Stat> {

	private final Stat old;

	public RemoveElse(Stat old) {
		this.old = old;
	}
	
	@Override
	public String toString() {
		return "-else()";
	}
	
	public IfThen apply(IfThenElse ite) {
		return new IfThen(ite.getCond(), ite.getBody());
	}
	
	public IfThenElse unapply(IfThen it) {
		return new IfThenElse(it.getCond(), it.getBody(), old);
	}

}
