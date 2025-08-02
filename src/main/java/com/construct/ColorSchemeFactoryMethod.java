/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.construct;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ColorSchemeFactoryMethod {

    private static final Logger log = LoggerFactory.getLogger(ColorSchemeFactoryMethod.class);

    public static void main(String[] argw) {
        ColorSchemeFactoryMethod color = new ColorSchemeFactoryMethod();
        color.runExampe();
    }

    public void runExampe() {
        // 1. They Have Names
        // This is how you do it with a static factory method:
        Color tomato = makeFromPalette(255, 99, 71);

        // It seems that makeFromPalette() is semantically richer than just new Color(),
        //  right? Well, yes. Who knows what those three numbers mean if we just pass
        //  them to the constructor. But the word "palette" helps us figure everything out immediately.
        // True.
        // However, the right solution would be to use polymorphism and encapsulation,
        // to decompose the problem into a few semantically rich classes:
        // see ColorSchemeOOD interface and color def
        //
        // They Can Cache
        // Let's say I need a red tomato color in multiple places in the application:
        // They Can Subtype
    }

    private static Map< Integer, Color> CACHE = new HashMap<>();
    private static final int hex = 0;

    public static class Color {

        private final int hex;

        public Color(String rgb) {
            this(Integer.parseInt(rgb, 16));
        }

        public Color(int red, int green, int blue) {
            this(red << 16 + green << 8 + blue);
        }

        public Color(int h) {
            hex = h;
        }

    }

    public static Color makeFromRGB(String rgb) {
        return new Color(Integer.parseInt(rgb, 16));
    }

    public static Color makeFromPalette(int red, int green, int blue) {
        return new Color(red << 16 + green << 8 + blue);
    }

    public static Color makeFromHex(int h) {
        return new Color(h);
    }
}
