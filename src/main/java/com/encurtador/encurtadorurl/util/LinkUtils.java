package com.encurtador.encurtadorurl.util;


public final class LinkUtils {

    public static final String PREFIX = "http://localhost:8080/";

    private LinkUtils() {
    }

    public static String gerarHash() {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        String[] caracteres = {
            "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "x", "w",
            "y", "z", "0", "1", "2", "3",
            "4", "5", "6", "7", "8", "9",
            "!", "@", "?", "-", "$", "(" };
        for (int i = 0; i <= 10; i++) {
            sb.append(caracteres[(int) (Math.random() * (caracteres.length-1) + 0)]);
        }
        return sb.toString();
    }
}
