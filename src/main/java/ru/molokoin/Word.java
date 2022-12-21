package ru.molokoin;

public class Word {
    public static String reverce(String string){
        return new StringBuilder(string).reverse().toString();
    }
}
