package test;

public class test {
    
    
    public static void main (String args[])
    {
        int x =Integer.parseInt("-9");
        double c = Double.parseDouble("5");
        int b = Integer.parseInt("444",16);

        System.out.println(x);
        System.out.println(c);
        System.out.println(b);
        String  convertingString="123456";
        System.out.println("String Before Conversion :  "+ convertingString);
        int output=    stringToint( convertingString );
        System.out.println("");
        System.out.println("");
        System.out.println("int value as output "+ output);
        System.out.println("");
    }
    
    // The input str consists of only '-' and numbers
    public static int stringToint( String str ){
    	// index i and result stores in number
        int i = 0, number = 0;
        // set up the flag is Negative
        boolean isNegative = false;
        // len is the maximum iteration
        int len = str.length();
        // to see if the first element is -
        if( str.charAt(0) == '-' ){
            isNegative = true;
            i = 1;
        }
        
        while( i < len ){
            number *= 10;
            number += ( str.charAt(i++) - '0' );
        }
        if( isNegative )
        number = -number;
        return number;
    }   
}