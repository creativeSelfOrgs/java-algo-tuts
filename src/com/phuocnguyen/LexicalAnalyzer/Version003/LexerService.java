package com.phuocnguyen.LexicalAnalyzer.Version003;

public interface LexerService {
	/* read path to file */
	public void readInput(String filePath);

	/* method to move current point line */
	public void move();

	/* ignore white spaces */
	public void ignoreWhiteSpaces();

	/* find next token in file */
	public boolean findNextToken();

	/* get current Token */
	public TokenLibraries currentTokenLibraries();

	/* get current lexeme */
	public String currentLexeme();

	/* check state is successful */
	public boolean isSuccessful();

	/* manage all error */
	public String errorMessages();

	/* check Exhausted */
	public boolean isExhausted();
}
