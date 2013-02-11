package nl.cwi.swat.liveql.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.stat.Answerable;
import nl.cwi.swat.liveql.ast.stat.Block;
import nl.cwi.swat.liveql.ast.stat.Computed;
import nl.cwi.swat.liveql.ast.stat.Conditional;
import nl.cwi.swat.liveql.ast.stat.IfThen;
import nl.cwi.swat.liveql.ast.stat.IfThenElse;
import nl.cwi.swat.liveql.ast.stat.Question;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.ast.stat.Visitor;
import nl.cwi.swat.liveql.ast.types.Type;

import static nl.cwi.swat.liveql.check.CheckExpr.check;

public class CheckStat implements Visitor {
	private final Map<Ident, Type> typeEnv;
	private final List<Message> errors;
	
	public static List<Message> checkStat(Stat stat) {
		return checkStat(stat, new ArrayList<Message>());
	}
	
	public static List<Message> checkStat(Stat stat, List<Message> errors) {
		CheckStat check = new CheckStat(errors);
		stat.accept(check);
		return errors;
	}
	
	private CheckStat(List<Message> errors) {
		this.errors = errors;
		this.typeEnv = new HashMap<Ident, Type>();
	}

	private void addError(Stat stat, String msg) {
		errors.add(new Message(stat.getLine(), msg));
	}
	
	private void defineVariable(Ident var, Type type) {
		typeEnv.put(var,  type);
	}
	
	private boolean isDefined(Ident var) {
		return typeEnv.containsKey(var);
	}
	
	private Boolean checkExpr(Expr expr) {
		return check(expr, typeEnv, errors);
	}
	
	private void checkName(Question question, Type type) {
		Ident name = question.getName();
		if (isDefined(name)) {
			if (!typeEnv.get(name).isCompatibleTo(type)) {
				addError(question, "duplicate definition with different type");
			}
		}
		else {
			defineVariable(name, type);
		}
	}
	
	private void checkCondition(Conditional stat) {
		Expr cond = stat.getCond();
		if (checkExpr(cond)) {
			if (!cond.typeOf(typeEnv).isCompatibleToBool()) {
				addError(stat, "condition should be of boolean type");
			}
		}
	}

	@Override
	public void visit(Computed stat) {
		checkName(stat, stat.getExpr().typeOf(typeEnv));
		if (checkExpr(stat.getExpr())) {
			if (!stat.getType().isCompatibleTo(stat.getExpr().typeOf(typeEnv))) {
				addError(stat, "expression type not equal to declared type");
			}
		}
	}

	@Override
	public void visit(Answerable stat) {
		checkName(stat, stat.getType());
	}


	@Override
	public void visit(IfThen stat) {
		checkCondition(stat);
		stat.getBody().accept(this);
	}

	@Override
	public void visit(IfThenElse stat) {
		checkCondition(stat);
		stat.getBody().accept(this);
		stat.getElseBody().accept(this);
	}

	@Override
	public void visit(Block stat) {
		for (Stat s: stat.getStats()) {
			s.accept(this);
		}
	}

	
}
