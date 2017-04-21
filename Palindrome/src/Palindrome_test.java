/*
 * All Palindrome related test and solution.
 * 125. Is Palindrome
 * 9. Palindrome Number
 * Use static method in order to easy examine the answer
 * 
 */
public class Palindrome_test 
{		
		// Constructor
    	public Palindrome_test()
    	{
        
    	}
	    public static String test_string;
	    public static void main(String[] args)
	    {	
	    	// String for tests:
	    	
	    	String test_string= "A man, a plan, a canal: Panama";
		    int test_number = 343;
		    
	    	if(  isPalindrome(test_string) )
	    		
	    		System.out.println( test_string + " is a palindrome");
	    	else
	    		System.out.println( test_string + " not a palindrome");
	    	if(  isPalindrome(test_number) )
	    		
	    		System.out.println( test_number + " is a palindrome");
	    	else
	    		System.out.println( test_number + " not a palindrome");
	    }
	    
	   /* 125. Valid Palindrome (Solution 1)
	    * Given a string, determine if it is a palindrome, 
	    * considering only alphanumeric characters and ignoring cases.
		*	For example,
		* 	"A man, a plan, a canal: Panama" is a palindrome.
		*		"race a car" is not a palindrome. 
		*
		* Idea: Using front and end two pointers to compare:
		* 		(1) if NULL string, return true;
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
	   public static boolean isPalindrome2(String s) {
		   	// filter out all non letter and non digits elements and formalize them into lower case
	        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	        // reverse the string using string buffer
	        String rev = new StringBuffer(actual).reverse().toString();
	        // return comparison
	        return actual.equals(rev);
	    }
	   
	   /* 9. Palindrome number
	    * Determine whether an integer is a palindrome. Do this without extra space.
	    * Use two pointers as well
	    * (1) Special case: if x < 0 or x is not zero however ends with 0 ---> return false
	    * (2) int rev initialize as 0,
	    * 		let rev store from right of the number x to mid
	    * 		and x stores the number from left to mid
	    * Example: x = 1221 -> rev = 12, x = 12
	    */
	   public static boolean isPalindrome(int x) {
		    if (x<0 || (x!=0 && x%10==0)) 
		    	return false;
		    int rev = 0;
		    while (x>rev){
		    	rev = rev*10 + x%10;
		    	x = x/10;
		    }
		    return (x==rev || x==rev/10);
		}
}
