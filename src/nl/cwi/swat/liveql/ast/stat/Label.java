package nl.cwi.swat.liveql.ast.stat;

import nl.cwi.swat.liveql.ast.ASTNode;

public class Label implements ASTNode {
	private final String value;
	private int line;
	
	public Label(String value, int line) {
		this.value = unquote(value);
		this.line = line;
	}
	
	@Override
	public int getLine() {
		return line;
	}
	
	public String getValue() {
		return value;
	}
	
	private static String unquote(String str) {
		return str.substring(1, str.length() - 1)
				.replaceAll("\\\\", "\\")
				.replaceAll("\\n", "\n")
				.replaceAll("\\t", "\t")
				.replaceAll("\\r", "\r");
	}
	
	@Override
	public String toString() {
		return "\"" + getValue() + "\""; 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Label)) {
			return false;
		}
		return getValue().equals(((Label)obj).getValue());
	}
}
