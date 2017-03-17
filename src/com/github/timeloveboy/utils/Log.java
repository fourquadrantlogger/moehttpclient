package com.github.timeloveboy.utils;

/**
 * Created by timeloveboy on 17-3-16.
 */

public final class Log {
    public static void v(Object... objects) {
        for (Object o : objects) {
            System.out.print(o);
        }
        System.out.println();
    }
}