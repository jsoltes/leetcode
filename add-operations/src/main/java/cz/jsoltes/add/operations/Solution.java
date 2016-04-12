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

    private int calculateResult(int op1, int op2, int op3, char sign) {
        int result = 0;
        if (sign == '+') {
            result = op1 + op2 * op3;
        } else if (sign == '-') {
            result = op1 - op2 * op3;
        }
        return result;
    }

    //method that recursively generates all solutions for target
    private List<String> generateSolutions(String num, int len, int target, int index, int op1, int op2, int op3, char sign, StringBuilder solution, List<String> solutions) {
        int result = calculateResult(op1, op2, op3, sign);
        if (index == len) { //base case
            if (target == result) {
                solutions.add(solution.toString());
            }
        } else { //recursive case
            //this condition has to be always met 
            String rest = num.substring(index);

            StringBuilder original = new StringBuilder(solution);
            String nextChar = num.substring(index, index + 1);
            int nextInt = Integer.parseInt(nextChar);

            //add + and the first digit of the next number
            solution.append('+').append(nextChar);
            generateSolutions(num, len, target, index + 1, result, nextInt, 1, '+', solution, solutions);

            //add - and the first digit of the next number
            solution = new StringBuilder(original);
            solution.append('-').append(nextChar);
            generateSolutions(num, len, target, index + 1, result, nextInt, 1, '-', solution, solutions);

            //add * and the first digit of the next number
            solution = new StringBuilder(original);
            solution.append('*').append(nextChar);
            generateSolutions(num, len, target, index + 1, op1, op2 * op3, nextInt, sign, solution, solutions);

            solution = new StringBuilder(original);
            //adds another digit, but only to the numbers that don't start on 0
            int len1 = solution.length();
            if (!((len1 == 1 || "+-*".indexOf(solution.charAt(len1 - 2)) != -1) && solution.charAt(len1 - 1) == '0')) {
                solution.append(nextInt);
                if (op3 == 1) {
                    generateSolutions(num, len, target, index + 1, op1, op2 * 10 + nextInt, op3, sign, solution, solutions);
                } else {
                    generateSolutions(num, len, target, index + 1, op1, op2, op3 * 10 + nextInt, sign, solution, solutions);
                }
            }
        }
        return solutions;
    }

    public List<String> addOperators(String num, int target) {
        if (num.isEmpty() ||  Long.parseLong(num) < target || (num.charAt(0)!='0' && (Long.parseLong(num)+target)<1)) {
            return Collections.EMPTY_LIST;
        } else if (num.equals(Integer.toString(target))) {
            return Arrays.asList(num);
        } else {
            String firstChar = num.substring(0, 1);
            int firstInt = Integer.parseInt(firstChar);
            return generateSolutions(num, num.length(), target, 1, 0, firstInt, 1, '+', new StringBuilder(firstChar), new ArrayList<String>());
        }
    }
}
