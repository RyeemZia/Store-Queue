//Custom Linked List/Queue to store customers
public class Queue<T>{
	private int size =0;
	private Node first, last;
	//nodes created that hold a generic object
	class Node{
		T customer;
		Node next;
		
	}
	//enqueue the generic object that is passed in 
	public Queue<T> enqueue(T customer){
		Node temp = last;
		last = new Node();
		last.customer = customer;
		
		if(size++ == 0) {
				first =last;
		}
		else {
			temp.next = last;
		}
		return this;
	
	}
	//dequeue generic object that is first
	public T dequeue() {
		if(size ==0) {
			throw new java.util.NoSuchElementException();
			
		}
		T customer = first.customer;
		first = first.next;
		if(--size == 0) {
			last =null;
		}
		return customer;
	}
	//general getters and setters methods
	public Node getFirst() {
		return first;
	}
	public void setFirst(Node first) {
		this.first = first;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Node getLast() {
		return last;
	}
	public void setLast(Node last) {
		this.last = last;
	}
	
	
	
	
	
	
}