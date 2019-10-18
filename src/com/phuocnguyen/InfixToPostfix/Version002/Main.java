package com.phuocnguyen.InfixToPostfix.Version002;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		MyEvaluation myEvaluation = new MyEvaluation();
		try {
			System.out.println("Enter your post-fix string: ");
			String input = myEvaluation.getInput();
			System.out.println(myEvaluation.calculateExpressionPostfix(input));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
