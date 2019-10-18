package com.phuocnguyen.LexicalAnalyzer.Version004;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Vector;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

	private CharSequence input;
	private int where = 0;
	private final int[] lineStartOffsets;

	private Location locationOf(int offset) {
		for (int ln = 0; ln < lineStartOffsets.length; ++ln) {
			int curr = lineStartOffsets[ln];
			if (curr == offset)
				return new Location(ln, offset - curr);

			if (curr > offset) {
				int col0 = lineStartOffsets[ln - 1];
				return new Location(ln - 1, offset - col0);
			}
		}

		assert false;
		return null;
	}

	public Lexer(String s) {
		input = s;

		Vector<Integer> ints = new Vector<Integer>();
		ints.add(0);

		CharSequence cs = input;
		for (int offset = 0; offset < cs.length(); ++offset) {
			char c = cs.charAt(offset);
			if (c != '\n')
				continue;

			ints.add(offset + 1);
		}

		ints.add(cs.length());

		this.lineStartOffsets = new int[ints.size()];
		int ln = -1;
		for (int curr : ints) {
			++ln;
			lineStartOffsets[ln] = curr;
		}
	}

	public Lexer(InputStream is) throws IOException {
		this(new InputStreamReader(is));
	}

	private static String makeStr(Reader r) throws IOException {
		StringBuilder sb = new StringBuilder();

		while (true) {
			int n = r.read();
			if (n < 0)
				break;

			char c = (char) n;
			sb.append(c);
		}

		return sb.toString();
	}

	public Lexer(Reader r) throws IOException {
		this(makeStr(r));
	}

	public Token next(Pattern p) {
		Matcher m = p.matcher(input);
		boolean b = m.find();
		if (!b)
			return null;

		MatchResult mr = m.toMatchResult();
		if (m.start() != 0)
			return null;

		String s = input.subSequence(mr.start(), mr.end()).toString();
		Token result = new Token(s, 0, locationOf(where + mr.start()), locationOf(where + mr.end()));

		input = input.subSequence(mr.end(), input.length());
		where += mr.end();

		return result;
	}

	public Token next(String regexp) {
		return next(Pattern.compile(regexp));
	}
}
