/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes.add.operations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jsoltes
 */
public class Solution {

    private List<String> findAllSolutions(String num, int target, int result) {
        List<String> solution = new ArrayList<>();
        int len = num.length();
        result = num.charAt(0);
        if (result > target) {
            result -= num.charAt(1);
        }
        return solution;
    }

    public List<String> addOperators(String num, int target) {
        List<String> solution = new ArrayList<>();
        if (Integer.valueOf(num) == target) {
            solution.add(num);
        } else if (Integer.valueOf(num) < target) {
        } else {
            solution = findAllSolutions(num, target, 0);
        }
        return solution;
    }
}
