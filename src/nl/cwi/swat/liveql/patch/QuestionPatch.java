package nl.cwi.swat.liveql.patch;


public class QuestionPatch extends StatPatch {
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
