package cs;
/*
 * A Link list structure with reverse linked list function and test bench
 */

public class LinkList {
	static ListNode head;
	
	static class ListNode{
		int data;
		ListNode next;
		
		ListNode(int d){
			data = d;
			next = null;
		} // constructor
	}
	// Reverse a linked list
	// Iterate trough the linked list:
	// In loop, change next to prev, prev to current and current to next.
	ListNode ReverseList(ListNode headnode){
		ListNode prev = null;
		ListNode current = headnode;
		ListNode next = null;
		while (current != null){
			next = current.next; // first make next pointer store the next value
			current.next = prev; // Then reverse the direction of the current pointer
			prev = current;      // update prev pointer to move forward
			current = next;      // update current to move forward to the previously stored next
		}
		headnode = prev;         // after the iteration, prev pointer will store the headnode
		return headnode;
	}
	
	// RECURSIVE METHOD: 
	
	
	// Prints the list
	void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
	

	public static void main (String[] args){
		LinkList list = new LinkList();
		list.head = new ListNode(85);
		list.head.next = new ListNode(13);
		list.head.next.next = new ListNode(3);
		
		System.out.println("Given linked List");
		list.printList(head);
		head = list.ReverseList(head);
		System.out.println("reversed linked list ");
		list.printList(head);
		
	}
}
