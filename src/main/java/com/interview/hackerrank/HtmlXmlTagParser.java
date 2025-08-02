/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlXmlTagParser {

    private static final Pattern HTML_XML_PATTERN = Pattern.compile("<(.+?)>([^<]+)</\\1>");
    private static final Pattern XML_PATTERN = Pattern.compile("(?:<TAG.*?>)(.*?)(?:<\\/TAG>)");
    private static Pattern HTML_PATTERN = Pattern.compile("<(?:\"[^\"]*\"['\"]*|'[^']*'['\"]*|[^'\">])+>");

    /*
    The regex pattern <(.+?)>([^<]+)</\\1> breaks down as:
        <(.+?)>: Matches the opening tag and captures the tag name
        ([^<]+): Captures the content (anything except '<')
        </\\1>: Matches the closing tag with the same name as the opening tag   
    
        <(.+?)>  .+?: one or more times of any characters - name inside the opening tag <>
     */
    public static void main(String[] args) {
        String html = "<h1>Valid content</h1><h1><a>Nested</a>Invalid</h1>";
        doJsoupParser(html);

        System.out.println("\n Calling tagContentExtractor ...");
        List<String> list = Arrays.asList("<h1>Nayeem loves counseling</h1>", "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
                "<Amee>safat codes like a ninja</amee>", "<SA premium>Imtiaz has a secret crush</SA premium>");
        tagContentExtractor(list);
        /* result:
            Nayeem loves counseling
            Sanjay has no watch
            So wait for a while
            None
            Imtiaz has a secret crush        
         */
        
        System.out.println("\n Calling tagContentExtractor using HTML_XML_PATTERN ");
        tagContentExtractor(HTML_XML_PATTERN, list);
    }

    private static void doJsoupParser(String html) {
        Document doc = Jsoup.parse(html);

        for (Element element : doc.select("*")) {
            if (element.childNodeSize() == 1 && !element.html().contains("<")) {
                System.out.println(element.text());
            }
        }
    }
    
    private static void tagContentExtractor(Pattern pattern, List<String> list) {
        for (String line : list) {
            Matcher matcher = pattern.matcher(line);
            boolean found = false;

            while (matcher.find()) {
                System.out.println(matcher.group(2));
                found = true;
            }

            if (!found) {
                System.out.println("None");
            }
        }
    }

    private static void tagContentExtractor(List<String> list) {
        for (String line : list) {
            Matcher matcher = HTML_XML_PATTERN.matcher(line);
            boolean found = false;

            while (matcher.find()) {
                System.out.println(matcher.group(2));
                found = true;
            }

            if (!found) {
                System.out.println("None");
            }
        }
    }

    private static void scanner() {
        Scanner scanner = new Scanner(System.in);
        int numLines = Integer.parseInt(scanner.nextLine());

        while (numLines-- > 0) {
            String line = scanner.nextLine();
            Matcher matcher = HTML_XML_PATTERN.matcher(line);
            boolean found = false;

            while (matcher.find()) {
                System.out.println(matcher.group(2));
                found = true;
            }

            if (!found) {
                System.out.println("None");
            }
        }

        scanner.close();
    }

    /*
Explanation:
Pattern Matching:

The regex pattern <(.+?)>([^<]+)</\\1> breaks down as:
    <(.+?)>: Matches the opening tag and captures the tag name
    ([^<]+): Captures the content (anything except '<')
    </\\1>: Matches the closing tag with the same name as the opening tag

Processing Logic:
    For each input line, we search for all valid tag-content pairs
    When a match is found, we print the content (group 2 of the match)
    If no valid content is found, we print "None"

Handling Edge Cases:
    Nested tags are automatically excluded because the regex only matches non-nested content
    Case-sensitive matching ensures tags like <Amee> and </amee> won't match
    The solution handles tags with spaces (like <SA premium>)

Sample Execution:
For the input:
    4
    <h1>Nayeem loves counseling</h1>
    <h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>
    <Amee>safat codes like a ninja</amee>
    <SA premium>Imtiaz has a secret crush</SA premium>
The program will output:
    Nayeem loves counseling
    Sanjay has no watch
    So wait for a while
    None
    Imtiaz has a secret crush    
     */
}
