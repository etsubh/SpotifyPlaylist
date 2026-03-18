// Project 4 Fall 2025
// Virginia Tech Honor Code Pledge:

//

// As a Hokie, I will conduct myself with honor and integrity at all times.

// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.

// -- Etsub Habtewold (etsubh)
// LLM Statement:

//

// During the preparation of this assignment, I, Etsub Habtewold used ClaudeAI
// in the test cases to ensure the effective implementations of accurate edge
// cases.
// After using this tool, I reviewed and edited the content as needed to ensure
// its
// accuracy and take full responsibility for the content in relation to grading.
// I understand
// that I am responsible for being able to complete this work without the use of
// assistance.
package dailymixes;

import queue.QueueInterface;
import queue.EmptyQueueException;

// -------------------------------------------------------------------------
/**
 * A queue using a circular array.
 * 
 * @author etsub
 * @version Nov 4, 2025
 * @param <T>
 *            the param
 */
public class ArrayQueue<T>
    implements QueueInterface<T>
{

    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    /**
     * Default capacity for the queue
     */
    public static final int DEFAULT_CAPACITY = 20;

    /**
     * Constructor with default capacity
     */
    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    }


    // ----------------------------------------------------------
    /**
     * Constructor with specified capacity
     * 
     * @param capacity
     *            the initial capacity of the queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity)
    {
        queue = (T[])new Object[capacity + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * Gets the size of the queue
     * 
     * @return the number of elements in the queue
     */
    public int getSize()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Gets the length of the underlying array
     * 
     * @return the length of the underlying array
     */
    public int getLengthOfUnderlyingArray()
    {
        return queue.length;
    }


    // ----------------------------------------------------------
    /**
     * Clears the queue and resets it to default capacity
     */
    @SuppressWarnings("unchecked")
    public void clear()
    {
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * Helper method to increment an index in circular fashion
     * 
     * @param index
     *            the index to increment
     * @return the next index, wrapped around if necessary
     */
    private int incrementIndex(int index)
    {
        return (index + 1) % queue.length;
    }


    // ----------------------------------------------------------
    /**
     * Checks if the queue is empty
     * 
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }


    // ----------------------------------------------------------
    /**
     * Checks if the queue is full (one empty slot remains)
     * 
     * @return true if the queue is full, false otherwise
     */
    private boolean isFull()
    {
        return size == queue.length - 1;
    }


    // ----------------------------------------------------------
    /**
     * Adds an element to the back of the queue
     * 
     * @param newEntry
     *            the element to add
     */
    @Override
    public void enqueue(T newEntry)
    {
        if (isFull())
        {
            ensureCapacity();
        }

        queue[enqueueIndex] = newEntry;
        enqueueIndex = incrementIndex(enqueueIndex);
        size++;
    }


    // ----------------------------------------------------------
    /**
     * Removes and returns the front element from the queue
     * 
     * @return the front element
     * @throws EmptyQueueException
     *             if the queue is empty
     */
    @Override
    public T dequeue()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        T front = queue[dequeueIndex];
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;

        return front;
    }


    // ----------------------------------------------------------
    /**
     * Returns the front element without removing it
     * 
     * @return the front element
     * @throws EmptyQueueException
     *             if the queue is empty
     */
    @Override
    public T getFront()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        return queue[dequeueIndex];
    }


    // ----------------------------------------------------------
    /**
     * Expands the capacity of the queue New capacity is double the old capacity
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity()
    {
        int oldCapacity = queue.length - 1;
        int newCapacity = 2 * oldCapacity;

        @SuppressWarnings("unchecked")
        T[] newQueue = (T[])new Object[newCapacity + 1];

        // Copy elements to new array starting at index 0
        int currentIndex = dequeueIndex;
        for (int i = 0; i < size; i++)
        {
            newQueue[i] = queue[currentIndex];
            currentIndex = incrementIndex(currentIndex);
        }

        queue = newQueue;
        dequeueIndex = 0;
        enqueueIndex = size;
    }


    // ----------------------------------------------------------
    /**
     * Converts the queue to an array
     * 
     * @return an array containing all elements in queue order
     * @throws EmptyQueueException
     *             if the queue is empty
     */
    public Object[] toArray()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        Object[] result = new Object[size];
        int currentIndex = dequeueIndex;

        for (int i = 0; i < size; i++)
        {
            result[i] = queue[currentIndex];
            currentIndex = incrementIndex(currentIndex);
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Returns a string representation of the queue
     * 
     * @return string representation in formats
     */
    @Override
    public String toString()
    {
        if (isEmpty())
        {
            return "[]";
        }

        StringBuilder str = new StringBuilder();
        str.append("[");

        int currentIndex = dequeueIndex;
        for (int i = 0; i < size; i++)
        {
            str.append(queue[currentIndex].toString());

            if (i < size - 1)
            {
                str.append(", ");
            }

            currentIndex = incrementIndex(currentIndex);
        }

        str.append("]");
        return str.toString();
    }


    // ---------------------------------------------------------
    /**
     * Checks if this queue is equal to another object Two queues are equal if
     * they contain the same elements in the same order
     * 
     * @param obj
     *            the object to compare to
     * @return true if the queues are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != this.getClass())
        {
            return false;
        }

        @SuppressWarnings("unchecked")
        ArrayQueue<T> other = (ArrayQueue<T>)obj;
        if (this.size != other.size)
        {
            return false;
        }

        int thisIndex = this.dequeueIndex;
        int otherIndex = other.dequeueIndex;

        for (int i = 0; i < size; i++)
        {
            T thisElement = this.queue[thisIndex];
            T otherElement = other.queue[otherIndex];

            if (!thisElement.equals(otherElement))
            {
                return false;
            }

            thisIndex = incrementIndex(thisIndex);
            otherIndex = other.incrementIndex(otherIndex);
        }

        return true;
    }
}
