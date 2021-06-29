package edu.miracosta.cs113;

import java.util.Collections;
import java.util.LinkedList;


public class Polynomial {


    //instance variable is linked list of Term objs
    LinkedList<Term> PolyList = new LinkedList<Term>();

    //Constructor

    //defeault
    public Polynomial() {


    }

    public Polynomial(Polynomial p) {

        for(int i=0; i<p.getNumTerms(); i++){
            Term copy = new Term(p.getTerm(i));
            this.PolyList.add(copy);
            //this.setTerm(i,copy);
        }

    }


    //toString
    @Override
    public String toString() {

        if (PolyList.size() == 0) {
            return "0";
        }

        //sort list
        Collections.sort(PolyList);
        Collections.reverse(PolyList);
        //converst to string
        String poly = PolyList + "";
        poly = poly.replaceAll(",", "");
        poly = poly.substring(1, poly.length() - 1);

        if (poly.charAt(0) == '+') {
            poly = poly.substring(1);
        }
        return poly;


    }

    public void add(Polynomial p) {
        boolean flag = false;
        Term termSum = new Term();

        for (int i = 0; i < p.getNumTerms(); i++) {

            for (int j = 0; j < this.getNumTerms(); j++) {

                if (this.getTerm(j).getExponent() == p.getTerm(i).getExponent()) {


                    termSum.setCoefficient(this.getTerm(j).getCoefficient() + p.getTerm(i).getCoefficient());
                    termSum.setExponent(this.getTerm(j).getExponent());
                    this.PolyList.add(termSum);
                  // this.setTerm(j, termSum);
                    flag = true;
                }


            }

            if (!flag) {

                this.addTerm(p.getTerm(i));
            }

        }

        //sorts linkedList backwards
        Collections.sort(this.PolyList);

        //puts LinkedList in order
        Collections.reverse(this.PolyList);


    }

    public void addTerm(Term t) {
        Term termSum = new Term();
        boolean flag = false;

        //first for adds like exponents
        for (int i = 0; i < this.getNumTerms(); i++) {

            if (this.getTerm(i).getExponent() == t.getExponent()) {

                termSum.setCoefficient(this.getTerm(i).getCoefficient() + t.getCoefficient());
                termSum.setExponent(t.getExponent());
                this.setTerm(i, termSum);
                flag = true;
            }

        }
        if (!flag) {

            PolyList.add(t);
        }


        //sorts linkedList backwards
        Collections.sort(PolyList);

        //puts LinkedList in order
        Collections.reverse(PolyList);

    }


    public Term getTerm(int i) {

        return PolyList.get(i);
    }

    public void setTerm(int i, Term t) {

        PolyList.set(i, t);
    }


    public void clear() {

        PolyList.clear();
    }

    public int getNumTerms() {
        return PolyList.size();
    }



}
