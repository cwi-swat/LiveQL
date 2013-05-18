package nl.cwi.swat.liveql.patch;

public interface Visitor {
	void visit(FormPatch patch);
	void visit(BlockPatch patch);
	void visit(IfThenPatch patch);
	void visit(IfThenElsePatch patch);
	void visit(QuestionPatch patch);
}
