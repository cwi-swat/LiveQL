package nl.cwi.swat.liveql.ast.stat;

import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.diff.edits.ToAnswerable;
import nl.cwi.swat.liveql.patch.QuestionPatch;
import nl.cwi.swat.liveql.patch.StatPatch;

public class Answerable extends Question {
	public Answerable(Label label, Ident name, Type type) {
		super(label, name, type);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "answerable(" + getLabel() + ", " + getName() + ", " + getType() + ")";
	}

	@Override
	public StatPatch diff(Stat other) {
		return other.diffToAnswerable(this, new QuestionPatch());
	}
	
	@Override
	public StatPatch diffToAnswerable(Answerable me, QuestionPatch patch) {
		diffLabel(me, patch);
		diffType(me, patch);
		return patch;
	}

	@Override
	public StatPatch diffToComputed(Computed me, QuestionPatch patch) {
		diffLabel(me, patch);
		diffType(me, patch);
		patch.addEdit(new ToAnswerable(me.getExpr()));
		return patch;
	}

}
