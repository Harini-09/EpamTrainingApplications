package ConsumerAndSupplier;

public class Main {
	public static void main(String[] args) {

		Utility1 utility1 = new Utility1();
		utility1.setGradeIfPriceGreaterThan1000();
		utility1.setPremiumIfPriceGreaterThan3000();
		utility1.printPremiumProductsSuffixed();
		
		Utility2 utility2 = new Utility2();
		utility2.printRandomProduct();
		utility2.getRandomOTP();
	}
}
