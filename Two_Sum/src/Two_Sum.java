/*
 * Given an array of integers, 
 * return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, 
 * and you may not use the same element twice.
 */

import java.util.HashMap;

public class Two_Sum {
	public static void main(String[] args) {
		Integer i = new Integer(6);
		String s = i.toString();//sets s the string representation of i
		

		System.out.println(i); // print out 2
		
		int i2 = 6;
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
	    	
	        Integer diff = (Integer)(target - numbers[i]);
	        if(hash.containsKey(diff)){
	            int toReturn[] = {hash.get(diff), i};
	            return toReturn;
	        }

	        hash.put(numbers[i], i);

	    }
	    
	    return null;
	    
	}
}


