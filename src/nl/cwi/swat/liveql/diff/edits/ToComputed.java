package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.stat.Question;

public class ToComputed extends AnswerableEdit {

	private final Expr expr;

	public ToComputed(Question question, Expr expr) {
		super(question);
		this.expr = expr;
	}
	
	public Expr getExpr() {
		return expr;
	}

}
