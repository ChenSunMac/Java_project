package short_url;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class List_Set_Map {

 public static void main(String[] args) {
   List<String> al = new ArrayList<String>();
   al.add("Chaitanya");
   al.add("Rahul");
   al.add("Ajeet");
   System.out.println("ArrayList Elements: ");
   System.out.print(al);

   List<String> ll = new LinkedList<String>();
   ll.add("Kevin");
   ll.add("Peter");
   ll.add("Kate");
   System.out.println("\nLinkedList Elements: ");
   System.out.print(ll);
   System.out.print("\n");
   int count[] = {11, 22, 33, 44, 55};
   Set<Integer> hset = new HashSet<Integer>();
   for(int i = 0; i<5; i++){
       hset.add(count[i]);
    }
   System.out.println(hset);
   TreeSet<Integer> treeset = new TreeSet<Integer>(hset);
   System.out.println("The sorted list is:");
   System.out.println(treeset);
   
   // Set - Stores the reference to objects, no duplicate object
   Set<String> set=new HashSet<String>();  
   
   String s1=new String("hello");  
    
   String s2=s1;  // s2 refers to the same object as s1
     
   String s3=new String("world");  
    
   set.add(s1);  
    
   set.add(s2);  
    
   set.add(s3);  
    
   System.out.println(set.size());
   
   
   
 }
}