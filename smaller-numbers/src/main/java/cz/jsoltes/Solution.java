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
        //first element
        count[len - 1] = 0;
        updated[len - 1] = 1;
        lower[len - 1] = -1;
        higher[len - 1] = -1;
        //the rest
        int compared = -1;
        int elem;
        int nextCompared;
        int celem=0;
        int leftWing=0;
        int rightWing=0;
        int root=len-1;
        int newRoot;
        //iterates through the nums backwards
        for (int i = len-2; i >= 0; i--) {
            elem = nums[i];
            count[i] = 0;
            updated[i] = 1;
            higher[i] = -1;
            lower[i] = -1;
            nextCompared = root;
            //iterates through the tree, updates updated count and calculates count
            do {
                compared = nextCompared;
                celem=nums[compared];
                if (elem > celem) {
                    count[i] += updated[compared];
                    nextCompared = higher[compared];
                } else if (elem < celem) {
                    updated[compared] += 1;
                    nextCompared = lower[compared];
                } else if (elem == celem) {
                    count[i]+=updated[compared]-1;
                    break;
                } 
            } while(nextCompared!=-1);
            //adds the element to the tree structure
            if (elem > celem) {
                higher[compared] = i;
            } else if (elem < celem) {
                lower[compared] = i;
            } else if (elem == celem) {
                higher[i]=higher[compared];
                higher[compared] = i;
            }
            //balances the tree
            if(elem>=nums[root]){
                rightWing++;
            }else{
                leftWing++;
            }
            if(rightWing-leftWing==2){
                newRoot=higher[root];
                higher[root]=-1;
                while(lower[newRoot]!=-1){
                    higher[newRoot]=newRoot;
                    newRoot=lower[newRoot];
                }
                lower[newRoot]=root;
                updated[newRoot]=updated[root]+1;
                root=newRoot;
                rightWing=0;
                leftWing=0;
            }else if (leftWing-rightWing==2){
                newRoot=lower[root];
                lower[root]=-1;
                while(higher[newRoot]!=-1){
                    lower[newRoot]=newRoot;
                    newRoot=higher[newRoot];
                }
                higher[newRoot]=root;
                updated[newRoot]=updated[root]-1;
                root=newRoot;
                rightWing=0;
                leftWing=0;
            }
        }
        List<Integer> solution=new ArrayList<>(len);
        for(int c:count) solution.add(c);
        return solution;
    }
}
