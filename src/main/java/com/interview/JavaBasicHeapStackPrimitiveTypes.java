/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

/**
 *
 * @author javaugi
 */
public class JavaBasicHeapStackPrimitiveTypes {
    
}
/*
The Java Heap and Stack are two fundamental memory areas used by the Java Virtual Machine (JVM) during the execution of a Java program. They serve different purposes and have distinct characteristics. Here's a comparison of the key differences:   

Feature         Java Heap                                                       Java Stack

Purpose         Stores objects and arrays created by the application.           Stores local variables, method calls, and execution environment.
Storage         Objects (instances of classes), arrays.                         Primitive data types (e.g., int, float), object references (pointers to objects in the heap), return addresses, local variables.
Management      Managed by the JVM's garbage collector. Memory is               Memory is allocated and deallocated in a LIFO (Last-In, First-Out) manner when methods are called and return.
                    allocated and deallocated automatically.
Size            Generally larger in size.  The size can be configured           Generally smaller compared to the heap. The size can also be configured using JVM options (e.g., -Xss).
                    using JVM options (e.g., -Xms, -Xmx).
Speed           Relatively slower to access compared to the stack               Faster to access as memory allocation and deallocation are simple pointer manipulations.
                    due to garbage collection overhead.
Thread Safety	Shared among all threads in the JVM. Access to objects in the   Each thread has its own private Java Stack. Therefore, it is inherently thread-safe for local variables and method calls within a thread.
                    heap needs to be synchronized to prevent race conditions.
Lifespan	Objects in the heap exist as long as they are reachable         Data in the stack frame exists only for the duration of the method execution. Once the method completes, its stack frame is popped off.
                    by any part of the application. They are eligible for 
                    garbage collection when they are no longer reachable.	
Exception	OutOfMemoryError is thrown if the heap becomes full             StackOverflowError is thrown if the depth of method calls exceeds the stack size limit.
                    and no more memory can be allocated.
*/

/*
Here's a table summarizing the primitive data types in Java, along with their default values and minimum/maximum values:

Primitive Type	Default Value	Minimum Value                       Maximum Value                       Size (bits)

byte            0               -128                                127                                 8
short           0               -32,768                             32,767                              16
int             0               -2,147,483,648                      2,147,483,647                       32
long            0L              -9,223,372,036,854,775,808          9,223,372,036,854,775,807           64
float           0.0f            Approximately ±1.4e-45              Approximately ±3.4e+38              32
double          0.0d            Approximately ±4.9e-324             Approximately ±1.8e+308             64
boolean         false           N/A (conceptually true or false)    N/A (conceptually true or false)    Varies (JVM-dependent, often treated as 1)
char            '\u0000'	'\u0000' (0)                        '\uffff' (65,535)                   16

Important Notes:
    Default Values: These are the values assigned to instance variables (fields) of a class if they are not explicitly 
        initialized. Local variables within methods do not have default values and must be initialized before use.
    Minimum and Maximum Values: You can access the minimum and maximum values for numeric primitive types (except 
        boolean and char) using the corresponding wrapper classes' constant values:
        Byte.MIN_VALUE,         Byte.MAX_VALUE
        Short.MIN_VALUE,        Short.MAX_VALUE
        Integer.MIN_VALUE,      Integer.MAX_VALUE
        Long.MIN_VALUE,         Long.MAX_VALUE
        Float.MIN_VALUE,        Float.MAX_VALUE   
        Double.MIN_VALUE,       Double.MAX_VALUE
        Character.MIN_VALUE,        Character.MAX_VALUE   
    boolean: The boolean type represents a logical value that can be either true or false. It doesn't have a numeric
        minimum or maximum in the same way as other primitive types.
    char: The char type represents a single 16-bit Unicode character. Its range corresponds to the entire 
        Unicode Basic Multilingual Plane (BMP).
    Size: The size in bits is fixed for each primitive type, ensuring portability across different platforms.
    
This table provides a quick reference for the fundamental characteristics of Java's primitive data types.
*/