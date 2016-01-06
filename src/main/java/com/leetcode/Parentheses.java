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
    //prepares the String and returns minRightIndexes and minLeftIndexes
    public List<Object> prepare(StringBuilder sb, char parenthesis){
        List<Integer> indexes=new ArrayList<Integer>();
        int minNumber=0;
        int balance=0;
        int count=0;
        int left=0;
        char otherParenthesis;
        if (parenthesis=='(') otherParenthesis=')';
        else{
            sb.reverse();
            otherParenthesis='(';
        }
        int sblength=sb.length();
        for(int i=0;i<sblength;i++){
            char currentChar=sb.charAt(i);
            if(currentChar==parenthesis) {
                balance++;
                count=0;
                left++;
            }
            if(currentChar==otherParenthesis) {
                balance--;
                count++;
            }
            if(balance<0){
                char previousChar=0;
                if(i>=1) previousChar=sb.charAt(i-1);
                if(i-sb.indexOf(Character.toString(parenthesis))<=2 || (count>left && (previousChar==')'|| previousChar=='(') && previousChar==sb.charAt(i-2))){
                    sb.deleteCharAt(i--); //after this cursor goes on the next character so we have to decrease it
                    sblength--; 
                } else {
                    indexes.add(i);
                    minNumber++;
                } 
                balance=0;
            }
        }
        List<Object> result=new ArrayList<Object>();
        if(parenthesis=='('){ //leftSide
            if(minNumber==0) result.add(Arrays.asList(-1));
            else result.add(indexes);
            if(balance>0) result.addAll(prepare(sb,')'));
            else result.addAll(Arrays.asList(Arrays.asList(-1),sb));
        } 
        else { //rightSide
            sb.reverse(); //reversing the string back to normal
            if(minNumber==0)result.add(Arrays.asList(-1));
            else{    
                int isize=indexes.size();
                for(int i=0;i<isize;i++){//we also have to reverse the indexes
                    indexes.set(i, sblength-1-indexes.get(i));
                }
                result.add(indexes);
            }
            result.add(sb);
        }
    return result;
    }
    //generates list of solutions based on minIndexes and input string
    public List<String> generate(StringBuilder sb,int start,int minNumber){
        List<String> solutions=new ArrayList<String>();
        StringBuilder original=new StringBuilder(sb);
        char parenthesis=sb.charAt(start);
        if(minNumber==1){ //base case
            for(int i=start;i<sb.length();i++){
                if(sb.charAt(i)==parenthesis){
                    while(i<sb.length()-1 && sb.charAt(i)==sb.charAt(i+1)){ //always deletes only the last one from group
                        i++;
                    }
                sb.deleteCharAt(i);
                if (parenthesis==')') solutions.add(sb.toString());
                else solutions.add(sb.reverse().toString());
                sb=new StringBuilder(original);
                }
            }
            return solutions;
        }
        if(minNumber>1){ //recursive case
            sb.deleteCharAt(start);
            solutions.addAll(generate(sb,sb.indexOf(Character.toString(parenthesis), start),minNumber-1));
            
            int start2=original.indexOf(Character.toString(parenthesis), start+1);
            while(start<original.length()-1 && start2==start+1){
                start2=original.indexOf(Character.toString(parenthesis), start2+1);
                start++; //always on the first of the group
            }
            if(start2!=-1){
            int firstMinIndex=original.length()-1;
            int balance=0;
            char otherParenthesis;
            if(parenthesis=='(') otherParenthesis=')';
            else otherParenthesis='(';
            for(int i=0;i<original.length();i++){
                if(original.charAt(i)==otherParenthesis) balance++;
                if(original.charAt(i)==parenthesis) balance--;
                if(balance<0){
                    firstMinIndex=i;
                    break;
                }
            }
            if(start2<=firstMinIndex){
                solutions.addAll(generate(original,start2,minNumber));
            }
            }
            return solutions;
        }
        return solutions;
    }
    //method that controls the flow of the program
    public List<String> removeInvalidParentheses(String s){
        List<String> solutions=new ArrayList<String>();
        StringBuilder sb = new StringBuilder(s);
        List<Object> prepared=prepare(sb,'(');
        List<Integer> minRightIndexes=(List<Integer>)prepared.get(0);
        List<Integer> minLeftIndexes=(List<Integer>)prepared.get(1);
        sb=(StringBuilder)prepared.get(2);
        //code for dividing on leftSide, middle and rightSide
        int len,firstLeftIndex;
        len=firstLeftIndex=sb.length();
        int minRightNumber,minLeftNumber,lastRightIndex;
        minRightNumber=minLeftNumber=0;
        lastRightIndex=-1;
        if(minRightIndexes.get(0)!=-1){
            minRightNumber=minRightIndexes.size();
            lastRightIndex=minRightIndexes.get(minRightNumber-1);
        }
        if(minLeftIndexes.get(0)!=-1){
            minLeftNumber=minLeftIndexes.size();
            firstLeftIndex=minLeftIndexes.get(minLeftNumber-1);
        }
        String leftSide=sb.substring(0,lastRightIndex+1);
        String middle=sb.substring(lastRightIndex+1,firstLeftIndex);
        String rightSide=sb.substring(firstLeftIndex,len);
        
        System.out.println("minLeftIndexes "+minLeftIndexes);
        System.out.println("minRightIndexes "+minRightIndexes);
        System.out.println("lastRightIndex "+(lastRightIndex));
        System.out.println("leftSide "+leftSide);
        System.out.println("middle "+middle);
        System.out.println("rightSide "+rightSide);
        
        
        List<String> leftSideSolutions=new ArrayList<String>(Arrays.asList(""));
        if(minRightNumber!=0) leftSideSolutions=generate(new StringBuilder(leftSide),leftSide.indexOf(')'),minRightNumber);
        List<String> rightSideSolutions=new ArrayList<String>(Arrays.asList(""));
        if(minLeftNumber!=0){
            StringBuilder rightSideSB=new StringBuilder(rightSide);
            rightSideSB.reverse();
            rightSideSolutions=generate(rightSideSB,rightSideSB.indexOf("("),minLeftNumber);
        }
        for(String l:leftSideSolutions){
            for(String r:rightSideSolutions){
                solutions.add(l+middle+r);
            }
        }
        return solutions;
    }
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        List<String> result =p.removeInvalidParentheses("r)(p()q)ux)((()");
        List<String> expected = new ArrayList<String>(Arrays.asList("r(p(q)ux)()","r(p()qux)()","r(p()q)ux()"));
        System.out.println("result   "+result);
        System.out.println("expected "+expected);
    }
}
