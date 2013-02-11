package nl.cwi.swat.liveql.patch;

import java.util.ArrayList;
import java.util.List;

public class BlockPatch extends StatPatch {

	private final List<StatPatch> kids;

	public BlockPatch() {
		super();
		this.kids = new ArrayList<StatPatch>();
	}
	
	public List<StatPatch> getKids() {
		return kids;
	}
	
	public void addKid(StatPatch kid) {
		kids.add(kid);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public boolean isEmpty() {
		return super.isEmpty() && getKids().isEmpty();
	}
	
}
