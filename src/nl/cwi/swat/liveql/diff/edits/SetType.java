package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.diff.Edit;

public class SetType implements Edit<Stat> {

	private final Type type;
	private final Type old;

	public SetType(Type type, Type old) {
		this.type = type;
		this.old = old;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "type(" + old + " -> " + getType() + ")";
	}

}
