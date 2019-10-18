package com.phuocnguyen.LexicalAnalyzer.Version003;

public class MainLexeme {

	public static void main(String[] args) {
		LexerServiceImplement lexerServiceImplement = new LexerServiceImplement();
		lexerServiceImplement.readInput("Test01.txt");
		while (!lexerServiceImplement.isExhausted()) {
			System.out.println(
					lexerServiceImplement.currentLexeme() + " " + lexerServiceImplement.currentTokenLibraries());
			lexerServiceImplement.move();
		}
		if (lexerServiceImplement.isSuccessful()) {
			System.out.println("\nDone");
		} else {
			System.out.println(lexerServiceImplement.errorMessages());
		}
	}

}
