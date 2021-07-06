package edu.miracosta.cs113;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        // TODO:
        // Implement a recursive solution following the given documentation.

       Combos obj = new Combos(cents);
        LinkedList<Combos> comboList = new LinkedList<>();

        return calculateChange(cents, obj, comboList).size();
    }



    public static LinkedList<Combos> calculateChange(int cents, Combos currentCombo, LinkedList<Combos> comboList){

        if(cents - currentCombo.getP()==0){

            if (comboList.contains(currentCombo)){

            }else{
                System.out.println(currentCombo);
                comboList.add(currentCombo);
            }
        }else if(cents<0){
            return comboList;
        }

        if(currentCombo.getP() >= 25){

            Combos newCombo = new Combos(currentCombo.getQ() +1, currentCombo.getD(), currentCombo.getN(), currentCombo.getP()-25);


            calculateChange(cents -25, newCombo, comboList);

        }
        if(currentCombo.getP() >= 10){

            Combos newCombo = new Combos(currentCombo.getQ() , currentCombo.getD()+1, currentCombo.getN(), currentCombo.getP()-10);


            calculateChange(cents -10, newCombo, comboList);

        }
        if(currentCombo.getP() >= 5){

            Combos newCombo = new Combos(currentCombo.getQ() , currentCombo.getD(), currentCombo.getN()+1, currentCombo.getP()-5);


            calculateChange(cents -5, newCombo, comboList);

        }

        return comboList;






    }




    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.

        PrintWriter outPut =null;
        LinkedList<Combos> changeCombos = calculateChange(cents,new Combos(), new LinkedList<Combos>());
        try{
            outPut = new PrintWriter(new FileOutputStream("C:\\Users\\coolk\\Documents\\GitHub\\cs113-hw06-recursiontrees-pjkrupski\\src\\edu.miracosta.cs113\\change\\CoinCombinations.txt"));
        }catch(IOException e){
           System.err.println(e.getMessage());
        }
        ListIterator<Combos> myIterator = changeCombos.listIterator(0);
        while(myIterator.hasNext()){
            outPut.write(myIterator.next().toString()+"/n");
            myIterator.next();
        }
    }

} // End of class ChangeCalculator