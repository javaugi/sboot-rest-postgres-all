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


/*
 *   https://dzone.com/articles/constructors-or-static-factory-methods by Yegor Bugayenko
 * I believe Joshua Bloch said it first in his very good book "Effective Java": static
 * factory methods are the preferred way to instantiate objects compared with
 * constructors. I disagree. Not only because I believe that static methods are
 * pure evil, but mostly because in this particular case they pretend to be good
 * and make us think that we have to love them.
 *
 * Let's analyze the reasoning and see why it's wrong, from an object-oriented
 * point of view.
 *
 * Which one do you like better?
 * According to Joshua Bloch, there are three basic advantages to using static factory
 * methods instead of constructors (there are actually four, but the fourth one is not applicable to Java anymore):
 *   They have names.
 *   They can cache.
 *   They can subtype.
 * I believe that all three make perfect sense ... if the design is wrong. They are good excuses for workarounds.
 * Let's take them one by one.
 */
/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ColorSchemeOOD {

    private static final Logger log = LoggerFactory.getLogger(ColorSchemeOOD.class);

    public static void main(String[] argw) {
        ColorSchemeOOD color = new ColorSchemeOOD();
        color.runExampe();
    }

    public void runExampe() {
        // This is how you make a red tomato color object with a constructor:
        Color tomato = new RGBColor(255, 99, 71);

        //Now, we make an instance of Palette once and ask it to return a color to us every time we need it:
        Palette palette = new Palette();
        Color c1 = palette.take(255, 99, 71);
        Color c2 = palette.take(255, 99, 71);
    }

    public class Palette {

        private final Map< Integer, Color> colors
                = new HashMap<>();

        Color take(int red, int green, int blue) {
            final int hex = red << 16 + green << 8 + blue;
            //return colors.computerIfAbsent(hex, h - > new Color(h));
            return null;
        }
    }

    interface Color {
    }

    //They Have Names
    class HexColor implements Color {

        private final int hex;

        HexColor(int h) {

            this.hex = h;

        }

    }

    class RGBColor implements Color {

        private final Color origin;

        RGBColor(int red, int green, int blue) {

            this.origin = new HexColor(
                    red << 16 + green << 8 + blue
            );

        }

    }
}
