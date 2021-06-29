package edu.miracosta.cs113;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    //this has a Node<Character> root here, extended from BinaryTree

    MorseCodeTree(){
        super(' ');
    }
    MorseCodeTree(Node<Character> pNode){
        root = pNode;
    }

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
        BinaryTree<Character> tree = readMorseCodeTree();

        String toReturn = "";
        String[] letters = morseCode.split(" ");

        Node resetNode = new Node(' ');
        for (int i = 0; i < letters.length; i++) {
            resetNode = tree.root;
            while (letters[i].length() > 0) {
                if (letters[i].charAt(0) == ('*') || letters[i].charAt(0) == ('-') )
                {

                } else{
                    throw new InputMismatchException();
                }
                if (letters[i].charAt(0) == '*') {
                    tree.root = tree.root.getLeft();
                } else if (letters[i].charAt(0) == '-') {
                    tree.root = tree.root.getRight();
                }
                letters[i] = letters[i].substring(1);
            }
            toReturn += tree.root.toString();
            tree.root = resetNode;  //return to head root
        }
        return toReturn;
    }
    /**
     *  A non-recursive method to build a morse code tree, using characters '*' or '-' to either go left or right in the tree, building new nodes and assigning character data as needed.
     *  Reads in the morse code sequence with its accompanying character from a txt file MorseCodeAlphabet.txt to build the tree.
     *
     * @return a build Morse Code Tree represented by a BinaryTree<Character> object
     */
    public BinaryTree<Character> readMorseCodeTree(){
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream("MorseCode.txt"));
        } catch (FileNotFoundException e){
            System.out.println("No File Found");
        }
        while(input.hasNextLine()) {
            Node temp = root;

            String code = input.nextLine();
            Character data = code.charAt(0);
            String location = code.substring(2);
            for (int i = 0; i < location.length(); i++) {
                if (location.charAt(i) == '*'){
                    if (temp.left == null) {
                        temp.left = new Node();
                    }
                    temp = temp.left;
                } else if (location.charAt(i) == '-'){
                    if (temp.right == null) {
                        temp.right = new Node();
                    }
                    temp = temp.right;
                }
            }
            temp.data = data;

        }
        input.close();
        return new BinaryTree<>(root);
    }

} // End of class MorseCodeTree
