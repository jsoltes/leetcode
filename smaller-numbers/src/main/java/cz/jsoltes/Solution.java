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

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        List<Integer> solution = new ArrayList<>(len);
        if (len==0) return  solution;
        int prevCount; //count of the previous number
        int count; //count of the current number
        List<Integer> lowerElements = new ArrayList<>(); //lower elements
        List<Integer> newLowerElements = new ArrayList<>(); //new lower elements
        List<Integer> higherElements = new ArrayList<>(); //higher elements
        List<Integer> newHigherElements = new ArrayList<>(); //new higher elements
        //for the last element
        solution.add(0);
        prevCount=0;
        
        for (int i = len - 2; i >= 0; i--) {
            int elem = nums[i];
            int prevElem = nums[i + 1];
            
            if(elem>prevElem){
               count=prevCount+1;
               int hsize=higherElements.size();
               for(int j=0;j<hsize;j++){
                   int h = higherElements.get(j);
                   if(elem<=h) { //breaking point
                       newHigherElements.addAll(higherElements.subList(j, hsize));
                       while(j>0){
                           j--;
                           if(higherElements.get(j)!=prevElem) newLowerElements.add(higherElements.get(j));
                           count++;
                       }
                       break;
                   }
               }
               newLowerElements.add(prevElem);
               newLowerElements.addAll(lowerElements);
               
            } else if (elem<prevElem){
                count=prevCount;
                int lsize=lowerElements.size();
                if(lsize!=0){
                    for(int j=lsize-1;j>=0;j--){
                      int l = lowerElements.get(j);
                      if(elem<=l){//breaking point
                          newLowerElements=lowerElements.subList(j+1, lsize);
                          while(j>=0){
                              newHigherElements.add(lowerElements.get(j));
                              count--;
                              j--;
                          }
                          break;
                        }
                    }
                }
                newHigherElements.add(prevElem);
                newHigherElements.addAll(higherElements);
            }else {
                count=prevCount;
            }
            solution.add(count);
            prevCount=count;
            lowerElements=new ArrayList<>(newLowerElements);
            newLowerElements.clear();
            higherElements=new ArrayList<>(newHigherElements);
            newHigherElements.clear();
            if(i==7) {
                System.out.println(elem);
                System.out.println(higherElements);
                System.out.println(lowerElements);
            }
        }
        Collections.reverse(solution);
        return solution;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("zadanie  [26,78,27, 100,33, 67, 90,23, 66, 5, 38, 7, 35,23, 52,22, 83,51, 98, 69, 81,32, 78,28, 94,13, 2, 97, 3, 76,99,51, 9,21,84,66,65,36,100,41]");
        System.out.println("result   " + s.countSmaller(new int[]{26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23, 52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41}));
        System.out.println("expected [10, 27, 10, 35, 12, 22, 28, 8, 19, 2, 12, 2, 9, 6, 12, 5, 17, 9, 19, 12, 14, 6, 12, 5, 12, 3, 0, 10, 0, 7, 8, 4, 0, 0, 4, 3, 2, 0, 1, 0]");
    }
}
