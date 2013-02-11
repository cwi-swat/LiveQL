package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Label;
import nl.cwi.swat.liveql.ast.stat.Question;

public class SetLabel extends QuestionEdit {

	private final Label label;

	public SetLabel(Question question, Label label) {
		super(question);
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		return "label(" + getQuestion().getLabel() + " -> " + getLabel() + ")";
	}
}
