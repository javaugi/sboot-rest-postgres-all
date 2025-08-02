/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

import com.interview.hackerrank.Student;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
/*
1×× Informational

2×× Success

3×× Redirection

4×× Client Error * 400 Bad Request * 401 Unauthorized ... * 405 Method Not Allowed ...

5×× Server Error * 500 Internal Server Error * 501 Not Implemented * 502 Bad Gateway ...

In other words, each major number (200, 400, 500, etc.) is a CATEGORY. You can "refine" the error code by choosing a specific error within the "category".

To your original question:

If the Client request is "bad" (for example, illegal username/password), then return a 4xx.

If the Server somehow fails (for example, you can't read the database), then return a 5xx.

The "official" list of HTTP error codes is RFC 7231:
*/

public class PrivateInnerClassMethod {

    public static void main(String[] args) throws Exception {
        //runMain1();
        printStudentMethods();
    }//end of main

    private static void runMain1() throws IOException, SecurityException, ExitControl.ExitTrappedException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        ExitControl.forbidExit(405);
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine().trim());
            Object o;// Must be used to hold the reference of the instance of the class Solution.Inner.Private

            //Write your code here
            o = new Inner().new Private();

            Method method = o.getClass().getDeclaredMethod("powerof2", int.class);
            method.setAccessible(true);
            String result = (String) method.invoke(o, num);
            System.out.println(num + " is " + result);
            System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");

        }//end of try
        catch (ExitControl.ExitTrappedException e) {
            System.out.println("Unsuccessful Termination!!");
        }
    }

    private static void printStudentMethods() {
        Class student = Student.class;
        Method[] methods = student.getDeclaredMethods();

        ArrayList<String> methodList = new ArrayList<>();
        for (Method method : methods) {            
            methodList.add(method.getName());
        }
        Collections.sort(methodList);
        for (String name : methodList) {
            System.out.println(name);
        }        
    }
    
    
    static class Inner {

        private class Private {

            private String powerof2(int num) {
                return ((num & num - 1) == 0) ? "power of 2" : "not a power of 2";
            }
        }
    }//end of Inner

}//end of Solution

/*
class DoNotTerminate { //This class prevents exit(0)

    public static class ExitTrappedException extends SecurityException {

        private static final long serialVersionUID = 1L;
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}
// */

class ExitControl {
    public static class ExitTrappedException extends SecurityException {
        public ExitTrappedException(String message) {
            super(message);
        }
        private static final long serialVersionUID = 1L;
    }

    private static boolean exitAllowed = true;

    public static void allowExit(boolean allow) {
        exitAllowed = allow;
    }

    public static void forbidExit(int status) throws ExitTrappedException {
        if (!exitAllowed) {
            throw new ExitTrappedException("System.exit blocked with ThrowTrapperException");
        }
        System.exit(status);
    }
}
