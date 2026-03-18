// Virginia Tech Honor Code Pledge:

//

// As a Hokie, I will conduct myself with honor and integrity at all times.

// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.

// -- Etsub Habtewold (etsubh)
// LLM Statement:

//

// // LLM Statement:

// I have not used any assistance for the assignment beyond course resources and
// staff.
package dailymixes;

import queue.EmptyQueueException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test for array queue to ensure it works properly.
 * 
 * @author etsub
 * @version Nov 4, 2025
 */
public class ArrayQueueTest
    extends TestCase
{
    private ArrayQueue<String> queue;

    /**
     * Sets up test fixtures
     */
    public void setUp()
    {
        queue = new ArrayQueue<String>();
    }


    /**
     * Tests basic enqueue and dequeue
     */
    public void testEnqueueDequeue()
    {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
    }


    /**
     * Tests isEmpty
     */
    public void testIsEmpty()
    {
        assertTrue(queue.isEmpty());
        queue.enqueue("A");
        assertFalse(queue.isEmpty());
    }


    /**
     * Tests getSize
     */
    public void testGetSize()
    {
        assertEquals(0, queue.getSize());
        queue.enqueue("A");
        assertEquals(1, queue.getSize());
    }


    /**
     * Tests getFront
     */
    public void testGetFront()
    {
        queue.enqueue("A");
        assertEquals("A", queue.getFront());
        assertEquals(1, queue.getSize());
    }


    /**
     * Tests dequeue throws exception when empty
     */
    public void testDequeueException()
    {
        Exception thrown = null;
        try
        {
            queue.dequeue();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof EmptyQueueException);
    }


    /**
     * Tests clear
     */
    public void testClear()
    {
        queue.enqueue("A");
        queue.clear();
        assertTrue(queue.isEmpty());
    }


    /**
     * Tests toString
     */
    public void testToString()
    {
        assertEquals("[]", queue.toString());
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals("[A, B]", queue.toString());
    }


    /**
     * Tests equals
     */
    public void testEquals()
    {
        ArrayQueue<String> other = new ArrayQueue<String>();
        assertTrue(queue.equals(other));

        queue.enqueue("A");
        other.enqueue("A");
        assertTrue(queue.equals(other));
    }


    /**
     * Tests constructor with default capacity
     */
    public void testDefaultConstructor()
    {
        assertEquals(0, queue.getSize());
        assertEquals(21, queue.getLengthOfUnderlyingArray());
        assertTrue(queue.isEmpty());
    }


    /**
     * Tests constructor with custom capacity
     */
    public void testCustomCapacityConstructor()
    {
        ArrayQueue<String> customQueue = new ArrayQueue<String>(10);
        assertEquals(0, customQueue.getSize());
        assertEquals(11, customQueue.getLengthOfUnderlyingArray());
    }


    /**
     * Tests getFront throws exception when empty
     */
    public void testGetFrontException()
    {
        Exception thrown = null;
        try
        {
            queue.getFront();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof EmptyQueueException);
    }


    /**
     * Tests clear resets to default capacity
     */
    public void testClearResetsCapacity()
    {
        for (int i = 0; i < 21; i++)
        {
            queue.enqueue("Item" + i);
        }
        assertEquals(41, queue.getLengthOfUnderlyingArray());

        queue.clear();
        assertEquals(21, queue.getLengthOfUnderlyingArray());
        assertEquals(0, queue.getSize());
    }


    /**
     * Tests toArray basic functionality
     */
    public void testToArray()
    {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        Object[] array = queue.toArray();

        assertEquals(3, array.length);
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
        assertEquals("C", array[2]);
    }


    /**
     * Tests toArray throws exception when empty
     */
    public void testToArrayEmpty()
    {
        Exception thrown = null;
        try
        {
            queue.toArray();
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof EmptyQueueException);
    }


    /**
     * Tests toArray with circular wrapping
     */
    public void testToArrayWrapped()
    {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("D");
        queue.enqueue("E");

        Object[] array = queue.toArray();
        assertEquals(3, array.length);
        assertEquals("C", array[0]);
        assertEquals("D", array[1]);
        assertEquals("E", array[2]);
    }


    /**
     * Tests ensureCapacity expansion
     */
    public void testEnsureCapacity()
    {
        assertEquals(21, queue.getLengthOfUnderlyingArray());

        // Fill to capacity (20)
        for (int i = 0; i < 20; i++)
        {
            queue.enqueue("Item" + i);
        }

        assertEquals(20, queue.getSize());
        assertEquals(21, queue.getLengthOfUnderlyingArray());

        // Add one more to trigger expansion
        queue.enqueue("Item20");

        assertEquals(21, queue.getSize());
        assertEquals(41, queue.getLengthOfUnderlyingArray());
    }


    /**
     * Tests ensureCapacity maintains order
     */
    public void testEnsureCapacityOrder()
    {
        for (int i = 0; i < 21; i++)
        {
            queue.enqueue("" + i);
        }

        for (int i = 0; i < 21; i++)
        {
            assertEquals("" + i, queue.dequeue());
        }
    }


    /**
     * Tests circular wrapping behavior
     */
    public void testCircularWrapping()
    {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        queue.dequeue();
        queue.dequeue();

        queue.enqueue("D");
        queue.enqueue("E");

        assertEquals("[C, D, E]", queue.toString());
        assertEquals("C", queue.getFront());
    }


    /**
     * Tests equals with same object
     */
    public void testEqualsSameObject()
    {
        assertTrue(queue.equals(queue));
    }


    /**
     * Tests equals with null
     */
    public void testEqualsNull()
    {
        assertFalse(queue.equals(null));
    }


    /**
     * Tests equals with different class
     */
    public void testEqualsDifferentClass()
    {
        assertFalse(queue.equals("not a queue"));
    }


    /**
     * Tests equals with different sizes
     */
    public void testEqualsDifferentSize()
    {
        ArrayQueue<String> other = new ArrayQueue<String>();

        queue.enqueue("A");
        other.enqueue("A");
        other.enqueue("B");

        assertFalse(queue.equals(other));
    }


    /**
     * Tests equals with different elements
     */
    public void testEqualsDifferentElements()
    {
        ArrayQueue<String> other = new ArrayQueue<String>();

        queue.enqueue("A");
        other.enqueue("B");

        assertFalse(queue.equals(other));
    }


    /**
     * Tests equals with wrapped indices
     */
    public void testEqualsWrapped()
    {
        ArrayQueue<String> queue1 = new ArrayQueue<String>();
        ArrayQueue<String> queue2 = new ArrayQueue<String>();

        // Queue1: normal order
        queue1.enqueue("A");
        queue1.enqueue("B");
        queue1.enqueue("C");

        // Queue2: wrap around
        queue2.enqueue("X");
        queue2.enqueue("Y");
        queue2.dequeue();
        queue2.dequeue();
        queue2.enqueue("A");
        queue2.enqueue("B");
        queue2.enqueue("C");

        assertTrue(queue1.equals(queue2));
    }


    /**
     * Tests dequeue after wrapping
     */
    public void testDequeueAfterWrapping()
    {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.dequeue();
        queue.enqueue("C");

        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertTrue(queue.isEmpty());
    }


    /**
     * Tests multiple enqueue and dequeue operations
     */
    public void testMultipleOperations()
    {
        for (int i = 0; i < 10; i++)
        {
            queue.enqueue("" + i);
        }

        for (int i = 0; i < 5; i++)
        {
            assertEquals("" + i, queue.dequeue());
        }

        assertEquals(5, queue.getSize());

        for (int i = 10; i < 15; i++)
        {
            queue.enqueue("" + i);
        }

        assertEquals(10, queue.getSize());
    }
}
