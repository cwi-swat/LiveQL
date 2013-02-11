package nl.cwi.swat.liveql.ast.types;

public interface Visitor<T, U> {
	T visit(Bool type, U arg);
	T visit(Int type, U arg);
	T visit(Money type, U arg);
	T visit(Str type, U arg);
	T visit(Numeric type, U arg);
	T visit(nl.cwi.swat.liveql.check.Error type, U arg);
}
