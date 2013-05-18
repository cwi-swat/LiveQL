package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.expr.Expr;

public class ToAnswerable extends QLEdit {
	
	private final Expr old;

	public ToAnswerable(Expr old) {
		this.old = old;
	}
	
	public Expr getOld() {
		return old;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
