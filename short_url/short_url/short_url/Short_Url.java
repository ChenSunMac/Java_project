/*
 * each short url corresponding to a decimal integer, this integer is auto increment id in SQL
 * If upper case and lower case letters are differentiated, then each character can represent 
 * a single digit within a number of base 62 (26 + 26 + 10). 
 * In order to form the key, a hash function can be made, 
 * or a random number generated so that key sequence is not predictable. 
 * Or users may propose their own keys.
 */
package short_url;

import java.util.HashMap;

public class Short_Url {
    HashMap<String, Integer> ltos;
    HashMap<Integer, String> stol;
    static int COUNTER;
    String elements;
    Short_Url() {
        ltos = new HashMap<String, Integer>();
        stol = new HashMap<Integer, String>();
        COUNTER = 1;
        elements = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
    
    // input a url and return a shorter version
    
    public String longToShort(String url) {
        String shorturl = base10ToBase62(COUNTER);
        ltos.put(url, COUNTER);
        stol.put(COUNTER, url);
        COUNTER++;
        return "http://tiny.url/" + shorturl;
    }
    
    public String shortToLong(String url) {
        url = url.substring("http://tiny.url/".length());
        int n = base62ToBase10(url);
        return stol.get(n);
    }
    
    public int base62ToBase10(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); i++) {
            n = n * 62 + convert(s.charAt(i));
        }
        return n;
        
    }
    public int convert(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }
        return -1;
    }
    public String base10ToBase62(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.insert(0, elements.charAt(n % 62));
            n /= 62;
        }
        while (sb.length() != 6) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }
}