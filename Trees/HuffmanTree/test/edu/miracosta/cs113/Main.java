package edu.miracosta.cs113;

import java.io.*;

public class Main {

    public static final int BITS_PER_CHAR = 16;

    public static void main(String args[]) {

        String url = "https://en.wikipedia.org/wiki/Huffman_coding";

        //Strings that will be used
        String originalFile = "original.txt", encodedFile = "encoded.txt", decodedFile = "decoded.txt", temp, cleanLine, encodedText, decodedText;
// int vars for bits
        int originalBitCount = 0, decodedBitCount = 0, encodedBitCount = 0;



        //objs
        BufferedReader br, br2;
        PrintWriter toEncodedFile = null;
        PrintWriter toDecodedFile = null;
        StringBuilder sb = null;
        StringBuilder sb2 = null;


        // Generating the Original text file using URL
        TextFileGenerator tfg = new TextFileGenerator();
        sb = new StringBuilder();
        sb2 = new StringBuilder();


        try {
            tfg.makeCleanFile(url, originalFile);

            // original text bit count
            originalBitCount = tfg.getNumChars(originalFile) * BITS_PER_CHAR;

            // reading original file
            br = new BufferedReader(new FileReader(originalFile));
            temp = br.readLine();
            while (temp != null) {
                cleanLine = tfg.cleanString(temp);
                sb.append(cleanLine + "\n");
                temp = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("try again");
        }

        // Building HuffmanTree, using original text
        HuffmanTree huffy = new HuffmanTree(sb.toString());

        // create encoded file of 0's and 1's
        try {
            toEncodedFile = new PrintWriter(new FileOutputStream("encoded.txt"));
            encodedText = huffy.encode(sb.toString());
            toEncodedFile.println(encodedText);
            toEncodedFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Could not open output file " + encodedFile);
            System.exit(0);
        }

        // reading encoded file to grab String of code
        try {
            br2 = new BufferedReader(new FileReader(encodedFile));
            temp = br2.readLine();
            while (temp != null) {
                sb2.append(temp);
                temp = br2.readLine();
            }
            br2.close();

        } catch (IOException ex) {
            System.err.println("Could not open output file " + encodedFile);
            System.exit(0);
        }

        // translating code to text
        try {
            toDecodedFile = new PrintWriter(new FileOutputStream(decodedFile));
            decodedText = huffy.decode(sb2.toString());
            toDecodedFile.print(decodedText);
            toDecodedFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Could not open output file " + encodedFile);
            System.exit(0);
        }


        // Original bit count:
        System.out.printf("Original bit count is: %,d \n", originalBitCount);

        // Decoded bit count, should be same as original
        decodedBitCount = tfg.getNumChars("decoded.txt") * BITS_PER_CHAR;
        System.out.printf("Decoded bit count (should be same as original): %,d \n", decodedBitCount);

        // Encoded bit count
        encodedBitCount = tfg.getNumChars("encoded.txt");
        System.out.printf("Encoded bit count (should be smaller than original): %,d \n", encodedBitCount);

        // Percentage output
        System.out.println("Percentage of compression is: " + (originalBitCount / encodedBitCount * 100) + "%");

    } // end main
} // end class