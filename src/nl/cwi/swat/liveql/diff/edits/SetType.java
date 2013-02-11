package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Question;
import nl.cwi.swat.liveql.ast.types.Type;

public class SetType extends QuestionEdit {

	private final Type type;

	public SetType(Question question, Type type) {
		super(question);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "type(" + getQuestion().getType() + " -> " + getType() + ")";
	}

}
