package lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

//import FunctionalInterface2.Product;

public class Fp {

	public static void main(String[] args) {
		//Normal method
//		NotificationSender sms1 = new SMSSenderV1();
//		sms1.sendNotification();
//		
//		NotificationSender sms2 = new SMSSenderV2();
//		sms2.sendNotification();
		
		
		//Anonymous class
//		NotificationSender sms1 = new SMSSenderV1() {
//			public void sendNotification() {
//			System.out.println("SMS notification is sent using V1");
//			}
//				// TODO Auto-generated method stub
//		};
//		
//		sms1.sendNotification();
		
		
		//lambda
		NotificationSender sms1 = () -> {System.out.println("SMS notification is sent using V1");
		};
		sms1.sendNotification();

		
	}

}
