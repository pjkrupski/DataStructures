package edu.miracosta.cs113;

public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
    //What is this method heading doing?


    //Class built based on textbook page 476
    protected Node<E> rotateRight(Node<E> root){
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;

        return temp;
    }

    protected Node<E> rotateLeft(Node<E> root){
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }



}
