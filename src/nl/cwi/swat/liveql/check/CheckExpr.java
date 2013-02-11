package nl.cwi.swat.liveql.check;

import java.util.List;
import java.util.Map;

import nl.cwi.swat.liveql.ast.expr.Add;
import nl.cwi.swat.liveql.ast.expr.And;
import nl.cwi.swat.liveql.ast.expr.Div;
import nl.cwi.swat.liveql.ast.expr.Eq;
import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.expr.GEq;
import nl.cwi.swat.liveql.ast.expr.GT;
import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.expr.Int;
import nl.cwi.swat.liveql.ast.expr.LEq;
import nl.cwi.swat.liveql.ast.expr.LT;
import nl.cwi.swat.liveql.ast.expr.Mul;
import nl.cwi.swat.liveql.ast.expr.NEq;
import nl.cwi.swat.liveql.ast.expr.Neg;
import nl.cwi.swat.liveql.ast.expr.Not;
import nl.cwi.swat.liveql.ast.expr.Or;
import nl.cwi.swat.liveql.ast.expr.Pos;
import nl.cwi.swat.liveql.ast.expr.Str;
import nl.cwi.swat.liveql.ast.expr.Sub;
import nl.cwi.swat.liveql.ast.expr.Visitor;
import nl.cwi.swat.liveql.ast.types.Bool;
import nl.cwi.swat.liveql.ast.types.Numeric;
import nl.cwi.swat.liveql.ast.types.Type;

public class CheckExpr implements Visitor<Boolean> {

	private final Map<Ident, Type> typeEnv;
	private final List<Message> messages;
	
	private CheckExpr(Map<Ident, Type> tenv, List<Message> messages) {
		this.typeEnv = tenv;
		this.messages = messages;
	}

	public static boolean check(Expr expr, Map<Ident, Type> typeEnv, List<Message> errs) {
		CheckExpr check = new CheckExpr(typeEnv, errs);
		return expr.accept(check);
	}

	private static String opErrorMessage(String op, Type type) {
		return op + " requires " + type + " type for arguments";
	}
	
	private void addError(Expr expr, String msg) {
		messages.add(new Message(expr.getLine(), msg));
	}

	private boolean check(Expr expr, Type type, String op, Expr ...args) {
		if (!checkArgs(args)) {
			return false;
		}
		for (Expr x: args) {
			if (!type.isCompatibleTo(x.typeOf(typeEnv))) {
				addError(expr, opErrorMessage(op, type));
				return false;
			}
		}
		return true;
	}
	
	private boolean checkNumeric(Expr expr, String op, Expr ...args) {
		return check(expr, new Numeric(), op, args);
	}
	
	private boolean checkBool(Expr expr, String op, Expr ...args) {
		return check(expr, new Bool(), op, args);
	}

	private boolean checkArgs(Expr... args) {
		boolean argsOk = true;
		for (Expr x: args) {
			argsOk &= x.accept(this);
		}
		return argsOk;
	}
	
	
	private Boolean checkEqual(Expr expr, String op, Expr lhs, Expr rhs) {
		if (!checkArgs(lhs, rhs)) {
			return false;
		}
		Type t1 = lhs.typeOf(typeEnv);
		Type t2 = rhs.typeOf(typeEnv);
		if (!t1.isCompatibleTo(t2)) {
			addError(expr, op + " requires compatible types");
			return false;
		}
		return true;
	}

	@Override
	public Boolean visit(Add ast) {
		return checkNumeric(ast, "+", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(And ast) {
		return checkBool(ast, "&&", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(Div ast) {
		return checkNumeric(ast, "/", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(Eq ast) {
		return checkEqual(ast, "==", ast.getLhs(), ast.getRhs());
	}

	
	@Override
	public Boolean visit(GEq ast) {
		return checkNumeric(ast, ">=", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(GT ast) {
		return checkNumeric(ast, ">", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(Ident ast) {
		if (!typeEnv.containsKey(ast)) {
			addError(ast, "undefined identifier");
			return false;
		}
		return true;
	}

	@Override
	public Boolean visit(Int ast) {
		return true;
	}

	@Override
	public Boolean visit(LEq ast) {
		return checkNumeric(ast, "<=", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(LT ast) {
		return checkNumeric(ast, "<", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(Mul ast) {
		return checkNumeric(ast, "*", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(Neg ast) {
		return checkNumeric(ast, "unary -", ast.getArg());
	}

	@Override
	public Boolean visit(NEq ast) {
		return checkEqual(ast, "!=", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(Not ast) {
		return checkBool(ast, "!", ast.getArg());
	}

	@Override
	public Boolean visit(Or ast) {
		return checkBool(ast, "||", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(Pos ast) {
		return checkNumeric(ast, "unary +", ast.getArg());
	}

	@Override
	public Boolean visit(Sub ast) {
		return checkNumeric(ast, "-", ast.getLhs(), ast.getRhs());
	}

	@Override
	public Boolean visit(nl.cwi.swat.liveql.ast.expr.Bool bool) {
		return true;
	}

	@Override
	public Boolean visit(Str str) {
		return true;
	}

}
