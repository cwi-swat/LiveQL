package nl.cwi.swat.liveql.ast.stat;

import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.diff.edits.SetLabel;
import nl.cwi.swat.liveql.diff.edits.SetType;
import nl.cwi.swat.liveql.patch.StatPatch;

public abstract class Question extends Stat {
	private final Label label;
	private final Ident name;
	private final Type type;

	public Question(Label label, Ident name, Type type) {
		this.label = label;
		this.name = name;
		this.type = type;
	}
	
	public Label getLabel() {
		return label;
	}
	
	public Ident getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public boolean match(Stat other) {
		return other.matchQuestion(this);
	}
	
	@Override
	public boolean matchQuestion(Question other) {
		return getName().equals(other.getName());
	}

	protected void diffType(Question me, StatPatch patch) {
		if (!getType().equals(me.getType())) {
			patch.addEdit(new SetType(me, getType()));
		}
	}

	protected void diffLabel(Question me, StatPatch patch) {
		if (!getLabel().equals(me.getLabel())) {
			patch.addEdit(new SetLabel(me, getLabel()));
		}
	}
	
	@Override
	public int getLine() {
		return getName().getLine();
	}
	
}
