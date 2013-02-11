package nl.cwi.swat.liveql.diff;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/*
 * See here for source of the algorithm: 
 *    http://en.wikipedia.org/wiki/Longest_common_subsequence_problem 
 */


public class LCSMatrix<X extends Iterable<X> & Match<X>> {
	private final X DUMMY = null;
	private final Map<X, Map<X,Integer>> matrix = new IdentityHashMap<X, Map<X,Integer>>();
	private final List<X> rows = new ArrayList<X>();
	private final List<X> cols = new ArrayList<X>();
	
	public LCSMatrix(X s1, X s2) {
		initMatrix(s1, s2);
		fillMatrix(s1, s2);
	}

	private void fillMatrix(X s1, X s2) {
		X prev_i = DUMMY;
		X prev_j = DUMMY;
		for (X i: s1) {
			for (X j: s2) {
				if (i.match(j)) {
					matrix.get(i).put(j, matrix.get(prev_i).get(prev_j) + 1);
				}
				else {
					matrix.get(i).put(j, Math.max(
							matrix.get(i).get(prev_j),
							matrix.get(prev_i).get(j)));
				}
				prev_j = j;
			}
			prev_j = DUMMY;
			prev_i = i;
		}
	}

	private void initMatrix(X s1, X s2) {
		rows.add(DUMMY);
		cols.add(DUMMY);
		for (X i: s1) {
			matrix.put(i, new IdentityHashMap<X, Integer>());
			matrix.get(i).put(DUMMY, 0);
			rows.add(i);
		}
		matrix.put(DUMMY, new IdentityHashMap<X, Integer>());
		for (X j: s2) {
			matrix.get(DUMMY).put(j, 0);
			cols.add(j);
		}
		matrix.get(DUMMY).put(DUMMY, 0);
	}
	
	public int getLCSLength() {
		return matrix.get(rows.get(rows.size() - 1)).get(cols.get(cols.size() - 1));
	}
	
	public X getX(int i) {
		return rows.get(i);
	}
	
	public X getY(int i) {
		return cols.get(i);
	}
	
	public int get(int i, int j) {
		return matrix.get(getX(i)).get(getY(j));
	}
	
	public int lastIndexX() {
		return rows.size() - 1;
	}

	public int lastIndexY() {
		return cols.size() - 1;
	}

	public List<Edit<X>> getEdits(EditFactory<X> fact) {
		List<Edit<X>> result = new ArrayList<Edit<X>>(); 
		collectDiff(result, fact, lastIndexX(), lastIndexY());
		return result;
	}
	
	private void collectDiff(List<Edit<X>> result, EditFactory<X> fact, int i, int j) {
		if (i > 0 && j > 0 && getX(i).match(getY(j))) {
			collectDiff(result, fact, i - 1, j - 1);
			result.add(fact.noChange(i, getX(i), getY(j)));
		}
		else if (j > 0 && (i == 0 || get(i, j-1) >= get(i-1, j))) {
			collectDiff(result, fact, i, j - 1);
			result.add(fact.insert(i, getY(j))); // or j?
		}
		else if (i > 0 && (j == 0 || get(i, j-1) < get(i-1, j))) {
			collectDiff(result, fact, i - 1, j);
			result.add(fact.remove(i, getX(i)));
		}
	}

}
