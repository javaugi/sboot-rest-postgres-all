package com.sisllc.mathformulas.ci.ch18.Question18_8;

import java.util.ArrayList;

public class SearchStringFromArray {

    public static void main(String[] args) {
        String testString = "mississippi";
        String[] stringList = {"is", "sip", "hi", "sis"};
        SuffixTree tree = new SuffixTree(testString);
        for (String s : stringList) {
            ArrayList<Integer> list = tree.search(s);
            if (list != null) {
                System.out.println(s + ": " + list.toString());
            }
        }
    }
}
