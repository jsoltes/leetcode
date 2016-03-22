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
        int result = 0;
        //first we get rid of the *
        int start = 0;
        for (int i = 1; i < solution.length(); i++) {
            char current = solution.charAt(i);
            if (current == '*') {
                int factor1 = Integer.valueOf(solution.substring(start, i));
                int start2 = i + 1;
                while (current != '+' && current != '-' && current != '*') {
                    i++;
                    current = solution.charAt(i);
                }
                int factor2 = Integer.valueOf(solution.substring(start2, i));
                int product = factor1 * factor2;
                solution.replace(start, i, Integer.toString(product));
            } else if (current == '+' || current == '-') {
                start=i+1;
            }
        }
        //now we calculate simple solution
        char current = solution.charAt(1);
        int i=1;
        while(current!='+' || current!='-'){
            i++;
            current=solution.charAt(i);
        }
        result=Integer.valueOf(solution.substring(0, i));
        int len=solution.length();
        for(int j=i;j<len;i++){
            current = solution.charAt(j);
            if(current == '+'){
                start=i+1;
                while(current!='+' || current!='-'){
                i++;
                current=solution.charAt(i);
                }
                result+=Integer.valueOf(solution.substring(start,i));
            } else if (current == '-'){
                start=i+1;
                while(current!='+' || current!='-'){
                i++;
                current=solution.charAt(i);
                }
                result-=Integer.valueOf(solution.substring(start,i));
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

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addOperators("00", 0));
    }
}
