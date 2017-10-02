package neu.edu.ccs.cs5010;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by zontakm on 9/12/2017.
 * This is a dummy implementation of Stack ADT
 */
public class MyStack implements IStack {

    Stack<Integer> stack = new Stack<>();
    @Override
    public IStack push(int elt) {
        stack.push(elt);
        return this;
    }

    public IStack pop() {

        stack.pop();
        return this;
    }
    public int top() {

        return stack.peek();
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
