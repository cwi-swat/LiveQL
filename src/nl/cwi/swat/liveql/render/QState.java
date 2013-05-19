package nl.cwi.swat.liveql.render;

import java.util.List;

import javax.swing.JLabel;

import nl.cwi.swat.liveql.ast.expr.Expr;
import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.types.Type;
import nl.cwi.swat.liveql.eval.Bool;
import nl.cwi.swat.liveql.eval.Eval;
import nl.cwi.swat.liveql.eval.Value;

import static nl.cwi.swat.liveql.ast.expr.UsedVars.usedVars;


class QState {
	private Ident name;
	private List<Expr> conds;
	private Expr computed;
	private Value value;
	private Type type;
	private Widget widget;
	private JLabel label;

	public QState(Ident name, Type type, List<Expr> conds, Expr computed, Value value, 
			Widget widget, JLabel label) {
		this.name = name;
		setType(type);
		setConds(conds);
		setValue(value);
		setWidget(widget);
		setComputed(computed);
		this.label = label;
	}
	
	public boolean isEditable() {
		return getComputed() == null;
	}
	
	public boolean hasDataDependencyOn(Ident x) {
		if (isEditable()) {
			return false;
		}
		return usedVars(computed).contains(x);
	}
	
	public boolean hasControlDependencyOn(Ident x) {
		for (Expr c: conds) {
			if (usedVars(c).contains(x)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean recompute(State theState) {
		if (isEditable()) {
			return false;
		}
		Value newValue = getComputed().accept(new Eval(theState));
		if (newValue.equals(getValue())) {
			return false;
		}
		widget.setValue(newValue);
		return true;
	}
	
	private boolean isVisible(State env) {
		for (Expr cond: conds) {
			System.out.println("Evaling: " + cond);
			Value value = cond.accept(new Eval(env));
			boolean enabled = value.isDefined() && ((Bool)value).getValue();
			if (!enabled) {
				return false;
			}
		}
		return true;
	}

	public void updateVisibility(State env) {
		boolean visible = isVisible(env);
		widget.setVisible(visible);
		label.setVisible(visible);
	}

	
	public Expr getComputed() {
		return computed;
	}
	
	public List<Expr> getConds() {
		return conds;
	}
	
	public Ident getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
	
	public Value getValue() {
		return value;
	}
	
	public void setComputed(Expr computed) {
		this.computed = computed;
		widget.setEditable(isEditable());
	}
	
	public void setConds(List<Expr> conds) {
		this.conds = conds;
	}
	
	public void setValue(Value value) {
		this.value = value;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget w) {
		this.widget = w;
	}
	
}