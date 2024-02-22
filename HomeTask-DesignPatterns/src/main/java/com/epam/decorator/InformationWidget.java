package com.epam.decorator;

public class InformationWidget extends WebPageWidgetsDecorator {
		private int widgetRank = 7;
		public InformationWidget(WebPage webPage) {
			super(webPage);
			
		}

		@Override
		public Integer calculateRank() {
			// TODO Auto-generated method stub
			return super.calculateRank()+widgetRank;
		}

	}
