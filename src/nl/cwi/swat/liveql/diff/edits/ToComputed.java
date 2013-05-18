package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class ToComputed implements Edit<Stat> {

	private final Expr expr;

	public ToComputed(Expr expr) {
		this.expr = expr;
	}
	
	public Expr getExpr() {
		return expr;
	}

}
