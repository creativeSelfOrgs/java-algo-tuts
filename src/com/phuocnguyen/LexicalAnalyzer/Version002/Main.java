package com.phuocnguyen.LexicalAnalyzer.Version002;

public class Main {

	public static void main(String[] args) {
		LexerServiceImplement lexerServiceImplement = new LexerServiceImplement();
		/*
		 * input demo for the first loading into program
		 */
		String input = "Test01.txt";
		String output = "Out01.txt";
		/* get input from command line */
		if (args.length > 1) {
			input = args[0];
			output = args[1];
		}
		lexerServiceImplement.LexerOutPutService(input, output);
		/*
		 * set of input
		 */
		String[] inputs = { "Test01.txt", "Test02.txt", "Test03.txt", "Test04.txt" };
		String[] outputs = { "Out01.txt", "Out02.txt", "Out03.txt", "Out04.txt" };

		/*
		 * loop for each elements input
		 */
		for (int i = 0; i < inputs.length; i++) {
			for (int j = i; j < outputs.length; j++) {
				lexerServiceImplement.LexerOutPutService(inputs[i], outputs[j]);
			}
		}

	}

}
