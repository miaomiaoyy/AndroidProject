

package neu.edu.ccs.cs5010;

import neu.edu.ccs.cs5010.IStack;
import neu.edu.ccs.cs5010.MyStack;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * created by Kathleen Yuan
 * use different stack to test the functions of the MyStack and if either one can't pass testcase
 * we will look into detail of the MyStack methods
 */

public class StackTest {
    private IStack stack1,stack2,stack3,stack4;
    @Before
    public void setUp() throws Exception {
        stack1 = new MyStack();
        stack2 = new MyStack();
        stack3 = new MyStack();
        stack4 = new MyStack();
    }

    @Test
    public void pushSuccessfulTest() throws Exception {
        stack1.push(1);
        assertEquals(1,stack1.top(),0);
        stack1.push(2);
        assertEquals(2,stack1.top(),0);
        stack1.pop();
        assertEquals(1,stack1.top(),0);
        stack1.pop();
        assertEquals(true,stack1.isEmpty());
    }

    @Test
    public void pushArray() throws Exception {
        assertEquals(true,stack2.isEmpty());
        int[] arr = {7,6,2,4,5,0,9,3,2,4,6,7,5,12,3,24,5,7,18,9,3,0,14,8,21,35,6,3,5,7,8,23,56,6,7,88,123,5,7,89,4,2,67,9,20};
        for(int i:arr) {
            stack2.push(i);
        }
        assertEquals(20,stack2.top(),0);
    }




    @Test
    public void popsuccessfultest() throws Exception {
        stack3.push(3);
        stack3.push(4);
        stack3.push(5);
        stack3.pop();
        assertEquals(4,stack3.top(),0);
        stack3.pop();
        stack3.pop();
        assertEquals(true,stack3.isEmpty());
    }

    @Test
    public void top() throws Exception {
        stack3.push(1);
        stack3.push(2);
        assertEquals(2,stack3.top(),0);
    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(stack4.isEmpty(),true);
        stack4.push(1);
        stack4.pop();
        assertEquals(stack4.isEmpty(),true);
    }

}
