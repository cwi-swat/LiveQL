package nl.cwi.swat.liveql.patch;

import nl.cwi.swat.liveql.ast.stat.Question;

public class QuestionPatch extends StatPatch {
	private final Question source;

	public QuestionPatch(Question source) {
		this.source = source;
	}
	
	public Question getSource() {
		return source;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
