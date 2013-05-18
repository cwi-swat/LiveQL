package nl.cwi.swat.liveql.patch;

public class IfThenPatch extends ConditionalPatch {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
