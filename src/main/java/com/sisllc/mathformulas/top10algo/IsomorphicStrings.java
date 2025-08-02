/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.top10algo;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class IsomorphicStrings {

    private static final Logger log = LoggerFactory.getLogger(IsomorphicStrings.class);

    public static void main(String[] args) {
        System.out.println("egg vs add: " + isMmorphic("egg", "add"));
        System.out.println("aabbb vs ddeee: " + isMmorphic("aabbb", "ddeee"));
    }

    public static boolean isMmorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (map.containsKey(c1)) {
                // if not consistant with previous ones
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                //if c2 is already being mapped. Time complexity O(n) here
                if (map.containsValue(c2)) {
                    return false;
                }
                map.put(c1, c2);
            }
        }

        return true;
    }
}
