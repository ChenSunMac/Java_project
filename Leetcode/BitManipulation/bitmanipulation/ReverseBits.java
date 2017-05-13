package bitmanipulation;

/*
 * Reverse bits of a given 32 bits unsigned integer.
 * For example, given input 43261596 
 * (represented in binary as 00000010100101000001111010011100), 
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 */

public class ReverseBits {
	
	public static void main(String[] args)
    {	
		int test_number = 11; // must be unsigned number
		System.out.println( "The reverse bit of " + "test_number is" +  reverseBits(test_number) + " 1s");
    }
	
	/*
	 * for 8 bit binary number abcdefgh, the process is as follow:
	 * abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
	 */
	
    public static int reverseBits(int n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }
}
