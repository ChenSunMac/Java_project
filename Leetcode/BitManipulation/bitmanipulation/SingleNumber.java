package bitmanipulation;

import java.util.Arrays;

/* Given an array of integers, every element appears twice except for one. 
 * Find that single one.
 */

public class SingleNumber {
	
	public static void main(String[] args)
    {	
		int[] test_array = {3,1,2,2,1}; // must be unsigned number
		System.out.println( Arrays.toString(test_array) + " has single number " +  SingleNumber_finder(test_array));
    }
	
	public static int SingleNumber_finder(int[] A){
		int xor = 0;
		for (int i = 0; i < A.length; i++){
			xor = xor^A[i];
			
		}
		return xor;
	}
}
