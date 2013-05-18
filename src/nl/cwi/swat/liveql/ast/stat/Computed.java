package nl.cwi.swat.liveql.ast.stat;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.diff.edits.SetExpression;
import nl.cwi.swat.liveql.diff.edits.ToComputed;
import nl.cwi.swat.liveql.patch.QuestionPatch;
import nl.cwi.swat.liveql.patch.StatPatch;

public class Computed extends Question {
	private final Expr expr;

	public Computed(Label label, Ident name, Type type, Expr expr) {
		super(label, name, type);
		this.expr = expr;
	}
	
	public Expr getExpr() {
		return expr;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "computed(" + getLabel() + ", " + getName() + ", " + getType() + ")";
	}

	
	@Override
	public StatPatch diff(Stat other) {
		return other.diffToComputed(this, new QuestionPatch());
	}
	
	@Override
	public StatPatch diffToComputed(Computed me, QuestionPatch patch) {
		diffLabel(me, patch);
		diffType(me, patch);
		if (!getExpr().equals(me.getExpr())) {
			patch.addEdit(new SetExpression(getExpr(), me.getExpr()));
		}
		return patch;
	}

	@Override
	public StatPatch diffToAnswerable(Answerable me, QuestionPatch patch) {
		diffLabel(me, patch);
		diffType(me, patch);
		patch.addEdit(new ToComputed(getExpr()));
		return patch;
	}
	
}
