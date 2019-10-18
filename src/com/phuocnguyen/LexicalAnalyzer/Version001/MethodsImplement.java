package com.phuocnguyen.LexicalAnalyzer.Version001;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MethodsImplement implements Methods {

	@Override
	public List<String> readFileText(String fileName) throws IOException {
		/*
		 * open thread to read file input
		 */
		File file = new File(fileName);
		FileInputStream fileInputStream = new FileInputStream(file);
		/*
		 * declared ArrayList to save all words from file text
		 */
		List<String> listString = new ArrayList<String>();
		/*
		 * initial two variables, to hand in file and convert characters into string
		 */
		int c = fileInputStream.read();
		String stringValuesOf = null;
		/*
		 * processing read file
		 */
		while (c != -1) {
			/* convert char into string */
			stringValuesOf = String.valueOf((char) c);
			/* add string into list data */
			listString.add(stringValuesOf);
			/* continue read until the end of line */
			c = fileInputStream.read();
		}
		/*
		 * close thread
		 */
		fileInputStream.close();
		return listString;
	}

	@Override
	public void writeToFileUsingBufferWriter(String name_file, String contents) throws IOException {
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		fileWriter = new FileWriter(name_file, true);
		bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(String.valueOf(contents));
		bufferedWriter.write(String.valueOf("\n"));
		bufferedWriter.close();
		fileWriter.close();
	}

	@Override
	public void streamToken(String path, String output) throws IOException {
		/*
		 * open thread to read file input
		 */
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);
		/*
		 * declared array list to save all words
		 */
		List<String> subListString = new ArrayList<String>();
		List<Integer> subListNumber = new ArrayList<Integer>();
		List<String> subListOther = new ArrayList<String>();
		String out;
		/*
		 * open thread stream Token
		 */
		@SuppressWarnings({ "deprecation" })
		StreamTokenizer streamTokenizer = new StreamTokenizer(fileInputStream);
		streamTokenizer.ordinaryChars('.', '.');
		/*
		 * declared a variable to run in while, check token
		 */
		int characters;
		while ((characters = streamTokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
			switch (characters) {
			/* if characters is word */
			case StreamTokenizer.TT_WORD: {
				/* add words into list */
				subListString.add(streamTokenizer.sval);
				break;
			}
			/* if characters is number */
			case StreamTokenizer.TT_NUMBER: {
				/* add numbers into list */
				subListNumber.add((int) streamTokenizer.nval);
			}
			default: {
				/* convert from char into string and save to list */
				subListOther.add(String.valueOf((char) characters));
				/* System.out.println((char) characters); */
			}
			}
		}
		fileInputStream.close();

		/* Handling */
		/*
		 * for token is words: 33
		 */
		for (String subString : subListString) {
			if (subString.equalsIgnoreCase("and")) {
				out = subString + " " + "ANDnumber";
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("array")) {
				out = (subString + " " + "ARRAYnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("begin")) {
				out = (subString + " " + "BEGINnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("constant")) {
				out = (subString + " " + "CONSTnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("div")) {
				out = (subString + " " + "DIVnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("downto")) {
				out = (subString + " " + "DOWNTOnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("else")) {
				out = (subString + " " + "ELSEnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("elseif")) {
				out = (subString + " " + "ELSIFnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("end")) {
				out = (subString + " " + "ENDnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("endif")) {
				out = (subString + " " + "ENDIFnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("endloop")) {
				out = (subString + " " + "ENDLOOPnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("endrec")) {
				out = (subString + " " + "ENDRECnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("exit")) {
				out = (subString + " " + "EXITnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("for")) {
				out = (subString + " " + "FORnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("forward")) {
				out = (subString + " " + "FORWARDnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("function")) {
				out = (subString + " " + "FUNCTIONnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("if")) {
				out = (subString + " " + "IFnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("is")) {
				out = (subString + " " + "ISnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("loop")) {
				out = (subString + " " + "LOOPnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("not")) {
				out = (subString + " " + "NOTnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("of")) {
				out = (subString + " " + "OFnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("or")) {
				out = (subString + " " + "ORnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("procedure")) {
				out = (subString + " " + "PROCEDUREnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("program")) {
				out = (subString + " " + "PROGRAMnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("record")) {
				out = (subString + " " + "RECORDnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("repeat")) {
				out = (subString + " " + "REPEATnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("return")) {
				out = (subString + " " + "RETURNnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("then")) {
				out = (subString + " " + "THENnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("to")) {
				out = (subString + " " + "TOnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("type")) {
				out = (subString + " " + "TYPEnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("until")) {
				out = (subString + " " + "UNTILnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("var")) {
				out = (subString + " " + "VARnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (subString.equalsIgnoreCase("while")) {
				out = (subString + " " + "WHILEnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (isMatchesIdentifier(subString)) {
				out = (subString + " " + "IDnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (isMatchesStringConstant(subString)) {
				out = (subString + " " + "SCONSTnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (isMatchesEOF(subString)) {
				out = (subString + " " + "EOFnumber");
				writeToFileUsingBufferWriter(output, out);
			} else {
				out = (subString + " " + "ERRORnumber");
				writeToFileUsingBufferWriter(output, out);
			}

		}

		/*
		 * for token is special characters: 17
		 */
		System.out.println();
		for (String otherCharacter : subListOther) {
			if (otherCharacter.equals(";")) {
				out = (otherCharacter + " " + "SEMInumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(":")) {
				out = (otherCharacter + " " + "COLONnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(",")) {
				out = (otherCharacter + " " + "COMMMAnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(".")) {
				out = (otherCharacter + " " + "DOTnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("(")) {
				out = (otherCharacter + " " + "LPARENnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(")")) {
				out = (otherCharacter + " " + "RPARENnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("<")) {
				out = (otherCharacter + " " + "LTnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(">")) {
				out = (otherCharacter + " " + "GTnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("=")) {
				out = (otherCharacter + " " + "EQnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("-")) {
				out = (otherCharacter + " " + "MINUSnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("+")) {
				out = (otherCharacter + " " + "PLUSnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("*")) {
				out = (otherCharacter + " " + "TIMESnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(" .. ")) {
				out = (otherCharacter + " " + "DOTDOTnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(" := ")) {
				out = (otherCharacter + " " + "COLEQnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("<=")) {
				out = (otherCharacter + " " + "LEnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals(">=")) {
				out = (otherCharacter + " " + "GEnumber");
				writeToFileUsingBufferWriter(output, out);
			} else if (otherCharacter.equals("<>")) {
				out = (otherCharacter + " " + "NEnumber");
				writeToFileUsingBufferWriter(output, out);
			} 
		}

		/*
		 * for token is numbers
		 */
		System.out.println();
		for (int number : subListNumber) {
			if (isMatchesIntegerConstant(String.valueOf(number))) {
				out = number + " " + "ICONSTnumber";
				writeToFileUsingBufferWriter(output, out);
			}
		}
	}

	@Override
	public boolean isMatchesIdentifier(String pattern) {
		String matches = "(?:\\b[_a-zA-Z]|\\B\\$)[_$a-zA-Z0-9]*+";
		boolean check = Pattern.matches(matches, pattern);
		return check;
	}

	@Override
	public boolean isMatchesIntegerConstant(String pattern) {
		String matches = "[+-]?[0-9][0-9]*";
		boolean check = Pattern.matches(matches, pattern);
		return check;
	}

	@Override
	public boolean isMatchesCharConstant(String pattern) {
		String matches = "[$&+,:;=\\\\?@#|/'<>.^*()%!-]";
		boolean check = Pattern.matches(matches, pattern);
		return check;
	}

	@Override
	public boolean isMatchesStringConstant(String pattern) {
		String matches = "\"\\(?:[^\\\"]\\|\\\\\\(?:.\\|[\n]\\)\\)*\"";
		boolean check = Pattern.matches(matches, pattern);
		return check;
	}

	@Override
	public boolean isMatchesEOF(String pattern) {
		String matches = "/\\\"(.+?)\\\".+?(\\w.+?)$/";
		boolean check = Pattern.matches(matches, pattern);
		return check;
	}

}
