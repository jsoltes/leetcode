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
                solutions.add(sb.toString());
                sb=original;
                }
            }
            return solutions;
        }
        if(minNumber>1){ //recursive case
            System.out.println("start "+start);
            sb.deleteCharAt(start);
            solutions.addAll(generate(sb,sb.indexOf(Character.toString(parenthesis), start),minNumber-1));
            System.out.println("solutions "+solutions);
            
            System.out.println("original "+original);
            int start2=original.indexOf(Character.toString(parenthesis), start+1);
            while(start<original.length()-1 && start2==start+1){
                start2=original.indexOf(Character.toString(parenthesis), start2+1);
                start++; //always on the first of the group
            }
            System.out.println("start2 "+start2);
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
            System.out.println("firstMinIndex "+firstMinIndex);
            if(start2<=firstMinIndex){
                solutions.addAll(generate(original,start2,minNumber));
            }
            }
            return solutions;
        }
        return solutions;
    }
    
    public List<String> connect(List<String> leftSideList,String middle,List<String> rightSideList){
        List<String> solutions=new ArrayList<String>();
        for(String r:rightSideList){
            for(String l:leftSideList){
                solutions.add(l+middle+r);
            }
        }
        return solutions;
    }
    /*
    //method that controls the flow of the program
    public List<String> removeInvalidParentheses(String s){
        StringBuilder sb = new StringBuilder(s);
        //prepare from the left side
        Object[] fromLeft = prepare(sb,'(');
        sb=(StringBuilder)fromLeft[0];
        List<Integer> minRightIndexes=(List<Integer>)fromLeft[1];
        int lastRightIndex=minRightIndexes.get(minRightIndexes.size()-1); //last index of ')' for removal
        int minRightNumber=(Integer)fromLeft[2];
        //now the decision tree
        int lastRight=sb.lastIndexOf(")");
        if(lastRightIndex==lastRight && sb.lastIndexOf("(")<lastRight){ //we don't need to prepare right side
            if(minRightNumber==0) return Arrays.asList(sb.toString()); //means there is only one solution
            else return generate2(sb, minRightNumber, getIndexes(sb, ')', minRightNumber, minRightIndexes));
        }
        else{ //we need to prepare right side
            Object[] fromBoth=prepare(sb,')');//now it is prepared from both sides
            sb=(StringBuilder)fromBoth[0];
            List<Integer> minLeftIndexes=(List<Integer>)fromBoth[1];
            int firstLeftIndex=minLeftIndexes.get(minLeftIndexes.size()-1);//first index of '(' for removal
            int minLeftNumber=(Integer)fromBoth[2];
            System.out.println("sb "+sb);
            System.out.println("minLeftNumber "+minLeftNumber);
            System.out.println("minRightNumber "+minRightNumber);
            if (minRightNumber==0 && minLeftNumber==0) return Arrays.asList(sb.toString());//there is only one solution
            StringBuilder leftSide=new StringBuilder(sb.substring(0, lastRightIndex+1));
            if(minLeftNumber!=0){
                StringBuilder rightSide=new StringBuilder(sb.substring(firstLeftIndex, sb.length()));
                String middle =sb.substring(lastRightIndex+1, firstLeftIndex);
                for(int i=0;i<minLeftIndexes.size();i++) minLeftIndexes.set(i, minLeftIndexes.get(i)-firstLeftIndex);
                List<String> rightSideSolutions = generate2(rightSide,minLeftNumber,getIndexes(rightSide,'(',minLeftNumber,minLeftIndexes));
                if(minRightNumber==0) { //there are only left solutions   
                    List<String> solutions = new ArrayList<String>();
                    for(String r:rightSideSolutions) solutions.add(middle+r);
                    return solutions;
                }
                if (minLeftNumber!=0){
                List<String> leftSideSolutions=generate2(leftSide, minRightNumber, getIndexes(leftSide, ')', minRightNumber,minRightIndexes));
                return connect(leftSideSolutions, middle, rightSideSolutions);
                }
            }
            if(minLeftNumber==0 && minRightNumber!=0) {
                return generate2(sb, minRightNumber, getIndexes(leftSide, ')', minRightNumber,minRightIndexes));
            }
        }
        return null;
    }
    */
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        List<String> result =p.generate(new StringBuilder("((()(()()").reverse(),1,3);
        List<String> expected = Arrays.asList(")))(((",")())((","))()((",")()()(","))(()(","))(()(");
        System.out.println("result "+result);
        System.out.println("expected "+expected);
    }
}
