/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jsoltes
 */
public class Solution {

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return Arrays.asList(); //the case of empty input
        }
        int count[] = new int[len];
        int updated[] = new int[len]; //updated count+1
        int lower[] = new int[len]; //pointers to lower numbers
        int higher[] = new int[len]; //pointers to higher numbers
        int compared = -1;
        int celem = 0;
        int root = len - 1;
        //iterates through the nums backwards
        for (int i = len - 2; i >= 0; i--) {
            int elem = nums[i];
            int nextCompared = root;
            //iterates through the tree, updates updated count and calculates count
            do {
                compared = nextCompared;
                celem = nums[compared];
                if (elem > celem) {
                    count[i] += updated[compared]+1;
                    nextCompared = higher[compared];
                } else if (elem < celem) {
                    updated[compared] += 1;
                    nextCompared = lower[compared];
                } else if (elem == celem) {
                    count[i] += updated[compared];
                    break;
                }
            } while (nextCompared != 0);
            //adds the element to the tree structure
            if (elem > celem) {
                higher[compared] = i;
            } else if (elem < celem) {
                lower[compared] = i;
            } else if (elem == celem) {
                higher[i] = higher[compared];
                higher[compared] = i;
            }
        }
        List<Integer> solution = new ArrayList<>(len);
        for (int c : count) {
            solution.add(c);
        }
        return solution;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.countSmaller(new int[]{5,2,6,1}));
    }
}
