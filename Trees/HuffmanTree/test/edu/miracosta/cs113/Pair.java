package edu.miracosta.cs113;

public class Pair {

    String code = null;
    Character data = null;


    public Pair(String code, Character data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
