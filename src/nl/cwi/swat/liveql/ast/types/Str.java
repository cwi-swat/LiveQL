package nl.cwi.swat.liveql.ast.types;

import nl.cwi.swat.liveql.eval.Bool;
import nl.cwi.swat.liveql.eval.Int;
import nl.cwi.swat.liveql.eval.Value;

public class Str extends Type {
	public Str() {
		super();
	}
	
	public Str(int line) {
		super(line);
	}
	
	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToStr();
	}
	
	@Override
	public boolean isCompatibleToStr() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Str;
	}

	@Override
	public String toString() {
		return "string";
	}

	@Override
	public <T, U> T accept(Visitor<T, U> visitor, U arg) {
		return visitor.visit(this, arg);
	}

	@Override
	public Value convertFromStr(nl.cwi.swat.liveql.eval.Str str) {
		return str;
	}

	@Override
	public Value convertFromInt(Int n) {
		return new nl.cwi.swat.liveql.eval.Str(n.getValue().toString());
	}

	@Override
	public Value convertFromBool(Bool bool) {
		return new nl.cwi.swat.liveql.eval.Str(bool.getValue().toString());
	}
}
