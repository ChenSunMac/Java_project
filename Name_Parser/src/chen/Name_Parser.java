/*
 * NameParser - Parse names into [Title, First Name, Middle Name, Last Name]
 * 
 * Function: read and parse UTF-8 encoded txt file and write UTF-8 encoded csv file
 *   
 * Main Assumptions:
 * 			(1) Each line of the input txt file have at least [First Name] and [Last Name] information
 * 			(2) The [Title] information can appear at anywhere in the line, one can have multiple Titles
 * 					e.g. AABB Prof. Dr. CCDD ----> Title = Prof. Dr. 
 * 			(3) First Name will only have one entry, Middle Name and Last Name may have multiple entries
 * 					e.g. AABB CCDD FFGG HHBB ----> First Name = AABB
 * 			(4) Last Name usually took one entry
 * 					e.g. AABB CCDD FFGG HHBB ----> Last Name = HHBB
 * 					but we need to detect compound last names such as "Von Fange."
 *	 					e.g. AABB CCDD von de YYBB ---->	Last Name = von de YYBB
 * 					I established a set base on Wikipedia article: 
 * 					<https://en.wikipedia.org/wiki/Nobiliary_particle">Nobiliaryparticle>. 
 * The Process:
 * 			(1) Give directory path to the String filename and read the txt file line by line
 * 			(2) For each line read store in the list, 
 * 				(2.1) detect title entry, 
 * 					if there is a title, get them and filter them out from the original list
 * 					if there is no title, let title field be "" and move on
 * 				(2.2) Now the list remained is only associated with full name
 * 					we base on assumption (3) and (4) to store the first, middle, last names
 * 			(3) After parsing the file, we write the result back in .csv file. 
 * 
 * Author:
 * 			Chen Sun
 * 
 * 
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
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Name_Parser {
	
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
			// Need to change the file location
			String filename = "C:\\Users\\Sun\\Downloads\\cantest-die\\cantest-die\\input.txt";
			File fileDir = new File(filename);

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
			Name_Parser parser = new Name_Parser();
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
					case "phd":
						title_temp = "PhD.";
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
	 * Splits a full name into the following parts:
	 * <ul>
	 * <li>Title, e.g. Mr., Mrs., Ms., etc.</li>
	 * <li>first name</li>
	 * <li>Middle name</li>
	 * <li>Last Name</li>
	 * 
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

}