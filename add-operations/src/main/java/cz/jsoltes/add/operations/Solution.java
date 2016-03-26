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

    //helper method that calculates result from stringbuilder
    private int getResult(StringBuilder solution) {
        //first we get rid of the * signs (0+0->0+0, 0*0->0)
        int start1 = 0;
        int len = solution.length();
        char current;
        boolean noSigns = true;
        //1*2*3
        for (int i = 1; i < len; i++) {
            current = solution.charAt(i);//*
            if (current == '*') {
                int factor1 = Integer.valueOf(solution.substring(start1, i));//1
                int start2 = ++i;//2
                while (i < len && "+-*".indexOf(solution.charAt(i)) == -1) {
                    i++;//3
                }
                int factor2 = Integer.valueOf(solution.substring(start2, i));//2
                int product = factor1 * factor2;//2
                solution.replace(start1, i, Integer.toString(product));
                int newLen = solution.length();
                i -= len - newLen +1;
                len = newLen;
            } else if (current == '+' || current == '-') {
                start1 = i+1;
                noSigns = false;
            }
        }
        //now we calculate the solution
        int result = 0;
        if (noSigns == true) { //if after removing the * there is nothing more to do
            result = Integer.valueOf(solution.toString());
        } else {
            //first we get the first number
            int i;
            for (i = 1; i < len; i++) {
                current = solution.charAt(i);
                if (current == '+' || current == '-') {
                    result = Integer.valueOf(solution.substring(0, i));
                    break;
                }
            }
            //then we sum up the rest
            int start; 
            for (int j = i; j < len; j++) {
                current = solution.charAt(j);
                if (current == '+') {
                    start = ++j;
                    while (j<len && solution.charAt(j) != '+' && solution.charAt(j) != '-') {
                        j++;
                    }
                    result += Integer.valueOf(solution.substring(start, j--));
                } else if (current == '-') {
                    start = ++j;//2
                    while (j < len && solution.charAt(j) != '+' && solution.charAt(j) != '-') {
                        j++;
                    }
                    result -= Integer.valueOf(solution.substring(start,j--));//2,2
                }
            }
        }
        return result;
    }

    //method that recursively generates all solutions for target
    private List<String> generateSolutions(String num, int target, StringBuilder solution) {
        if (num.isEmpty()) { //base case
            String s = solution.toString();
            int result = getResult(solution);
            //if the solution gives target result, returns the solution, else, returns empty list
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

            if ('0' != lastElement) {
                solution = new StringBuilder(original);
                solution.append(num.charAt(0));
                solutions.addAll(generateSolutions(num.substring(1), target, solution));
            }
            return solutions;
        }
    }

    public List<String> addOperators(String num, int target) {
        char char1 = num.charAt(0);
        return generateSolutions(num.substring(1), target, new StringBuilder().append(char1));
    }
}
