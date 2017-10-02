package neu.edu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class QueueTest {
    IQueue queue1,queue2,queue3,queue4;
    @Before
    public void setUp() throws Exception {
        queue1 = new MyQueue();
        queue2 = new MyQueue();
        queue3 = new MyQueue();
        queue4 = new MyQueue();
    }

    @Test
    public void enqueueSuccessful() throws Exception {
        assertEquals(true,queue1.isEmpty());
        queue1.enqueue(1);
        queue1.enqueue(2);
        assertEquals(queue1.front(),1);
        assertEquals(false,queue1.isEmpty());
    }

    @Test
    public void enqueueArray() throws Exception {
        assertEquals(true,queue2.isEmpty());
        int[] arr = {7,6,2,4,5,0,9,3,2,4,6,7,5,12,3,24,5,7,18,9,3,0,14,8,21,35,6,3,5,7,8,23,56,6,7,88,123,5,7,89,4,2,67,9,20};
        for(int i:arr) {
            queue2.enqueue(i);
        }
        assertEquals(7,queue2.front(),0);
        queue2.enqueue(100);
        assertEquals(7,queue2.front(),0);
        queue2.dequeue();
        assertEquals(6,queue2.front(),0);
        assertEquals(false,queue2.isEmpty());
    }


    @Test
    public void dequeueTest() throws Exception {
        assertEquals(true,queue2.isEmpty());
        int i = 5;
        while(i > 0) {
           queue2.enqueue(9);
           i--;
        }
        assertEquals(9,queue2.front(),0);
    }

    @Test(expected = NullPointerException.class)
    public void frontEmpty() throws Exception{
        queue3.front();
        }


    @Test
    public void frontTest() throws Exception {
        queue3.enqueue(-1);
        assertEquals(-1,queue3.front(),0);
        queue3.dequeue();
       // queue3.front();

    }

    @Test
    public void isEmpty() throws Exception {
        assertEquals(false,queue2.isEmpty());
        assertEquals(true,queue3.isEmpty());
        assertEquals(true,queue4.isEmpty());
    }

}