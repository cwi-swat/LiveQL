package nl.cwi.swat.liveql.ast.types;

import nl.cwi.swat.liveql.eval.Value;

public class Int extends Numeric {
	public Int() {
		super();
	}
	
	public Int(int line) {
		super(line);
	}
	
	@Override
	public boolean isCompatibleTo(Type t) {
		return t.isCompatibleToInt();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Int;
	}
	
	@Override
	public String toString() {
		return "integer";
	}
	
	public <T, U> T accept(Visitor<T,U> visitor, U arg) {
		return visitor.visit(this, arg);
	}
	
	@Override
	public Value convertFromStr(nl.cwi.swat.liveql.eval.Str str) {
		try {
			return new nl.cwi.swat.liveql.eval.Int(Integer.parseInt(str.getValue()));
		}
		catch (NumberFormatException e) {
			return new nl.cwi.swat.liveql.eval.Int(0);
		}
	}

	@Override
	public Value convertFromInt(nl.cwi.swat.liveql.eval.Int n) {
		return n;
	}

	@Override
	public Value convertFromBool(nl.cwi.swat.liveql.eval.Bool bool) {
		return new nl.cwi.swat.liveql.eval.Int(bool.getValue() ? 1 : 0);
	}
}
