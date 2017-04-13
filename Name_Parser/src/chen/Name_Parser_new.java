/*
 * NameParser - Parse names into first, last, Middle Names, etc.
 * 
 *
 * Contributors:
 *     Chen Sun
 */

package chen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Name_Parser_new {
	
	private static List<String> nobiliaryParticleList = Arrays
			.asList(new String[] { "vere", "von", "van", "de", "del", "della",
					"di", "da", "pietro", "vanden", "du", "st.", "st", "la",
					"ter", "al", "ibn", "de la", "van der" });
	private static List<String> salutationList = Arrays.asList(new String[] {
			"mr", "mister", "mrs", "miss", "ms", "dr","prof" ,"phd"});
	
	
		/**
	 * Test
	 */
	public static void main(String[] args) {
		try {
			File fileDir = new File("C:\\Users\\Sun\\Downloads\\cantest-die\\cantest-die\\input.txt");

			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));

			String str = null;
			List<String> lines = new ArrayList<String>();

			while ((str = in.readLine()) != null) {
			    System.out.println(str);
			    lines.add(str);
			}
	        in.close();

			/*//-Parser test sample 1
			String[] testNames_1 = {"Herr Michael Hartwich",
					"Philip Cleary, PhD",
					"Dr John Cullen",
					"Dr. Patrick J Breen",
					"Ms. Cynthia Adams" ,"Brigitte von Lance","Anne Carli-Cham"};
	        */
			String[] test_input_from_file = lines.toArray(new String[]{});
			Name_Parser_new parser = new Name_Parser_new();
	        System.out.println("begin");
	        Writer out = new BufferedWriter(new OutputStreamWriter(
	        	    new FileOutputStream("result.csv"), "UTF-8"));
    	    out.write("Title,");
    	    out.write("First Name,");
    	    out.write("Middle Name,");
    	    out.write("Last Name\r\n");

			for (String s : test_input_from_file) {
				parser.splitFullName(s);
				out.write(parser.getHonorific() + ",");
				out.write(parser.getFirstName() + ",");
				out.write(parser.getInitials() + ",");
				out.write(parser.getLastName() + "\r\n");				
				System.out.printf("%5s, %15s, %15s, %15s \n",
						parser.getHonorific(), parser.getFirstName(),
						parser.getInitials(), parser.getLastName());

			}    
        	    out.close();
        	    System.out.println("done");	
		    }
		    catch (UnsupportedEncodingException e)
		    	{
					System.out.println(e.getMessage());
		    	}
		    catch (IOException e)
		    	{
					System.out.println(e.getMessage());
		    	}
		    catch (Exception e)
		    	{
					System.out.println(e.getMessage());
		    	}

	}

	private String firstName;
	private String honorific;
	private String initials;
	private String lastName;

	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getHonorific() {
		return this.honorific;
	}
	
	public String getInitials() {
		return this.initials;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	

	
	/**
	 * Detect compound last names such as "Von Fange."
	 * 
	 * Naturally there is a name for these kind of things; in this case it is
	 * nobiliary particle. See the Wikipedia article: <a
	 * href="https://en.wikipedia.org/wiki/Nobiliary_particle">Nobiliary
	 * particle</a>.
	 * 
	 * 
	 * @param s a {@link String} containing the name to test
	 * @return <code>true</code> if a compound name; otherwise false
	 */
	private boolean isCompoundLastName(String s) {
		String word = s.toLowerCase();
		for (String n : nobiliaryParticleList) {
			if (word.equals(n))
			return true;
		}
		return false;
	}
	

	
	private boolean isHonorific(String s) {
		String word = s.toLowerCase();
		for (String salutation : salutationList ) {
			if (word.equals(salutation))
			return true;
		}
		return false;
	}
	
	
	/**
	 * Check to see if the given {@link String} is in Pascal case, e.g.
	 * "McDonald."
	 * 
	 * @param s
	 *            the {@link String} to examine
	 * @return <code>true</code> if a match was found; false otherwise
	 */
	private boolean isPascalCase(String s) {
		// Considered (?<=[a-z])(?=[A-Z]).
		Pattern p = Pattern.compile("(?<=[a-z])(?=[A-Z])");
		Matcher m = p.matcher(s);
		return m.find();
	}

	
	// Delete the title part in the list
	private List<String> ReviseWithOutTitle(List<String> filteredlist){
		List<String> onlyname = new ArrayList<String>();
		onlyname.addAll(filteredlist);
		
		for (String element : filteredlist) {
			String word = element.toLowerCase();
			if (isHonorific(word)){
				onlyname.remove(element);
			}
		}
		
		return onlyname;
	}
	
	
	// Extract title String from the string list
	private String ExtractTitle (List<String> filteredlist) {
		String title = "";
		String title_temp;
		
		for (String element : filteredlist){
			String word = element.toLowerCase();
			if (isHonorific(word)){
				switch (word) {
					case "mr":
					case "mister":
						title_temp = "Mr.";
						break;
					case "mrs":
						title_temp = "Mrs.";
						break;
					case "miss":
					case "ms":
						title_temp = "Ms.";
						break;
					case "dr":
						title_temp = "Dr.";
						break;
					case "prof":
						title_temp = "Prof.";
						break;
					default:
						title_temp = "";
				}
				title = title + title_temp;
			}
		}
		
		return title;
	}
	

	

	/**
	 * 
	 * @param word
	 * @param delimiter
	 * @return
	 * 
	 * @since 1.8
	 */
	private String safeUpperCaseFirst(String word, String delimiter) {
		String[] parts = word.split(delimiter);
		String[] words = new String[parts.length];
		// TODO: Ummm... Why not a conventional for-loop?
		int count = 0;
		for (String s : parts) {
			words[count] = isPascalCase(s) ? s : upperCaseFirst(s.toLowerCase());
			count++;
		}
		// Requires Java 8.
		return String.join(delimiter, words);
	}
	
	/**
	 * Splits a full name into the following parts:
	 * <ul>
	 * <li>Honorific, e.g. Mr., Mrs., Ms., etc.</li>
	 * <li>Given name or first name</li>
	 * <li>Surname or last name</li>
	 * <li>Given name or first name</li>
	 * <li>Suffix, e.g. II, Sr., PhD, etc.</li>
	 * </ul>
	 * 
	 * @param s
	 *            a {@link String} containing the full name to split
	 */
	public void splitFullName(String s) {
		this.initials = "";
		// Transform the string into a segmented list
		String fullName = s.trim();
		String[] unfilteredParts = fullName.split("\\s+");
		List<String> nameParts = new ArrayList<String>();
		for (String part : unfilteredParts) {
				part = part.replaceAll("[\\pP]", "");  //delete all Unicode ',' 
				nameParts.add(part);
		}
		this.honorific = ExtractTitle (nameParts);
		List<String> realname = ReviseWithOutTitle(nameParts);
	
		int wordCount = realname.size();

		
		String word;
		String middlename = "";
		String lastname = "";
		int count = 0;
		//[A,B] we use A as first name and B as last name
		if (wordCount == 2){
			this.firstName = realname.get(0);
			this.initials = "";
			this.lastName =  realname.get(1);
		}
		//[A,B,C,...], A as first name, check if B or C or ... is compound last name
		if (wordCount > 2){

			this.firstName = realname.get(0);
			
			// check if is the compound last name
			for (int i = 1; i < wordCount - 1; i++) {
				word = realname.get(i);
				// Move on to parsing the last name if we find an indicator of a
				// compound last name such as von, van, etc. We use i != startIndex
				// to allow for rare cases where an indicator is actually the first
				// name, like "Von Fabella."
				if (isCompoundLastName(word) == false) {
					middlename = middlename + word;
				}
				else if (isCompoundLastName(word)){
					count = i;
					break;
				}

			}
			// 
			this.initials = middlename;
			if (count < 1){
				this.lastName =  realname.get(wordCount - 1);
			}
			else{
				for (int j = count; j < wordCount; j++) {
					lastname = lastname + " " + realname.get(j);
				}
				this.lastName = lastname;
			}
		}
	}

	/**
	 * Uppercase the first character in a given {@link String}.
	 * 
	 * @param s
	 *            the {@link String} upon which to operate
	 * @return a {@link String} with the first character in uppercase
	 */
	private String upperCaseFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
