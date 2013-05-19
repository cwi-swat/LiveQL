package nl.cwi.swat.liveql.eval;

import nl.cwi.swat.liveql.ast.expr.Ident;

public interface Env {

	boolean containsKey(Ident var);

	Value get(Ident var);

}
