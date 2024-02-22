package practiseprograms;

public class ReverseString {
	 public static void main(String[] args) {
	        String str = "Welcome";
	        StringBuilder reversedString = new StringBuilder();
	        for (int i = str.length() - 1; i >= 0; i--) {
	        	reversedString.append(str.charAt(i));
	        }
	        System.out.println(reversedString);
	    }
}
