package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.ToIntFunction;


public class HuffmanTree implements HuffmanInterface {
    private ArrayList<Pair> pairList = new ArrayList<Pair>();
    private String[] codeTable;
    private StringBuilder sb = new StringBuilder();
    private String blank = "";
    //Node of HuffmanTree
    private HuffmanNode Node;
    private BinaryTree<HuffmanNode> huffTree;


    // lambda expression used instead of Comparator<HuffmanNode> anonymous class.
    // PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(10, (x, y) -> y.weight - x.weight);

    // PriorityQueue<HuffmanTree> q = new PriorityQueue<HuffmanTree>(10, (x, y) -> y.Node.weight - x.Node.weight);


    //defeault
    public HuffmanTree() {


    }

/*
    //FulllyLoaded
    public HuffmanTree(char c, int weight) {
        Node.data = c;
        Node.weight = 0;
    }
*/

    public HuffmanTree(String s) {

        boolean flag = false;
        HuffmanNode[] nodeArray = new HuffmanNode[s.length()];

        //need to load s into priQ
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < nodeArray.length; j++) {
                if (nodeArray[j] != null && s.charAt(i) == (char) nodeArray[j].symbol ) {
                    flag = true;
                }
            }
            if (!flag) {
                nodeArray[i] = new HuffmanNode(getWeight(s, s.charAt(i)), (Character) s.charAt(i));
            }
            flag = false;
        }
//embeded for loops do not add duplicate characters, buildTree() will account for null elements of the HuffmanTree array


        buildTree(nodeArray);
        buildMap(huffTree,blank);
        printMap();

    }


    //Helper method to find weight
    public static double getWeight(String s, char c) {
        double count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c)
                count++;
        }
        return count;
    }

    public void buildTree(HuffmanNode[] symbols) {

        Queue<BinaryTree<HuffmanNode>> theQueue = new PriorityQueue<BinaryTree<HuffmanNode>>(symbols.length, new CompareHuffmanTrees());

        //This for loads the priority queue and skips the null elements
        for (int i = 0; i < symbols.length; i++) {
            BinaryTree<HuffmanNode> aBinaryTree = new BinaryTree<HuffmanNode>(symbols[i], null, null);
            if(symbols[i] != null) {
                theQueue.offer(aBinaryTree);
            }
        }

        while (theQueue.size() > 1) {
            BinaryTree<HuffmanNode> left = theQueue.poll();
            BinaryTree<HuffmanNode> right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffmanNode sum = new HuffmanNode(wl + wr, null);
            BinaryTree<HuffmanNode> newTree = new BinaryTree<>(sum, left, right);
            theQueue.offer(newTree);
        }
        huffTree = theQueue.poll(); //huffTree is a binary tree of huffman nodes with blank data with null left and right subtrees

    }


    @Override
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffmanNode> currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {

            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            } else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffmanNode theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }


        return result.toString();
    }

    public String toString() {

        return huffTree.toString2();
    }

    @Override
    public String encode(String message) {

        for(int i=0; i<message.length(); i++ ){
            for(int j=0; j<pairList.size(); j++){
                if(message.charAt(i)==pairList.get(j).data){
                    sb.append(pairList.get(j).code ); //will have 1 space at end
                }
            }
        }

        return  sb.toString();
    }

    //Only returns the size of what the encoded String will be
    public int encode(int message) {


        for(int i =0; i<message; i++){
            sb.append("i");
        }

        return  sb.length();
    }
    public String printMap(){


        for(int i =0; i<pairList.size(); i++){

        }
        return "";
    }

    public void buildMap(BinaryTree<HuffmanNode> huffTree , String s) {


        Pair temp = new Pair(null, null);


        //baseCase
        if(huffTree.root.data.symbol != null){

            temp.setCode(s);
            temp.setData((Character) huffTree.root.data.symbol); //WHy do I need to typeCast this??
            pairList.add(temp);
        }else{
            buildMap(huffTree.getLeftSubtree(), s+"0");

            buildMap(huffTree.getRightSubtree(), s+"1");
        }





    }

    private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffmanNode>> {


        @Override
        public int compare(BinaryTree<HuffmanNode> treeLeft, BinaryTree<HuffmanNode> treeRight) {

            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }


    protected static class HuffmanNode<Character> {
        public double weight = 0;
        public Character symbol;

        /**
         * Constructor which stores the given data in this Node.
         *
         * @param symbol The data to hold within the node
         */
        public HuffmanNode(double weight, Character symbol) {

            this.symbol = symbol; //still needed even with super call?
            this.weight = weight;
        }


        public double getWeight() {

            return weight;
        }

        public String toString() {

            return symbol + " weight " + weight;
        }
    }
}


