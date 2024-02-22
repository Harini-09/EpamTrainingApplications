package com.epam.decorator;

public class MobileVersion implements WebPage{
	private int pageRank = 10;
	@Override
	public Integer calculateRank() {
		return pageRank;
	}

	
	

}
