package edu.miracosta.cs113;

import java.util.*;
import java.util.function.UnaryOperator;

public class DoubleLinkedList<E> implements List {

    public Node<E> head = null;
    public Node<E> tail = null;
    private int size = 0;

    //default constructor
    public DoubleLinkedList() {

        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    //setters and getters


    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /*
    public static void reverse(DoubleLinkedList list, Node sorter, Node current ){
            current = this.head();

        //reverse linked list so it prints in order
        while(current != null){

            sorter = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(sorter);
            current= current.getPrev();
        }

    }
*/

    @Override
    public String toString() {


        //one node for printing doubly linked list
        Node<E> tempHead = this.head;
        Node<E> tempTail = this.tail;
        String strVal = "";
        int i = 0;
        if (this.head == null) {

            return "[]";
        }

        if (this.getSize() == 1) {

            strVal = strVal + "[" + tempHead.getData() + "]"; // Tack each digit onto end of a string

            tempHead = tempHead.getNext();

        }

        if (this.getSize() > 1) {

            //must traverse list from the tail
            i = this.getSize();
            while (tempHead != null) {

                //if accounts for fisrt element
                if (strVal.equals("")) {
                    strVal = strVal + "[" + tempHead.getData() + ", ";
                    tempHead = tempHead.getNext();
                    i--;
                }
                strVal = strVal + tempHead.getData() + ", "; // Tack each digit onto end of a string
                tempHead = tempHead.getNext();
                i--;
                //if accounts for last element
                if (i == 1) {
                    strVal = strVal + tempHead.getData() + "]";
                    //temp.getData() +
                    i--;

                    break;
                }

            }

        }

        return strVal;


    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        //loop through list and return true if equivalent is found
        System.out.println("Debug 132");
       Node<E> temp =head;
        E other = (E) o;
        while(temp != null){

            if(temp.getData().equals(other)){
                return true;
            }

          temp = temp.getNext();
        }

        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }


    @Override
    public boolean add(Object o) {
        // System.out.println("Entered boolean");

        this.listIterator(size).add(o); //adds object to the tail





        /*
        Node newNode = new Node(o);
        if (isEmpty()) {
            tail = newNode;
            // head = newNode; considering this
        } else {
            head.prev = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;
         */
        return false;
    }

    @Override
    public boolean remove(Object o) {
        System.out.println("Wrong method");
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }


    @Override
    public void replaceAll(UnaryOperator operator) {

    }

    @Override
    public void sort(Comparator c) {

    }

    @Override
    public void clear() {

        head = null;
        size = 0;
/*
        Node<E> e = head.next;

        while (e != head) {

            Node<E> next = e.next;

            e.next = e.prev = null;

            e.data = null;

            e = next;

        }

        head.next = head.prev = head;

        size = 0;
*/

    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        if (size == 0 || index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        Node temp = head;
        //  System.out.println("made it to get method ");
        while (temp != null) {
            //    System.out.println("made it to while ");
            if (i == index) {
                //         System.out.println("made it to if ");
                //       System.out.println("i = "+i);
                return temp.getData();

            }
            temp = temp.getNext();
            i++;

        }


        System.out.println("bout to return null and i = " + i);
        return null;
    }

    @Override
    public Object set(int index, Object element) throws IndexOutOfBoundsException {



        if(index>=size || index<0 || size==0){
            throw new IndexOutOfBoundsException();
        }
        DoubleListIterator iter = new DoubleListIterator(index);
        iter.add(element);




        return null;

    }

    @Override
    public void add(int index, Object element) {
        int i =0;
        while(head != null){
            if(i == index){
                this.listIterator(size).add(element);
            }

            i++;
        }
// this.listIterator(size).add(o);



    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException {
        System.out.println("made it to remove ");

        if(index == size || index<0){
           throw new IndexOutOfBoundsException();
        }
        int i = 0;
        System.out.println("line 361");
        Node temp = this.head;
        System.out.println("line 363");
        while (temp != null) {
            System.out.println("made it to while ");
            if (i == index) {
                System.out.println("made it to if on  " + i + " iteration");
                temp.prev.setNext(temp.next);
                temp.next.setPrev(temp.prev);
                temp.setPrev(null);
                temp.setNext(null);
                return temp;

            }

            temp = temp.next;
            i++;
        }


        System.out.println("made it to final return ");
        return null;
    }

    @Override
    public int indexOf(Object o) {

        int i = 0;
        Node temp = head;

        while (temp != null) {

            if (temp.getData().equals(o)) {
                return i;
            }
            i++;
            temp = temp.getNext();
        }


        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        int i = size - 1;
        Node temp = tail;

        while (temp != null) {


            if (temp.getData().equals(o)) {
                return i;
            }
            temp = temp.getPrev();
            i--;

        }


        return -1;

    }

    @Override
    public ListIterator listIterator() {
        System.out.println("Debug line 403");
        DoubleListIterator iter = new DoubleListIterator(0);
        System.out.println("Debug line 405");


        return iter;
    }

    @Override
    public ListIterator listIterator(int index) throws IndexOutOfBoundsException {
      //  System.out.println("Debug Main line 444");
        DoubleListIterator iter = new DoubleListIterator(index);




        if (index < 0 || index > size) {

            throw new IndexOutOfBoundsException();
        }
      // System.out.println("Debug Main line 454");

        return iter;

    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    //Inner node class, as stated in slides
    public static class Node<E> {


        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem) {

            data = dataItem;

        }


        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }


    //inner class for iterator
    private class DoubleListIterator implements ListIterator {

        //A reference to the next item

        private Node<E> nextItem;

        //A reference to the last item returned

        private Node<E> lastItemReturned;

        //the index of the current item
        private int index = 0;



        public DoubleListIterator(int i) {

            //Validate i parameter
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid Index " + i);

            }
            lastItemReturned = null; //No item returned yet
            //Special case of last item
            if (i == size) {
                index = size;
                nextItem = null;
             //   System.out.println("Debug 528");
            } else {//Start at the begining
                nextItem = head;
                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }

            }


        }


        public int getIndex() {

            return this.index;
        }

        public void setIndex(int input) {

            index = input;
        }


        @Override
        public boolean hasNext() throws NoSuchElementException {

            return nextItem != null;
        }

        @Override
        public E next() throws NoSuchElementException {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        @Override
        public boolean hasPrevious() {

            /*
            Indicate whether movement backward is defined
            @return true if call to previous will not throw an exception
             */

            if(size==0){
                return false;
            }
            return (nextItem == null && size != 0)
                    || nextItem.prev != null;


        }

        @Override
        public E previous() throws NoSuchElementException {

     /*
     Move the iterator backward and return the previous item
     @return the previous item in the list
     @throws NoSuchElementException if there is no such obj
      */
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) { //Iterator is past the last element
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;

        }

        @Override
        public int nextIndex() {


            return this.getIndex();
        }

        @Override
        public int previousIndex() {


            return this.getIndex() - 1;
        }

        @Override
        public void remove() throws IllegalStateException {


               throw new IllegalStateException();







        }

        @Override
        public void set(Object o)throws IllegalStateException  {




           throw new IllegalStateException();

        }

        @Override
        public void add(Object obj) {

            /*
            Add a new item between the item that will be returned
            by next and the item that will be returned by previous.
            If previous is called after add, the element added is
            returned */

            if (head == null) {//Add to an empty list
                head = new Node<E>((E) obj);
                tail = head; //differs from book

            } else if (nextItem == head) { //Insert at head
                //Create a new node
                Node<E> newNode = new Node<E>((E) obj);
                //Link it to the nextItem
                newNode.next = nextItem; //Step 1
                //Link nextItem to the new node
                nextItem.prev = newNode; //Step 2
                //The new node is now the head
                head = newNode; //Step 3
            } else if (nextItem == null) { //Insert at tail
                //Create a new node
                Node<E> newNode = new Node<E>((E) obj);
                //Link the tail to the new node
                tail.next = newNode; //Step 1
                //Link the new node to the tail
                newNode.prev = tail; //Step 2
                //The new node is the new tail
                tail = newNode; //Step3
            } else { //Insert into the middle
                //Create a new node
                Node<E> newNode = new Node<E>((E) obj);
                //Link it to nextItem.prev
                newNode.prev = nextItem.prev; // Step 1
                nextItem.prev.next = newNode; //Step 2
                //Link it to the nextItem
                newNode.next = nextItem; //Step 3
                nextItem.prev = newNode; //Step 4
            }
                //Increase size and index and set lastItemReturned
                size++;
                index++;
                lastItemReturned = null;

            //End add method
        }
    }


}
