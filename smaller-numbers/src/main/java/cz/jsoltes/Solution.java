/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes;

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
        Integer count[] = new Integer[len];
        int updated[] = new int[len]; //updated count+1
        int lower[] = new int[len]; //pointers to lower numbers
        int higher[] = new int[len]; //pointers to higher numbers
        //first element
        count[len - 1] = 0;
        updated[len - 1] = 1;
        lower[len - 1] = -1;
        higher[len - 1] = -1;
        //the rest
        int compared = -1;
        for (int i = len - 2; i >= 0; i--) {
            int elem = nums[i];
            count[i] = 0;
            updated[i] = 1;
            higher[i] = -1;
            lower[i] = -1;
            int nextCompared = len - 1;
            while (nextCompared != -1) {
                compared = nextCompared;
                if (elem > nums[compared]) {
                    count[i] += updated[compared];
                    nextCompared = higher[compared];
                } else if (elem < nums[compared]) {
                    updated[compared] += 1;
                    nextCompared = lower[compared];
                } else if (elem == nums[compared]) {
                    count[i]+=updated[compared]-1;
                    nextCompared=-1;
                }
            }
            if (elem > nums[compared]) {
                higher[compared] = i;
            } else if (elem < nums[compared]) {
                lower[compared] = i;
            } else if (elem == nums[compared]) {
                higher[i]=higher[compared];
                higher[compared] = i;
            }
        }

        for (int i = 2; i < 3; i++) {
            System.out.println("elem " + nums[i] + " count " + count[i] + " updated " + updated[i] + " higher " + higher[i] + " lower " + lower[i]);
        }

        return Arrays.asList(count);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("zadanie  [26,78,27, 100,33, 67, 90,23, 66, 5, 38, 7, 35,23, 52,22, 83,51, 98, 69, 81,32, 78,28, 94,13, 2, 97, 3, 76,99,51, 9,21,84,66,65,36,100,41]");
        System.out.println("result   " + s.countSmaller(new int[]{26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41}));
        System.out.println("expected [10, 27, 10, 35, 12, 22, 28, 8, 19, 2, 12, 2, 9, 6, 12, 5, 17, 9, 19, 12, 14, 6, 12, 5, 12, 3, 0, 10, 0, 7, 8, 4, 0, 0, 4, 3, 2, 0, 1, 0]");
    }
}
