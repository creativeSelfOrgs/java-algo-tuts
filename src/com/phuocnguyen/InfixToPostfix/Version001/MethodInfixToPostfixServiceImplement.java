package com.phuocnguyen.InfixToPostfix.Version001;

import java.util.Arrays;
import java.util.Stack;

public class MethodInfixToPostfixServiceImplement implements MethodInfixToPostfixService {

	@Override
	public boolean isOperator(char character) {

		char operator[] = { '+', '-', '*', '/', '^', ')', '(' };
		/*
		 * sort all elements in arrays
		 */
		Arrays.sort(operator);

		if (Arrays.binarySearch(operator, character) > -1) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * You also have to match the consecutive digits into numbers (operands),
	 * separate operators, separated by a space. These elements I will call a token.
	 */

	@Override
	public String[] handling_String(String sample) {
		String string_Sample = "";
		String elementsMath[] = null;
		char character;
		/*
		 * Standardized String
		 */
		sample = sample.trim();
		sample = sample.replaceAll("\\s+", " ");

		for (int i = 0; i < sample.length(); i++) {
			character = sample.charAt(i); /* sample.substring(i,1); */
			if (!isOperator(character)) {
				string_Sample += character;
			} else {
				string_Sample = string_Sample + " " + character + " ";
			}
		}
		string_Sample = string_Sample.trim();
		string_Sample = string_Sample.replaceAll("\\s+", " ");
		elementsMath = string_Sample.split(" ");
		return elementsMath;
	}

	/*
	 * Add (+), subtract (-), multiply (*), divide (/). Accordingly the operators
	 * "*, /" have the same priority and are higher than the "+, -" operators.
	 */

	@Override
	public int priority_Operator(char character) {
		if (character == '+' || character == '-') {
			return 1;
		} else if (character == '*' || character == '/') {
			return 2;
		} else if (character == '^') {

			return 3;
		}
		return 0;
	}

	/*
	 * Read each token in the infix expression from left to right, with each token
	 * performing the following steps: - If it is operand: output. - If it is an
	 * opening sign, "(": on the stack - If the parenthesis ")": get the operators
	 * in the stack and put them into the output until the parentheses mark is
	 * reached "(". - If the operator: + / As long as at the top of the stack is the
	 * operator and that operator has a higher priority or equal to the current
	 * operator, then take that operator out of the stack and output it. + / Put the
	 * current operator on the stack After reviewing all infix expressions, if the
	 * element is in the stack and the element is in, take the tokens in it and turn
	 * it into output.
	 */

	@Override
	public String[] postfix_Service(String[] elementsMath) {
		Stack<String> stack = new Stack<String>();
		String sample = "";
		String subset[]; /* return to output */
		char character;
		char sub_character;
		/*
		 * browse elements
		 */
		for (int i = 0; i < elementsMath.length; i++) {
			/* character is the first character of each element */
			character = elementsMath[i].charAt(0);
			/* if character is not an operator */
			if (!isOperator(character)) {
				/* export the element to sample */
				sample = sample + " " + elementsMath[i];
			} else {/* character is the operator */

				if (character == '(') {/* character is "(" -> element array on Stack */
					stack.push(elementsMath[i]);
				} else {
					if (character == ')') {/* character is ")" */
						/* Review the elements in Stack */
						do {
							sub_character = stack.peek().charAt(0); /* sub_character is the first part of the element */
							if (sub_character != '(') {
								sample = sample + " " + stack.peek(); /* while sub_character != "(" */

							}
							stack.pop();
						} while (sub_character != '(');
					} else {
						while (!stack.isEmpty()
								&& priority_Operator(stack.peek().charAt(0)) >= priority_Operator(character)) {
							/*
							 * Stack is not wide and while the element in Stack has priority> = current
							 * molecule
							 */
							sample = sample + " " + stack.peek(); /* export the element in Stack out sample */
							stack.pop();
						}
						/* put current element into Stack */
						stack.push(elementsMath[i]);
					}
				}
			}
		}
		/* If Stack still has the element, push it all into sample */
		while (!stack.isEmpty()) {
			sample = sample + " " + stack.peek();
			stack.pop();
		}
		subset = sample.split(" ");
		return subset;
	}

	/*
	 * Loop through the tokens of the post-fix expression from left to right: - If
	 * it is operand: push on stack - If it is an operator: pop two operands in the
	 * stack and calculate their value based on this operator. Push that result back
	 * to the stack. The remaining element in the stack after the loop is the result
	 * of the expression.
	 */
	@Override
	public String valueOfPostfix(String elementMath[]) {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < elementMath.length; i++) {
			char character = elementMath[i].charAt(i);
			if (!isOperator(character)) {
				stack.push(elementMath[i]);
			} else {
				double num = 0f;
				double num1 = Float.parseFloat(stack.pop());
				double num2 = Float.parseFloat(stack.pop());
				switch (character) {
				case '+':
					num = num2 + num1;
					break;
				case '-':
					num = num2 - num1;
					break;
				case '*':
					num = num2 * num1;
					break;
				case '/':
					num = num2 / num1;
					break;
				default:
					break;
				}
				stack.push(Double.toString(num));
			}
		}
		return stack.pop();
	}
}
