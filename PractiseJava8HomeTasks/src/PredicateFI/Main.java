package PredicateFI;

public class Main {
	public static void main(String[] args) {
		Utility utility = new Utility();
		utility.getProductsIfPriceGreaterThan1000();
		utility.getElectronicsProducts();
		utility.getElectronicsProductsGreaterThan1000();
		utility.getElectronicsProductsOrPriceGreaterThan1000();
		utility.getElectronicsProductsLessThan1000();
	}

}
