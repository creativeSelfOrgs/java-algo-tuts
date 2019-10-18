package com.phuocnguyen.LexicalAnalyzer.Version002;

public interface LexerService {

	public char read();

	/* Checks if a character is a digit */
	public boolean isNumeric(char character);

	/* Checks if a character is a alpha */
	public boolean isAlpha(char character);

	public Token nextToken();

	public void LexerOutPutService(String input, String output);
}
