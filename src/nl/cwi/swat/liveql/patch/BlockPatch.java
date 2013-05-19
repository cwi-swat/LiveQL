package nl.cwi.swat.liveql.patch;

import java.util.ArrayList;
import java.util.List;

public class BlockPatch extends StatPatch {

	public BlockPatch() {
		super();
	}
	
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public boolean isIdentity() {
		if (!super.isIdentity()) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
}
