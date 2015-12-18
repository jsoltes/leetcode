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
    public List<String> generate(StringBuilder side,int minNumber, List<Integer> indexes, char parenthesis){
        List<String> solutions=new ArrayList<String>(); 
        
        System.out.println("indexes: "+indexes);
        
        int isize=indexes.size();
        for (int i=0;i<isize;i++){ //iterates through indexes of indexes! 
            int position = indexes.get(i);
            side.deleteCharAt(position);
            if (minNumber>1){        
                isize--;
                indexes=indexes.subList(i+1, indexes.size());
                isize=indexes.size();
                for(int j=0;j<isize;j++){
                    indexes.set(j, indexes.get(j)-1);
                    
                }
                solutions.addAll(generate(side,minNumber-1,indexes,parenthesis));
            }
            else solutions.add(side.toString());
            side.insert(position, parenthesis);
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
            
    public List<String> removeInvalidParentheses(String s){
   ////////////firstly we get rightMinNumber and rightMinIndexes/////////////////////// 
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
                    sblength--; //because the string is now shorter
                } else {
                    rightMinIndexes.add(i);
                    rightMinNumber++;
                } 
                balance=0;
            }
        }
/////////then we add all other possible indexes to rightMinIndexes and we create rightIndexes/////////
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
///////////if balance>0 there are extra left parentheses to be removed and we get leftMinNumber and leftIndexes////////
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
//////if still after we prepare the string from the right side there are some parentheses to be removed we add also other possible indexes/////
        if(leftMinNumber>0){
            int firstLeftIndex=leftIndexes.get(0);
            int start2=sb.lastIndexOf(")")-1;
            for(int i=start2;i>firstLeftIndex;i--){
                if(sb.charAt(i)=='(' && !leftIndexes.contains(i) && sb.charAt(i-1)!='(') leftIndexes.add(i);
            }
        }
    }
///////////we use rightMinNumber and leftMinNumber to manage the flow of the program////////////////////
        //first case - the string is already prepared - so the program has only one solution
        if(rightMinNumber==0 && leftMinNumber==0) return Arrays.asList(sb.toString());
        //second case - there are at least right parentheses to be removed
        if(rightMinNumber!=0) { //
            List<String> leftSideList = generate(sb,rightMinNumber,rightIndexes,')');
            //if there are also left parentheses to be removed
            if(leftMinNumber!=0){
                Collections.sort(leftIndexes);
                List<String> rightSideList = generate(sb,leftMinNumber,leftIndexes,'(');
                Collections.sort(leftIndexes); //TODO zistit ci to tu potrebujem
                int smallestLeftIndex=leftIndexes.get(0);
                int biggestRightIndex=rightIndexes.get(rightIndexes.size()-1);
                String middle = sb.substring(biggestRightIndex+1,smallestLeftIndex);
                //every element in rightSideList and leftSideList have to be trimmed so that we can connect them
                for(int j=0;j<leftSideList.size();j++){
                    leftSideList.set(j, leftSideList.get(j).substring(0, biggestRightIndex+1-rightMinNumber));
                }
                for(int j=0;j<rightSideList.size();j++){
                    rightSideList.set(j, rightSideList.get(j).substring(smallestLeftIndex+1-rightMinNumber, sb.length()-rightMinNumber));
                }
                //tu miesam hrusky s jablkami robim s listom to, co som chcel sobit so stringami, ktore ten list obsahuje - opravit
                return connect(leftSideList,middle,rightSideList);
            }
            //there are only right parentheses to be removed
            return leftSideList; 
        } 
        //third case - there are only left parentheses to be removed
        Collections.sort(leftIndexes);
        List<String> rightSideList = generate(sb,leftMinNumber,leftIndexes,'(');
        return rightSideList;
    }
    
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        List<String> result = p.removeInvalidParentheses("()())())))())(()");
        System.out.println("result "+result);
    }
}
