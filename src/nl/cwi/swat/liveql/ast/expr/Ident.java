package nl.cwi.swat.liveql.ast.expr;

import java.util.Map;

import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.check.Error;

public class Ident extends Expr {

	private final String name;
	private int line;

	public Ident(String name, int line) {
		this.name = name;
		this.line = line;
	}
	
	@Override
	public int getLine() {
		return line;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public Type typeOf(Map<Ident, Type> typeEnv) {
		if (typeEnv.containsKey(this)) {
			return typeEnv.get(this);
		}
		return new Error();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ident)) {
			return false;
		}
		return getName().equals(((Ident)obj).getName());
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
