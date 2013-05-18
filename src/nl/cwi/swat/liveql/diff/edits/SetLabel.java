package nl.cwi.swat.liveql.diff.edits;

import nl.cwi.swat.liveql.ast.stat.Label;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class SetLabel implements Edit<Stat> {

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
}
