package nl.cwi.swat.liveql.ast.stat;

import java.util.Collections;
import java.util.Iterator;

import nl.cwi.swat.liveql.ast.ASTNode;
import nl.cwi.swat.liveql.diff.Edit;
import nl.cwi.swat.liveql.diff.LCSMatrix;
import nl.cwi.swat.liveql.diff.Match;
import nl.cwi.swat.liveql.diff.edits.BlockEdit;
import nl.cwi.swat.liveql.diff.edits.BlockEditFactory;
import nl.cwi.swat.liveql.patch.BlockPatch;
import nl.cwi.swat.liveql.patch.ConditionalPatch;
import nl.cwi.swat.liveql.patch.QuestionPatch;
import nl.cwi.swat.liveql.patch.StatPatch;

public abstract class Stat implements ASTNode, Iterable<Stat>, Match<Stat> {
	public abstract void accept(Visitor visitor);
	
	public Iterator<Stat> iterator() {
		return Collections.singletonList(this).iterator();
	}

	public boolean matchConditional(Conditional other) {
		return false;
	}

	public boolean matchQuestion(Question other) {
		return false;
	}
	
	public abstract StatPatch diff(Stat other);
	
	public StatPatch diffToComputed(Computed me, QuestionPatch patch) {
		throw new UnsupportedOperationException();
	}

	public StatPatch diffToAnswerable(Answerable me, QuestionPatch patch) {
		throw new UnsupportedOperationException();
	}

	public StatPatch diffToIfThenElse(IfThenElse me, ConditionalPatch patch) {
		throw new UnsupportedOperationException();
	}

	public StatPatch diffToIfThen(IfThen me, ConditionalPatch patch) {
		throw new UnsupportedOperationException();
	}

	// Blocks can diff to any other statement (flattening).
	public BlockPatch diffToBlock(Block me, BlockPatch patch) {
		LCSMatrix<Stat> lcs = new LCSMatrix<Stat>(me, this);
		for (Edit<Stat> edit: lcs.getEdits(new BlockEditFactory())) {
			((BlockEdit)edit).register(patch);
		}
		return patch;
	}

}
