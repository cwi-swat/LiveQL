package nl.cwi.swat.liveql.ast.stat;

public interface Visitor {
	void visit(Computed stat);
	void visit(Answerable stat);
	void visit(IfThen stat);
	void visit(IfThenElse stat);
	void visit(Block stat);
}
