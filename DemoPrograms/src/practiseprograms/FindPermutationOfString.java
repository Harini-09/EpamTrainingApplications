package practiseprograms;

import java.util.ArrayList;
import java.util.List;

public class FindPermutationOfString {
	 public static void main(String[] args) {
	        String str = "abc";
	        List<String> permutations = new ArrayList<>();
	        findPermutations(str.toCharArray(), 0, permutations);
	        for (String permutation : permutations) {
	            System.out.println(permutation);
	        }
	    }


	    private static void findPermutations(char[] arr, int index, List<String> permutations) {
	        if (index == arr.length - 1) {
	            permutations.add(String.valueOf(arr));
	        } else {
	            for (int i = index; i < arr.length; i++) {
	                swap(arr, index, i);
	                findPermutations(arr, index + 1, permutations);
	                swap(arr, index, i); 
	            }
	        }
	    }

	    private static void swap(char[] arr, int i, int j) {
	        char temp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = temp;
	    }
}
