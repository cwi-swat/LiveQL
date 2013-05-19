package nl.cwi.swat.liveql.patch;

import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;
import nl.cwi.swat.liveql.diff.edits.NoChange;
import nl.cwi.swat.liveql.diff.edits.QLEdit;


public class PatchToJTree implements Visitor {

	private Stack<DefaultMutableTreeNode> stack;

	public static void patchIntoJTree(FormPatch root, DefaultMutableTreeNode top) {
		PatchToJTree p2j = new PatchToJTree(top);
		root.accept(p2j);
	}
	
	private PatchToJTree(DefaultMutableTreeNode top) {
		this.stack = new Stack<DefaultMutableTreeNode>();
		stack.push(top);
	}
	
	@Override
	public void visit(BlockPatch patch) {
		for (QLEdit e: patch.getEdits()) {
			// temp hack
			if (e instanceof NoChange) {
				((NoChange)e).getPatch().accept(this);
			}
			else {
				stack.peek().add(new DefaultMutableTreeNode(e.toString()));
			}
		}
	}

	@Override
	public void visit(IfThenPatch patch) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("if");
		for (Edit<Stat> e: patch.getEdits()) {
			node.add(new DefaultMutableTreeNode(e.toString()));
		}
		DefaultMutableTreeNode thn = new DefaultMutableTreeNode("then");
		stack.push(thn);
		patch.getBodyPatch().accept(this);
		stack.pop();
		
		node.add(thn);
		stack.peek().add(node);
	}
	
	@Override
	public void visit(IfThenElsePatch patch) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("if");
		for (Edit<Stat> e: patch.getEdits()) {
			node.add(new DefaultMutableTreeNode(e.toString()));
		}
		DefaultMutableTreeNode thn = new DefaultMutableTreeNode("then");
		stack.push(thn);
		patch.getBodyPatch().accept(this);
		stack.pop();
		
		DefaultMutableTreeNode els = new DefaultMutableTreeNode("else");
		stack.push(els);
		patch.getElsePatch().accept(this);
		stack.pop();
		
		node.add(thn);
		node.add(els);
		stack.peek().add(node);
	}

	@Override
	public void visit(QuestionPatch patch) {
		if (patch.getEdits().isEmpty()) {
			return;
		}
		for (Edit<Stat> e: patch.getEdits()) {
			stack.peek().add(new DefaultMutableTreeNode(e.toString()));
		}
	}

	@Override
	public void visit(FormPatch patch) {
		patch.getBody().accept(this);
	}

}
