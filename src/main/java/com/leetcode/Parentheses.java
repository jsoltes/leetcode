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

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    //count left parentheses
    public int countLeft(String s){
        int numLeft=0; 
        for(int i=0;i<s.length();i++) 
            if(s.charAt(i)=='(') numLeft++;
        return numLeft;
    }
    
    //count right parentheses
    public int countRight(String s){
        int numRight=0;
        for(int i=0;i<s.length();i++) 
            if(s.charAt(i)==')') numRight++;
        return numRight;
    }
    
    //decide if the string contains invalid parentheses
    public boolean isValid(String s){     
        //the empty string is valid
        if ("".equals(s)) return true;
        //start and end characters have to be '(' and ')'
        if (s.charAt(0)!='(' || s.charAt(s.length()-1)!=')') return false;
        //number of left and right parentheses has to be equal
        return countLeft(s) == countRight(s);
    }

    //returns minimum number of invalid parentheses
    public int getMinNumber(String s){
        return Math.abs(countLeft(s)-countRight(s));
    }
    
    //returns the exceeding parenthesis
    public char getExceedingParenthesis(String s){
        if(countLeft(s)>countRight(s)) return '(';
        return ')';
    }
    
    //removes next parentesis of particular type from the given position
    public String removeParenthesis(String s, int index){
        StringBuilder sb = new StringBuilder(s);
        sb.replace(index,index+1,"");
        return sb.toString();
    }
    
    public List<Integer> getPositions(String s,char c){
        //treba este vymysliet co ked s.equals("");
        List<Integer> result = new ArrayList<Integer>();
        for(int i=0;i<s.length();i++){
            if (s.charAt(i)==c) result.add(i);
        }
        return result;
    }
    /*
    public void addToList(String s,List<String> solutions,int minNumber){ //((())())())),null,2//
        if ("".equals(s)) solutions.add("");
        else{
            String original =s;
            char parenthesis=getExceedingParenthesis(getS());
        
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)==parenthesis){
                    s=removeParenthesis(s,i);
                    if(minNumber>1){
                        addToList(s,solutions,minNumber-1);
                    }
                    else{
                    if(isValid(s) && solutions.contains(s)!=true)
                        solutions.add(s);
                    s=original;
                    }
                }
            }
        }
    }
    */
            
    public List<String> removeInvalidParentheses(String s){
        List<String> solutions =new ArrayList<String>();
        //the input String is already ok
        if(isValid(s)) return Arrays.asList(s); 
        //some parentheses have to be removed
        else{ 
            //addToList(s,solutions,getMinNumber(this.s));
            return solutions;
        }
    }
    
    public static void main(String[] args) {
        
    }
}
