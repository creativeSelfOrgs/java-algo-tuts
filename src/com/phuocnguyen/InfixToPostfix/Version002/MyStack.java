package com.phuocnguyen.InfixToPostfix.Version002;

import java.io.Serializable;

public class MyStack implements Serializable {

	private static final long serialVersionUID = 1L;

	private int[] array;
	private int top;
	private int sub;

	public MyStack(int value) {
		sub = value;
		array = new int[sub];
		top = -1;

	}

	public void push(int key) {
		array[++top] = key;
	}

	public int pop() {
		return (array[top--]);
	}
}
