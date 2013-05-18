package nl.cwi.swat.liveql.render;

import java.util.Stack;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.diff.edits.AddElse;
import nl.cwi.swat.liveql.diff.edits.InsertStat;
import nl.cwi.swat.liveql.diff.edits.NoChange;
import nl.cwi.swat.liveql.diff.edits.QLEdit;
import nl.cwi.swat.liveql.diff.edits.RemoveElse;
import nl.cwi.swat.liveql.diff.edits.RemoveStat;
import nl.cwi.swat.liveql.diff.edits.SetCondition;
import nl.cwi.swat.liveql.diff.edits.SetExpression;
import nl.cwi.swat.liveql.diff.edits.SetLabel;
import nl.cwi.swat.liveql.diff.edits.SetType;
import nl.cwi.swat.liveql.diff.edits.ToAnswerable;
import nl.cwi.swat.liveql.diff.edits.ToComputed;
import nl.cwi.swat.liveql.patch.BlockPatch;
import nl.cwi.swat.liveql.patch.FormPatch;
import nl.cwi.swat.liveql.patch.IfThenElsePatch;
import nl.cwi.swat.liveql.patch.IfThenPatch;
import nl.cwi.swat.liveql.patch.QuestionPatch;

public class RenderDelta implements nl.cwi.swat.liveql.diff.edits.Visitor, nl.cwi.swat.liveql.patch.Visitor {

	private Stack<Expr> conditions = new Stack<Expr>();
	private int comp = 0;
	
	@Override
	public void visit(FormPatch patch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BlockPatch patch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IfThenPatch patch) {
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
		patch.getBodyPatch().accept(this);
	}

	@Override
	public void visit(IfThenElsePatch patch) {
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
		patch.getBodyPatch().accept(this);
		patch.getElsePatch().accept(this);
	}

	@Override
	public void visit(QuestionPatch patch) {
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
	}

	@Override
	public void visit(AddElse e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(InsertStat e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NoChange e) {
		throw new AssertionError("NoChange should be mapped to statement diffs");
	}

	@Override
	public void visit(RemoveElse e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RemoveStat e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SetCondition e) {
		// TODO Auto-generated method stub
		//render(new And(cond, stat.getCond()), stat.getBody(), state, panel, editor, window);
	}

	@Override
	public void visit(SetExpression e) {
		// TODO Auto-generated method stub
//		JLabel label = addLabel(stat.getLabel());
//		Control ctl = typeToWidget(stat.getType(), false);
//		registerDeps(stat.getExpr(), new ComputedObserver(stat, ctl, state));
//		registerDeps(cond, new ConditionObserver(cond, label, ctl.getComponent(), state, window)); 
//		registerComputed(stat, ctl);
//		add(ctl.getComponent());
	}

	@Override
	public void visit(SetLabel e) {
		// find the label at comp-id n
		// update the text

	}

	@Override
	public void visit(SetType e) {
		// change the control
		// migrate the value in state if possible
	}

	@Override
	public void visit(ToAnswerable e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ToComputed e) {
		// TODO Auto-generated method stub

	}

}
