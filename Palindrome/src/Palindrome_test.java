/*
 * All Palindrome related test and solution.
 * 
 * Use static method in order to easy examine the answer
 * 
 */
public class Palindrome_test 
{		
		// Constructor
    	public Palindrome_test()
    	{
        
    	}
	    public String test_string;
	    public static void main(String[] args)
	    {	
	    	// String for tests:
	    	
	    	String test_string = "A man, a plan, a canal: Panama";
		   
	    	if(  isPalindrome(test_string) )
	    		
	    		System.out.println( test_string + " is a palindrome");
	    	else
	    		System.out.println( test_string + " not a palindrome");
	    }
	    
	   /* 125. Valid Palindrome (Solution 1)
	    * Given a string, determine if it is a palindrome, 
	    * considering only alphanumeric characters and ignoring cases.
		*	For example,
		* 	"A man, a plan, a canal: Panama" is a palindrome.
		*		"race a car" is not a palindrome. 
		*
		* Idea: (1) if NULL string, return true;
		* 		(2) if 
	    */
	   public static boolean isPalindrome(String s) 
	   {	
		   	// If the string is null then return true
	        if (s == null || s.length() == 0) {
	            return true;
	        }
	        // 
	        int front = 0;
	        int end = s.length() - 1;
	        while (front < end) 
	        {
	            while (front < s.length() && !isvalid(s.charAt(front)))
	            { // nead to check range of a/b
	                front++;
	            }
	            if (front == s.length()) 
	            { // for emtpy string “.,,,”     
	                return true; 
	            }
	            while (end >= 0 && ! isvalid(s.charAt(end))) { // same here, need to check border of a,b
	                end--;
	            }

	            if (Character.toLowerCase(s.charAt(front)) != Character.toLowerCase(s.charAt(end))) {
	                break;
	            } else {
	                front++;
	                end--;
	            }
	   
	        }
	        return end <= front;   
	   }
	   private static boolean isvalid(char c) {
		   return Character.isLetter(c) || Character.isDigit(c);
	   }
	   /*
	    * 125. Valid Palindrome (Solution 2)
	    */
	   public boolean isPalindrome2(String s) {
		   	// filter out all non letter and non digits elements and formalize them into lower case
	        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	        // reverse the string using string buffer
	        String rev = new StringBuffer(actual).reverse().toString();
	        // return comparison
	        return actual.equals(rev);
	    }
}
