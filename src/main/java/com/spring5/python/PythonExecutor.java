/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.python;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.python.util.PythonInterpreter;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyBaseString;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonExecutor {
    public static final PythonInterpreter PY_EXEC = new PythonInterpreter();
    
    public static void main(String[] args) {
        PY_EXEC.exec("print('Hello from Jython')");
        
        int[] costs = {5, 3, 7, 2};
        List<List<Integer>> features = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(2, 4),
            Arrays.asList(1, 3, 5),
            Arrays.asList(4, 5)
        );
        
        Gson gson = new Gson();
        String pyCosts = gson.toJson(costs);
        String pyFeatures = gson.toJson(features);
        System.out.println("PythonExecutor pyCosts=" + pyCosts + "-pyFeatures=" + pyFeatures);
        runMinCost(pyCosts, pyFeatures);
    }
    
    private static void runMinCost(String costs, String features) {                
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "py/minCostModels.py", costs, features);
            Process process = pb.start();
           // Read the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }             
    }
}
