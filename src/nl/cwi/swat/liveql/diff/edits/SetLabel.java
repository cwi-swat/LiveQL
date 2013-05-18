package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Label;

public class SetLabel extends QLEdit {

	private final Label label;
	private final Label old;

	public SetLabel(Label label, Label old) {
		this.label = label;
		this.old = old;
	}

	public Label getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		return "label(" + old + " -> " + getLabel() + ")";
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
