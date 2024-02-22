package com.epam.decorator;

public  class WebPageWidgetsDecorator implements WebPage{
	
	protected WebPage webPage;
	public WebPageWidgetsDecorator(WebPage webPage){
		this.webPage = webPage;
	}
	
	@Override
	public Integer calculateRank() {
		// TODO Auto-generated method stub
		
		return webPage.calculateRank();
	}
	

}
