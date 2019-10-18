package com.phuocnguyen.InfixToPostfix.Version001;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MethodInfixToPostfixServiceImplement methodInfixToPostfixServiceImplement = new MethodInfixToPostfixServiceImplement();
		String input;
		String[] elements = null;
		String result = null;
		System.out.println("Enter args: ");
		input = scanner.nextLine();
		elements = methodInfixToPostfixServiceImplement.handling_String(input);
		elements = methodInfixToPostfixServiceImplement.postfix_Service(elements);

		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i] + " ");

		}
		result = methodInfixToPostfixServiceImplement.valueOfPostfix(elements);
		System.out.println(result);

		scanner.close();
	}

}
