package nl.cwi.swat.liveql.diff;

public interface Match<X> {
	public boolean match(X other);
}
