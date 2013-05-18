package nl.cwi.swat.liveql.ast.form;

import java.util.Iterator;

import nl.cwi.swat.liveql.ast.ASTNode;
import nl.cwi.swat.liveql.ast.expr.Ident;
import nl.cwi.swat.liveql.ast.stat.Block;
import nl.cwi.swat.liveql.ast.stat.Stat;
import nl.cwi.swat.liveql.patch.FormPatch;

public class Form implements ASTNode, Iterable<Stat> {

	private final Ident name;
	private final Block body;

	public Form(Ident name, Block body) {
		this.name = name;
		this.body = body;
	}
	
	public Block getBody() {
		return body;
	}
	
	public Ident getName() {
		return name;
	}

	@Override
	public Iterator<Stat> iterator() {
		return getBody().iterator();
	}

	@Override
	public int getLine() {
		return getName().getLine();
	}
	
	public FormPatch diff(Form other) {
		return new FormPatch(body.diff(other.getBody()));
	}


}
