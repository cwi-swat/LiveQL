package nl.cwi.swat.liveql.eval;

import nl.cwi.swat.liveql.ast.expr.Add;
import nl.cwi.swat.liveql.ast.expr.And;
import nl.cwi.swat.liveql.ast.expr.Bool;
import nl.cwi.swat.liveql.ast.expr.Div;
import nl.cwi.swat.liveql.ast.expr.Eq;
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

public class Eval implements Visitor<Value> {
	
	private final Env env;

	public Eval(Env env) {
		this.env = env;
	}

	
	@Override
	public Value visit(Add exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.add(r);
	}
	
	@Override
	public Value visit(Mul exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.mul(r);
	}
	
	@Override
	public Value visit(Ident var) {
		if (env.containsKey(var)) {
			return env.get(var);
		}
		return Undefined.UNDEF;
	}

	@Override
	public Value visit(And exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.and(r);
	}

	@Override
	public Value visit(Div exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.div(r);
	}

	@Override
	public Value visit(Eq exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.eq(r);
	}

	@Override
	public Value visit(GEq exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.geq(r);
	}

	@Override
	public Value visit(GT exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.gt(r);
	}

	@Override
	public Value visit(Int exp) {
		return new nl.cwi.swat.liveql.eval.Int(exp.getValue());
	}

	@Override
	public Value visit(LEq exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.leq(r);	
	}

	@Override
	public Value visit(LT exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.lt(r);	
	}

	@Override
	public Value visit(Neg exp) {
		Value a = exp.getArg().accept(this);
		return a.neg();
	}

	@Override
	public Value visit(NEq exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.neq(r);
	}

	@Override
	public Value visit(Not exp) {
		Value a = exp.getArg().accept(this);
		return a.not();
	}

	@Override
	public Value visit(Or exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.or(r);
	}

	@Override
	public Value visit(Pos exp) {
		Value a = exp.getArg().accept(this);
		return a.pos();
	}

	@Override
	public Value visit(Sub exp) {
		Value l = exp.getLhs().accept(this);
		Value r = exp.getRhs().accept(this);
		return l.sub(r);
	}

	@Override
	public Value visit(Bool bool) {
		return new nl.cwi.swat.liveql.eval.Bool(bool.getValue());
	}

	@Override
	public Value visit(Str str) {
		return new nl.cwi.swat.liveql.eval.Str(str.getValue());
	}

}
