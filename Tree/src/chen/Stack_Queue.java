package chen;

import java.util.Stack;
/*
 * Use 2 Stacks to implement a Queue;
 * 		Keep 2 stacks, let's call them inbox and outbox
 * (1) Enqueue: Push new element onto inbox 
 * (2) Dequeue: if outbox is empty, refill it by poping each element from inbox 
 * 				and pushing it onto outbox
 * 				Then pop and return the top element from outbox
 * In total, each element will be pushed and poped twice
 * 
 */
public class Stack_Queue<E>
{
	public static void main(String [ ] args)
	{
		Stack_Queue<String> Test_queue = new Stack_Queue<String>();
		Test_queue.queue("first_in");
		Test_queue.queue("second_in");
		System.out.println("Finished");
	}

    private Stack<E> inbox = new Stack<E>();
    private Stack<E> outbox = new Stack<E>();

    public void queue(E item) {
        inbox.push(item);
    }

    public E dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
               outbox.push(inbox.pop());
            }
        }
        return outbox.pop();
    }

}