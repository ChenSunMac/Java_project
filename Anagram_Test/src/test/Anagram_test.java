package test;

import java.util.ArrayList;

/* Valid_Anagram
 * isAnagram():
 * 	Given two strings s and t, write a function to determine if t is an anagram of s.
 * 
 * anagrams():
 * 	input a String[] array, return a ArrayList<String> 
 * 
 * 
 */



import java.util.HashMap;

public class Anagram_test {

	
	public static void main(String[] args) { 
		String a = "abcdefg";
		String b = "gfbcaed";
		System.out.println(isAnagram(a,b));
		
		String[] test = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(anagrams(test));		
		
		
	}
	
	
	
	// given two Strings, return true or false to tell if they are anagram
	public static boolean isAnagram(String s, String t){
		if ( s.length() != t.length() )
			return false;
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer> ();
		
		for (int i=0; i<s.length(); i++){
			char c1 = s.charAt(i);
			if (map.containsKey(c1)){
				map.put( c1, map.get(c1) + 1 );
			}
			else {
				map.put(c1, 1);
			}
		}
		
		for (int i = 0; i <s.length(); i++){
			char c2 = t.charAt(i);
	        if(map.containsKey(c2)){
	            if(map.get(c2)==1){
	                map.remove(c2);
	            }else{
	                map.put(c2, map.get(c2)-1);
	            }
	        }
	        else{
	            return false;
	        }   
		}
		
		if (map.size() > 0)
			return false;
		
		return true;
	}	
	
	
	// input a list of Strings, return a list of String that they are anagrams
	private static int getHash(int[] count) {
        int hash = 0;
        int a = 378551;
        int b = 63689;
        for (int num : count) {
            hash = hash * a + num;
            a = a * b;
        }
        return hash;
    }
	
    public static ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }

            int hash = getHash(count);
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>());
            }

            map.get(hash).add(str);
        }

        for (ArrayList<String> tmp : map.values()) {
            if (tmp.size() > 1) {
                result.addAll(tmp);
            }
        }

        return result;
    }
	
}


