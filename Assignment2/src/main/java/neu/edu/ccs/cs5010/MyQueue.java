package neu.edu.ccs.cs5010;

public class MyQueue implements IQueue {
    public IQueue enqueue(int elt) { return this; }
    public IQueue dequeue() { return this; }
    public int front() { return 0; }
    public boolean isEmpty() {return true; }
}
