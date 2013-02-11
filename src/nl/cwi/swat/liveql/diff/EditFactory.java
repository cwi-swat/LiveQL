package nl.cwi.swat.liveql.diff;


public interface EditFactory<X> {
	Edit<X> insert(int i, X x);
	Edit<X> remove(int i, X x);
	Edit<X> noChange(int i, X old, X nw);
}
