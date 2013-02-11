package nl.cwi.swat.liveql.render;

import java.io.IOException;
import java.io.InputStream;

public class Util {

	public static Pair getPositionForLine(String txt, int theLine) {
		int position = 0;
		int lineNo = 0;
		String lastLine = "";
		int lastPosition = 0;
		for (String line : txt.split("\\n")) {
			System.out.println("LIne " + lineNo);
			if (lineNo == theLine) {
				break;
			}
			lastLine = line;
			lastPosition = position;
			lineNo++;
			if (position < txt.length()) {
				position += line.length() + 1;
			}
		}
		return new Pair(lastPosition, lastPosition + lastLine.length());
	}

	public static class Pair {
		public final int start;
		public final int end;

		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static String readStream(InputStream fis) {
		StringBuilder sb = new StringBuilder();
		try {
			int content;
			while ((content = fis.read()) != -1) {
				sb.append((char) content);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}
}
