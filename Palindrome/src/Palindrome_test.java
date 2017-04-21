
public class Palindrome_test 
{		
		// Constructor
    	public Palindrome_test()
    	{
        
    	}
	    public String test_string;
	    public static void main(String[] args)
	    {
	    	String test_string = "A man, a plan, a canal: Panama";
	    	//Palindrome_test test_case = new Palindrome_test();
	    	//test_case.test_string = "A man, a plan, a canal: Panama";
		   
	    	if(  isPalindrome(test_string) )
	    		
	    		System.out.println( test_string + " is a palindrome");
	    	else
	    		System.out.println( test_string + " not a palindrome");
	    }
	   public static boolean isPalindrome(String s) 
	   {
	        if (s == null || s.length() == 0) 
	        {
	            return true;
	        }
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
	private static boolean isvalid(char charAt) {
		// TODO Auto-generated method stub
		return false;
	}
}
