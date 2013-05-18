package nl.cwi.swat.liveql.patch;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;
import nl.cwi.swat.liveql.diff.edits.QLEdit;

public abstract class StatPatch implements PatchNode {
	
	private final List<QLEdit> edits;

	public StatPatch() {
		this.edits = new ArrayList<QLEdit>();
	}
	
	public List<QLEdit> getEdits() {
		return edits;
	}
	
	public void addEdit(QLEdit edit) {
		edits.add(edit);
	}
	
	public boolean isIdentity() {
		return edits.isEmpty();
	}
	
	@Override
	public String toString() {
		StringWriter w = new StringWriter();
		accept(new Print(w));
		return w.toString();
	}
	
	public boolean isEmpty() {
		return getEdits().isEmpty();
	}
		
}
