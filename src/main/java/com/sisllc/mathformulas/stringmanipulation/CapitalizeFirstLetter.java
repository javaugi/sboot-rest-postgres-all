package com.sisllc.mathformulas.stringmanipulation;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class CapitalizeFirstLetter {

    private static final Logger log = LoggerFactory.getLogger(CapitalizeFirstLetter.class);

    public static final String SENTENCE = "If performance matters, and you have many words that need to be changed, \n"
            + "you might wanna implement another solution operating directly on a char \n "
            + "array that does not generate many new string objects and will perform better \n"
            + "I'm thinking something like the following: there are two method, one to \n "
            + "camelize a word, and the other to split sentence into words, then loop through \n"
            + "and camelize the whole sentence.";

    public static final String[] WORDS_EXCLUDED = {"a", "the", "to", "at", "in", "with", "and", "but", "or"};
    public static final List<String> EXCLUDED_WORD_LIST = Arrays.asList(WORDS_EXCLUDED);

    /*
    Write a function to capitalize each word in a SENTENCE except a, the, to, at, in, with, and but, or ?
     */
    public static void main(String[] args) {
        CapitalizeFirstLetter cap = new CapitalizeFirstLetter();
        log.info("\n {}", SENTENCE);
        String capped = cap.camelizeSentence(SENTENCE);
        log.info("\n {}", capped);
        //System.out.println("" + capped);
    }

    public String camelizeWord(String word) {
        char[] chars = word.toCharArray();
        char c = chars[0];
        chars[0] = Character.toUpperCase(c);

        return new String(chars);
    }

    public String camelizeSentence(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            //System.out.println("word=" + word + "-");
            //if (word == null || word.isEmpty() || word.trim().isEmpty()) {
            //    continue;
            //}
            if (EXCLUDED_WORD_LIST.contains(word)) {
                sb.append(" ").append(word);
                continue;
            }
            sb.append(" ").append(camelizeWord(word));
        }
        return sb.toString().trim();
    }
}
