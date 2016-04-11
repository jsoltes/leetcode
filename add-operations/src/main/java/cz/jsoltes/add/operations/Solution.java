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

    private int calculateResult(int op1, int op2, char sign, int result) {
        if (sign == '+') {
            result = op1 + op2;
        } else if (sign == '-') {
            result = op1 - op2;
        } else if (sign == '*') {
            result = op1 * op2;
        }
        return result;
    }

    //method that recursively generates all solutions for target
    private List<String> generateSolutions(String num, int last, int target, int index, int op1, int op2, char sign, int result, StringBuilder solution, List<String> solutions) {
        if (index == last) { //base case
            if (target == result) {
                solutions.add(solution.toString());
            }
        } else if (result - Integer.parseInt(num.substring(index, last + 1)) <= target) { //recursive case
            StringBuilder original = new StringBuilder(solution);

            //add + and the first digit of the next number
            String nextChar = num.substring(index, index + 1);
            solution.append('+').append(nextChar);
            result = calculateResult(op1, op2, sign, result);
            generateSolutions(num, last, target, index + 1, result, Integer.parseInt(nextChar), '+', result, solution, solutions);

            //add - and the first digit of the next number
            solution = new StringBuilder(original);
            nextChar = num.substring(index, index + 1);
            solution.append('-').append(nextChar);
            result = calculateResult(op1, op2, sign, result);
            generateSolutions(num, last, target, index + 1, result, Integer.parseInt(nextChar), '-', result, solution, solutions);

            //add * and the first digit of the next number
            solution = new StringBuilder(original);
            nextChar = num.substring(index, index + 1);
            solution.append('*').append(nextChar);
            op2 = op2 * Integer.parseInt(nextChar);
            generateSolutions(num, last, target, index + 1, op1, op2, sign, result, solution, solutions);
            //adds another digit, but only to the numbers that doesn't start on 0
            //TODO finish this part
            solution = new StringBuilder(original);
            nextChar = num.substring(index, index + 1);
        }
        return solutions;
    }

    public List<String> addOperators(String num, int target) {
        if (num.isEmpty() || Long.parseLong(num) < target) {
            return Collections.EMPTY_LIST;
        } else if (num.equals(Integer.toString(target))) {
            return Arrays.asList(num);
        } else {
            String firstChar = num.substring(0, 1);
            int firstNum = Integer.parseInt(firstChar);
            return generateSolutions(num, num.length() - 1, target, 1, 0, firstNum, '+', firstNum, new StringBuilder(firstChar), new ArrayList<String>());
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addOperators("105", 5));
    }
}
