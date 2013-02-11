package nl.cwi.swat.liveql.check;

public class Message {
	private final String message;
	private final int line;
	
	public Message(int line, String message) {
		this.line = line;
		this.message = message;
	}
	
	public int getLine() {
		return line;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "MESSAGE: " + message;
	}

}
