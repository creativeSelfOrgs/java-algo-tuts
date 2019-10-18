package com.phuocnguyen.InfixToPostfix.Version001;

public interface MethodInfixToPostfixService {

	/* Priority of operators */
	public int priority_Operator(char character);

	/* check operator and operand */
	public boolean isOperator(char character);

	/* Standardize Infix expressions before conversion */
	public String[] handling_String(String sample);

	/* Algorithm to convert an Infix expression to Prefix format */
	public String[] postfix_Service(String[] elementsMath);

	/* To find value of post-fix */
	public String valueOfPostfix(String elements[]);
}
