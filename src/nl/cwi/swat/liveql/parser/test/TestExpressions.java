package nl.cwi.swat.liveql.parser.test;

import static org.junit.Assert.assertEquals;
import nl.cwi.swat.liveql.ast.expr.Add;
import nl.cwi.swat.liveql.ast.expr.And;
import nl.cwi.swat.liveql.ast.expr.GT;
import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.expr.Int;
import nl.cwi.swat.liveql.ast.expr.LEq;
import nl.cwi.swat.liveql.ast.expr.LT;
import nl.cwi.swat.liveql.ast.expr.Mul;
import nl.cwi.swat.liveql.ast.expr.Not;
import nl.cwi.swat.liveql.parser.antlr.ANTLRParser;

import org.junit.Test;

public class TestExpressions {

	private final IParse parser;

	public TestExpressions() {
		this.parser = new ANTLRParser();
	}

	
	@Test
	public void testAdds() throws ParseError {
		assertEquals(Add.class, parser.parse("a + b").getClass());
		assertEquals(Add.class, parser.parse("a + b + c").getClass());
		assertEquals(Add.class, parser.parse("(a + b + c)").getClass());
		assertEquals(Add.class, parser.parse("a + (b + c)").getClass());
		assertEquals(Add.class, parser.parse("(a + b) + c").getClass());
		assertEquals(Add.class, parser.parse("(a + b)").getClass());
		assertEquals(Add.class, parser.parse("a + b * c").getClass());
		assertEquals(Add.class, parser.parse("a * b + c").getClass());
	}

	@Test
	public void testMuls() throws ParseError {
		assertEquals(Mul.class, parser.parse("a * b").getClass());
		assertEquals(Mul.class, parser.parse("a * b * c").getClass());
		assertEquals(Mul.class, parser.parse("a * (b * c)").getClass());
		assertEquals(Mul.class, parser.parse("(a * b) * c").getClass());
		assertEquals(Mul.class, parser.parse("(a * b)").getClass());
		assertEquals(Mul.class, parser.parse("(a + b) * c").getClass());
		assertEquals(Mul.class, parser.parse("a * (b + c)").getClass());
	}
	
	@Test
	public void testRels() throws ParseError {
		assertEquals(LT.class, parser.parse("a < b").getClass());
		assertEquals(LT.class, parser.parse("a < b + c").getClass());
		assertEquals(LT.class, parser.parse("a < (b * c)").getClass());
		assertEquals(LT.class, parser.parse("(a * b) < c").getClass());
		assertEquals(LEq.class, parser.parse("(a <= b)").getClass());
		assertEquals(GT.class, parser.parse("a + b > c").getClass());
		assertEquals(GT.class, parser.parse("a > b + c").getClass());
	}
	
	@Test
	public void testBools() throws ParseError {
		assertEquals(Not.class, parser.parse("!b").getClass());
		assertEquals(And.class, parser.parse("a && b").getClass());
		assertEquals(And.class, parser.parse("a > b && b > c").getClass());
		assertEquals(And.class, parser.parse("(a > b) && (b > c)").getClass());
	}


	@Test
	public void testIds() throws ParseError {
		assertEquals(Ident.class, parser.parse("a").getClass());
		assertEquals(Ident.class, parser.parse("abc").getClass());
		assertEquals(Ident.class, parser.parse("ABC").getClass());
		assertEquals(Ident.class, parser.parse("ABCDEF").getClass());
		assertEquals(Ident.class, parser.parse("abc2323").getClass());
		assertEquals(Ident.class, parser.parse("a2bc232").getClass());
		assertEquals(Ident.class, parser.parse("a2bc232aa").getClass());
	}

	@Test
	public void testNums() throws ParseError {
		assertEquals(Int.class, parser.parse("0").getClass());
		assertEquals(Int.class, parser.parse("1223").getClass());
		assertEquals(Int.class, parser.parse("234234234").getClass());
	}
	
}
