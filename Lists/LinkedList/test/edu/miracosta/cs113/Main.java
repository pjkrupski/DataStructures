package edu.miracosta.cs113;

import java.util.Collections;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {


        //build terms for poly1
        Term t = new Term(5, 7);
        Term u = new Term(5, 9);
        Term v = new Term(-2, 2);
        Term w = new Term(-4, -3);

        //build terms for poly 2
        Term l = new Term(145, 9);
        Term m = new Term(-5, 7);
        Term n = new Term(15, 5);


        Term any;
        Polynomial Poly1 = new Polynomial();
        Polynomial Poly2 = new Polynomial();
        Polynomial UserBuilt = new Polynomial();
        Scanner Keyboard = new Scanner(System.in);


        //FIll up Poly 1
        Poly1.addTerm(t);
        Poly1.addTerm(u);
        Poly1.addTerm(v);
        Poly1.addTerm(w);

        //FIll up Poly2
        Poly2.addTerm(l);
        Poly2.addTerm(m);
        Poly2.addTerm(n);


        int i = 0, e, c, j;


        //menu settings
        while (true) {

            System.out.println("Select 1 to view first Polynomial");
            System.out.println("Select 2 to clear first Polynomial");
            System.out.println("Select 3 to add terms to first Polynomial");
            System.out.println("Select 4 to view second Polynomial");
            System.out.println("Select 5 to clear second Polynomial");
            System.out.println("Select 6 to add terms to second Polynomial");
            System.out.println("Select 7 to create new polynomial");
            System.out.println("Select 8 to exit");


            i = Keyboard.nextInt();

            if (i == 1) {
                System.out.println(Poly1.toString());
            } else if (i == 2) {
                Poly1.clear();
                System.out.println("Poly 1 has been cleared");
            } else if (i == 3) {
                System.out.println("Please enter an integer coefficient ");
                c = Keyboard.nextInt();
                System.out.println("Please enter an integer exponent");
                e = Keyboard.nextInt();
                any = new Term(c, e);
                Poly1.addTerm(any);
                System.out.println("You have added " + any.toString() + " to Poly 1");
            } else if (i == 4) {
                System.out.println(Poly2.toString());
            } else if (i == 5) {
                Poly2.clear();
                System.out.println("Poly 2 has been cleared");
            } else if (i == 6) {
                System.out.println("Please enter an integer coefficient ");
                c = Keyboard.nextInt();
                System.out.println("Please enter an integer exponent");
                e = Keyboard.nextInt();
                any = new Term(c, e);
                Poly2.addTerm(any);
                System.out.println("You have added " + any.toString() + " to Poly 2");
            } else if (i == 7) {
                System.out.println("Its time to build your own polynomial");
                System.out.println("Please enter how many terms it will be");
                j = Keyboard.nextInt();
                for (i = 0; i < j; i++) {
                    System.out.println("Please enter an integer coefficient ");
                    c = Keyboard.nextInt();
                    System.out.println("Please enter an integer exponent");
                    e = Keyboard.nextInt();
                    any = new Term(c, e);
                    UserBuilt.addTerm(any);

                }
                //sorts linkedList backwards
                Collections.sort(UserBuilt.PolyList);

                //puts LinkedList in order
                Collections.reverse(UserBuilt.PolyList);
                System.out.println("Here is your polynomial");
                System.out.println(UserBuilt);
            } else if (i == 8) {
                System.exit(0);

            } else {
                System.out.println("PLease enter integer 1-8");
            }

        }

    }


}
