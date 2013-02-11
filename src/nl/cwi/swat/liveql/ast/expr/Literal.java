package nl.cwi.swat.liveql.ast.expr;


public abstract class Literal extends Expr {

	private final int line;

	public Literal(int line) {
		this.line = line;
	}
	
	@Override
	public int getLine() {
		return line;
	}

}
