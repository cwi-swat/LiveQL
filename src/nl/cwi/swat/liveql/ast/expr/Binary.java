package nl.cwi.swat.liveql.ast.expr;

public abstract class Binary extends Expr {
	private final Expr lhs, rhs;
	
	protected Binary(Expr lhs, Expr rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public int getLine() {
		return lhs.getLine();
	}
	
	public Expr getLhs() {
		return lhs;
	}
	
	public Expr getRhs() {
		return rhs;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		Binary bin = (Binary)obj;
		return getLhs().equals(bin.getLhs()) && getRhs().equals(bin.getRhs());
	}
}
