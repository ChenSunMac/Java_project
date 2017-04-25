/*
 * Given an array of integers, 
 * return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, 
 * and you may not use the same element twice.
 * (1) array not ordered
 * (2) array ascending order
 */

import java.util.Arrays;
import java.util.HashMap;

public class Two_Sum {
	public static void main(String[] args) {
		int[] test_array = {1, 2, 3, 6};
		int[] result1 = two_sum_unordered(test_array, 7);
		int[] result2 = two_sum_ordered(test_array, 7);
		// in order to print the value of a array instead of the object itself, we use
		// Arrays.toString(...)
		System.out.print(Arrays.toString(result1));
		System.out.print(Arrays.toString(result2));		
	}
	
	
	/*
	 * Unodered input array: example:
	 * Given nums = [2, 7, 11, 15], target = 9, 
	 * Because nums[0] + nums[1] = 2 + 7 = 9,
	 * return [0, 1].
	 */
	public static int[] two_sum_unordered(int[] numbers, int target) {
	    // build a hashmap
	    HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
	    //  numbers.length provides the length of input array
	    for(int i = 0; i < numbers.length; i++){
	    	// diff is the number wanted
	        Integer diff = (Integer)(target - numbers[i]);
	        
	        // search in the hash table
	        if(hash.containsKey(diff)){
	            int toReturn[] = {hash.get(diff), i}; //hash.get(diff)
	            return toReturn;
	        }
	        // put in the table: <key> = number, <value> = index;
	        hash.put(numbers[i], i);
	    }
	    
	    return null;
	    
	}
	
	/*
	 * If the list is sorted in ascending order, we can use two pointer to scan the array
	 */
	public static int[] two_sum_ordered(int[] numbers, int target) {
	    // initialize the two pointers
		int l = 0, r = numbers.length - 1;
	    int[] toReturn = new int[2];
	    while (l != r) {
	    	if (numbers[l] + numbers[r] == target){
	    		toReturn[0] = l + 1;
	    		toReturn[1] = r + 1;
	    		break;
	    	}
	    	else if (numbers[l] + numbers[r] > target) 
	        	r--;
	        else 
	        	l++;
	    }

		return toReturn;
	}
}


