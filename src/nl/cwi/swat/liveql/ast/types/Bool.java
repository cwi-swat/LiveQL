package nl.cwi.swat.liveql.ast.types;

import nl.cwi.swat.liveql.eval.Int;
import nl.cwi.swat.liveql.eval.Str;
import nl.cwi.swat.liveql.eval.Value;

public class Bool extends Type {
	public Bool(int line) {
		super(line);
	}
	
	public Bool() {
		super();
	}

	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToBool();
	}
	
	@Override
	public boolean isCompatibleToBool() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Bool;
	}
	
	@Override
	public String toString() {
		return "boolean";
	}

	@Override
	public <T, U> T accept(Visitor<T, U> visitor, U arg) {
		return visitor.visit(this, arg);
	}

	@Override
	public Value convertFromStr(Str str) {
		return new nl.cwi.swat.liveql.eval.Bool(Boolean.parseBoolean(str.getValue()));
	}

	@Override
	public Value convertFromInt(Int n) {
		return new nl.cwi.swat.liveql.eval.Bool(n.getValue() != 0);
	}

	@Override
	public Value convertFromBool(nl.cwi.swat.liveql.eval.Bool bool) {
		return bool;
	}

	@Override
	public Value defaultValue() {
		return new nl.cwi.swat.liveql.eval.Bool(false);
	}
}
