package nl.cwi.swat.liveql.ast.expr;

import java.io.IOException;
import java.io.Writer;

public class Print implements Visitor<Void> {

	private final Writer writer;

	public Print(Writer writer) {
		this.writer = writer;
	}
	
	private void print(Object str) {
		try {
			writer.write(str.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void printBinary(Expr lhs, String op, Expr rhs) {
		lhs.accept(this);
		print(" " + op + " ");
		rhs.accept(this);
	}
	
	@Override
	public Void visit(Add ast) {
		printBinary(ast.getLhs(), "+", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(And ast) {
		printBinary(ast.getLhs(), "&&", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Div ast) {
		printBinary(ast.getLhs(), "/", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Eq ast) {
		printBinary(ast.getLhs(), "==", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(GEq ast) {
		printBinary(ast.getLhs(), ">=", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(GT ast) {
		printBinary(ast.getLhs(), ">", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Ident ast) {
		print(ast.getName());
		return null;
	}

	@Override
	public Void visit(Int ast) {
		print(ast.getValue());
		return null;
	}

	@Override
	public Void visit(LEq ast) {
		printBinary(ast.getLhs(), "<=", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(LT ast) {
		printBinary(ast.getLhs(), "<", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Mul ast) {
		printBinary(ast.getLhs(), "*", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Neg ast) {
		print("-");
		ast.getArg().accept(this);
		return null;
	}

	@Override
	public Void visit(NEq ast) {
		printBinary(ast.getLhs(), "!=", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Not ast) {
		printUnary("!", ast.getArg());
		return null;
	}

	private void printUnary(String op, Expr arg) {
		print(op);
		arg.accept(this);
	}

	@Override
	public Void visit(Or ast) {
		printBinary(ast.getLhs(), "||", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Pos ast) {
		printUnary("+", ast.getArg());
		return null;
	}

	@Override
	public Void visit(Sub ast) {
		printBinary(ast.getLhs(), "-", ast.getRhs());
		return null;
	}

	@Override
	public Void visit(Bool bool) {
		print(bool.getValue());
		return null;
	}

	@Override
	public Void visit(Str str) {
		print(str.getValue());
		return null;
	}

}
