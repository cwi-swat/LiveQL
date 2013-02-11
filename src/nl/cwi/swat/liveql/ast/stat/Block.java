package nl.cwi.swat.liveql.ast.stat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.cwi.swat.liveql.patch.BlockPatch;
import nl.cwi.swat.liveql.patch.StatPatch;

public class Block extends Stat {
	private final List<Stat> stats;
	private final int line;

	public Block(List<Stat> stats, int line) {
		this.stats = stats;
		this.line = line;
	}

	public List<Stat> getStats() {
		return stats;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Iterator<Stat> iterator() {
		final List<Iterator<Stat>> iters = new ArrayList<Iterator<Stat>>();
		for (Stat s: getStats()) {
			iters.add(s.iterator());
		}
		return new Iterator<Stat>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				while (index < iters.size()) {
					if (iters.get(index).hasNext()) {
						return true;
					}
					index++;
				}
				return false;
			}

			@Override
			public Stat next() {
				return iters.get(index).next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}

	@Override
	public boolean match(Stat other) {
		throw new UnsupportedOperationException("cannot match blocks");
	}
	
	@Override
	public String toString() {
		List<Stat> l = getStats();
		String s = "[";
		if (!l.isEmpty()) {
			s += l.get(0);
			for (int i = 1; i < l.size(); i++) {
				s += "; " + l.get(1);
			}
		}
		return s + "]";
	}
	
	@Override
	public StatPatch diff(Stat other) {
		return other.diffToBlock(this, new BlockPatch());
	}
	
	@Override
	public int getLine() {
		return line;
	}
	
}
