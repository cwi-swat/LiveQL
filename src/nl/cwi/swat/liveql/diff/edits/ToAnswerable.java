package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class ToAnswerable implements Edit<Stat> {
	
	private final Expr old;

	public ToAnswerable(Expr old) {
		this.old = old;
	}

}
