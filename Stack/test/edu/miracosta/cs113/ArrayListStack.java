package edu.miracosta.cs113;

import java.util.*;
//Page 164

/*Class ListStack<E> implements the interface StackInt<E> as an adapter to the List. This implementation is functionally equivalent to that given in java.util.Stack<E> except that the underlying List<E> is not publicaly exposed.
 */



public class ArrayListStack<E> implements StackInterface{
        //StackInt<E>{

// The List containing the data
private ArrayList<E> theData;

//Construct an empty stack using an ArrayList as the container

public ArrayListStack(){
        theData = new ArrayList<E> ();

        }

/*
Push an object onto the stack
*/

    @Override
    public E push(Object obj){
        theData.add((E) obj);
        return (E) obj;
    }


//Peek at the top object on the stack

@Override
public E peek(){
        if (empty()){
        throw new EmptyStackException();
        }

        return theData.get(theData.size()-1); //why is -1 here?
        }


//pop the top object off the stack

@Override
public E pop(){
        if(empty()){
        throw new EmptyStackException();

        }
        return theData.remove(theData.size()-1);  //Had to add -1 for test 69 and 77 to pass
        //why is that????

        }



//return true if the stack is empty

@Override
public boolean empty(){

        return theData.size()==0;
        }



        }




