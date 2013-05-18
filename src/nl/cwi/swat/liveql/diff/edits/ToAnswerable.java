package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;

public class ToAnswerable extends ComputedEdit {
	
	private final Expr old;

	public ToAnswerable(Expr old) {
		this.old = old;
	}

}
