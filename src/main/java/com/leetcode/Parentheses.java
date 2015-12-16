package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Integer> rightIndexes=new ArrayList<Integer>();
        int rightMinNumber=0;
        
        int balance=0;
        int left=0;
        //gets indexes of wrong right parentheses (from which you can get rightMinNumber and all possible removal of right parentheses)
        for(int i=0;i<sb.length();i++){
            char currentChar=sb.charAt(i);
            if(currentChar=='(') {
                balance++;
                left++;
            }
            if(currentChar==')') {
                balance--;
            }
            if(balance<0){
                //in these cases we remove:((((()))))), )(), ()),)a)() in these cases not: ()()),()a)()
                //odstranit - ak left =0; ak left=1 a na i-1 pozicii nie je symbol; ak left je viac ako 1 a na i-1 pozicii je to iste ako na i-2
                char previousChar=sb.charAt(i-1);
                if((left==0) || (left==1 && (previousChar=='(' || previousChar==')')) || (left>=2 && previousChar==sb.charAt(i-2))){
                    sb.deleteCharAt(i);
                    i--; //because otherways we would skip one character
                    
                } else {
                    rightIndexes.add(i);
                    rightMinNumber++;
                } 
                balance=0;
            }
        }

        if(rightMinNumber>0){
            //adds other possible right indexes
            for(int i=sb.indexOf("(")+1;i<rightIndexes.get(rightIndexes.size()-1);i++){
                if(sb.charAt(i)==')' && !rightIndexes.contains(i) && sb.charAt(i+1)!=')') rightIndexes.add(i);
            }
        }
        
        
        //if at the end there are extra left parentheses to be removed... 
        List<Integer> leftIndexes=new ArrayList<Integer>();
        int leftMinNumber=0;
        if(balance>0){
            balance=0;
            int right=0;
            //for loop for getting indexes of wrong left parentheses (from which you can get leftMinNumber and all possible removals of left indexes)
            for(int i=sb.length()-1;i>=0;i--){
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
                    } else {
                        leftIndexes.add(i);
                    } 
                    balance=0;
                }
            }
        if(leftMinNumber>0){
            //adds other possible left indexes
        for(int i=sb.lastIndexOf(")")-1;i>leftIndexes.get(0);i--){
                if(sb.charAt(i)=='(' && !leftIndexes.contains(i) && sb.charAt(i-1)!='(') leftIndexes.add(i);
            }
        }
        }
        Collections.sort(leftIndexes);
        Collections.sort(rightIndexes);
        info[0]=sb.toString();
        info[1]=rightIndexes;
        info[2]=leftIndexes;
        info[3]=rightMinNumber;
        info[4]=leftMinNumber;
        return info;
    }
    /*
    public Set<String> addToList(String s,int minNumber){ //"()(((()m)"
        Set<String> solutions=new HashSet<String>();
        
        String p=prepare(s);
        if(s.length()-p.length()>minNumber) return solutions;//this means we don't have any valid solutions with this one
        minNumber-=s.length()-p.length();
        s=p; //()(()m), 0
        
        
        if (isValid(p)) solutions.add(p);
        else{
            String original =s;  
            for(int i=0;i<s.length();i++){ 
                if(s.charAt(i)=='(' || s.charAt(i)==')'){
                    //if the char at the previous index was the same, we would just generate more of the same solutions
                    if(i==0 || s.charAt(i)!=s.charAt(i-1)){
                        s=removeParenthesis(s,i);
                        if(minNumber>1){
                            
                            //we use prepare to have less cycles
                            solutions.addAll(addToList(s,minNumber-1));
                        }
                        else{
                        if(isValid(s) && solutions.contains(s)!=true)
                            solutions.add(s);
                        }
                        s=original;
                    }
                }
            }
        }
        return solutions;
    }
            
    public List<String> removeInvalidParentheses(String s){
        s=prepare(s); //
        //the input String is already ok
        if(isValid(s)) return Arrays.asList(s);
        //some parentheses have to be removed
        else{
            int minNumber=getMinNumber(s);
            Set<String> solutions=addToList(s,minNumber);
            return new ArrayList(solutions);
        }
    }
    */
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        Object[] info = p.prepare("((((())))))");
        String s = (String)p.prepare("((((())))))")[0];
        List<Integer> result1 = (List<Integer>) info[1];
        List<Integer> result2 = (List<Integer>) info[2];
        System.out.println(result2);
    }
}
