package nl.cwi.swat.liveql.diff.edits;

public interface Visitor {
	void visit(AddElse e);
	void visit(InsertStat e);
	void visit(NoChange e);
	void visit(RemoveElse e);
	void visit(RemoveStat e);
	void visit(SetCondition e);
	void visit(SetExpression e);
	void visit(SetLabel e);
	void visit(SetType e);
	void visit(ToAnswerable e);
	void visit(ToComputed e);
}
