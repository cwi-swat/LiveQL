package nl.cwi.swat.liveql.parser.test;

import nl.cwi.swat.liveql.ast.expr.Expr;

public interface IParse {
	Expr parse(String src) throws ParseError;
}
