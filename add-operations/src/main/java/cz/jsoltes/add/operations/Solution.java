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
            String nextString, theRest;
            StringBuilder nextSolution;
            len=num.length();
            for (int i = 0; i < len; i++) {
                nextString = num.substring(0, i + 1);
                theRest = num.substring(i + 1, len);
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
            }
            return solutions;
        }
    }

    public List<String> addOperators(String num, int target) {
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
            String firstChar = num.substring(0,1);
            StringBuilder sb = new StringBuilder(firstChar);
            return generatePositiveSolutions(num.substring(1, len), len - 1, target, Integer.valueOf(firstChar), sb);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addOperators("123", 6));
    }
}
