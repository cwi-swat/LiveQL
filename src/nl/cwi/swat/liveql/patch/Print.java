package nl.cwi.swat.liveql.patch;

import java.io.IOException;
import java.io.Writer;

import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.diff.Edit;

public class Print implements Visitor {
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
		for (StatPatch p: patch.getKids()) {
			p.accept(this);
		}
	}

	@Override
	public void visit(ConditionalPatch patch) {
		print("if");
		indent();
		printEdits(patch);
		dedent();
		print("{");
		indent();
		patch.getThenPatch().accept(this);
		dedent();
		print("}");
		if (!patch.getElsePatch().isEmpty()) { 
			print("else {");
			indent();
			patch.getElsePatch().accept(this);
			dedent();
			print("}");
		}
	}

	private void printEdits(StatPatch patch) {
		for (Edit<Stat> e: patch.getEdits()) {
			print(e);
		}
	}

	@Override
	public void visit(QuestionPatch patch) {
		if (patch.getEdits().isEmpty()) {
			return;
		}
		print("question " + patch.getSource().getName());
		indent();
		printEdits(patch);
		dedent();
	}

}