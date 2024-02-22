package com.epam.decorator;

public class Main {
	public static void main(String[] args) {
		WebPage webPage = new MobileVersion();
		WebPage widget1 = new NavigationWidget(webPage);
		WebPage widget2 = new InformationWidget(widget1);
		
		System.out.println("Total Rank of a web Page is : "+widget2.calculateRank());

		
	}
}
