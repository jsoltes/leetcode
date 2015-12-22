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
    public List<String> generate(StringBuilder sb,int minNumber, List<Integer> indexes, char parenthesis){
        List<String> solutions=new ArrayList<String>();
        StringBuilder original=new StringBuilder(sb);
        int isize=indexes.size();
        for (int i=0;i<isize;i++){
            int position = indexes.get(i);
            sb.deleteCharAt(position);
            //we need to delete duplicates at the same time
            int count=0;
            if(position+1==indexes.get(i)){
                sb.deleteCharAt(position);
                count++;
            }
            //////recursive case//////
            if (minNumber>1){
                indexes=indexes.subList(i+1+count,isize);
                List<Integer> indexes2=new ArrayList<Integer>(indexes);
                isize=isize-1-count;//the same as indexes.size()
                for(int j=0;j<isize;j++){
                    indexes.set(j, indexes.get(j)-1-count);
                }
                solutions.addAll(generate(sb,minNumber-1-count,indexes,parenthesis));
                if(isize>=minNumber){
                    //we need to delete duplicates at the same time
                    /*
                    if(position+1==indexes2.get(i)){
                        indexes2=indexes2.subList(1,indexes2.size());
                    }*/
                    solutions.addAll(generate(original,minNumber,indexes2,parenthesis));
                }
                return solutions;
            }
            //////end of recursive case//////
            solutions.add(sb.toString());
            sb=new StringBuilder(original);
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
    //for special cases like this (r(()()->(r)() we have to generate extra solutions
    public List<String> generateSpecial(StringBuilder sb,int minNumber,char parenthesis){//l(((())((z))
        List<String> specialSolutions=new ArrayList<String>();
        int count=0;
        boolean canRemove=false;
        char otherParenthesis=0;
        if(parenthesis=='(') otherParenthesis=')';
        else otherParenthesis='(';
        StringBuilder original=new StringBuilder(sb);
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i)==parenthesis) count++;
            if(sb.charAt(i)!=parenthesis){
                if(count>=minNumber && canRemove) {
                    sb.delete(i-minNumber, i);
                    specialSolutions.add(sb.toString());
                    sb=original;
                }
                if(count!=0 || sb.charAt(i)==otherParenthesis) canRemove=true;
                count=0;
            }
        }
        return specialSolutions;
    }
    */
    //prepares string and returns one part(left or right) of the string
    public StringBuilder prepare(StringBuilder sb){
        List<Integer> indexes=new ArrayList<Integer>();
        int minNumber=0;
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
                //remove - if left =0; if left=1 and on i-1 position isn't any symbol; if left is 2 and on i-1 position is the same as on i-2
                char previousChar=0;
                if(left>=1) previousChar=sb.charAt(i-1);
                if((left==0) || (left==1 && (previousChar=='(' || previousChar==')')) || (left==2 && previousChar==sb.charAt(i-2))){
                    sb.deleteCharAt(i); //after this cursor goes on the next character so we have to decrease it
                    i--; //because otherways we would skip one character
                    sblength--; //because the string is now shorter
                } else {
                    indexes.add(i);
                    minNumber++;
                } 
                balance=0;
            }
        }
        return sb;
    }
    
    public List<String> removeInvalidParentheses(String s){
   ////////////firstly we get rightMinNumber and rightMinIndexes/////////////////////// 
        StringBuilder sb = new StringBuilder(s);
        List<Integer> rightIndexes=new ArrayList<Integer>();
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
                if((left==0) || (left==1 && (previousChar=='(' || previousChar==')')) || (left==2 && previousChar==sb.charAt(i-2))){
                    sb.deleteCharAt(i); //after this cursor goes on the next character so we have to decrease it
                    i--; //because otherways we would skip one character
                    sblength--; //because the string is now shorter
                } else {
                    rightIndexes.add(i);
                    rightMinNumber++;
                } 
                balance=0;
            }
        }
/////////then we add all other possible indexes to rightMinIndexes and we create rightIndexes/////////
        if(rightMinNumber>0){
            //adds other possible right indexes
            List<Integer> newIndexes=new ArrayList<Integer>();
            int start=sb.indexOf("(")+1;
            int end=sb.lastIndexOf(")");
            int lastRightIndex=rightIndexes.get(rightIndexes.size()-1);
            for(int i=start;i<lastRightIndex;i++){
                int count=0;
                while(i<=lastRightIndex && sb.charAt(i)==')'){
                    newIndexes.add(i);
                    count++;
                    i++;
                }
                if(count>0 && count<=rightMinNumber && newIndexes.get(newIndexes.size()-1)!=end){
                    rightIndexes.addAll(newIndexes);
                    newIndexes.clear();
                }
            }
        }
        Collections.sort(rightIndexes);
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
                    if((right==0) || (right==1 && (nextChar=='(' || nextChar==')')) || (right==2 && nextChar==sb.charAt(i+2))){
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
            List<Integer> newIndexes=new ArrayList<Integer>();//()v)(()(())
            int firstLeftIndex=leftIndexes.get(0);
            System.out.println("firstLeftIndex "+firstLeftIndex);//4
            int start2=sb.lastIndexOf(")")-1;
            int end2=sb.indexOf("(");
            for(int i=start2;i>firstLeftIndex;i--){
                int count=0;
                while(i>=firstLeftIndex && sb.charAt(i)=='('){
                    newIndexes.add(i);
                    count++;
                    i--;
                }
                if(count>1 && count<=leftMinNumber){
                    leftIndexes.addAll(newIndexes);
                }
                if(count>1 && count>leftMinNumber){
                    leftIndexes.add(i+1);
                }
                newIndexes.clear();
            }
        }
    }
        System.out.println("sb "+sb);
        System.out.println("rightMinNumber "+rightMinNumber);
        System.out.println("leftMinNumber "+leftMinNumber);
        System.out.println("leftIndexes " +leftIndexes);
        System.out.println("rightIndexes "+rightIndexes);

///////////we use rightMinNumber and leftMinNumber to manage the flow of the program////////////////////
        //first case - the string is already prepared - so the program has only one solution
        if(rightMinNumber==0 && leftMinNumber==0) return Arrays.asList(sb.toString());
        //second case - there are at least right parentheses to be removed
        StringBuilder original=new StringBuilder(sb);
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
                /*//for this case (r(()()->(r)() we have to add another special cases:
                if(leftMinNumber>=2){
                    rightSideList.addAll(generateSpecial(original,leftMinNumber,'('));
                }
                if(rightMinNumber>=2){
                    leftSideList.addAll(generateSpecial(original,rightMinNumber,')'));
                }
                //end of the special cases*/
                return connect(leftSideList,middle,rightSideList);
            }
            
            //there are only right parentheses to be removed
            /*//for this case (r(()()->(r)() we have to add this special case:
                if(rightMinNumber>=2){
                    leftSideList.addAll(generateSpecial(original,rightMinNumber,')'));
                }
            //end of the special case*/
            return leftSideList; 
        } 
        //third case - there are only left parentheses to be removed
        Collections.sort(leftIndexes);
        List<String> rightSideList = generate(sb,leftMinNumber,leftIndexes,'(');
        //System.out.println("before special "+rightSideList);
        /*//for this case (r(()()->(r)() we have to add this special case:
        System.out.println("before special "+rightSideList);
        if(leftMinNumber>=2){
            rightSideList.addAll(generateSpecial(original,leftMinNumber,'('));
        }
        //end of the special case*/
        return rightSideList;
    }
    
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        List<String> result = p.removeInvalidParentheses("())v)(()(((((())");
        System.out.println("result "+result);
    }
}
