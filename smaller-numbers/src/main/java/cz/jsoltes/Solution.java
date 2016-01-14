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
        List<Integer> solution=new ArrayList<Integer>();
        List<Integer> options=new ArrayList<Integer>();
        int len=nums.length;
        for(int i=len-1;i>=0;i--){
            int count=0;
            int ref=nums[i];
            if(i==len-1 || ref>nums[i+1]){ //go through the whole rest
                for(int j=i+1;j<len;j++){
                    if(nums[j]<ref) {
                        count++;
                        options.add(j);
                    }
                }
                solution.add(0,count);
            }
            else if(ref<nums[i+1]){ //go only through the options from the last time
                for(int o:options){
                    if(ref<o) count++;
                }
                solution.add(0,count);
            }
            else solution.add(0,solution.get(0));
        }
        return solution;
    }
    public static void main(String[] args) {
        Solution s=new Solution();
        System.out.println("result "+s.countSmaller(new int[]{5183,2271,3067,539,8939,2999,9264,737,3974,5846,-210,9278,5800,2675,6608,1133,-1,6018,9672,5179,9842}));
    }
}
