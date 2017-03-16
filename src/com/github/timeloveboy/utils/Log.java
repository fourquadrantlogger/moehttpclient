package com.github.timeloveboy.utils;

/**
 * Created by timeloveboy on 17-3-16.
 */

public final class Log {
    public static void v(Object... objects) {
        System.out.println();
        Object[] var1 = objects;
        int var2 = objects.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object o = var1[var3];
            System.out.print(o);
        }

    }
}