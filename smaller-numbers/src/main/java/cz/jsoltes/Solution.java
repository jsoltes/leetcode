/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jsoltes
 */
public class Solution {

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> solution = new ArrayList<>(len);
        int prevCount = 0; //count of the previous number
        int count; //count of the current number
        List<Integer> zeroCountIndexes = new ArrayList<>(); //list of indexes with zero count
        int counts[] = new int[len];
        //info from the last element is always the same
        counts[len - 1] = 0;
        zeroCountIndexes.add(len - 1);
        
        for (int i = len - 2; i >= 0; i--) {
            
            int elem = nums[i];
            int prevElem = nums[i + 1];
            int zsize = zeroCountIndexes.size();
            
            if (elem > prevElem) {
                count = prevCount + 1;
                //now we iterate through the zeroCountIndexes from the end (at the end is the lowest index)
                if (zsize > 1) {
                    for (int j = zsize - 2; j >= 0; j--) {
                        int k = zeroCountIndexes.get(j);
                        if (nums[k] < elem) {
                            count++;
                            while (nums[k - 1] < elem) {
                                count++;
                                k--;
                            }
                        } else {
                            break;
                        }
                    }
                }
            } else if (elem < prevElem) {
                count = 0;
                if (zsize > 0) {
                    for (int j = zsize - 1; j >= 0; j--) {
                        int k = zeroCountIndexes.get(j);
                        if (nums[k] < elem) {
                            count++;
                            while (nums[k - 1] < elem) {
                                count++;
                                k--;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (count == 0) {
                    zeroCountIndexes.add(i);
                }
            } else { //the one and only case when elem==prevElem
                count = prevCount;
                if (count == 0) {
                    zeroCountIndexes.add(i);
                }
            }
            prevCount = count;
            counts[i] = count;
        }
        for (int c : counts) {
            solution.add(c);
        }
        return solution;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("zadanie  [26,78,27, 100,33, 67, 90,23, 66, 5, 38, 7, 35,23, 52,22, 83,51, 98, 69, 81,32, 78,28, 94,13, 2, 97, 3, 76,99,51, 9,21,84,66,65,36,100,41]");
        System.out.println("result   " + s.countSmaller(new int[]{26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41}));
        System.out.println("expected [10, 27, 10, 35, 12, 22, 28, 8, 19, 2, 12, 2, 9, 6, 12, 5, 17, 9, 19, 12, 14, 6, 12, 5, 12, 3, 0, 10, 0, 7, 8, 4, 0, 0, 4, 3, 2, 0, 1, 0]");
    }
}
