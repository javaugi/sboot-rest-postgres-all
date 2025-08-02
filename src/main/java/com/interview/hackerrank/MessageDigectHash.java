/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author javaugi
 */
public class MessageDigectHash {
    public static void main(String[] args) {
        //scanner();
        String input = "M@keItH@rdToGuess_101";
        System.out.println("\n md5Hash with input=" + input);
        md5Hash(input);
        System.out.println("\n sha1Hash with input=" + input);
        sha1Hash(input);
        System.out.println("\n sha256Hash with input=" + input);
        sha256Hash(input);
    }    

    private static void scanner() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        
        md5Hash(input);
    }    

    private static void md5Hash(String input) {
        try {
            // Create MD5 Hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            
            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            
            System.out.println(hexString.toString());
            String md5Hex = DigestUtils.md5Hex(input);
            System.out.println("md5Hex hexString=" + hexString.toString() + "-md5Hex=" + md5Hex + "-equals=" + (md5Hex.equals(hexString.toString())));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("MD5 algorithm not found");
        }
    }    
    
    private static void sha1Hash(String input) {
        try {
            // Create MD5 Hash
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            
            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            
            System.out.println(hexString.toString());
            String md5Hex = DigestUtils.sha1Hex(input);
            System.out.println("sha1Hex hexString=" + hexString.toString() + "-md5Hex=" + md5Hex + "-equals=" + (md5Hex.equals(hexString.toString())));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-1 algorithm not found");
        }
    }    
    
    private static void sha256Hash(String input) {
        try {
            // Create MD5 Hash
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            
            // Convert byte array to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            
            System.out.println(hexString.toString());
            String md5Hex = DigestUtils.sha256Hex(input);
            System.out.println("sha256Hex hexString=" + hexString.toString() + "-md5Hex=" + md5Hex + "-equals=" + (md5Hex.equals(hexString.toString())));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-256 algorithm not found");
        }
    }    
}

/*
Explanation:
MessageDigest Class:
    We use Java's MessageDigest class from the java.security package to compute the MD5 hash
    Get an instance of the MD5 algorithm with MessageDigest.getInstance("MD5")

Hashing Process:
    Convert the input string to bytes using getBytes()
    Update the digest with these bytes using md.update()
    Complete the hash computation with md.digest(), which returns a byte array

Byte to Hex Conversion:
    Convert each byte in the digest to a 2-digit hexadecimal value
    Use String.format("%02x", b) to ensure each byte is represented by exactly 2 hex digits
    Append all hex values together to form the final hash string

Output:
Print the resulting hexadecimal string in lowercase

Sample Execution:
For input:
    HelloWorld
The program will output:
    68e109f0f40ca72a15e05cc22786f8e6
For input:
    Javarmi123
The program will output:
    2da2d1e0ce7b4951a858ed2d547ef485
Important Notes:
Security Warning:
    MD5 is considered cryptographically broken and should not be used for security-critical applications
    For password hashing, consider using stronger algorithms like SHA-256, SHA-3, or PBKDF2

Alternative Implementation (using Apache Commons Codec):

    import org.apache.commons.codec.digest.DigestUtils;
    String md5Hex = DigestUtils.md5Hex(input);
Input Constraints:
    The program handles all alphanumeric characters (a-z, A-Z, 0-9)
    No special characters or spaces are expected based on the problem constraints
    This solution provides a straightforward implementation of MD5 hashing in Java while properly handling the byte-to-hex conversion that's required for the standard MD5 hash representation.
*/