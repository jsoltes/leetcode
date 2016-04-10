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
    private long getResult(StringBuilder solution) {
        //first we get rid of the * signs
        int start1 = 0;
        int len = solution.length();
        char current;
        boolean noSigns = true;
        for (int i = 1; i < len; i++) {
            current = solution.charAt(i);
            if (current == '*') {
                int factor1 = Integer.parseInt(solution.substring(start1, i));
                int start2 = ++i;
                while (i < len && "+-*".indexOf(solution.charAt(i)) == -1) {
                    i++;
                }
                int factor2 = Integer.parseInt(solution.substring(start2, i));
                int product = factor1 * factor2;
                solution.replace(start1, i, Integer.toString(product));
                int newLen = solution.length();
                i -= len - newLen + 1;
                len = newLen;
            } else if (current == '+' || current == '-') {
                start1 = i + 1;
                noSigns = false;
            }
        }
        //now we calculate the solution
        long result = 0;
        if (noSigns == true) { //if after removing the * there is nothing more to do
            result = Long.parseLong(solution.toString());
        } else {
            //first we get the first number
            int i;
            for (i = 1; i < len; i++) {
                current = solution.charAt(i);
                if (current == '+' || current == '-') {
                    result = Integer.parseInt(solution.substring(0, i));
                    break;
                }
            }
            //then we sum up the rest
            int start;
            for (int j = i; j < len; j++) {
                current = solution.charAt(j);
                if (current == '+') {
                    start = ++j;
                    while (j < len && solution.charAt(j) != '+' && solution.charAt(j) != '-') {
                        j++;
                    }
                    result += Integer.parseInt(solution.substring(start, j--));
                } else if (current == '-') {
                    start = ++j;
                    while (j < len && solution.charAt(j) != '+' && solution.charAt(j) != '-') {
                        j++;
                    }
                    result -= Integer.parseInt(solution.substring(start, j--));
                }
            }
        }
        return result;
    }

    //method that recursively generates all solutions for target
    private List<String> generateSolutions(String num, int target, StringBuilder solution, boolean containsSign, List<String> solutions) {
        if (num.isEmpty()) { //base case
            String s = solution.toString();
            long result = getResult(solution);
            //if the solution gives target result, returns the solution, else, returns empty list
            if (result < 2147483647 && target == result) {
                solutions.add(s);
                return solutions;
            }
        } else { //recursive case
            int lastPosition = solution.length() - 1;
            char lastElement = solution.charAt(lastPosition);
            StringBuilder original = new StringBuilder(solution);
            //add + and first digit of the next number
            solution.append('+').append(num.charAt(0));
            generateSolutions(num.substring(1), target, solution, true, solutions);
            //add - and first digit of the next number
            solution = new StringBuilder(original);
            solution.append('-').append(num.charAt(0));
            generateSolutions(num.substring(1), target, solution, true, solutions);
            //add * and first digit of the next number
            solution = new StringBuilder(original);
            solution.append('*').append(num.charAt(0));
            generateSolutions(num.substring(1), target, solution, true, solutions);
            //adds another digit
            if ('0' != lastElement || (lastPosition - 1 >= 0 && "+-*".indexOf(original.charAt(lastPosition - 1)) == -1)) {//this ensures we don't get numbers starting on 0
                solution = new StringBuilder(original);
                if (containsSign || num.charAt(0) == '0' || Integer.parseInt(solution.toString()) - Integer.parseInt(num) <= target) {
                    solution.append(num.charAt(0));
                    generateSolutions(num.substring(1), target, solution, containsSign, solutions);
                }
            }
            return solutions;
        }
        return null;
    }

    public List<String> addOperators(String num, int target) {
        if (num.isEmpty() || Long.parseLong(num) < target) {
            return Collections.EMPTY_LIST;
        } else if (num.equals(Integer.toString(target))) {
            return Arrays.asList(num);
        } else {
            char char1 = num.charAt(0);
            return generateSolutions(num.substring(1), target, new StringBuilder().append(char1), false, new ArrayList<String>());
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addOperators("105", 5));
    }
}
