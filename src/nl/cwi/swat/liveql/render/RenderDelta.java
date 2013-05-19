package nl.cwi.swat.liveql.render;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.swing.JComponent;
import javax.swing.JLabel;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.expr.Not;
import nl.cwi.swat.liveql.ast.stat.Answerable;
import nl.cwi.swat.liveql.ast.stat.Block;
import nl.cwi.swat.liveql.ast.stat.Computed;
import nl.cwi.swat.liveql.ast.stat.IfThen;
import nl.cwi.swat.liveql.ast.stat.IfThenElse;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.ast.stat.Visitor;
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
import nl.cwi.swat.liveql.eval.Undefined;
import nl.cwi.swat.liveql.patch.BlockPatch;
import nl.cwi.swat.liveql.patch.FormPatch;
import nl.cwi.swat.liveql.patch.IfThenElsePatch;
import nl.cwi.swat.liveql.patch.IfThenPatch;
import nl.cwi.swat.liveql.patch.QuestionPatch;

public class RenderDelta implements 
    nl.cwi.swat.liveql.ast.stat.Visitor, 
	nl.cwi.swat.liveql.diff.edits.Visitor, 
	nl.cwi.swat.liveql.patch.Visitor {

	private Stack<Expr> conds = new Stack<Expr>();
	private int question = 0;
	private State state;
	private Container panel;
	private Set<QState> dirty;
	
	public RenderDelta(State state, Container panel) {
		this.state = state;
		this.panel = panel;
		this.dirty = new HashSet<QState>();
	}
	
	@Override
	public void visit(FormPatch patch) {
		patch.getBody().accept(this);
	}

	@Override
	public void visit(BlockPatch patch) {
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
	}

	@Override
	public void visit(IfThenPatch patch) {
		// start with original condition (needed for AddElse)
		conds.push(patch.getCond());
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
		patch.getBodyPatch().accept(this);
		conds.pop();
	}

	@Override
	public void visit(IfThenElsePatch patch) {
		conds.push(patch.getCond());
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
		patch.getBodyPatch().accept(this);
		Expr e = conds.pop();
		conds.push(new Not(e));
		patch.getElsePatch().accept(this);
		conds.pop();
	}

	@Override
	public void visit(QuestionPatch patch) {
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
		// TODO: update conditions here.
		question++; // move to next
	}

	@Override
	public void visit(AddElse e) {
		// NB: this depends on the condition of the if this edit is applied
		// to, but that expression maybe itself (have been?) updated by an edit.
		// the ordering of edits in an IfThen[Else] patch ensures the condition
		// is always *first* updated on the stack. 
		Expr c = conds.pop();
		conds.push(new Not(c));
		e.getElseBody().accept(this);
		conds.pop();
		conds.push(c);
	}

	@Override
	public void visit(InsertStat e) {
		e.getStatement().accept(this);
	}

	@Override
	public void visit(NoChange e) {
		// No change edits are in BlockPatches.
		// in this case we just run the nested
		// patch on the current question
		e.getPatch().accept(this);
	}


	private class StatRemover implements Visitor {
		@Override
		public void visit(Block stat) {
			for (Stat s: stat.getStats()) {
				s.accept(this);
			}
		}
		
		@Override
		public void visit(IfThenElse stat) {
			for (Stat s: stat.getBody()) {
				s.accept(this);
			}
			for (Stat s: stat.getElseBody()) {
				s.accept(this);
			}
		}
		
		@Override
		public void visit(IfThen stat) {
			for (Stat s: stat.getBody()) {
				s.accept(this);
			}
		}
		
		@Override
		public void visit(Answerable stat) {
			removeCurrentWidgetAndLabel();
		}
		
		@Override
		public void visit(Computed stat) {
			removeCurrentWidgetAndLabel();
		}
	}
	
	@Override
	public void visit(RemoveElse e) {
		e.getOld().accept(new StatRemover());
	}

	@Override
	public void visit(RemoveStat e) {
		e.getRemoved().accept(new StatRemover());
	}

	@Override
	public void visit(SetCondition e) {
		// replace the condition on the stack
		conds.pop();
		conds.push(e.getCondition());
	}

	@Override
	public void visit(SetExpression e) {
		QState q = state.getState(question);
		q.setComputed(e.getExpr());
		dirty.add(q);
	}

	@Override
	public void visit(SetLabel e) {
		JLabel l = currentLabel();
		l.setText(e.getLabel().getValue());
	}

	private JLabel currentLabel() {
		return (JLabel) panel.getComponent(currentLabelIndex());
	}

	private int currentLabelIndex() {
		return question * 2;
	}
	
	private void removeCurrentWidgetAndLabel() {
		panel.remove(currentLabelIndex());
		panel.remove(currentWidgetIndex());
	}
	
	private void updateCurrentWidget(JComponent comp) {
		panel.remove(currentWidgetIndex());
		panel.add(comp, currentWidgetIndex());
	}

	private int currentWidgetIndex() {
		return currentLabelIndex() + 1;
	}

	@Override
	public void visit(SetType e) {
		QState q = state.getState(question);
		q.setType(e.getType());
		q.setValue(q.getValue().convert(e.getType()));
		Widget w = TypeToWidget.typeToWidget(state, e.getType(), q.getName());
		q.setWidget(w);
		w.setValue(q.getValue());
		updateCurrentWidget(w.getComponent());
		dirty.add(q);
	}

	@Override
	public void visit(ToAnswerable e) {
		QState q = state.getState(question);
		q.setComputed(null);
		dirty.add(q);
	}

	@Override
	public void visit(ToComputed e) {
		QState q = state.getState(question);
		q.setComputed(e.getExpr());
		dirty.add(q);
	}
	
	@Override
	public void visit(Computed stat) {
		Widget w = TypeToWidget.typeToWidget(state, stat.getType(), stat.getName());
		JLabel jlabel = new JLabel(stat.getLabel().getValue());
		// What to do about hidemode?
		panel.add(jlabel, currentLabelIndex());
		panel.add(w.getComponent(), currentWidgetIndex());
		
		QState q = new QState(stat.getName(), stat.getType(), activeConds(), stat.getExpr(),
									Undefined.UNDEF, w, jlabel);
		state.add(question, q, w);
		q.updateVisibility(state);
		dirty.add(q);
		question++;
	}

	@Override
	public void visit(Answerable stat) {
		Widget w = TypeToWidget.typeToWidget(state, stat.getType(), stat.getName());
		JLabel jlabel = new JLabel(stat.getLabel().getValue());
		// What to do about hidemode?
		panel.add(jlabel, currentLabelIndex());
		panel.add(w.getComponent(), currentWidgetIndex());
		
		QState q = new QState(stat.getName(), stat.getType(), activeConds(), null,
									Undefined.UNDEF, w, jlabel);
		state.add(question, q, w);
		q.updateVisibility(state);
		dirty.add(q);
		question++;
	}
	
	@Override
	public void visit(IfThen stat) {
		conds.push(stat.getCond());
		stat.getBody().accept(this);
		conds.pop();
	}

	@Override
	public void visit(IfThenElse stat) {
		conds.push(stat.getCond());
		stat.getBody().accept(this);
		conds.pop();
		conds.push(new Not(stat.getCond()));
		stat.getElseBody().accept(this);
		conds.pop();
	}

	@Override
	public void visit(Block stat) {
		for (Stat s: stat.getStats()) {
			s.accept(this);
		}
	}
	
	private List<Expr> activeConds() {
		List<Expr> exprs = new ArrayList<Expr>();
		exprs.addAll(conds);
		return exprs;
	}

}
