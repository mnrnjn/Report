package com.report;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Sample {

	public static void main(String[] args) {
		String[] myStringArray = {"{}","{}","{}"};
		System.out.println(isValid(myStringArray)[2]);
	}
	public static String[] isValid(String[] s) {
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
	 
		Stack<Character> stack = new Stack<Character>();
	 
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
			char curr = s[i].charAt(s[i].length() - 1);
	 
			if (map.keySet().contains(curr)) {
				System.out.println(map.keySet());
				stack.push(curr);
			} else if (map.values().contains(curr)) {
				if (!stack.empty() && map.get(stack.peek()) == curr) {
					System.out.println(">>>>>"+map.get(stack.peek()));
					stack.pop();
				} else {
					return "NO".split("");
				}
			}
		}
	 
		if(stack.empty())
			return "YES".split("");
		return s; 
	}
}
