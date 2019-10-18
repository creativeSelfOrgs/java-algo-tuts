package com.phuocnguyen.InfixToPostfix.Version002;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;

public class MyEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	public double calculateExpressionPostfix(String input_Postfix) {
		int getSize = 0;
		double result = 0;
		char word;
		double no_One;
		double no_Two;
		/*
		 *
		 */
		input_Postfix = input_Postfix.trim();
		input_Postfix = input_Postfix.replaceAll("\\s+", " ");
		getSize = input_Postfix.length();
		/*
		 * declared new Stack
		 */
		MyStack myStack = new MyStack(getSize);
		for (int i = 0; i < getSize; i++) {
			word = input_Postfix.charAt(i); /* get each character */
			if (word >= '0' && word <= '9') {
				myStack.push(word - '0');
			} else {
				no_One = myStack.pop();
				no_Two = myStack.pop();
				switch (word) {
				case '+': {
					result = no_One + no_Two;
					break;
				}
				case '-': {
					result = -no_One + no_Two;
					break;
				}
				case '*': {
					result = no_One * no_Two;
					break;
				}
				case '/': {
					result = no_Two / no_One;
					break;
				}
				default: {
					result = 0;
					break;
				}

				}
				myStack.push((int) (result));
			}
		}
		result = myStack.pop();
		return (int) result;

	}

	public String getInput() throws IOException {
		DataInputStream dataInputStream = new DataInputStream(System.in);
		@SuppressWarnings("deprecation")
		String sampleString = dataInputStream.readLine();
		return sampleString;
	}
}
