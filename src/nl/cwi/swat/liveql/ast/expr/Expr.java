package nl.cwi.swat.liveql.ast.expr;

import java.util.Map;

import nl.cwi.swat.liveql.ast.ASTNode;
import nl.cwi.swat.liveql.ast.types.Type;

public abstract class Expr implements ASTNode {

	public abstract Type typeOf(Map<Ident, Type> typeEnv);
	public abstract <T> T accept(Visitor<T> visitor);
}
