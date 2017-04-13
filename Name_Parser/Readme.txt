NameParser - Parse names into [Title, First Name, Middle Name, Last Name] 

This project is write for the test from Veeva Systems.

================================================================
The original requirement:
This exercise is to test your data analysis skills and programming skills of data transformation.

Problem description

===================

Given a file (UTF-8 encoded) containing names (one name per row), write a Java or Groovy program to convert this file into a CSV file with AT LEAST the following columns:

	Title, FirstName, MiddleName, LastName


A SAMPLE input file "input.txt" is attached. 
Your program should be able to handle not just the sample inputs but other files of the same format.



Instruction

===========

- When in doubt, STATE YOUR ASSUMPTIONS and proceed.

- The input file is UTF-8 encoded.

- Your program output should be the "output.csv" file which should contain AT LEAST the above 4 columns.

- Your program should maximize the usage of everything provided in the input file and minimize data overlap between columns in the output file.

- Your program must be written in Java or Groovy. You can optionally state the VERSION of the programming language you used.

- Your output file must be UTF-8 encoded.




=================================================================
Here lists the result and assumptions:


Function: read and parse UTF-8 encoded txt file and write UTF-8 encoded csv file
=============================================   
 Main Assumptions:
 			(1) Each line of the input txt file have at least [First Name] and [Last Name] information
 			(2) The [Title] information can appear at anywhere in the line, one can have multiple Titles
  					e.g. AABB Prof. Dr. CCDD ----> Title = Prof. Dr. 
  			(3) First Name will only have one entry, Middle Name and Last Name may have multiple entries
  					e.g. AABB CCDD FFGG HHBB ----> First Name = AABB
  			(4) Last Name usually took one entry
  					e.g. AABB CCDD FFGG HHBB ----> Last Name = HHBB
  					but we need to detect compound last names such as "Von Fange."
 	 					e.g. AABB CCDD von de YYBB ---->	Last Name = von de YYBB
  					I established a set base on Wikipedia article: 
  					<https://en.wikipedia.org/wiki/Nobiliary_particle">Nobiliaryparticle>. 
============================================= 
 The Process:
  			(1) Give directory path to the String filename and read the txt file line by line
  			(2) For each line read store in the list, 
  				(2.1) detect title entry, 
  					if there is a title, get them and filter them out from the original list
  					if there is no title, let title field be "" and move on
  				(2.2) Now the list remained is only associated with full name
  					we base on assumption (3) and (4) to store the first, middle, last names
  			(3) After parsing the file, we write the result back in .csv file. 

=============================================

Test and Notes:
	To test the Name_Parser, one can simply change the value of filename to specify the input file location
	(1) The default file location in "C:\\Users\\Sun\\Downloads\\cantest-die\\cantest-die\\input.txt", where the attachment was downloaded,it need to be changed to the loacation where the input.txt is at in the new test bench.
	(2) String[] testNames_1 is a local test sample I tried to examine how parsing function works, uncommented those lines to quick check the parse functions
	(3) The result.csv is the output file which has 4 columns.


  
  Author:
  			Chen Sun, 2017, April 13th