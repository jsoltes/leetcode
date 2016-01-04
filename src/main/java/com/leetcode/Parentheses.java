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
    //prepares the String from one side based on the input parenthesis
    public Object[] prepare(StringBuilder sb, char parenthesis){
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
        for(int i=0;i<sblength;i++){//o(()()()m()((()t
            char currentChar=sb.charAt(i);//t;);(;(
            if(currentChar==parenthesis) {
                balance++;//1
                count=0;
                left++;//1
            }
            if(currentChar==otherParenthesis) {
                balance--;//0;-1
                count++;//1;2
            }
            if(balance<0){
                char previousChar=0;
                if(i>=1) previousChar=sb.charAt(i-1);
                if(i-sb.indexOf(Character.toString(parenthesis))<=2 || (count>left && (previousChar==')'|| previousChar=='(') && previousChar==sb.charAt(i-2))){
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
        if(parenthesis=='('){ //leftSide
            Object[] result;
            if(minNumber==0) result=new Object[]{sb,Arrays.asList(-1),0};
            else result=new Object[]{sb,indexes,minNumber};
            } 
        else { //rightSide
            Object[] result;
            sb.reverse(); //reversing the string back to normal
            if(minNumber==0) result=new Object[]{sb,Arrays.asList(-1),0};
            else{
                int isize=indexes.size();
                for(int i=0;i<isize;i++){//we also have to reverse the indexes
                    indexes.set(i, sblength-1-indexes.get(i));
                }
                result = new Object[]{sb,indexes,minNumber};
            }
        }
    return null;
    }
    //gets indexes for all possible parentheses removals
    public List<Integer> getIndexes(StringBuilder sb, char parenthesis, int minNumber, List<Integer> minIndexes){//
        List<Integer> indexes = new ArrayList<Integer>();//"(l))())t", ')', 2,[3,6]
        int length=sb.length();
        for(int i=0;i<length;i++){
            if(sb.charAt(i)==parenthesis){
                if(i+1<length && sb.charAt(i+1)!=parenthesis) { //if it is sole parenthesis
                    indexes.add(i);
                    i++; //we increment, because the next character isn't the parenthesis
                }
                else {//there is a group of parentheses
                    List<Integer> newIndexes = new ArrayList<Integer>();
                    while(i<length && sb.charAt(i)==parenthesis){
                        newIndexes.add(i);
                        i++;
                    }
                    if(i-newIndexes.get(0)<=minNumber && newIndexes.get(0)!=sb.indexOf("(")) indexes.addAll(newIndexes);
                    else indexes.add(newIndexes.get(0));
                }
            }
        }
        for(int mi:minIndexes) if(!indexes.contains(mi)) indexes.add(mi);
        if(parenthesis=='(') Collections.sort(indexes);
        System.out.println("minIndexes "+minIndexes);
        System.out.println("indexes"+indexes);
        return indexes;
    }
    public List<String> generate2(StringBuilder sb,int minNumber, List<Integer> indexes){
        List<StringBuilder> solutions=new ArrayList<StringBuilder>();
        StringBuilder original=new StringBuilder(sb);
        for(int i=0;i<indexes.size();i++){
            int position = indexes.get(i);
            if(i==0||position-1!=indexes.get(i-1)) {
                sb.deleteCharAt(position);
                solutions.add(sb);
                sb=original;
            }
        }
        List<String> results=new ArrayList<String>();
        for(StringBuilder sbu:solutions) results.add(sbu.toString());
        return results;
    }
 
    public List<String> generate(StringBuilder sb,int minNumber, List<Integer> indexes){
        List<String> solutions=new ArrayList<String>();//"()())()))())()",4,[1, 3, 4, 6, 7, 8, 10, 11]
        StringBuilder original=new StringBuilder(sb);//()())()))())()
        int isize=indexes.size();//8
        if(minNumber==1){ //base case
            for (int i=0;i<isize;i++){
                int position=indexes.get(i);
                if(position==0 || (position-1>=0 && sb.charAt(position)!=sb.charAt(position-1))){
                    sb.deleteCharAt(position);
                    solutions.add(sb.toString());
                    sb=new StringBuilder(original);
                }
            }
            return solutions;
        }
        else { //recursive case
            int position=indexes.get(0);//1
            int p=1;
            sb.deleteCharAt(position);//(())()))())()
            indexes=indexes.subList(1,isize);//[3, 4, 6, 7, 8, 10, 11]
            List<Integer> indexes2=new ArrayList<Integer>(indexes);//[3, 4, 6, 7, 8, 10, 11]
            System.out.println("position "+position);
            System.out.println("just indexes 2 "+indexes2);
            isize=indexes.size();
            for(int j=0;j<isize;j++) indexes.set(j, indexes.get(j)-1);//[2, 3, 5, 6, 7, 9, 10]
            if(indexes.size()>0) solutions.addAll(generate(sb,minNumber-1,indexes));//(())()))())(),3,[2, 3, 5, 6, 7, 9, 10];
            if(isize>=minNumber){//
                System.out.println("indexes2 before "+indexes2);
                while(position+1==indexes2.get(0)){//
                    indexes2=indexes2.subList(1,indexes2.size());
                    position++;
                }//
                System.out.println("indexes2 after "+indexes2);
                if(indexes2.get(indexes2.size()-1)==original.lastIndexOf(")")) indexes2=indexes2.subList(0, indexes2.size()-1);
                solutions.addAll(generate(original,minNumber,indexes2));//()())()))())(),4,[3, 4, 6, 7, 8, 10, 11] 
            }
        return solutions;
        }
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
    
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        List<String> result = p.removeInvalidParentheses("()())())))())(()");
        System.out.println("result "+result);
    }
}
