package nl.cwi.swat.liveql.ast.expr;

public abstract class Unary extends Expr {
	private final Expr arg;
	
	@Override
	public int getLine() {
		return arg.getLine();
	}
	
	protected Unary(Expr arg) {
		this.arg = arg;
	}
	
	public Expr getArg() {
		return arg;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		Unary un = (Unary)obj;
		return getArg().equals(un.getArg());
	}
}
