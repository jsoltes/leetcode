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
    //prepares the String and returns minRightIndexes and minLeftIndexes
    public List<Object> prepare(StringBuilder sb, char parenthesis){
        List<Integer> indexes=new ArrayList<Integer>();
        int minNumber=0;
        int balance=0;
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
                left++;
            }
            if(currentChar==otherParenthesis) {
                balance--;
            }
            if(balance<0){
                char previousChar=((i>=1)?sb.charAt(i-1):0);
                if(i-sb.indexOf(Character.toString(parenthesis))<=2 || (-balance>left && (previousChar==')'|| previousChar=='(') && previousChar==sb.charAt(i-2))){
                    sb.deleteCharAt(i--); //after this cursor goes on the next character so we have to decrease it
                    sblength--; 
                } else {
                    indexes.add(i);
                    minNumber++;
                } 
                balance=0;
            }
        }
        List<Object> result=new ArrayList<Object>(3);
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
    public List<StringBuilder> generate(StringBuilder sb,int start,int minNumber){
        List<StringBuilder> solutions=new ArrayList<StringBuilder>();
        StringBuilder original=new StringBuilder(sb);
        char parenthesis=sb.charAt(start);
        int len=sb.length();
        if(minNumber==1){ //base case
            for(int i=start;i<len;i++){
                if(sb.charAt(i)==parenthesis){
                    while(i<len-1 && sb.charAt(i)==sb.charAt(i+1)){ //always deletes only the last one from group
                        i++;
                    }
                sb.deleteCharAt(i);
                if (parenthesis==')') solutions.add(sb);
                else solutions.add(sb.reverse());
                sb=new StringBuilder(original);
                }
            }
            return solutions;
        }
        else if(minNumber>1){ //recursive case
            sb.deleteCharAt(start);
            solutions.addAll(generate(sb,sb.indexOf(Character.toString(parenthesis), start),minNumber-1));
            
            int start2=original.indexOf(Character.toString(parenthesis), start+1);
            while(start<len-1 && start2==start+1){
                start2=original.indexOf(Character.toString(parenthesis), start2+1);
                start++; //always on the first of the group
            }
            if(start2!=-1){
            int firstMinIndex=len-1;
            int balance=0;
            char otherParenthesis;
            if(parenthesis=='(') otherParenthesis=')';
            else otherParenthesis='(';
            for(int i=0;i<len;i++){
                char thisChar=original.charAt(i);
                if(thisChar==otherParenthesis) balance++;
                if(thisChar==parenthesis) balance--;
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
        String rightSide=sb.substring(firstLeftIndex,len);
        StringBuilder middle=sb.delete(firstLeftIndex,len).delete(0, lastRightIndex+1);

        List<StringBuilder> leftSideSolutions= (minRightNumber == 0) ? new ArrayList<StringBuilder>(Arrays.asList(new StringBuilder(""))): generate(new StringBuilder(leftSide),leftSide.indexOf(')'),minRightNumber);
        List<StringBuilder> rightSideSolutions=new ArrayList<StringBuilder>(Arrays.asList(new StringBuilder("")));
        if(minLeftNumber!=0){
            StringBuilder rightSideSB=new StringBuilder(rightSide);
            rightSideSB.reverse();
            rightSideSolutions=generate(rightSideSB,rightSideSB.indexOf("("),minLeftNumber);
        }
        List<String> solutions=new ArrayList<String>(leftSideSolutions.size()*rightSideSolutions.size());
        for(StringBuilder l:leftSideSolutions){
            for(StringBuilder r:rightSideSolutions){
                solutions.add(new StringBuilder(l).append(middle).append(r).toString());
            }
        }
        return solutions;
    }
}
