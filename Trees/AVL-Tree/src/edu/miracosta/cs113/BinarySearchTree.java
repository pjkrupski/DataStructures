package edu.miracosta.cs113;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree {

    //Data Fields
    //Returns value from the public add method
    protected boolean addReturn;

    //Return value from the public delete method
    protected E deleteReturn;

    //constructor
    public BinarySearchTree() {

    }


    public boolean add(E item) {

        root = add(root, item);
        return addReturn;
    }

    //recursive add

    private Node<E> add(Node<E> localRoot, E item) {


        if (localRoot == null) {
            //item is not in the tree --insert it

            addReturn = true;
            return new Node<E>(item);
        } else if (contains(item)==true) {
            /*
            compareTo not working?
            //item.compareTo(localRoot.data) == 0
            condition not being met to return 0 because addReturn wont set to false

            Tried
             item.toString().equals(localRoot.data.toString())
             int compResult = item.compareTo(localRoot.data);
             item.hashCode()==localRoot.data.hashCode()
             */
            //item is equal to localRoot.data
            addReturn = true;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            //item is less than localRoot .data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            //item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    public boolean contains(E item) {

        if(find(root,item)!=null){
            return true;
        }
        return false;
    }

    public E find(E target) {

        return (E) find(root, target);
    }

    //Recursively search Binary Search Tree
    private E find(Node<E> localRoot, E target) {

        //Base case
        if (localRoot == null) {
            return null;
        }
        //Compare the target with the data field at the root
        int compResult = target.compareTo(localRoot.data);  //do I need to build compareTO?
        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else
            return find(localRoot.right, target);

    }

    public boolean remove(E s) {

        if(contains(s)==false){
            return false;
        }
        if( delete(root,s)!= null){  //why didnt this work for remove failed?
            return true;
        }

        return false;
    }

    public E delete(E target) {

        root = delete(root, target);
        return deleteReturn;
    }

    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            //item is not in the tree
            deleteReturn = null;
            return localRoot;
        }

        //Search for item to delete
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            //item is smaller than localRoot.data
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            //item is larger than localRoot.data
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            //item is at local root
            deleteReturn = localRoot.data;

            if (localRoot.left == null) {
                //If there is no left child. return right child
                //which can also be null
                return localRoot.right;
            } else if (localRoot.right == null) {
                //If there is no right child, return left child
                return localRoot.left;
            } else {
                //Node being deleted has 2 children, replace the data with predessesor
                if (localRoot.left.right == null) {
                    //Replace the data with the data in the left child
                    localRoot.data = localRoot.left.data;
                    //Replace the left child with its left child
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    //Serach for the inorder predecessor (ip) and replace deleted nodes data with ip
                    localRoot.data = findLagestChild(localRoot.left);
                    return localRoot;
                }


            }

        }


    }

    //Method thats called in the delete method
    private E findLagestChild(Node<E> parent) {

        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLagestChild(parent.right);
        }

    }
}
