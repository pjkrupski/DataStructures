package edu.miracosta.cs113;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner keyboard1 = new Scanner(System.in), input =null;
        MorseCodeTree tester = new MorseCodeTree();
        String morseLetter[] = {"*-  ", "-***", "-*-*", "-** ", "*   ", "**-*", "--* ",
                "****", "**  ", "*---", "-*- ", "*-**", "--  ", "-*  ", "--- ", "*--*",
                "--*-", "*-* ", "*** ", "-   ", "**- ", "***-", "*-- ", "-**-", "-*--", "--**"};

        int i = 0;


        //Build table for choice 1


        String chart[][] = {{tester.translateFromMorseCode(morseLetter[0]), " *- ", " H ", " **** ", " O ", " --- ", " V ", " ***- "},
                {" A ", " *- ", " H ", " **** ", " O ", " --- ", " V ", " ***- "}};


        while (true) {


            System.out.println("1) test output for all morse code letters with their respective translated alphabet letters ");
            System.out.println("2) enter an input textfile name with morse code to decode and output the translated text to the console, ");
            System.out.println("3) enter in a line of morse code through the console to decode morse code and output the translated text to the console.");
            System.out.println("4) quit");
            i = keyboard1.nextInt();

            if (i == 1) {

                int w = 0;
                System.out.println("________________________________________________________");

                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        System.out.print(morseLetter[w] + "     " + tester.translateFromMorseCode(morseLetter[w]));
                        w++;
                        System.out.print("     ");
                    }
                    System.out.println("  ");

                }
                System.out.printf("________________________________________________________%n");


            } else if (i == 2) {
                Scanner keyboard2 = new Scanner(System.in);
                System.out.println("Enter name of MorseCode file you would like to decrypt   ENCLUDE .txt");
                String fileName =keyboard2.nextLine();
                decryptFile(fileName);



            } else if (i == 3) {
                Scanner keyboard3 = new Scanner(System.in);
                System.out.println("Please enter Morse Code to traslate");
              String translate = keyboard3.nextLine();
              decryptWord(translate);


            } else if (i == 4) {
                System.exit(0);
            } else {
                System.out.println("Please enter 1, 2, 3, or 4");
            }


        }


    }
    public static void decryptFile(String fileName){

        MorseCodeTree tester = new MorseCodeTree();
       Scanner input = null;

        try{

            input = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("No File Found");
        }

        while (input.hasNextLine()) {
            String temp = input.nextLine();
            System.out.print(temp + " translates to ");
            System.out.println(tester.translateFromMorseCode(temp));
        }



    }

    public static void decryptWord(String word){

        MorseCodeTree tester = new MorseCodeTree();
        String[] letters = word.split(" ");
        for(int i =0; i<letters.length; i++){
            System.out.print(tester.translateFromMorseCode(letters[i])+" ");

        }

        System.out.println(" ");
    }


}

