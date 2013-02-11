package nl.cwi.swat.liveql.parser.antlr;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import nl.cwi.swat.liveql.ast.form.Form;
import nl.cwi.swat.liveql.parser.test.ParseError;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class FormParser {

	public static void main(String[] args) throws ParseError {
		Form f = parse("form bla { \"\" x: int }");
		System.out.println(f);
	}
	
	public static Form parse(String src) throws ParseError {
		try {
			return parse(new StringReader(src));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Form parse(Reader reader) throws IOException, ParseError {
		ANTLRReaderStream stream = new ANTLRReaderStream(reader);
		CommonTokenStream tokens = new CommonTokenStream();
		tokens.setTokenSource(new QLLexer(stream));
		QLParser parser = new QLParser(tokens);
		try {
			return parser.form();
		} catch (RecognitionException e) {
			throw new ParseError(e.getMessage());
		}
	}

}
