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
        List<Integer> startingIndexes = new ArrayList<>(); //list of indexes of elements which start the growing row
        int counts[] = new int[len];
        //info from the last element is always the same
        counts[len - 1] = 0;
        startingIndexes.add(len - 1);
        
        List<Integer> checkIndexes = new ArrayList<>(); //indexes to check by the current element
        List<Integer> nextCheckIndexes = new ArrayList<>(); //indexes to check by the next element
        
        for (int i = len - 2; i >= 0; i--) {
            
            int elem = nums[i];
            int prevElem = nums[i + 1];
            int ssize=startingIndexes.size();
            int csize = checkIndexes.size();
            
            if (elem > prevElem) {
                count = prevCount + 1;
                //now we iterate through the startingIndexes from the end (at the end is the lowest index)
                if(i==30) System.out.println(elem+" "+checkIndexes);
                if (csize > 0) {
                    for (int j = csize - 1; j >= 0; j--) {
                        int k = checkIndexes.get(j);
                        if (nums[k] < elem) {
                            count++;
                            while (nums[k - 1]>nums[k] && nums[k - 1] < elem) {
                                count++;
                                k--;
                            }
                            if(nums[k-1]>nums[k]) nextCheckIndexes.add(k-1);
                        } else {
                            nextCheckIndexes.add(k);
                        }
                    }
                }
            } else if (elem < prevElem) {
                count = 0;
                checkIndexes=new ArrayList(startingIndexes);
                csize=ssize;
                startingIndexes.add(i);
                if (csize > 0) {
                    for (int j = csize - 1; j >= 0; j--) {
                        int k = checkIndexes.get(j);
                        if (nums[k] < elem) {
                            count++;
                            while (nums[k - 1]>nums[k] && nums[k - 1] < elem) {
                                count++;
                                k--;
                            }
                            if(nums[k-1]>nums[k]) nextCheckIndexes.add(k);
                        } else {
                            nextCheckIndexes.add(k);
                        }
                    }
                }
            } else { //the one and only case when elem==prevElem
                count = prevCount;
                if (count == 0) {
                    startingIndexes.add(i);
                }
            }
            prevCount = count;
            counts[i] = count;
            checkIndexes=new ArrayList(nextCheckIndexes);
            nextCheckIndexes.clear();
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
