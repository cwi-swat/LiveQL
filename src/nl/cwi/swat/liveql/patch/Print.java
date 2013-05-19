package nl.cwi.swat.liveql.patch;

import java.io.IOException;
import java.io.Writer;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;
import nl.cwi.swat.liveql.diff.edits.AddElse;
import nl.cwi.swat.liveql.diff.edits.InsertStat;
import nl.cwi.swat.liveql.diff.edits.NoChange;
import nl.cwi.swat.liveql.diff.edits.QLEdit;
import nl.cwi.swat.liveql.diff.edits.RemoveElse;
import nl.cwi.swat.liveql.diff.edits.RemoveStat;
import nl.cwi.swat.liveql.diff.edits.SetCondition;
import nl.cwi.swat.liveql.diff.edits.SetExpression;
import nl.cwi.swat.liveql.diff.edits.SetLabel;
import nl.cwi.swat.liveql.diff.edits.SetType;
import nl.cwi.swat.liveql.diff.edits.ToAnswerable;
import nl.cwi.swat.liveql.diff.edits.ToComputed;

public class Print implements Visitor, nl.cwi.swat.liveql.diff.edits.Visitor {
	private int indent = 0;
	private final Writer writer;
	
	public Print(Writer writer) {
		this.writer = writer;
	}

	private void indent() {
		indent++;
	}
	
	private void dedent() {
		indent--;
	}
	
	private void print(Object s) {
		String padding = "";
		for (int i = 0; i < indent;  i++) {
			padding += " ";
		}
		try {
			writer.write(padding + s + "\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void visit(BlockPatch patch) {
		printEdits(patch);
	}

	@Override
	public void visit(IfThenPatch patch) {
		print("if");
		indent();
		printEdits(patch);
		dedent();
		print("{");
		indent();
		patch.getBodyPatch().accept(this);
		dedent();
		print("}");
	}

	@Override
	public void visit(IfThenElsePatch patch) {
		print("if");
		indent();
		printEdits(patch);
		dedent();
		print("{");
		indent();
		patch.getBodyPatch().accept(this);
		dedent();
		print("}");
		print("else {");
		indent();
		patch.getElsePatch().accept(this);
		dedent();
		print("}");
	}

	private void printEdits(StatPatch patch) {
		for (QLEdit e: patch.getEdits()) {
			e.accept(this);
		}
	}

	@Override
	public void visit(QuestionPatch patch) {
		if (patch.getEdits().isEmpty()) {
			print("NOP");
			return;
		}
		print("question");
		indent();
		printEdits(patch);
		dedent();
	}

	@Override
	public void visit(FormPatch patch) {
		patch.getBody().accept(this);
	}

	@Override
	public void visit(AddElse e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InsertStat e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NoChange e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(RemoveElse e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(RemoveStat e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SetCondition e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SetExpression e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SetLabel e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SetType e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ToAnswerable e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ToComputed e) {
		// TODO Auto-generated method stub
		
	}

}
