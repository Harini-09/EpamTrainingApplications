package com.epam.builder;

public class Bicycle {
	
	private boolean gears,double_stand,double_seat,carrier,mobile_holder,bottle_holder,helmet;
	private String colour;
	
	protected Bicycle() {
		
	}
	
	@Override
	public String toString() {
		return "Bicycle [gears=" + gears + ", double_stand=" + double_stand + ", double_seat=" + double_seat
				+ ", carrier=" + carrier + ", mobile_holder=" + mobile_holder + ", bottle_holder=" + bottle_holder
				+ ", helmet=" + helmet + ", colour=" + colour + "]";
	}

	static class BicycleBuilder {

		private boolean gears,double_stand,double_seat,carrier,mobile_holder,bottle_holder,helmet;
		private String colour;
		
		public static BicycleBuilder builder() {
			return new BicycleBuilder();
			
		}
		public BicycleBuilder gearsNeeded(boolean result) {
			this.gears = result;
			return this;
		}
		public BicycleBuilder doubleStandNeeded(boolean result) {
			this.double_stand = result;
			return this;
		}
		public BicycleBuilder doubleSeatNeeded(boolean result) {
			this.double_seat = result;
			return this;
		}
		public BicycleBuilder carrier(boolean result) {
			this.carrier = result;
			return this;
		}
		public BicycleBuilder mobileHolderNeeded(boolean result) {
			this.carrier = result;
			return this;
		}
		public BicycleBuilder bottleHolderNeeded(boolean result) {
			this.bottle_holder = result;
			return this;
		}
		public BicycleBuilder helmetNeeded(boolean result) {
			this.helmet = result;
			return this;
		}
		public BicycleBuilder whichColor(String colour) {
			this.colour = colour;
			return this;
		}
		
		public Bicycle build() {
			if(helmet == true) {
				Bicycle bicycle = new Bicycle();
				bicycle.bottle_holder = bottle_holder;
				bicycle.carrier = carrier;
				bicycle.colour = colour;
				bicycle.double_seat = double_seat;
				bicycle.gears = gears;
				bicycle.helmet = helmet;
				bicycle.mobile_holder = mobile_holder;
				bicycle.double_stand = double_stand;
				return bicycle;
			}
			else
				throw new RuntimeException("Helmet should needed.");
		}
		
	}

}
