package edu.miracosta.cs113;

import java.util.*;

//Implements the Queue interface using a circular array.

public class CircularArrayQueue<E> extends AbstractQueue<E> implements Queue<E> {


//Data Fields
//Index of the front of the queue

    private static final int DEFAULT_CAPACITY = 10;

//Index of the rear of the queue
    private int front;

//current size of queue
    private int rear;

//current capacity queue
    private int size;

//Default capacity of the queue
    private int capacity;

//Array to hold data
    private E[] theData;

//Constructors

//Construct a queue with the default initial capacity

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularArrayQueue(int initCapacity) {
        capacity = initCapacity;
        theData = (E[]) new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

//@SuppressWarnings("Unchecked")???


//Construct a queue with a specified initial capacity

    @Override
    public int size() {
        return 0;
    }


//Public Methods
//Inserts item at the rear of the queue

    @Override
    public boolean offer(E item) {
        if (size == capacity) {
            reallocate();
        }

        size++;
        rear = (rear + 1) % capacity;
        theData[rear] = item;
        return true;


    }


//Returns the item at the front of the queue without removing it


    @Override
    public E peek() {
        if (size == 0) {
            return null;
        } else {
            return theData[front];
        }
    }

//Removes the entry at the front of the queue and returns it if the queue is not empty

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }


//Private Methods
//Double capacity and reallocate the data

    //@SuppressWarnings("unchecked")
    private void reallocate() {
        int newCapacity = 2 * capacity;
        E[] newData = (E[]) new Object[newCapacity];

        int j = front;
        for (int i = 0; i < size; i++) {

            newData[i] = theData[j];

            j = (j + 1) % capacity;
        }
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        theData = newData;
    }
}
