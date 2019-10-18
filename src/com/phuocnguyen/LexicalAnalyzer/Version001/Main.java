package com.phuocnguyen.LexicalAnalyzer.Version001;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		MethodsImplement methodsImplement = new MethodsImplement();
		try {
			methodsImplement.streamToken("Test01.txt", "Out01.txt");
			methodsImplement.streamToken("Test02.txt", "Out02.txt");
			methodsImplement.streamToken("Test03.txt", "Out03.txt");
			methodsImplement.streamToken("Test04.txt", "Out04.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
