package com.epam.decorator;

public class DesktopVersion implements WebPage {
	private int pageRank = 20;
	@Override
	public Integer calculateRank() {
		return pageRank;
	}
	


}
