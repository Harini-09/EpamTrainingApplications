package com.epam.decorator;

public class NavigationWidget extends WebPageWidgetsDecorator {
	private int widgetRank = 5;
	public NavigationWidget(WebPage webPage) {
		super(webPage);
		
	}

	@Override
	public Integer calculateRank() {
		// TODO Auto-generated method stub
		return super.calculateRank()+widgetRank;
	}

}
