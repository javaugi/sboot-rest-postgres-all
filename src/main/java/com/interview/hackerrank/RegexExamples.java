/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Tips for HackerRank:
    Use Pattern.compile() and Matcher for complex patterns
    Remember to escape special characters with \\
    Use ^ and $ for start/end of string when needed
    Test your regex thoroughly with edge cases
    For performance, compile patterns once and reuse them

Final Tips for HackerRank:
✔ Use Pattern.compile() for efficiency (if matching multiple times).
✔ Test edge cases (empty strings, special characters).
✔ Use ^ and $ to enforce full-string matching when required.
✔ Escape special chars like ., +, * with \\.
 */
public class RegexExamples {

    private static final RegexExamples regex = new RegexExamples();

    public static void main(String[] args) throws Exception {
        // ^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$;
        
        Solution.runDiffTest();

        List<String> lists = Arrays.asList("Goodbye bye bye world world world\n",
                "Sam went went to to to his business\n",
                "Reya is is the the best player in eye eye game\n",
                "in inthe\n",
                "Hello hello Ab aB");

        System.out.println("\n 1 run removeRepeatedWordsExceptFirst with data=" + lists);
        for (String token : lists) {
            System.out.println(MyRegex0.removeRepeatedWordsExceptFirst(token));
        }

        String input = "";
        String patternValue = "";
        Solution.runTests();

        PatternMatching.matchingHelloWithDigits(input, patternValue);
        PatternMatching.extractGroups(input, patternValue);
        PatternMatching.findingRepeatedWords(input, patternValue);
        PatternMatching.matchingHelloWithDigits(input, patternValue);
        PatternMatching.matchingHtmlTags(input, patternValue);
        PatternMatching.matchingIp(input, patternValue);
        PatternMatching.matchingIp2(input, patternValue);
        PatternMatching.replacingPatterns(input, patternValue);;
        PatternMatching.splittingStrings(input, patternValue);
        PatternMatching.validateUsername(input, patternValue);

        PracticalExamples.countingWords(input, patternValue);
        PracticalExamples.extractAllNumbers(input, patternValue);
        PracticalExamples.validateDateDDMMYYYY(input, patternValue);
    }

    class Solution {

        public static void runDiffTest() {
            try {
                String pattern1 = new MyRegex().pattern;
                String pattern2 = new MyRegex2().pattern;

                StringDiff.printDiffApacheCommon(pattern1, pattern2);
                //StringDiff.printDiffWithLibrary(pattern1, pattern2);
                StringDiff.printStringDifferences(pattern1, pattern2);
                StringDiff.printWordDifferences(pattern1, pattern2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void runTests() {

            List<String> lists = Arrays.asList("000.12.12.034", "121.234.12.12", "23.45.12.56", "00.12.123.123123.123", "122.23", "Hell.ip");
            String patternValue = new MyRegex().pattern;
            System.out.println("\n 1 run with data=" + lists + "\n      patternValue" + patternValue);
            for (String ip : lists) {
                System.out.println(ip.matches(patternValue));
            }

            patternValue = new MyRegex2().pattern;
            System.out.println("\n 2 run with data=" + lists + "\n      patternValue" + patternValue);
            for (String ip : lists) {
                System.out.println(ip.matches(patternValue));
            }
        }
    }

    static class MyRegex0 {

        public static String removeRepeatedWordsExceptFirst(String input) {
            // The regex pattern to match repeated words
            String regex = "\\b(\\w+)(\\s+\\1\\b)+";
            // Compile the pattern with CASE_INSENSITIVE flag
            Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            // Create matcher object
            Matcher m = p.matcher(input);
            // Replace all occurrences with the first match
            System.out.println("removeRepeatedWordsExceptFirst input=" + input);
            while (m.find()) {
                input = input.replaceAll(m.group(), m.group(1));
                System.out.println("Looping removeRepeatedWordsExceptFirst input=" + input);
            }

            return input;
        }
    }

    static class MyRegex {

        public static String pattern = "";

        public MyRegex() {
            pattern = patternBuilder();
        }

        private String patternBuilder() {
            StringBuilder sb = new StringBuilder();
            sb.append("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");

            //"^((25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})$";
            //Explanation: "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
            return sb.toString();
        }
    }

    static class MyRegex2 {

        public static String pattern = "";

        public MyRegex2() {
            pattern = patternBuilder();
        }

        private String patternBuilder() {
            StringBuilder sb = new StringBuilder();
            //250-255   200-249     0-199   . (250-255/200-249/0-199)
            sb.append("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
            return sb.toString();
        }
    }

    /*
        Explanation: "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
            25[0-5] → Matches 250 to 255.
            2[0-4][0-9] → Matches 200 to 249.
            [01]?[0-9][0-9]? → Matches 0 to 199 (with optional leading zero).
            \\. → Escaped . (literal dot).
            {3} → Repeats the previous group 3 times (for the first 3 octets).        

            ✅ 1. Valid IPv4 Address Format - A valid IPv4 address: Consists of 4 octets (bytes) separated by dots: x.x.x.x. Each octet is in the range 0–255

            Examples of valid IPv4 addresses:
                192.168.1.1
                10.0.0.5
                172.16.254.3
                8.8.8.8
                127.0.0.1 (loopback)
            ✅ 2. Private vs Public IP Address Ranges
                Type            IP Range                        Use Case
                Private         10.0.0.0 – 10.255.255.255       Internal networks
                                172.16.0.0 – 172.31.255.255     Internal networks
                                192.168.0.0 – 192.168.255.255   Home/office routers
                Loopback        127.0.0.1 – 127.255.255.255     Localhost testing
                Public          Any IP not in the above         Internet-facing addresses
            ✅ 3. Reserved & Invalid Ranges
                Range               Description
                0.0.0.0/8           "This" network (non-routable)
                169.254.0.0/16      Link-local (auto-assign)
                255.255.255.255     Broadcast
            ✅ 4. IP Address Regex Validation (Java Example)
                public static boolean isValidIPv4(String ip) {
                    String pattern = 
                        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
                        + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
                    return ip.matches(pattern);
                }        
     */
 /*
    what is the correct IP pattern if the following is true: IP address is a string in the form "A.B.C.D", where the value 
        of A, B, C, and D may range from 0 to 255. Leading zeros are allowed. The length of A, B, C, or D can't be greater than 3

    Correct IP Address Regex Pattern
        Given the requirements:
            Format: "A.B.C.D" where A,B,C,D are 0-255
            Leading zeros allowed (e.g., "001", "012")
            Each segment length ≤ 3 characters
    Here's the corrected Java regex pattern:
        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})$";

    Breakdown of the Pattern:
        1. 25[0-5]          Matches 250-255 Covers the upper range of valid numbers
        2. 2[0-4][0-9]      Matches 200-249
        3. [01]?[0-9]{1,2}
                [01]?       → Optional 0 or 1 (for numbers like 012)
                [0-9]{1,2}  → 1-2 digits (0-99)
                    Combined matches 0-199 (with leading zeros)
        4. \\.              Escaped dot (literal '.' separator)
        5. {3}              Repeats the pattern exactly 3 times (for A.B.C)
        6. Final Segment    Same pattern as before but without trailing dot (for D)

    Key Improvements Over Previous Version:
        Changed [01]?[0-9][0-9]? to [01]?[0-9]{1,2} for better clarity
        Maintained leading zero support explicitly
        Ensured each segment is exactly 1-3 digits

    Test Cases:
        // Valid IPs
        "0.0.0.0"         // true
        "255.255.255.255" // true
        "001.012.123.254" // true (leading zeros allowed)
        "192.168.1.1"     // true

        // Invalid IPs
        "256.1.1.1"       // false (256 > 255)
        "1.1.1."          // false (incomplete)
        "1.1.1.1.1"       // false (too many segments)
        "1234.1.1.1"      // false (segment too long)
        "1.1.1.01a"       // false (invalid characters)

    Java Implementation Example:
        public boolean isValidIP(String ip) {
            String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})$";
            return ip.matches(ipPattern);
        }    
     */
    static class PracticalExamples {

        static int countingWords(String input, String patternValue) {
            if (input == null || input.isEmpty()) {
                input = "The quick brown fox";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "\\s+";
            }
            int wordCount = input.split(patternValue).length;
            System.out.println("countingWords               return wordCount=" + wordCount + "-input=" + input + "-patternValue=" + patternValue);
            return wordCount;
        }

        static int validateDateDDMMYYYY(String input, String patternValue) {
            if (input == null || input.isEmpty()) {
                input = "08082018";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|20)\\d\\d)$";
            }

            int wordCount = input.split(patternValue).length;
            System.out.println("validateDateDDMMYYYY        return wordCount=" + wordCount + "-input=" + input + "-patternValue=" + patternValue);
            return wordCount;
        }

        static int extractAllNumbers(String input, String patternValue) {
            if (input == null || input.isEmpty()) {
                input = "abc123def456";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "\\d+";
            }

            Pattern numberPattern = Pattern.compile(patternValue);
            Matcher m = numberPattern.matcher(input);
            int count = m.groupCount();
            System.out.println("extractAllNumbers           return count=" + count + "-input=" + input + "-patternValue=" + patternValue);
            while (m.find()) {
                System.out.println(m.group());
            }
            // Output: 123, 456

            return count;
        }
    }

    static class PatternMatching {

        /*
        Explanation:
            ^ - Asserts the start of the string.
            Hello - Matches the exact word "Hello".
            \\d+ - Matches one or more digits (\d is a digit, + means "one or more").
            $ - Asserts the end of the string.
         */
        public static boolean matchingHelloWithDigits(String input, String patternValue) {
            if (input == null || input.isEmpty()) {
                input = "Hello123";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "^Hello\\d+$";
            }
            boolean matches = input.matches(patternValue); // true if starts with Hello and ends with digit
            System.out.println("matchingHelloWithDigits     return matches=" + matches + "-input=" + input + "-patternValue=" + patternValue);
            return matches;
        }

        /*
        Explanation: (\\w+)@(\\w+)\\.(\\w+)"
            (\\w+) - First group: Matches one or more word characters ([a-zA-Z0-9_]).
            @ - Matches the @ symbol.
            (\\w+) - Second group: Matches the domain name.
            \\. - Escaped . (literal dot).
            (\\w+) - Third group: Matches the top-level domain (e.g., com, org).        
         */
        public static boolean extractGroups(String input, String patternValue) {
            if (input == null || input.isEmpty()) {
                input = "email@domain.com";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "(\\w+)@(\\w+)\\.(\\w+)";
            }

            Pattern pattern = Pattern.compile(patternValue);
            Matcher matcher = pattern.matcher(input);
            boolean matches = matcher.groupCount() == 3;
            System.out.println("extractGroups               return matches=" + matches + "-input=" + input + "-patternValue=" + patternValue);
            if (matcher.find()) {
                String username = matcher.group(1); // "email"
                String domain = matcher.group(2);   // "domain"
                String tld = matcher.group(3);      // "com"
                System.out.println("                            extractGroups username=" + username + "-domain=" + domain + "-tld=" + tld);
            }
            return matches;
        }

        /*
        Explanation: "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
            25[0-5] → Matches 250 to 255.
            2[0-4][0-9] → Matches 200 to 249.
            [01]?[0-9][0-9]? → Matches 0 to 199 (with optional leading zero).
            \\. → Escaped . (literal dot).
            {3} → Repeats the previous group 3 times (for the first 3 octets).        
         */
 /*
            ✅ 1. Valid IPv4 Address Format - A valid IPv4 address: Consists of 4 octets (bytes) separated by dots: x.x.x.x. Each octet is in the range 0–255

            Examples of valid IPv4 addresses:
                192.168.1.1
                10.0.0.5
                172.16.254.3
                8.8.8.8
                127.0.0.1 (loopback)
            ✅ 2. Private vs Public IP Address Ranges
                Type            IP Range                        Use Case
                Private         10.0.0.0 – 10.255.255.255       Internal networks
                                172.16.0.0 – 172.31.255.255     Internal networks
                                192.168.0.0 – 192.168.255.255   Home/office routers
                Loopback        127.0.0.1 – 127.255.255.255     Localhost testing
                Public          Any IP not in the above         Internet-facing addresses
            ✅ 3. Reserved & Invalid Ranges
                Range               Description
                0.0.0.0/8           "This" network (non-routable)
                169.254.0.0/16      Link-local (auto-assign)
                255.255.255.255     Broadcast
            ✅ 4. IP Address Regex Validation (Java Example)
                public static boolean isValidIPv4(String ip) {
                    String pattern = 
                        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
                        + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
                    return ip.matches(pattern);
                }        
         */
        public static boolean matchingIp(String input, String patternValue) {
            boolean matches = false;
            if (input == null || input.isEmpty()) {
                input = "192.168.1.1";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
            }
            /*
        Explanation: "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
            25[0-5] → Matches 250 to 255.
            2[0-4][0-9] → Matches 200 to 249.
            [01]?[0-9][0-9]? → Matches 0 to 199 (with optional leading zero).
            \\. → Escaped . (literal dot).
            {3} → Repeats the previous group 3 times (for the first 3 octets).        
             */

            Pattern pattern = Pattern.compile(patternValue);
            Matcher matcher = pattern.matcher(input);
            matches = matcher.matches();
            System.out.println("matchingIp                  return matches=" + matches + "-input=" + input + "-patternValue=" + patternValue);
            return matches;
        }

        public static boolean matchingIp2(String input, String patternValue) {
            boolean matches = false;
            if (input == null || input.isEmpty()) {
                input = "256.1.1.1";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
            }

            Pattern pattern = Pattern.compile(patternValue);
            Matcher matcher = pattern.matcher(input);
            matches = matcher.matches();
            System.out.println("matchingIp2                 return matches=" + matches + "-input=" + input + "-patternValue=" + patternValue);
            return matches;
        }

        /*
        Explanation: "^[a-zA-Z][a-zA-Z0-9_]{7,29}$"
            ^[a-zA-Z] → Must start with a letter.
            [a-zA-Z0-9_]{7,29} → Followed by 7-29 letters, digits, or underscores.
            $ → Ensures no extra characters.        
         */
        public static boolean validateUsername(String input, String patternValue) {
            boolean matches = false;

            if (input == null || input.isEmpty()) {
                input = "User_123";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "^[a-zA-Z][a-zA-Z0-9_]{7,29}$";
            }

            Pattern pattern = Pattern.compile(patternValue);
            Matcher matcher = pattern.matcher(input);
            matches = matcher.matches();
            System.out.println("validateUsername            return matches=" + matches + "-input=" + input + "-patternValue=" + patternValue);
            return matches;
        }

        /*
        Explanation: "\\b(\\w+)(\\s+\\1\\b)+"
            \\b → Word boundary.
            (\\w+) → First word (captured in group 1).
            (\\s+\\1\\b)+ → One or more whitespace followed by the same word (\1 refers to the first group).        
         */
        public static boolean findingRepeatedWords(String input, String patternValue) {
            boolean matches = false;
            if (input == null || input.isEmpty()) {
                input = "hello hello";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "\\b(\\w+)(\\s+\\1\\b)+";
            }

            Pattern pattern = Pattern.compile(patternValue, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            matches = matcher.matches();
            System.out.println("findingRepeatedWords        return matches=" + matches + "-input=" + input + "-patternValue=" + patternValue);
            return matches;
        }

        /*
        Explanation:
            < → Starts with <.
            ([^>]+) → Captures one or more characters that are not >.
            > → Ends with >.        
         */
        public static boolean matchingHtmlTags(String input, String patternValue) {
            boolean matches = false;
            if (input == null || input.isEmpty()) {
                input = "<a href=\"#\">";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "<([^>]+)>";
            }

            Pattern pattern = Pattern.compile(patternValue);
            Matcher matcher = pattern.matcher(input);
            matches = matcher.matches();
            System.out.println("matchingHtmlTags            return matches=" + matches + "-input=" + input + "-patternValue=" + patternValue);
            return matches;
        }

        /*
        Explanation:
            [,] → Matches a single comma.
            split() → Breaks the string into an array wherever the pattern matches.        
         */
        public static void splittingStrings(String input, String patternValue) {
            if (input == null || input.isEmpty()) {
                input = "Hello,world,how,are,you";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "[,]";
            }

            String[] words = input.split(patternValue);
            System.out.println("splittingStrings        return words=" + Arrays.toString(words) + "-input=" + input + "-patternValue=" + patternValue);
        }

        public static void replacingPatterns(String input, String patternValue) {
            if (input == null || input.isEmpty()) {
                input = "Hello   World";
            }
            if (patternValue == null || patternValue.isEmpty()) {
                patternValue = "\\s+";
            }

            String result = input.replaceAll(patternValue, " "); // "Hello World"
            System.out.println("replacingPatterns               return result=" + result + "-input=" + input + "-patternValue=" + patternValue);
        }
    }
}
/*
Java Regex Examples for HackerRank Tests
Here are some useful Java regex patterns that can help you solve common HackerRank challenges:

1. Basic Pattern Matching
java
String input = "Hello123";
boolean matches = input.matches("^Hello\\d+$"); // true if starts with Hello and ends with digits
2. Extracting Groups
java
Pattern pattern = Pattern.compile("(\\w+)@(\\w+)\\.(\\w+)");
Matcher matcher = pattern.matcher("email@domain.com");
if (matcher.find()) {
    String username = matcher.group(1); // "email"
    String domain = matcher.group(2);   // "domain"
    String tld = matcher.group(3);      // "com"
}
3. Common HackerRank Patterns
a) Matching IP Addresses
java
String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
b) Validating Usernames
java
String usernamePattern = "^[a-zA-Z][a-zA-Z0-9_]{7,29}$";
c) Finding Repeated Words
java
Pattern p = Pattern.compile("\\b(\\w+)(\\s+\\1\\b)+", Pattern.CASE_INSENSITIVE);
d) Matching HTML Tags
java
Pattern tagPattern = Pattern.compile("<([^>]+)>");
4. Splitting Strings
java
String[] tokens = "Hello,world,how,are,you".split("[,]");
5. Replacing Patterns
java
String result = "Hello   World".replaceAll("\\s+", " "); // "Hello World"
6. Practical Examples
a) Count Words
java
String input = "The quick brown fox";
int wordCount = input.split("\\s+").length;
b) Validate Date Format (DD-MM-YYYY)
java
String datePattern = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|20)\\d\\d)$";
c) Extract All Numbers from String
java
Pattern numberPattern = Pattern.compile("\\d+");
Matcher m = numberPattern.matcher("abc123def456");
while (m.find()) {
    System.out.println(m.group());
}
// Output: 123, 456
Tips for HackerRank:
    Use Pattern.compile() and Matcher for complex patterns
    Remember to escape special characters with \\
    Use ^ and $ for start/end of string when needed
    Test your regex thoroughly with edge cases
    For performance, compile patterns once and reuse them
 */
