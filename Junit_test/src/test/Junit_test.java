package test;

/*
 * Reverse String test
 * 1. String Builder .reverse(), .toString() -> return a string;
 * 		O(n) time and space
 * 2. transform the string to a list of characters and swap values;
 * 		O(n) time and space
 */

public class Junit_test {
	
	public static void main(String[] args) { 
		String test = "abcdfef";
		System.out.println(reverseString2(test));
		System.out.println(reverseString1(test));
	}
	
	public String concatenate(String s1, String s2){
		return s1 + s2;
	}
	
	public int multiply (int num1, int num2){
		return num1 * num2;
	}
	
	
	    public static String reverseString1(String s) {
	        return new StringBuilder(s).reverse().toString();
	    }
	    
	    public static String reverseString2(String s) {
	        char[] word = s.toCharArray();
	        int i = 0;
	        int j = s.length() - 1;
	        while (i < j) {
	            char temp = word[i];
	            word[i] = word[j];
	            word[j] = temp;
	            i++;
	            j--;
	        }
	        return new String(word);
	    }
	
}
