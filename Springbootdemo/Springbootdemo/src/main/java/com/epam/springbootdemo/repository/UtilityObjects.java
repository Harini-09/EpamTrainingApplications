package com.epam.springbootdemo.repository;

import java.util.Scanner;

public class UtilityObjects {
private UtilityObjects() {
		super();
	}
private static final Scanner sc=new Scanner(System.in);
public static Scanner getScanner() {
	return sc;
}
}
