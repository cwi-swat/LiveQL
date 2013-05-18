package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;

public class ToComputed extends AnswerableEdit {

	private final Expr expr;

	public ToComputed(Expr expr) {
		this.expr = expr;
	}
	
	public Expr getExpr() {
		return expr;
	}

}
