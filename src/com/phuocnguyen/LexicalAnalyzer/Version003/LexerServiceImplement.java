package com.phuocnguyen.LexicalAnalyzer.Version003;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class LexerServiceImplement implements LexerService {
	private StringBuilder stringContents = new StringBuilder();
	private TokenLibraries tokenLibraries;
	/* content lexemes in file */
	private String lexeme;
	/* check state exhausted */
	private boolean exhausted = false;
	/* manage error */
	private String message = "";
	/* manage blank chars */
	private Set<Character> blankChars = new HashSet<Character>();

	@Override
	public void readInput(String filePath) {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(stringContents::append);

		} catch (Exception e) {
			exhausted = true;
			message = "Could not read file: " + filePath;
			return;
		}
		blankChars.add('\r');
		blankChars.add('\n');
		blankChars.add((char) 8);
		blankChars.add((char) 9);
		blankChars.add((char) 11);
		blankChars.add((char) 12);
		blankChars.add((char) 32);
		move();
	}

	@Override
	public void move() {
		if (exhausted) {
			return;
		}
		if (stringContents.length() == 0) {
			exhausted = true;
			return;
		}
		ignoreWhiteSpaces();
		if (findNextToken()) {
			return;
		}
		exhausted = true;
		if (stringContents.length() > 0) {
			message = "Unexpected symbol: '" + stringContents.charAt(0) + "'";
		}
	}

	@Override
	public void ignoreWhiteSpaces() {
		int charsToDelete = 0;
		while (blankChars.contains(stringContents.charAt(charsToDelete))) {
			charsToDelete++;
		}
		if (charsToDelete > 0) {
			stringContents.delete(0, charsToDelete);
		}
	}

	@Override
	public boolean findNextToken() {
		for (TokenLibraries tokenLibraries2 : TokenLibraries.values()) {
			int end = tokenLibraries2.endOfMatch(stringContents.toString());
			if (end != -1) {
				tokenLibraries = tokenLibraries2;
				lexeme = stringContents.substring(0, end);
				stringContents.delete(0, end);
				return true;
			}
		}
		return false;
	}

	@Override
	public TokenLibraries currentTokenLibraries() {
		return tokenLibraries;
	}

	@Override
	public String currentLexeme() {
		return lexeme;
	}

	@Override
	public boolean isSuccessful() {
		return message.isEmpty();
	}

	@Override
	public String errorMessages() {
		return message;
	}

	@Override
	public boolean isExhausted() {
		return exhausted;
	}

}
