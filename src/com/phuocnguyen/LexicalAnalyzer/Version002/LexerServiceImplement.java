package com.phuocnguyen.LexicalAnalyzer.Version002;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LexerServiceImplement implements LexerService {
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private Token token;
	private static final char EOF = (char) (-1);
	/* The current character being scanned */
	private char current;

	/*
	 * Declared contractor
	 */
	public LexerServiceImplement() {

	}

	/* Read file input */
	public LexerServiceImplement(String name_file) {
		try {
			bufferedReader = new BufferedReader(new FileReader(name_file));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		/* Read the first character */
		current = read();
	}

	@Override
	public char read() {
		try {
			return (char) (bufferedReader.read());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return EOF;
		}
	}

	@Override
	public boolean isNumeric(char character) {
		if (character >= '0' && character <= '9')
			return true;

		return false;
	}

	@Override
	public boolean isAlpha(char character) {
		if (character >= 'a' && character <= 'z')
			return true;
		if (character >= 'A' && character <= 'Z')
			return true;

		return false;
	}

	@Override
	public Token nextToken() {
		int state = 1; /* Initial state */
		int numBuffer = 0; /* A buffer for number literals */
		String alphaBuffer = "";
		int decBuffer = 0;
		boolean skipped = false;
		while (true) {
			if (current == EOF && !skipped) {
				skipped = true;
			} else if (skipped) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				return null;
			}
			switch (state) {
			case 1:
				switch (current) {
				case ' ':
				case '\n':
				case '\b':
				case '\f':
				case '\r':
				case '\t':
					current = read();
					continue;
				case ';':
					current = read();
					return new Token(";", "SEMInumber");
				case ':':
					current = read();
					return new Token(":", "COLONnumber");
				case '.':
					current = read();
					return new Token(".", "DOTnumber");
				case '<':
					current = read();
					return new Token("<", "LTnumber");
				case '>':
					current = read();
					return new Token(">", "GTnumber");
				case '+':
					current = read();
					return new Token("+", "PLUSnumber");

				case '-':
					current = read();
					return new Token("-", "MINUSnumber");

				case '*':
					current = read();
					return new Token("*", "TIMESnumber");
				case '/':
					current = read();
					state = 14;
					continue;
				case ',':
					current = read();
					return new Token(",", "COMMMAnumber");
				case '(':
					current = read();
					return new Token("(", "LPARENnumber");
				case ')':
					current = read();
					return new Token(")", "RPARENnumber");
				case '{':
					current = read();
					return new Token("{", "LBnumber");
				case '}':
					current = read();
					return new Token("}", "RBnumber");
				case '%':
					current = read();
					return new Token("%", "MDnumber");
				case '=':
					current = read();
					state = 8;
					continue;
				case '!':
					current = read();
					state = 9;
					continue;
				case '&':
					current = read();
					state = 10;
					continue;
				case '|':
					current = read();
					state = 11;
					continue;
				case '"':
					current = read();
					state = 13;
					alphaBuffer = "";
					continue;
				default:
					state = 2;
					continue;
				}

				/* Integer - Start */
			case 2:
				if (isNumeric(current)) {
					numBuffer = 0;
					numBuffer += (current - '0');
					state = 3;
					current = read();
				} else {
					/* does not start with number or symbol go to case 5 */
					state = 5;
				}
				continue;

			/* Integer - Body */
			case 3:
				if (isNumeric(current)) {
					numBuffer *= 10;
					numBuffer += (current - '0');
					current = read();
				} else if (current == '.') {
					current = read();
					/* Has decimal point go to case 4 */
					state = 4;
				} else {
					return new Token(numBuffer + " ", "ICONSTnumber");
				}
				continue;
			/* Decimal - start */
			case 4:
				if (isNumeric(current)) {
					decBuffer = 0;
					decBuffer += (current - '0');
					state = 7;
					current = read();
				} else {
					return new Token("ERROR", "Invalid input: " + numBuffer + ".");
				}
				continue;
			/* Decimal - body */
			case 7:
				if (isNumeric(current)) {
					decBuffer *= 10;
					decBuffer += (current - '0');
					current = read();
				} else {
					return new Token(numBuffer + "." + decBuffer, "DCONSTnumber");
				}
				continue;

			/* Identifier - start */
			case 5:
				if (isAlpha(current) || current == '_') {
					alphaBuffer = "";
					alphaBuffer += current;
					state = 6;
					current = read();
				} else {
					alphaBuffer = "";
					alphaBuffer += current;
					current = read();
					return new Token("ERROR", "Invalid input:" + alphaBuffer + ".");
				}
				continue;

			/* Identifier - Body */
			case 6:
				if ((isAlpha(current) || isNumeric(current) || current == '_')) {

					alphaBuffer += current;
					current = read();

				} else {

					if (alphaBuffer.equals("class")) {
						return new Token(alphaBuffer, "CLASSnumber");

					} else if (alphaBuffer.equals("and")) {
						return new Token(alphaBuffer, "ANDnumber");
					} else if (alphaBuffer.equals("array")) {
						return new Token(alphaBuffer, "ARRAYnumber");
					} else if (alphaBuffer.equals("begin")) {
						return new Token(alphaBuffer, "BEGINnumber");
					} else if (alphaBuffer.equals("constant")) {
						return new Token(alphaBuffer, "CONSTnumber");
					} else if (alphaBuffer.equals("div")) {
						return new Token(alphaBuffer, "DIVnumber");
					} else if (alphaBuffer.equals("downto")) {
						return new Token(alphaBuffer, "DOWNTOnumber");
					} else if (alphaBuffer.equals("else")) {
						return new Token(alphaBuffer, "ELSEnumber");
					} else if (alphaBuffer.equals("elsif")) {
						return new Token(alphaBuffer, "ELSIFnumber");
					} else if (alphaBuffer.equals("end")) {
						return new Token(alphaBuffer, "ENDnumber");
					} else if (alphaBuffer.equals("endif")) {
						return new Token(alphaBuffer, "ENDIFnumber");
					} else if (alphaBuffer.equals("endloop")) {
						return new Token(alphaBuffer, "ENDLOOPnumber");
					} else if (alphaBuffer.equals("endrec")) {
						return new Token(alphaBuffer, "ENDRECnumber");
					} else if (alphaBuffer.equals("exit")) {
						return new Token(alphaBuffer, "EXITnumber");
					} else if (alphaBuffer.equals("endrec")) {
						return new Token(alphaBuffer, "ENDRECnumber");
					} else if (alphaBuffer.equals("for")) {
						return new Token(alphaBuffer, "FORnumber");
					} else if (alphaBuffer.equals("forward")) {
						return new Token(alphaBuffer, "FORWARDnumber");
					} else if (alphaBuffer.equals("function")) {
						return new Token(alphaBuffer, "FUNCTIONnumber");
					} else if (alphaBuffer.equals("if")) {
						return new Token(alphaBuffer, "IFnumber");
					} else if (alphaBuffer.equals("is")) {
						return new Token(alphaBuffer, "ISnumber");
					} else if (alphaBuffer.equals("loop")) {
						return new Token(alphaBuffer, "LOOPnumber");
					} else if (alphaBuffer.equals("not")) {
						return new Token(alphaBuffer, "NOTnumber");
					} else if (alphaBuffer.equals("of")) {
						return new Token(alphaBuffer, "OFnumber");
					} else if (alphaBuffer.equals("or")) {
						return new Token(alphaBuffer, "ORnumber");
					} else if (alphaBuffer.equals("procedure")) {
						return new Token(alphaBuffer, "PROCEDUREnumber");
					} else if (alphaBuffer.equals("program")) {
						return new Token(alphaBuffer, "PROGRAMnumber");
					} else if (alphaBuffer.equals("record")) {
						return new Token(alphaBuffer, "RECORDnumber");
					} else if (alphaBuffer.equals("repeat")) {
						return new Token(alphaBuffer, "REPEATnumber");
					} else if (alphaBuffer.equals("return")) {
						return new Token(alphaBuffer, "RETURNnumber");
					} else if (alphaBuffer.equals("then")) {
						return new Token(alphaBuffer, "THENnumber");
					} else if (alphaBuffer.equals("to")) {
						return new Token(alphaBuffer, "TOnumber");
					} else if (alphaBuffer.equals("type")) {
						return new Token(alphaBuffer, "TYPEnumber");
					} else if (alphaBuffer.equals("until")) {
						return new Token(alphaBuffer, "UNTILnumber");
					} else if (alphaBuffer.equals("var")) {
						return new Token(alphaBuffer, "VARnumber");
					} else if (alphaBuffer.equals("while")) {
						return new Token(alphaBuffer, "WHILEnumber");
					} /* another case */else if (alphaBuffer.equals("static")) {
						return new Token(alphaBuffer, "STATICnumber");
					} else if (alphaBuffer.equals("do")) {
						return new Token(alphaBuffer, "DOnumber");
					} else if (alphaBuffer.equals("begin")) {
						return new Token(alphaBuffer, "BEGINnumber");
					} else if (alphaBuffer.equals("public")) {
						return new Token(alphaBuffer, "PUBLICnumber");
					} else if (alphaBuffer.equals(":=")) {
						return new Token(alphaBuffer, "COLEQnumber");
					}

					return new Token(alphaBuffer, "IDnumber");
				}
				continue;

			// if ==
			case 8:
				if (current == '=') {
					current = read();
					return new Token("==", "DEQnumber");
				} else if (current != '=') {
					current = read();
					return new Token("=", "EQnumber");
				} else {
					current = read();
					return new Token(":=", "COLEQnumber");
				}

				// if !=
			case 9:
				if (current == '=') {
					current = read();
					return new Token("!=", "NEnumber");
				} else {
					return new Token("ERROR", "Invalid input: !");
				}

				// if &&
			case 10:
				if (current == '&') {
					current = read();
					return new Token("&&", "LAnumber");
				} else {
					return new Token("ERROR", "Invalid input: &");
				}
				// if ||
			case 11:
				if (current == '|') {
					current = read();
					return new Token("||", "LOnumber");
				} else {
					return new Token("ERROR", "Invalid input: |");
				}

			case 13:
				if (current == '"') {
					current = read();
					return new Token("\"" + alphaBuffer + "\"", "STnumber");
				} else if (current == '\n' || current == EOF) {
					current = read();
					return new Token("ERROR", "Invalid string literal");
				} else {
					alphaBuffer += current;
					current = read();
				}
				continue;

			case 14:
				if (current == '/') {
					state = 15;
					current = read();
				} else if (current == '*') {
					state = 16;
					current = read();
				} else {
					return new Token("/", "DVnumber");
				}
				continue;
			case 15:
				if (current == '\n') {

					state = 1;
				}
				current = read();
				continue;
			case 16:
				if (current == '*') {
					state = 17;

				}
				current = read();
				continue;
			case 17:
				if (current == '/') {
					current = read();
					state = 1;
				} else {
					current = read();
					state = 16;
				}
				continue;
			/*---------------------*/
			}
		}
	}

	@Override
	public void LexerOutPutService(String input, String output) {
		/* read input */
		LexerServiceImplement lexerServiceImplement = new LexerServiceImplement(input);

		try {
			bufferedWriter = new BufferedWriter(new FileWriter(output));
			while ((token = lexerServiceImplement.nextToken()) != null) {
				bufferedWriter.write(token.toString());
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			System.out.println("Done tokenizing file: " + input);
			System.out.println("Output written in file: " + output);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
