package com.epam.ryndych;

import java.util.Scanner;

public class MenuScanner {
	private static MenuScanner instance = null;
	private Scanner in = null;

	private MenuScanner() {
		in = new Scanner(System.in);
	}

	public int nextInt() {
		while (!in.hasNextInt()) {
			System.out.println("Incorrect input");
			in.next();
		}
		return in.nextInt();
	}

	public String nextString() {
		return in.next();
	}
	
	public String nextLine(){
		return in.nextLine();
	}

	public boolean hasNext(String str) {
		return in.hasNext(str);
	}

	public static synchronized MenuScanner getInstance() {
		if (instance == null) {
			instance = new MenuScanner();
		}
		return instance;
	}
}
