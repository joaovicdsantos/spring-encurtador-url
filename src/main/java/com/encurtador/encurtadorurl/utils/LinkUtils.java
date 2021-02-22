package com.encurtador.encurtadorurl.utils;


public final class LinkUtils {

    private LinkUtils() {
    }

    public static String gerarHash() {
        StringBuffer sb = new StringBuffer();
        String[] caracteres = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
                "r", "s", "t", "u", "v", "x", "w", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        for (int i = 0; i <= 16; i++) {
            sb.append(caracteres[(int) Math.random() * (caracteres.length)]);
        }
        return sb.toString();
    }
}
