package com.epam.builder;

public class Bicycle {
	private Boolean gears;
	private Boolean doubleStands;
	private Boolean doubleSeats;
	private Boolean carrier;
	private Boolean company;

	public static BicycleBuilder builder() {
		return new BicycleBuilder();
	}

	@Override
	public String toString() {
		return "Bicycle [gears=" + gears + ", doubleStands=" + doubleStands + ", doubleSeats=" + doubleSeats
				+ ", carrier=" + carrier + ", company=" + company + "]";
	}

	public static class BicycleBuilder {
		private Boolean gears;
		private Boolean doubleStands;
		private Boolean doubleSeats;
		private Boolean carrier;
		private Boolean company;

		public BicycleBuilder gears(boolean gears) {
			this.gears = gears;
			return this;
		}

		public BicycleBuilder doubleStands(boolean doubleStands) {
			this.doubleStands = doubleStands;
			return this;
		}

		public BicycleBuilder doubleSeats(boolean doubleSeats) {
			this.doubleSeats = doubleSeats;
			return this;
		}

		public BicycleBuilder carrier(boolean carrier) {
			this.carrier = carrier;
			return this;
		}

		public BicycleBuilder company(boolean company) {
			this.company = company;
			return this;
		}

		public Bicycle build() {
			if (company == false) {
				throw new RuntimeException("Company need to be specified");
			} else {
				Bicycle bicycle = new Bicycle();
				bicycle.gears = gears;
				bicycle.doubleStands = doubleStands;
				bicycle.doubleSeats = doubleSeats;
				bicycle.carrier = carrier;
				bicycle.company = company;
				return bicycle;
			}

		}

	}

}
