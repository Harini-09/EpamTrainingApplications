package Java8demo;

@FunctionalInterface
interface Calculator{
	//public void printResult();
	//public void printResult(int input);
	public int printResult(int i1,int i2);
}



public class LambdaDemo {
	
	public static void main(String[] args) {
//		Calculator calculator = new Calculator() {
//			public void printResult() {
//				System.out.println("Result");
//			}
//		};
		
		// () -> {body}
//		Calculator calculator = () -> System.out.println("Result");
//			
//		calculator.printResult();
		
		
//		Calculator calculator = (input) -> System.out.println(input);
//		
//		calculator.printResult(12);
		
		
		Calculator calculator = (i1,i2) -> i2-i1;
		
		System.out.println(calculator.printResult(12, 15));
		
	}
	
}
