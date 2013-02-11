package nl.cwi.swat.liveql.check;

import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.ast.types.Visitor;

public class Error extends Type {
	@Override
	public boolean isError() {
		return true;
	}
	
	@Override
	public String toString() {
		return "<error>";
	}

	@Override
	public boolean isCompatibleTo(Type t) {
		return false;
	}

	@Override
	public <T, U> T accept(Visitor<T, U> visitor, U arg) {
		return visitor.visit(this, arg);
	}
}
