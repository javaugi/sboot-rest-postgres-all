/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author javaugi
 */
public class MinCostModels {
    /*
Solving the Minimum Cost AI Models Problem
This problem requires selecting AI models to cover all required features at minimum cost. Here's how to approach it:
    Problem Understanding
    Given:
        costs: Array where costs[i] is the cost of the ith model
        features: 2D array where features[i] is an array of features the ith model provides
    Goal: Find the subset of models that covers all unique features at the minimum total cost.    
    
    Solution Approach
    Optimal Solution (Using Bitmasking for Small Feature Sets)
    When the number of unique features is small (≤20), we can use bitmasking for an efficient solution:    
    */

    public static void main(String[] args) {
        calcSelectModels();        
        minCostModels();
    }
    
    private static void calcSelectModels() {
        System.out.println("*** Starting calcSelectModels ");
        int[] costs = {3, 6, 2, 4, 7, 1};
        String[] features = {"01", "10", "11", "01", "01", "11"};
        System.out.println("calcSelectModels \n orig cost=" + Arrays.toString(costs) + "\n orig features=" + Arrays.toString(features));
        
        int[] selected = selectModels(costs, features);
        System.out.println("Selected model indices: " + Arrays.toString(selected));
        
        // Calculate total cost
        int totalCost = 0;
        for (int idx : selected) {
            totalCost += costs[idx];
        }
        System.out.println("Total cost: " + totalCost);
        System.out.println("*** Done calcSelectModels ");
    }
    
    public static int[] selectModels(int[] costs, String[] features) {
        // Determine all required features by OR-ing all feature bits
        int requiredFeatures = 0;
        for (String feat : features) {
            requiredFeatures |= Integer.parseInt(feat, 2);
        }
        
        int n = costs.length;
        int minCost = Integer.MAX_VALUE;
        List<Integer> bestIndices = new ArrayList<>();
        
        // Try all possible combinations of models
        for (int mask = 1; mask < (1 << n); mask++) {
            int currentFeatures = 0;
            int currentCost = 0;
            List<Integer> currentIndices = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentFeatures |= Integer.parseInt(features[i], 2);
                    currentCost += costs[i];
                    currentIndices.add(i);
                    
                    // Early termination if cost exceeds current minimum
                    if (currentCost >= minCost) {
                        break;
                    }
                }
            }
            
            // Check if all features are covered and cost is lower
            if (currentFeatures == requiredFeatures && currentCost < minCost) {
                minCost = currentCost;
                bestIndices = new ArrayList<>(currentIndices);
            }
        }
        
        // Convert to int array
        int[] result = new int[bestIndices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bestIndices.get(i);
        }
        return result;
    }
    
    private static void minCostModels() {
        System.out.println("--- Starting calcSelectModels ");
        // Example usage
        int[] costs = {5, 3, 7, 2};
        List<List<Integer>> features = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(2, 4),
                Arrays.asList(1, 3, 5),
                Arrays.asList(4, 5)
        );        
        System.out.println("minCostModels \n orig cost=" + Arrays.toString(costs) + "\n orig features=" + features);
        
        // For small feature sets (≤20 unique features)
        List<Integer> optimalSolution = MinCostModels.minCostModels(costs, features);
        System.out.println("Optimal solution: " + optimalSolution);

        System.out.println("--- Starting greedySolution ");
        // For large feature sets
        List<Integer> greedySolution = MinCostModelsGreedy.minCostModels(costs, features);
        System.out.println("Greedy solution: " + greedySolution);        
        System.out.println("--- Done calcSelectModels ");
    }

    //1. Optimal Bitmask Solution (for small feature sets ≤20)
    public static List<Integer> minCostModels(int[] costs, List<List<Integer>> features) {
        Set<Integer> allFeatures = new HashSet<>();
        for (List<Integer> model : features) {
            allFeatures.addAll(model);
        }

        Set<Integer> remainingFeatures = new HashSet<>(allFeatures);
        List<Integer> selectedModels = new ArrayList<>();
        int totalCost = 0;

        while (!remainingFeatures.isEmpty()) {
            double bestRatio = Double.NEGATIVE_INFINITY;
            Integer bestModel = null;
            Set<Integer> bestNewFeatures = new HashSet<>();

            for (int i = 0; i < features.size(); i++) {
                if (costs[i] == 0) {
                    continue;
                }

                // Features this model could add
                Set<Integer> newFeatures = new HashSet<>(remainingFeatures);
                newFeatures.retainAll(features.get(i));

                if (newFeatures.isEmpty()) {
                    continue;
                }

                // Cost per new feature ratio
                double ratio = (double) newFeatures.size() / costs[i];
                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    bestModel = i;
                    bestNewFeatures = new HashSet<>(newFeatures);
                }
            }

            if (bestModel == null) {
                return new ArrayList<>();  // Can't cover all features
            }

            selectedModels.add(bestModel);
            totalCost += costs[bestModel];
            remainingFeatures.removeAll(bestNewFeatures);
        }

        return selectedModels;
    } 
    
    /*
Explanation
Bitmask Solution:
    Converts each model's features into a bitmask
    Checks all possible combinations of models (2^n possibilities)
    Tracks the combination that covers all features at minimum cost
    Perfect for small feature sets (n ≤ 20)
Greedy Solution:
    At each step, selects the model with best "features per cost" ratio
    Doesn't guarantee optimal solution but works for large inputs
    Much faster for problems with many features
Optimization Tips
    Early Termination: Stop evaluating a combination if its cost already exceeds the current minimum.
    Preprocessing: Sort models by cost/feature ratio to potentially find good solutions faster.
    Memoization: Cache intermediate results if the problem allows.
Time Complexity
    Bitmask Solution: O(2^m * n) where m is number of models and n is number of features
    Greedy Solution: O(m^2 * f) where f is average features per model    
    */

    class MinCostModelsGreedy {
        //2. Greedy Approximation Solution (for large feature sets)
        public static List<Integer> minCostModels(int[] costs, List<List<Integer>> features) {
            Set<Integer> allFeatures = new HashSet<>();
            for (List<Integer> model : features) {
                allFeatures.addAll(model);
            }

            Set<Integer> remainingFeatures = new HashSet<>(allFeatures);
            List<Integer> selectedModels = new ArrayList<>();
            int totalCost = 0;

            while (!remainingFeatures.isEmpty()) {
                double bestRatio = Double.NEGATIVE_INFINITY;
                Integer bestModel = null;
                Set<Integer> bestNewFeatures = new HashSet<>();

                for (int i = 0; i < features.size(); i++) {
                    if (costs[i] == 0) {
                        continue;
                    }

                    // Features this model could add
                    Set<Integer> newFeatures = new HashSet<>(remainingFeatures);
                    newFeatures.retainAll(features.get(i));

                    if (newFeatures.isEmpty()) {
                        continue;
                    }

                    // Cost per new feature ratio
                    double ratio = (double) newFeatures.size() / costs[i];
                    if (ratio > bestRatio) {
                        bestRatio = ratio;
                        bestModel = i;
                        bestNewFeatures = new HashSet<>(newFeatures);
                    }
                }

                if (bestModel == null) {
                    return new ArrayList<>();  // Can't cover all features
                }

                selectedModels.add(bestModel);
                totalCost += costs[bestModel];
                remainingFeatures.removeAll(bestNewFeatures);
            }

            return selectedModels;
        }
    }
}
