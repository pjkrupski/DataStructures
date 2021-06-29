package edu.miracosta.cs113;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {

    private boolean increase;

    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    private AVLNode<E> add(AVLNode<E> localRoot, E item) {

        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            //Item is already in the tree
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {

            //item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            } else if (item.compareTo(localRoot.data) > 0) {
                localRoot.right = add((AVLNode<E>) localRoot.right, item);

                if (increase) {
                    incrementBalance(localRoot);
                    if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                        increase = false;
                        return rebalanceRight(localRoot);
                    }

                }
            }

        }


        return localRoot; //Rebalance not needed
    }

    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;

        if (leftChild.balance > AVLNode.BALANCED) {
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;

            if (leftRightChild.balance < AVLNode.BALANCED) {
                incrementBalance(leftRightChild);
                incrementBalance(localRoot);
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                decrementBalance(leftRightChild);
                decrementBalance(leftChild);
            }

            decrementBalance(leftChild);
            localRoot.left = rotateLeft(localRoot.left);
            incrementBalance(localRoot);
            incrementBalance(localRoot);

            return (AVLNode<E>) rotateRight(localRoot);

        } else {

            incrementBalance(leftChild);

            incrementBalance(localRoot);
            incrementBalance(localRoot);

            return (AVLNode<E>) rotateRight(localRoot);

        }

    }

    /**
     * Helper Method. rebalances tree to the right that has become out of
     * balance
     *
     * @param localRoot root to be rebalanced to the right
     * @return returns rebalanced tree
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.right;

        if (leftChild.balance > AVLNode.BALANCED) {
            decrementBalance(leftChild);
            decrementBalance(localRoot);
            decrementBalance(localRoot);

            return (AVLNode<E>) rotateLeft(localRoot);
        } else {
            AVLNode<E> leftRightChild = (AVLNode<E>) localRoot.right.left;
            incrementBalance((AVLNode<E>) localRoot.right);

            if (leftRightChild.balance < AVLNode.BALANCED) {
                incrementBalance(leftChild);
                incrementBalance((AVLNode<E>) leftChild.left);

            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                decrementBalance(localRoot);
                decrementBalance((AVLNode<E>) leftChild.left);
            }

            localRoot.right = rotateRight(localRoot.right);

            decrementBalance(localRoot);
            decrementBalance(localRoot);

            return (AVLNode<E>) rotateLeft(localRoot);

        }
    }

    /**
     * Helper Method. decrements the balance of a node
     *
     * @param node node whos balance is to be decremented
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }

    /**
     * Helper Method. increments the balance of a node
     *
     * @param node node whos balance is to be increments
     */
    private void incrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance++;
        if (node.balance == AVLNode.BALANCED) {
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }

    /*
        private void decrementBalance(AVLNode<E> node) {
            //Decrement the balance
            node.balance--;
            if (node.balance == AVLNode.BAlANCED) {
                //If now balanced, overall height has not increased
                increase = false;
            }
        }

        private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {

            //Obtain reference to left child
            AVLNode<E> leftChild = (AVLNode) localRoot.left;

            //See whether left-rght heavy
            if (leftChild.balance > AVLNode.BALANCED) {
                //Obtain reference to left-right child
                AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;

                //Adjust the balances to be their new values after the rotations are performed


                if (leftRightChild.balance < AVLNode.BAlANCED) {
                    leftChild.balance = AVLNode.BAlANCED;
                    leftRightChild.balance = AVLNode.BAlANCED;
                    localRoot.balance = AVLNode.RIGHT_HEAVY;
                } else {
                    leftChild.balance = AVLNode.LEFT_HEAVY;
                    leftRightChild.balance = AVLNode.BAlANCED;
                    localRoot.balance = AVLNode.BAlANCED;
                }
                //Perform left rotation
                localRoot.left = rotateLeft((leftChild));
            } else {
                //Left-Left case

             //   In this case the leftChild (the new root)
             //   and the root (new right child) will both be balanced after rotation

                leftChild.balance = AVLNode.BAlANCED;
                localRoot.balance = AVLNode.BAlANCED;
            }

            return (AVLNode<E>) rotateRight(localRoot);

        }

        private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {

            //Obtain reference to left child
            AVLNode<E> rightChild = (AVLNode) localRoot.right;

            //See whether left-rght heavy
            if (rightChild.balance > AVLNode.BAlANCED) {
                //Obtain reference to left-right child
                AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;

             //   Adjust the balances to be their new values after the rotations are performed


                if (rightLeftChild.balance < AVLNode.BAlANCED) {
                    rightChild.balance = AVLNode.BAlANCED;
                    rightLeftChild.balance = AVLNode.BAlANCED;
                    localRoot.balance = AVLNode.RIGHT_HEAVY;
                } else {
                    leftChild.balance = AVLNode.LEFT_HEAVY;
                    leftRightChild.balance = AVLNode.BAlANCED;
                    localRoot.balance = AVLNode.BAlANCED;
                }
                //Perform left rotation
                localRoot.left = rotateLeft((leftChild));
            } else {
                //Left-Left case

              //  In this case the leftChild (the new root)
              //  and the root (new right child) will both be balanced after rotation

                leftChild.balance = AVLNode.BAlANCED;
                localRoot.balance = AVLNode.BAlANCED;
            }

            return (AVLNode<E>) rotateRight(localRoot);

        }
    */
    //Inner class AVL node
    private static class AVLNode<E> extends Node<E> {

        //Constant to indicate left-heavy
        public static final int LEFT_HEAVY = -1;

        //Constant to indicate balanced
        public static final int BALANCED = 0;

        //Constant to indicate right-heavy
        public static final int RIGHT_HEAVY = 1;

        private int balance;


        /**
         * Constructor which stores the given data in this Node.
         *
         * @param data The data to hold within the node
         */
        public AVLNode(E data) {

            super(data);
            balance = BALANCED;
        }

        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }

    }


}
