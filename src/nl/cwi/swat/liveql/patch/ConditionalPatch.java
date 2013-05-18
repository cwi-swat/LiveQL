package nl.cwi.swat.liveql.patch;


public abstract class ConditionalPatch extends StatPatch {
	private StatPatch bodyPatch;

	public ConditionalPatch() {
		super();
		this.bodyPatch = null;
	}
	
	public StatPatch getBodyPatch() {
		return bodyPatch;
	}
	
	
	public void setBodyPatch(StatPatch bodyPatch) {
		this.bodyPatch = bodyPatch;
	}
	
	@Override
	public boolean isIdentity() {
		if (!super.isIdentity()) {
			return false;
		}
		return getBodyPatch().isIdentity();
	}
	
}
