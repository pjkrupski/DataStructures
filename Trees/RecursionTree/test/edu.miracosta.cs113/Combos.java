package edu.miracosta.cs113;

import java.util.Arrays;
import java.util.Objects;

public class Combos {

    private int q , d, n , p;

    public Combos(){

    }

    public Combos(int c){
        q = 0;
        d=0;
        n=0;
        p=c;

    }

    public Combos(int q , int d, int n, int p){
        this.q = q;
        this.d=d;
        this.n=n;
        this.p=p;

    }

    @Override
    public String toString() {
        return q +" quarters"+d+" dimes "+n+"  nickels "+p+" pennies";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combos combos = (Combos) o;
        return q == combos.q &&
                d == combos.d &&
                n == combos.n &&
                p == combos.p;
    }

    @Override
    public int hashCode() {
        return Objects.hash(q, d, n, p);
    }

    public void setQ(int q) {
        this.q = q;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public int getD() {
        return d;
    }

    public int getN() {
        return n;
    }

    public int getP() {
        return p;
    }
}
