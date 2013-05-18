package nl.cwi.swat.liveql.patch;

import java.io.StringWriter;

public class FormPatch implements PatchNode {

	private final StatPatch body;

	public FormPatch(StatPatch statPatch) {
		this.body = statPatch;
	}
	
	public StatPatch getBody() {
		return body;
	}

	@Override
	public String toString() {
		StringWriter w = new StringWriter();
		accept(new Print(w));
		return w.toString();
	}
	
	public boolean isIdentity() {
		return body.isIdentity();
	}
	
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
