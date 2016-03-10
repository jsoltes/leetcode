/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes.add.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    //method that recursively generates all solutions for positive target
    //TODO generalize for negative target
    private List<String> generatePositiveSolutions(String num, int len, int target, int result, StringBuilder solution) {
        if (num.isEmpty()) {
            if (result == target) {
                return Arrays.asList(solution.toString());
            } else {
                return Collections.EMPTY_LIST;
            }
        } else {
            List<String> solutions = new ArrayList<>();
            int nextResult;
            StringBuilder nextSolution;
            for (int i = 1; i < len; i++) {
                String nextString = num.substring(1, i + 1);
                String theRest = num.substring(i + 1, len);
                //a logic that decides that it is time to finish looping
                int resultDigits = Integer.toString(result).length();
                int nextDigits = nextString.length();
                int restDigits = theRest.length();
                int targetDigits = Integer.toString(target).length();
                if (Math.max(resultDigits, nextDigits) <= Math.max(restDigits, targetDigits)) {
                    //plus solutions
                    nextResult = result + Integer.valueOf(nextString);
                    nextSolution = solution.append("+").append(nextString);
                    solutions.addAll(generatePositiveSolutions(theRest, len, target, nextResult, nextSolution));
                    //minus solutions
                    nextResult = result - Integer.valueOf(nextString);
                    nextSolution = solution.append("-").append(nextString);
                    solutions.addAll(generatePositiveSolutions(theRest, len, target, nextResult, nextSolution));
                    //times solutions
                    nextResult = result * Integer.valueOf(nextString);
                    nextSolution = solution.append("*").append(nextString);
                    solutions.addAll(generatePositiveSolutions(theRest, len, target, nextResult, nextSolution));
                } else {
                    break;
                }
            }
            return solutions;
        }
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
            StringBuilder sb = new StringBuilder(Character.toString(num.charAt(0)));
            return generatePositiveSolutions(num, len, target, num.charAt(0), sb);
        }
    }
}
