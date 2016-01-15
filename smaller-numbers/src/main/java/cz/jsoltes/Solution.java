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
        int len=nums.length;
        Integer[] positions = new Integer[len*4];//date structure that hold information for every element //
        Integer count=0; //count of smaller numbers on the right
        Integer smaller=null; //position of the nearest smaller element on the right
        Integer higher=null; //position the nearest higher element on the right 
        for(int i=len-1;i>=0;i--){//
            int element=nums[i];//
            //we decide if the current number is smaller, higher or equal to the previous number
            if(i==len-1){
                count=0; 
                smaller=null;
                higher=null;  
            } else if(element>nums[i+1]){ //when the current is higher //
                higher=positions[4*(i+2)-1];//higher of the previous
                smaller=4*(i+2)-4;//previous element
                count=positions[4*(i+2)-3]+1; //count of the previous +1
                while(higher!=null && positions[higher]<element){
                    count++;
                    smaller=higher;
                    higher=positions[higher+3];
                }
                //we need to update previous elements
                positions[smaller+3]=4*i;  //nearest higher of the nearest smaller
                if(higher!=null) {
                    positions[higher+2]=4*i; //nearest smaller of the nearest higher
                } 
            } else{ //when the current is smaller than the previous
                smaller=positions[4*(i+2)-2];//smaller of the previous //20;
                higher=4*(i+2)-4;//index of the previous element //4; 
                count=positions[4*(i+2)-3]; //count of the previous
                while(smaller!=null && positions[smaller]>=element){
                    count--;
                    higher=smaller;
                    smaller=positions[smaller+2];
                }
                //we need to update previous elements
                positions[higher+2]=4*i; //nearest smaller of the nearest higher
                if(smaller!=null) { 
                    positions[smaller+3]=4*i; //nearest higher of the nearest smaller
                } 
            } /*else{ //the case when they equals 
                count=positions[4*(i+2)-3];
                smaller=positions[4*(i+2)-2];
                higher=positions[4*(i+2)-1];
            } */
            positions[4*(i+1)-4]=element;//
            positions[4*(i+1)-3]=count;//
            positions[4*(i+1)-2]=smaller;//
            positions[4*(i+1)-1]=higher;//
            /*
            String dude;
            if(positions[159]==null) dude=null;
            else dude=positions[positions[159]].toString();
            System.out.println("41 upper "+dude);
            if(positions[158]==null) dude=null;
            else dude=positions[positions[158]].toString();
            System.out.println("41 lower "+dude);
                    */
        }
        for (int i = 0; i < positions.length; i++) {
            if(i%4==0) System.out.print("| "+"("+i+") ");
            System.out.print(positions[i]+" ");
        }
        System.out.println();
        for(int i=0;i<len;i++) solution.add(positions[4*i+1]);
        return solution;
    }
    public static void main(String[] args) {
        Solution s =new Solution();
        System.out.println("zadanie: [26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41]");
        System.out.println("result "+s.countSmaller(new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41}));
    }
}
