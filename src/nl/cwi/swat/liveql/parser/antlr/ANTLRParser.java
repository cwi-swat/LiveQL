package nl.cwi.swat.liveql.parser.antlr;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.parser.test.IParse;
import nl.cwi.swat.liveql.parser.test.ParseError;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class ANTLRParser implements IParse {

	@Override
	public Expr parse(String src) throws ParseError {
		ANTLRStringStream stream = new ANTLRStringStream(src);
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(new QLLexer(stream));
		QLParser parser = new QLParser(tokens);
		try {
			return parser.orExpr();
		} catch (RecognitionException e) {
			throw new ParseError(e.getMessage());
		}
	}

}
