package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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
    private String s;
    //count left parentheses
    private int countLeft(String s){
        int numLeft=0; 
        for(int i=0;i<s.length();i++) 
            if(s.charAt(i)=='(') numLeft++;
        return numLeft;
    }
    
    //count right parentheses
    private int countRight(String s){
        int numRight=0;
        for(int i=0;i<s.length();i++) 
            if(s.charAt(i)==')') numRight++;
        return numRight;
    }
    
    //decide if the string contains invalid parentheses
    private boolean isValid(String s){     
        //start and end characters have to be '(' and ')'
        if (s.charAt(0)!='(' || s.charAt(s.length()-1)!=')') return false;
        //number of left and right parentheses has to be equal
        return countLeft(s) == countRight(s);
    }

    //returns minimum number of invalid parentheses
    private int getMinNumber(String s){
        return Math.abs(countLeft(s)-countRight(s));
    }
    
    //returns the exceeding parenthesis
    private char getExceedingParenthesis(String s){
        if(countLeft(s)>countRight(s)) return '(';
        return ')';
    }
    
    //removes next parentesis of particular type from the given position
    private String removeParenthesis(String s, int index){
        StringBuilder sb = new StringBuilder(s);
        sb.replace(index,index+1,"");
        return sb.toString();
    }
    
    private void addToList(String s,List<String> solutions,int minNumber){
        String original =this.s;
        char parenthesis=getExceedingParenthesis(original);
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==parenthesis){
                s=removeParenthesis(s,i);
                if(minNumber>1)
                    addToList(s,solutions,minNumber-1);
                else{
                  if(isValid(s) && solutions.contains(s)!=true)
                      solutions.add(s);
                  s=original;
                }
            }
        }
    }
            
    public List<String> removeInvalidParentheses(String s){
        List<String> solutions =new ArrayList<String>();
        //the input String is already ok
        if(isValid(s)) return Arrays.asList(s); 
        //some parentheses have to be removed
        else{ 
            addToList(s,solutions,getMinNumber(this.s));
            return solutions;
        }
    }
}
