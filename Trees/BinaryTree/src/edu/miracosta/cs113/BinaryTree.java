package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;


//This class has been built based on class slides and text book
public class BinaryTree<E> implements Serializable {
    //Instance variable
    protected Node<E> root;



    //constructors


    //default
    public BinaryTree() {
        root = null;
    }



    public BinaryTree(E data) {
        this.root = new Node<>(data);
    }


    protected BinaryTree(Node<E> temp) {
        this.root = temp;
    }

//fully loaded
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }


    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }
//getLeftSubtree

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Getter method to receive the left subtree of the root
     *
     * @return a new BinaryTree object that represents the tree object stemming from the root's left reference
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    //unused
    public Node<E> getLeft() {
        return root.left;
    }
//getRightSubtree

    //unused
    public Node<E> getRight() {
        return root.right;
    }

    /**
     * Getter method to receive the right subtree of the root
     *
     * @return a new BinaryTree object that represents the tree object stemming from the root's right reference
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * A recursive method to traverse through a BinaryTree data structure using preorder traversal, will start at the root then proceed to the left and then right references of the root, repeating until
     * it reaches the null level of leafs
     *
     * @param node  a node representing the root of the binary tree
     * @param depth an int representing the height of the tree structure
     * @param sb    a stringbuilder object to store the data at each level of the tree structure, used by the toString method in this class
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    public E getData() {
        return root.data;
    }

    public void setData(E pData) {
        root.data = pData;
    }

    public boolean setLeft(Node<E> node) {
        if (root.left == null) {
            root.left = node;
            return true;
        }
        return false;
    }

    public boolean setRight(E pData) {
        if (pData == null) {
            return false;
        }
        root.right.setRight(new Node<>(pData));
        return true;
    }

    //Node inner class, protected so MorseCodeTree has access to this inner class
    protected static class Node<E> {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node() {
        }

        public Node(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public void setData(E data) {
            this.data = data;
        }
    }

}