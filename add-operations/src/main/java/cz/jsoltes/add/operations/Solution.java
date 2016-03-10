/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes.add.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author jsoltes
 */
public class Solution {

    //method that recursively generates all solutions for positive target
    //TO-DO logic that builds the String solution
    //TO-DO refine upper boundary in the for cycle
    //TO-DO base case (num is empty)
    private List<String> generatePositiveSolutions(String num, int len, int target, int result) {
        List<String> solutions = new ArrayList<>();
        List<String> signs = Arrays.asList("+", "-", "*");
        int nextResult;
        for (int i = 1; i < len; i++) {
            nextResult=result+Integer.valueOf(num.substring(1,i+1));
            solutions.addAll(generatePositiveSolutions(num.substring(i+1, len), len, target, nextResult));
            
            nextResult=result-Integer.valueOf(num.substring(1,i+1));
            solutions.addAll(generatePositiveSolutions(num.substring(i+1, len), len, target, nextResult));
            
            nextResult=result*Integer.valueOf(num.substring(1,i+1));
            solutions.addAll(generatePositiveSolutions(num.substring(i+1, len), len, target, nextResult));
        }
        return solutions;
    }
    
    //method that recursively generates all solutions for negative target
    private List<String> generateNegativeSolutions(String num, int len, int target, int result) {
        List<String> solutions = new ArrayList<>();
        return solutions;
    }

    public List<String> addOperators(String num, int target) {
        List<String> solutions = new ArrayList<>();
        //num is empty String or its value is smaller than target
        if (num.length() == 0) {
            return solutions;
            //num is String representing the target
        } else if (Integer.valueOf(num) == target) {
            return Arrays.asList(num);
            //num is number higher than the target
        } else {
            int len = num.length();
            if (target >= 0) {
                return generatePositiveSolutions(num, len, target, num.charAt(0));
            } else {
                return generateNegativeSolutions(num, len, target, num.charAt(0));
            }
        }
    }
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("JavaScript");
        System.out.println(se.eval("3*2"));
    }
}
