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
    private List<String> generateSolutions(String num, int target, int digits, List<String> signs, StringBuilder solution) throws ScriptException {
        if (num.isEmpty()) { //base case
            String s = solution.toString();
            int result = getResultFrom(s);
            //if the solution gives target result, returns the solution, else, returns empty list+
            if (target == result) {
                return Arrays.asList(s);
            } else {
                return Collections.EMPTY_LIST;
            }
        } else { //recursive case
            List<String> solutions = new ArrayList<>();
            int len = num.length();
            String nextNumber = num.substring(0, digits);
            solution.append(nextNumber);
            if (digits < len) { //generates all possible lengths
                solutions.addAll(generateSolutions(num, target, digits + 1, signs, solution));
            }
            solution.append(signs.get(0));
            if (signs.size()>1) { //generates all possible signs
                solutions.addAll(generateSolutions(num, target, digits, signs.subList(1, signs.size()), solution));
            }
            //generates all possible solutions
            solutions.addAll(generateSolutions(num.substring(digits), target, digits, signs, solution));
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
            return generateSolutions(num, target, 1, Arrays.asList("+", "-", "*"), new StringBuilder());
        }
    }

    public static void main(String[] args) throws ScriptException {
        Solution s = new Solution();
        System.out.println(s.addOperators("123", 6));
    }
}
