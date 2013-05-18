package nl.cwi.swat.liveql.patch;

public interface PatchNode {
	void accept(Visitor visitor);
}
