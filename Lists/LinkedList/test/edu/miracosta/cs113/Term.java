package edu.miracosta.cs113;

import java.util.Objects;

public class Term implements Comparable {

    private int exponent, coefficient;

    //Constructors


    //default
    public Term() {
        this.exponent = 1;
        this.coefficient = 1;
    }

    //fullyloaded
    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;

    }


    public Term(String s) {

        int index=0, indexCarrot=0;
        String beforeX="", afterCarrot="";

        //FindTheX
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'x') {
                index=i;
            }
        }

        //if theres no X then exponent is 0 and coefficient is the whole string
        if (index==0) {
            this.exponent = 0;

            this.coefficient = Integer.parseInt(s);


        }else {
         //if there is an X then exponent must be found after the coefficient




                //Extract Coefficient and store in beforeX

                for (int i = 0; i < index; i++) {
                    beforeX = beforeX.concat(String.valueOf(s.charAt(i)));
                }

                if (String.valueOf(s.charAt(index - 1)).equals("-")) {
                    this.coefficient = -1;
                } else if (String.valueOf(s.charAt(index - 1)).equals("+")) {
                    this.coefficient = 1;
                } else {
                    this.coefficient = Integer.parseInt(beforeX);
                }







            //FindThe^
            for (int i = 0; i < s.length(); i++) {

                if (s.charAt(i) == '^') {
                    indexCarrot = i;
                }
            }


            //if theres no ^ then exponent is 1
            if (indexCarrot==0) {
                this.exponent = 1;
            }else {


                //Extract exponent and store in afterCarrot

                for (int i = indexCarrot + 1; i < s.length(); i++) {
                    afterCarrot = afterCarrot.concat(String.valueOf(s.charAt(i)));
                }

                this.exponent = Integer.parseInt(afterCarrot);

            }







        }

    }

    public Term(Term obj) {
        this.coefficient = obj.getCoefficient();
        this.exponent = obj.getExponent();
    }


    public void test() {

    }

    public void clear() {

    }

    public void setAll(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;

    }


    //Setters and Getters

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int Coefficient) {
        this.coefficient = Coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    @Override
    public String toString() {

        if (coefficient == 0) {
            return "";
        }


        if (coefficient == -1) {
            if (exponent == 0) {

                return "-1";
            } else if (exponent == 1) {
                return "-x";
            } else {
                return "-x^" + exponent;
            }
        }

        if (coefficient == 1) {
            if (exponent == 0) {

                return "+1";
            } else if (exponent == 1) {
                return "+x";
            } else {
                return "+x^" + exponent;
            }
        }

        if (coefficient < 0) {
            if (exponent == 0) {

                return "" + coefficient;
            } else if (exponent == 1) {
                return coefficient + "x";
            } else {
                return coefficient + "x^" + exponent;
            }
        }

        if (coefficient > 0) {
            if (exponent == 0) {

                return "+" + coefficient;
            } else if (exponent == 1) {
                return "+" + coefficient + "x";
            } else {
                return "+" + coefficient + "x^" + exponent;
            }
        }


        return "";
    }


    //clone method

    public Term clone() {
        return new Term(this);

    }


    //Equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return exponent == term.exponent &&
                coefficient == term.coefficient;
    }


    @Override
    public int compareTo(Object o) {
        Term that = (Term) o;
        if (this.getExponent() < that.getExponent()) {
            return -1;
        } else if (this.getExponent() == that.getExponent())
            return 0;
        else {
            return 1;
        }
    }





}
