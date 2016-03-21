/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes.add.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author jsoltes
 */
public class Solution {

    //helper method to calculate result from String
    private int getResultFrom(String input) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        return (int) engine.eval(input);
    }

    //method that recursively generates all solutions for target
    private List<String> generateSolutions(String num, int target, StringBuilder solution) throws ScriptException {
        if (num.isEmpty()) { //base case
            String s = solution.toString();
            System.out.println(s);
            int result = getResultFrom(s);
            //if the solution gives target result, returns the solution, else, returns empty list+
            if (target == result) {
                return Arrays.asList(s);
            } else {
                return Collections.EMPTY_LIST;
            }
        } else { //recursive case
            List<String> solutions = new ArrayList<>();
            int lastPosition = solution.length() - 1;
            char lastElement = solution.charAt(lastPosition);
            StringBuilder original = new StringBuilder(solution);
            
            solution.append('+').append(num.charAt(0));
            solutions.addAll(generateSolutions(num.substring(1), target, solution));

            solution = new StringBuilder(original);
            solution.append('-').append(num.charAt(0));
            solutions.addAll(generateSolutions(num.substring(1), target, solution));

            solution = new StringBuilder(original);
            solution.append('*').append(num.charAt(0));
            solutions.addAll(generateSolutions(num.substring(1), target, solution));

            solution = new StringBuilder(original);
            solution.append(num.charAt(0));
            solutions.addAll(generateSolutions(num.substring(1), target, solution));
            return solutions;
        }
    }

    public List<String> addOperators(String num, int target) throws ScriptException {
        List<String> solutions = new ArrayList<>();
        int len = num.length();
        //num is empty String or its value is smaller than target
        if (len == 0) {
            return solutions;
            //num is String representing the target
        } else if (Integer.valueOf(num) == target) {
            return Arrays.asList(num);
            //num is number higher than the target
        } else {
            return generateSolutions(num.substring(1), target, new StringBuilder().append(num.charAt(0)));
        }
    }

    public static void main(String[] args) throws ScriptException {
        Solution s = new Solution();
        System.out.println(s.addOperators("123", 6));
    }
}
