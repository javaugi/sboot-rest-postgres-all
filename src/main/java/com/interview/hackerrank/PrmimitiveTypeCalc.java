/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

/**
 *
 * @author javaugi
 */
public class PrmimitiveTypeCalc {

    public static void main(String[] args) {
        /*
        Byte:  -128 to 127
        Short: -32768 to 32767
        Int:   -2147483648 to 2147483647
        Long:  -9223372036854775808 to 9223372036854775807
         */

        System.out.println("Do Calc 1 ...");
        calc1();
        System.out.println("Do Calc 2 ...");
        calc2();
    }

    private static void calc2() {
        // Byte (-128 to 127)
        System.out.println("Byte:  " + ((byte) -Math.pow(2, 7)) + " to " + ((byte) (Math.pow(2, 7) - 1)));

        // Short (-32,768 to 32,767)
        System.out.println("Short: " + ((short) -Math.pow(2, 15)) + " to " + ((short) (Math.pow(2, 15) - 1)));

        // Int (-2^31 to 2^31-1)
        System.out.println("Int:   " + ((int) -Math.pow(2, 31)) + " to " + ((int) (Math.pow(2, 31) - 1)));

        // Long (-2^63 to 2^63-1)
        System.out.println("Long:  " + ((long) -Math.pow(2, 63)) + " to " + ((long) (Math.pow(2, 63) - 1)));
    }

    private static void calc1() {
        // Byte (-128 to 127)
        System.out.println("Byte:  " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);

        // Short (-32,768 to 32,767)
        System.out.println("Short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);

        // Int (-2^31 to 2^31-1)
        System.out.println("Int:   " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);

        // Long (-2^63 to 2^63-1)
        System.out.println("Long:  " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);

        /* int (32-bit signed integer) */
        System.out.println("int(min) = " + Integer.MIN_VALUE);
        //int(min) = -2147483648
        System.out.println("int(max) = " + Integer.MAX_VALUE);
        //int(max) = 2147483647
        System.out.println("int(bit-length) = " + Integer.SIZE);
        //int(bit-length) = 32

        /* byte (8-bit signed integer) */
        System.out.println("byte(min) = " + Byte.MIN_VALUE);
        //byte(min) = -128
        System.out.println("byte(max) = " + Byte.MAX_VALUE);
        //byte(max) = 127
        System.out.println("byte(bit-length) = " + Byte.SIZE);
        //byte(bit-length) = 8

        /* short (16-bit signed integer) */
        System.out.println("short(min) = " + Short.MIN_VALUE);
        //short(min) = -32768
        System.out.println("short(max) = " + Short.MAX_VALUE);
        //short(max) = 32767
        System.out.println("short(bit-length) = " + Short.SIZE);
        //short(bit-length) = 16

        /* long (64-bit signed integer) */
        System.out.println("long(min) = " + Long.MIN_VALUE);
        //long(min) = -9223372036854775808
        System.out.println("long(max) = " + Long.MAX_VALUE);
        //long(max) = 9223372036854775807
        System.out.println("long(bit-length) = " + Long.SIZE);
        //long(bit-length) = 64

        /* char (16-bit character or 16-bit unsigned integer) */
        System.out.println("char(min) = " + (int) Character.MIN_VALUE);
        //char(min) = 0
        System.out.println("char(max) = " + (int) Character.MAX_VALUE);
        //char(max) = 65535
        System.out.println("char(bit-length) = " + Character.SIZE);
        //char(bit-length) = 16

        /* float (32-bit floating-point) */
        System.out.println("float(min) = " + Float.MIN_VALUE);
        //float(min) = 1.4E-45
        System.out.println("float(max) = " + Float.MAX_VALUE);
        //float(max) = 3.4028235E38
        System.out.println("float(bit-length) = " + Float.SIZE);
        //float(bit-length) = 32

        /* double (64-bit floating-point) */
        System.out.println("double(min) = " + Double.MIN_VALUE);
        //double(min) = 4.9E-324
        System.out.println("double(max) = " + Double.MAX_VALUE);
        //double(max) = 1.7976931348623157E308
        System.out.println("double(bit-length) = " + Double.SIZE);
        //double(bit-length) = 64        

    }
}

/*
Key Points: Wrapper Classes contain these values as constants:
    Byte.MIN_VALUE/MAX_VALUE
    Short.MIN_VALUE/MAX_VALUE
    Integer.MIN_VALUE/MAX_VALUE
    Long.MIN_VALUE/MAX_VALUE

Pattern: For an N-bit signed integer type:
    Minimum = -2^(N-1)
    Maximum = 2^(N-1) - 1
Unsigned Types (Java 8+):
    int unsignedMax = Integer.parseUnsignedInt("4294967295");

Would you like me to explain any particular aspect in more detail?
*/
