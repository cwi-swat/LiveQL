package nl.cwi.swat.liveql.ast.stat;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.diff.edits.AddElse;
import nl.cwi.swat.liveql.patch.ConditionalPatch;
import nl.cwi.swat.liveql.patch.StatPatch;

public class IfThenElse extends Conditional {
	private final Stat elseBody;

	public IfThenElse(Expr cond, Stat body, Stat elseBody) {
		super(cond, body);
		this.elseBody = elseBody;
	}
	
	public Stat getElseBody() {
		return elseBody;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "ifThenElse(" + getCond() + ", " + getBody() + ", " + getElseBody() + ")";
	}
	
	@Override
	public StatPatch diff(Stat other) {
		return other.diffToIfThenElse(this, new ConditionalPatch());
	}
	
	@Override
	public StatPatch diffToIfThen(IfThen me, ConditionalPatch patch) {
		diffCond(me, patch);
		patch.addEdit(new AddElse(getElseBody()));
		patch.setThenPatch(me.getBody().diff(getBody()));
		return patch;
	}

	@Override
	public StatPatch diffToIfThenElse(IfThenElse me, ConditionalPatch patch) {
		diffCond(me, patch);
		patch.setThenPatch(me.getBody().diff(getBody()));
		patch.setElsePatch(me.getElseBody().diff(getElseBody()));
		return patch;
	}


}
