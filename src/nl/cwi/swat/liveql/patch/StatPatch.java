package nl.cwi.swat.liveql.patch;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public abstract class StatPatch implements PatchNode {
	
	private final List<Edit<Stat>> edits;

	public StatPatch() {
		this.edits = new ArrayList<Edit<Stat>>();
	}
	
	public List<Edit<Stat>> getEdits() {
		return edits;
	}
	
	public void addEdit(Edit<Stat> edit) {
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
