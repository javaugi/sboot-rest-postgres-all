/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author javaugi
 */
public class CovariantReturnTypes {
    //1. DeekSeek solution
    //2. Original assignemnt
    //3. Covariance types - theory
    //4. Covariance type theory from Google
    // 5. ChatGPT with examples
    /*
    Term  /Analogy
    Covariance (? extends)      
        Fruit basket where you can look (read) but can't put new fruits                 (can numbers.get(0) but cannot numbers.add(2)
    Contravariance (? super)    
        Trash bin where you can throw (write) things in but can't take them out easily  (cannot numbers.get(0) but can numbers.add(2)
    Invariance                     
        Fixed type box where everything must match exactly    
    */
    public static void main(String[] args) throws IOException {
        //readerMain();
        printRegionStateFlower("AndhraPradesh"); //WestBengal
        printRegionStateFlower("AndhraPradesh", "WestBengal"); //WestBengal
    }
    
    private static void invarianceDemo() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        // List<Number> numbers = integers; // ❌ Compilation error!

        List<Number> numbers = new ArrayList<>();
        numbers.add(2.5); // ok, a Double
        numbers.add(3);   // ok, an Integer
    }

    //(Reading only — ? extends)
    // You allow a subtype relationship — List<? extends Number> can accept List<Integer>, List<Float>, etc.
    // But you cannot add elements (except null) because you don't know the exact subtype.
    private static void covarianceDemo() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<? extends Number> numbers = integers; // ✅ OK
        Number n = numbers.get(0); // ✅ OK to read

        // numbers.add(10); // ❌ Compilation error!
        // numbers.add(2.5); // ❌ Still error!

        //✅ You can read elements safely as Number.
        //❌ You cannot write elements because Java doesn't know what specific subtype the list expects.
        System.out.println("n=" + n + "-all numbers=" + numbers); // prints 1
    }
    
    private static void contravarianceDemo() {
        List<? super Integer> numbers = new ArrayList<>();
        numbers.add(1); // ✅ OK
        numbers.add(2); // ✅ OK

        Object obj = numbers.get(0); // ✅ OK to read as Object
        // Integer num = numbers.get(0); // ❌ Not safe without cast

        //✅ You can write Integer values.
        //❌ You can only read as Object (not directly as Integer).
        System.out.println(obj); // prints 1        
    }

    private static void readerMain() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine().trim();
        printRegionStateFlower(s);
    }
    
    public static final GenericPrint doPrint = ele -> System.out.println(ele);
    
    private static void printRegionStateFlower(String... args) {
        Arrays.stream(args).forEach(s -> {
            switch (s) {
                case "WestBengal" -> System.out.println(new WestBengal().yourNationalFlower().whatsYourName());
                case "AndhraPradesh" -> System.out.println(new AndhraPradesh().yourNationalFlower().whatsYourName());
            }     
        });
    }

    private static void printRegionStateFlower(String s) {
        Region region = null;
        switch (s) {
            case "WestBengal":
                region = new WestBengal();
                break;
            case "AndhraPradesh":
                region = new AndhraPradesh();
                break;
        }
        Flower flower = region.yourNationalFlower();
        System.out.println(flower.whatsYourName());
    }
    
}
/*
To solve this problem, we need to implement classes that demonstrate the use of covariant return types in Java. The task involves creating classes for different states and their respective national flowers, ensuring that the method overriding in the state classes returns the specific flower type, which is a subclass of the original return type.

Approach
Understand Covariant Return Types: Covariant return type allows an overriding method to return a subclass of the return type of the overridden method. This means if a superclass method returns a type A, the overriding method in the subclass can return a type B where B is a subclass of A.

Class Hierarchy:

Flower Class: This will be the superclass for all specific flower types.

State Class: This will be the superclass for all state classes, with a method yourNationalFlower that returns a Flower object.

State Subclasses (WestBengal, AndhraPradesh, Karnataka): Each will override the yourNationalFlower method to return their specific flower type (subclass of Flower).

Flower Subclasses (Jasmine, Lily, Lotus): Each will have a method whatsYourName that returns the name of the flower as a string.

Solution Code
java
class Flower {
    String whatsYourName() {
        return "I have many names and types";
    }
}

class Jasmine extends Flower {
    @Override
    String whatsYourName() {
        return "Jasmine";
    }
}

class Lily extends Flower {
    @Override
    String whatsYourName() {
        return "Lily";
    }
}

class Lotus extends Flower {
    @Override
    String whatsYourName() {
        return "Lotus";
    }
}

class State {
    Flower yourNationalFlower() {
        return new Flower();
    }
}

class WestBengal extends State {
    @Override
    Jasmine yourNationalFlower() {
        return new Jasmine();
    }
}

class AndhraPradesh extends State {
    @Override
    Lily yourNationalFlower() {
        return new Lily();
    }
}

class Karnataka extends State {
    @Override
    Lotus yourNationalFlower() {
        return new Lotus();
    }
}
Explanation
Flower Class: This is the base class with a method whatsYourName that returns a generic string. This method will be overridden by each specific flower type.

Jasmine, Lily, Lotus Classes: These classes extend Flower and override the whatsYourName method to return their respective flower names.

State Class: This class has a method yourNationalFlower that returns a generic Flower object. This method will be overridden by each state subclass to return a specific flower type.

State Subclasses (WestBengal, AndhraPradesh, Karnataka): Each state subclass overrides the yourNationalFlower method to return an instance of their respective flower class (Jasmine, Lily, or Lotus). This demonstrates covariant return types as each subclass returns a more specific type than the superclass method.

When the main method (provided in the locked code) creates an instance of a state subclass and calls yourNationalFlower, it gets the specific flower instance, and calling whatsYourName on this instance returns the correct flower name, which is then printed. For example, if the input is "AndhraPradesh", the output is "Lily" because the yourNationalFlower method of AndhraPradesh returns a Lily instance, and its whatsYourName method returns "Lily".
*/
class Flower {
    String whatsYourName() {
        return "I have many names and types";
    }
}

class Jasmine extends Flower {
    @Override
    String whatsYourName() {
        return "Jasmine";
    }
}

class Lily extends Flower {
    @Override
    String whatsYourName() {
        return "Lily";
    }
}

class Lotus extends Flower {
    @Override
    String whatsYourName() {
        return "Lotus";
    }
}

class Region {
    Flower yourNationalFlower() {
        return new Flower();
    }
}

class State extends Region {
    @Override
    Flower yourNationalFlower() {
        return new Flower();
    }
}

class WestBengal extends State {
    @Override
    Jasmine yourNationalFlower() {
        return new Jasmine();
    }
}

class AndhraPradesh extends State {
    @Override
    Lily yourNationalFlower() {
        return new Lily();
    }
}

class Karnataka extends State {
    @Override
    Lotus yourNationalFlower() {
        return new Lotus();
    }
}

/*
Java allows for Covariant Return Types, which means you can vary your return type as long you are returning a subclass of your specified return type.

Method Overriding allows a subclass to override the behavior of an existing superclass method and specify a return type that is some subclass of the original return type. It is best practice to use the @Override annotation when overriding a superclass method.

Implement the classes and methods detailed in the diagram below:

image

You will be given a partially completed code in the editor where the main method takes the name of a state (i.e., WestBengal, or AndhraPradesh) and prints the national flower of that state using the classes and methods written by you.

Note: Do not use access modifiers in your class declarations.

Resources
Covariant Return Type
Java Covariant Type

Input Format

The locked code reads a single string denoting the name of a subclass of State (i.e., WestBengal, Karnataka, or AndhraPradesh), then tests the methods associated with that subclass. You are not responsible for reading any input from stdin.

Output Format

Output is handled for you by the locked code, which creates the object corresponding to the input string's class name and then prints the name returned by that class' national flower's whatsYourName method. You are not responsible for printing anything to stdout.

Sample Input 0

AndhraPradesh
Sample Output 0

Lily
Explanation 0

An AndhraPradesh object's yourNationalFlower method returns an instance of the Lily class, and the Lily class' whatsYourName method returns Lily, which is printed by the hidden code checker.
*/

/*
Let's say that we are designing a programming language and we want type safety. One of the places that type safety crops up is in the area of method returns. If I have a method named clone, for instance, and it returns a reference to Object, everything is fine as anyone who gets the returned object expects it to be an Object, nothing more:
        // this example is not in Java, but I hijack the syntax..

        Object aClone  = aThing.clone ();

The only problem is that a message like clone is bound to be very useful, so useful that you'd probably want a bunch of different object types to be able to respond to it in their own unique way. So you subclass and override clone:
        class Classy extends Object
        {
            public Object clone () {
                ...
            }
        }

But, then you have to:
        Classy aCopy   = (Classy)aClassyThing.clone ();

Why? Because the return type of clone is Object, so all of the overrides have to return Object also. This is known as invariance.
But, is it necessary?
What if the language allowed this:
        class Classy extends Object
        {
            // this clone overrides Object Object.clone ()
            public Classy clone () {
                ...
            }
        }

        Classy aClassyThing = new Classy ();
        Classy aCopy = aClassyThing.clone ();

By the way..
        Object aThing = aClassyThing;
        Object aCopy = aThing.clone ();

would call the same clone: the one for Classy. Why? Because the object itself knows how to respond to clone. All the compiler has to do is permit the assignment when it can be determined to be safe. Assigning to a more general type reference is always safe.
These are CovariantReturnTypes. The name comes from the fact that the type of the return is allowed to vary in the same direction that you subclass.
*/

/*
Covariance in Java refers to the ability of a subtype to be used in place of its supertype, specifically in the context of generic types and arrays. It allows for more flexible and intuitive type relationships. In simpler terms, if B is a subtype of A, then K<B> is considered a subtype of K<A>. 
Covariance in Arrays
Arrays in Java are covariant. If B is a subtype of A, then B[] is also a subtype of A[]. 
Java

Integer[] intArray = {1, 2, 3};
Number[] numberArray = intArray; // Valid because Integer is a subtype of Number
However, this can lead to ArrayStoreException at runtime if you attempt to assign an element of an incompatible type to the array:
Java

numberArray[0] = 3.14; // Throws ArrayStoreException
Covariance with Generic Types
Generics in Java are invariant by default, meaning that List<B> is not a subtype of List<A> even if B is a subtype of A. To achieve covariance with generics, you can use the ? extends wildcard:
Java

List<Integer> integerList = new ArrayList<>();
List<? extends Number> numberList = integerList; // Valid because List<Integer> is a subtype of List<? extends Number>
With a covariant generic type like List<? extends Number>, you can read elements from the list, but you cannot add elements to it (except for null), to maintain type safety.
Covariant Return Types
Java also supports covariant return types, where a subclass can override a method and specify a more specific return type that is a subtype of the original return type:
Java

class Animal {
    public Animal reproduce() {
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog reproduce() {
        return new Dog();
    }
}
In this case, the Dog class overrides the reproduce method and returns a Dog object, which is a subtype of Animal.
*/

/*
1. Invariance 🚫 (Default in Java Generics)
Definition:
If A is a subtype of B, it does NOT mean List<A> is a subtype of List<B>.

Example:

import java.util.ArrayList;
import java.util.List;

public class InvarianceExample {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);

        // List<Number> numbers = integers; // ❌ Compilation error!

        List<Number> numbers = new ArrayList<>();
        numbers.add(2.5); // ok, a Double
        numbers.add(3);   // ok, an Integer
    }
}
👉 Even though Integer is a subtype of Number,
List<Integer> is NOT a subtype of List<Number>.

Why?
Because allowing this could break type safety (e.g., inserting a Double into a List<Integer>).

2. Covariance ✅ (Reading only — ? extends)
Definition:
You allow a subtype relationship — List<? extends Number> can accept List<Integer>, List<Float>, etc.
But you cannot add elements (except null) because you don't know the exact subtype.

Example:

import java.util.Arrays;
import java.util.List;

public class CovarianceExample {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        List<? extends Number> numbers = integers; // ✅ OK

        Number n = numbers.get(0); // ✅ OK to read

        // numbers.add(10); // ❌ Compilation error!
        // numbers.add(2.5); // ❌ Still error!

        System.out.println(n); // prints 1
    }
}
✅ You can read elements safely as Number.
❌ You cannot write elements because Java doesn't know what specific subtype the list expects.

Summary:

Good for reading.

Safe output, but can't accept input.

3. Contravariance 🔄 (Writing only — ? super)
Definition:
You allow supertype relationships — List<? super Integer> can accept Integer, Number, or Object types.
You can safely add elements, but reading gives only Object.

Example:

import java.util.ArrayList;
import java.util.List;

public class ContravarianceExample {
    public static void main(String[] args) {
        List<? super Integer> numbers = new ArrayList<>();

        numbers.add(1); // ✅ OK
        numbers.add(2); // ✅ OK

        Object obj = numbers.get(0); // ✅ OK to read as Object
        // Integer num = numbers.get(0); // ❌ Not safe without cast

        System.out.println(obj); // prints 1
    }
}
✅ You can write Integer values.
❌ You can only read as Object (not directly as Integer).

Summary:

Good for writing.

Safe input, but output is generic.

🛑 Why it matters
When designing generic APIs, you choose:


Situation	Use
Need to only read from a structure?	Use covariance (? extends Type)
Need to only write to a structure?	Use contravariance (? super Type)
Need to read and write?	Use invariant (no wildcard)
🔥 One-Liner Tip: "PECS"
Producer Extends, Consumer Super


Role	Wildcard
If your generic produces objects → use extends	
If your generic consumes objects → use super	
🌟 Quick real-world analogy:

    Term                            Analogy
    Covariance (? extends)          Fruit basket where you can look (read) but can't put new fruits
    Contravariance (? super)        Trash bin where you can throw (write) things in but can't take them out easily
    Invariance                      Fixed type box where everything must match exactly
*/