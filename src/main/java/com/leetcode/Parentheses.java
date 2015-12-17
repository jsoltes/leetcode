package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Removes the minimum number of invalid parentheses in order to make the input string valid. 
 * Returns all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * 
 * @author Juraj Soltes
 */
public class Parentheses 
{  
    //removes all parentheses that would have to be removed in all cases
    //returns prepared string,  leftMinNumber, rightMinNumber and all possible leftIndexes and rightIndexes for removal
    public Object[] prepare(String s){
        Object[] info = new Object[5];
        StringBuilder sb = new StringBuilder(s);
        List<Integer> rightMinIndexes=new ArrayList<Integer>();
        int rightMinNumber=0;
        int balance=0;
        int left=0;
        //gets indexes of wrong right parentheses (from which you can get all possible removals of right parentheses)
        int sblength=sb.length();
        for(int i=0;i<sblength;i++){
            char currentChar=sb.charAt(i);
            if(currentChar=='(') {
                balance++;
                left++;
            }
            if(currentChar==')') balance--;
            if(balance<0){
                //in these cases we remove:((((()))))), )(), ()),)a)() in these cases not: ()()),()a)()
                //odstranit - ak left =0; ak left=1 a na i-1 pozicii nie je symbol; ak left je viac ako 1 a na i-1 pozicii je to iste ako na i-2
                char previousChar=0;
                if(left>=1) previousChar=sb.charAt(i-1);
                if((left==0) || (left==1 && (previousChar=='(' || previousChar==')')) || (left>=2 && previousChar==sb.charAt(i-2))){
                    sb.deleteCharAt(i); //after this cursor goes on the next character so we have to decrease it
                    i--; //because otherways we would skip one character
                    sblength--;
                } else {
                    rightMinIndexes.add(i);
                    rightMinNumber++;
                } 
                balance=0;
            }
        }
        List<Integer> rightIndexes=new ArrayList<Integer>();
        if(rightMinNumber>0){
            //adds other possible right indexes
            int start=sb.indexOf("(")+1;
            for(int rightMinIndex:rightMinIndexes){
                for(int i=start;i<rightMinIndex;i++){
                    if(sb.charAt(i)==')' && sb.charAt(i+1)!=')') rightIndexes.add(i);
                }
                rightIndexes.add(rightMinIndex);
                start=rightMinIndex+1;
            }
        }
        //if at the end there are extra left parentheses to be removed... 
        List<Integer> leftIndexes=new ArrayList<Integer>();
        int leftMinNumber=0;
        if(balance>0){
            balance=0;
            int right=0;
            //for loop for getting indexes of wrong left parentheses (from which you can get all possible removals of left indexes)
            int start=sb.length()-1;
            for(int i=start;i>=0;i--){
                char currentChar=sb.charAt(i);
                if(currentChar==')') {
                    balance++;
                    right++;
                }
                if(currentChar=='(') {
                    balance--;
                }
                if(balance<0){
                    //odstranit - ak right =0; ak right=1 a na i+1 pozicii nie je symbol; ak right je viac ako 1 a na i+1 pozicii je to iste ako na i+2
                    char nextChar = 0;
                    if(right>=1) nextChar=sb.charAt(i+1);
                    if((right==0) || (right==1 && (nextChar=='(' || nextChar==')')) || (right>=2 && nextChar==sb.charAt(i+2))){
                        sb.deleteCharAt(i);
                        //everytime we delete we have to decrement all left indexes
                        int size=leftIndexes.size();
                        for(int j=0;j<size;j++){
                            leftIndexes.set(j, leftIndexes.get(j)-1);
                        }
                    } else {
                        leftIndexes.add(i);
                        leftMinNumber++;
                    } 
                    balance=0;
                }
            }
        if(leftMinNumber>0){
            //adds other possible left indexes
            int firstLeftIndex=leftIndexes.get(0);
            for(int i=sb.lastIndexOf(")")-1;i>firstLeftIndex;i--){
                if(sb.charAt(i)=='(' && !leftIndexes.contains(i) && sb.charAt(i-1)!='(') leftIndexes.add(i);
            }
        }
    }
    Collections.sort(leftIndexes);
        
    info[0]=sb.toString();
    info[1]=rightIndexes;
    info[2]=leftIndexes;
    info[3]=rightMinNumber;
    info[4]=leftMinNumber;
        
    return info;
    }

    public List<String> generate(StringBuilder sb,List<Integer> rightIndexes, List<Integer> leftIndexes, int rightMinNumber, int leftMinNumber){
        List<String> solutions=new ArrayList<String>(); 
        //prepared="(()())())r())"
        //rightMinNumber=2
        //leftMinNumber=0
        //rightIndexes=2,5,8,12
        //leftIndexes=empty

        //side of the solution with right parentheses
        for(int i:rightIndexes){
            System.out.println(sb);
            sb.deleteCharAt(i);
            //if there are 2 and more to be removed, we have to go deeeper
            if(rightMinNumber>1){
                //everytime we go deeper, we have to decrease indexes, because the string shortenes
                //also it is enough to go through the sublist of right indexes
                List<Integer> sublist=rightIndexes.subList(rightIndexes.indexOf(i)+1, rightIndexes.size());
                for(int j=0;j<sublist.size();j++){
                    sublist.set(j, sublist.get(j)-1);
                }
                solutions.addAll(generate(sb,sublist, leftIndexes, rightMinNumber-1, leftMinNumber));
            }
            else{
                solutions.add(sb.toString());
            }
            sb.insert(i,")");//returns stringbuilder into the original state
            }
        //side of the solution with left parentheses
        if(leftMinNumber>0){
            if(solutions.isEmpty())
                for(int j:leftIndexes){
                    sb.deleteCharAt(j);
                    //if there are 2 and more to be removed, we have to go deeeper
                    if(leftMinNumber>1){
                        //everytime we go deeper, we have to decrease indexes, because the string shortenes
                        //also it is enough to go through the sublist of left indexes
                        List<Integer> sublist=rightIndexes.subList(rightIndexes.indexOf(j)+1, rightIndexes.size());
                        for(int i=0;j<sublist.size();i++){
                            sublist.set(i, sublist.get(i)-1);
                        }
                        solutions.addAll(generate(sb,rightIndexes, leftIndexes, rightMinNumber, leftMinNumber-1));
                    }
                    else{
                        solutions.add(sb.toString());
                    }
                    sb.insert(j,"(");
                }
            else {
                List<String> solutions2=new ArrayList<String>();
                for(String s:solutions){
                    StringBuilder sb2=new StringBuilder(s);
                    for(int j:leftIndexes){
                        sb2.deleteCharAt(j);
                        //if there are 2 and more to be removed, we have to go deeeper
                        if(leftMinNumber>1){
                            //everytime we go deeper, we have to decrease indexes, because the string shortenes
                            //also it is enough to go through the sublist of left indexes
                            List<Integer> sublist=rightIndexes.subList(rightIndexes.indexOf(j)+1, rightIndexes.size());
                                for(int i=0;j<sublist.size();i++){
                                    sublist.set(i, sublist.get(i)-1);
                                }
                            solutions.addAll(generate(sb2,rightIndexes, leftIndexes, rightMinNumber, leftMinNumber-1));
                        }
                        else{
                            solutions2.add(sb2.toString());
                        }
                        sb2.insert(j,"(");
                    }
                }
                return solutions2;
            }
        }
        return solutions;
    }
    
    public List<String> removeInvalidParentheses(String s){
        Object[] info=prepare(s);
        
        String prepared=(String) info[0];
        
        List<Integer> rightIndexes=(List<Integer>) info[1];
        List<Integer> leftIndexes=(List<Integer>) info[2];
        int rightMinNumber = (Integer) info[3];
        int leftMinNumber = (Integer) info[4];
        
        if(rightMinNumber==0 && leftMinNumber==0) return Arrays.asList(prepared);
        //if we are gonna delete right parentheses we need to decrease index for left parentheses
        if(leftMinNumber>0 && rightMinNumber>0){
                for(int j=0;j<leftIndexes.size();j++){
                    leftIndexes.set(j, leftIndexes.get(j)-rightMinNumber);
                }
            }
        StringBuilder sb=new StringBuilder(prepared);
        return generate(sb,rightIndexes,leftIndexes,rightMinNumber,leftMinNumber);
    }
    
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        List<String> result = p.removeInvalidParentheses(")(()())())r())");
        System.out.println(result);
    }
}
