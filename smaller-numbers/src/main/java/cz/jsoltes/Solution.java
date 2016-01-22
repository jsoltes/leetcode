/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author jsoltes
 */
public class Solution {

    private List<Integer>[] createTree(int[] nums, int len, int i, List<Integer> nodes) {
        if(i==len-1) return null; //base case
        else{ //recursive case
            List<Integer>[] tree=new ArrayList[len]; 
            return tree;
        }
    }

    public List<Integer> countSmaller(int[] nums) {

        int len = nums.length;
        List<Integer> solution = new ArrayList<>(len);
        List<Integer> firstNodes = new ArrayList<>(); //list of first nodes
        List<Integer> tree[] = new ArrayList[len]; //array of lists for storing the tree

        for (int i = 0; i < len; i++) {
            int elem = nums[i];
            //this part probably has to be done recursively
            int fsize = firstNodes.size();
            if (firstNodes.isEmpty() || nums[firstNodes.get(fsize - 1)] < elem) {
                firstNodes.add(i);
            } else {
                int j = fsize - 1;
                while (nums[firstNodes.get(j)] > elem) {
                    if (tree[j].isEmpty() || tree[j].get(tree[j].size() - 1) < elem) {
                        tree[j].add(i);
                    } else; //and the same recursively until ??
                    j--;
                }
            }
            //this part probably has to be done recursively
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
