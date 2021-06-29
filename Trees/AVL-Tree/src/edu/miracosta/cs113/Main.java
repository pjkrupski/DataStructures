package edu.miracosta.cs113;

import java.util.Random;


public class Main<E extends Comparable<E>> {





    public static void main(String[] args) {
        AVLTree aTree = new AVLTree();
        BinarySearchTree bsTree = new BinarySearchTree();
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<Integer>();
        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        // private static AVLTree<Integer> bsTree;
        Random rand = new Random();




        for(int i =0; i<22; i++){
            int randNum = rand.nextInt(10)+1;
            avlTree.add(randNum);
            searchTree.add(randNum);
            System.out.println(randNum);
        }

        System.out.println("__________________________________________________");
        System.out.println(avlTree);
        System.out.println("___________________________________________________");
        System.out.println(searchTree);
    }
}
