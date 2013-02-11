package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Question;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public abstract class QuestionEdit implements Edit<Stat> {
	private final Question question;

	public QuestionEdit(Question question) {
		this.question = question;
	}
	
	public Question getQuestion() {
		return question;
	}
}
