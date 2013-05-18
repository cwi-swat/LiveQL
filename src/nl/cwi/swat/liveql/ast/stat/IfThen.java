package nl.cwi.swat.liveql.ast.stat;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.diff.edits.RemoveElse;
import nl.cwi.swat.liveql.patch.ConditionalPatch;
import nl.cwi.swat.liveql.patch.StatPatch;

public class IfThen extends Conditional {
	public IfThen(Expr cond, Stat body) {
		super(cond, body);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "ifThen(" + getCond() + ", " + getBody() + ")";
	}
	
	@Override
	public StatPatch diff(Stat other) {
		return other.diffToIfThen(this, new ConditionalPatch());
	}
	
	@Override
	public StatPatch diffToIfThen(IfThen me, ConditionalPatch patch) {
		diffCond(me, patch);
		patch.setThenPatch(me.getBody().diff(getBody()));
		return patch;
	}

	@Override
	public StatPatch diffToIfThenElse(IfThenElse me, ConditionalPatch patch) {
		diffCond(me, patch);
		patch.addEdit(new RemoveElse(me));
		patch.setThenPatch(me.getBody().diff(getBody()));
		return patch;
	}


}
