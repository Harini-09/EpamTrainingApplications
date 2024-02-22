package Optional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class VendorApp {

    public static void main(String[] args) {
        /*
         * Code Task Will go here, Test your single lambda by calling all the below 5
         * methods to return products, no NullPointerException must be thrown in case of any issues
         * instead it can return empty map or useful message to a user in case of any null objects
         *
         */
    	
    	//Optional - Optional.ofNullable(), orElse, orElseGet, orElseThrow
    	
    	
    	//Vendor - who works for end client(who gives proj) Ex:employees,epam....products<-Client<-Vendor
    	
    	
    	
    	//System.out.println(vendor.getClient().getProduct().getProducts());
    	
    	  	
    	
//      		System.out.println(Optional.ofNullable(scenarioWithNoVendor())
//                .map(Vendor::getClient)			//client getVendor(vendor)
//                .map(Client::getProduct)			//product	getProduct(client)
//                .map(Product::getProducts)		//products  getProduct(product)
//                .orElseThrow(() -> new RuntimeException("No Products Found")));
//
//        /*Vendor vendor = scenarioWithAllObjectsPresent();
//        if(vendor != null){
//            Client client = vendor.getClient();
//            if(client != null){
//                Product product = client.getProduct();
//                if(product != null){
//                    Map<String, String> productsMap = product.getProducts();
//                    if(productsMap!= null && !productsMap.isEmpty()){
//                        System.out.println(productsMap);
//                    }else{
//                        throw new RuntimeException("No Product");
//                    }
//                }else{
//                    throw new RuntimeException("No Product");
//                }
//            }else{
//                throw new RuntimeException("No Product");
//            }
//        }else{
//            throw new RuntimeException("No Product");
//        }*/


    }

    private static Vendor scenarioWithAllObjectsPresent() {
        Map<String, String> productsMap = new HashMap<>();
        productsMap.put("P-011", "Ecommerce Portal");
        productsMap.put("P-012", "Ecommerce Admin Dashboard");
        productsMap.put("P-013", "Sales Portal");
        productsMap.put("P-014", "Logistics Portal");
        Product product = new Product(productsMap);
        Client client = new Client("Canadian-Tire Corporation", product);
        return new Vendor("EPAM Systems", client);
    }

    private static Vendor scenarioWithNoProductsMapForProduct() {
        Product product = new Product(null);
        Client client = new Client("Mastercard", product);
        return new Vendor("EPAM Systems", client);
    }

    private static Vendor scenarioWithNoProductForClient() {
        Client client = new Client("Mastercard", null);
        return new Vendor("EPAM Systems", client);
    }

    private static Vendor scenarioWithNoClientForVendor() {
        return new Vendor("EPAM Systems", null);
    }

    private static Vendor scenarioWithNoVendor() {
        return null;
    }
}
