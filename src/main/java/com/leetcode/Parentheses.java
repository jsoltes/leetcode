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
    //prepares string and returns one part(left+middle or right) of the string
    public Object[] prepare(StringBuilder sb, char parenthesis){
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
        //gets indexes of wrong right parentheses (from which you can get all possible removals of right parentheses)
        int sblength=sb.length();
        for(int i=0;i<sblength;i++){
            char currentChar=sb.charAt(i);
            if(currentChar==parenthesis) {
                balance++;
                left++;
            }
            if(currentChar==otherParenthesis) balance--;
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
        Object[] result;
            if(parenthesis=='('){ //leftSide
                if(minNumber==0) result=new Object[]{sb,-1,0};
                else{
                int lastIndex=indexes.get(indexes.size()-1); //last index of ')' for removal
                result=new Object[]{sb,lastIndex,minNumber};
                }
                
            } 
            else { //rightSide
                sb.reverse(); //reversing the string back to normal
                if(minNumber==0) result=new Object[]{sb,-1,0};
                else{
                int isize=indexes.size();
                for(int i=0;i<isize;i++){//we also have to reverse the indexes
                    indexes.set(i, sblength-1-indexes.get(i));
                }
                int firstIndex=indexes.get(indexes.size()-1);//first index of '(' for removal
                result = new Object[]{sb,firstIndex,minNumber};
                }
            }
        
        return result;
    }
    //gets indexes for all possible parentheses removals
    public List<Integer> getIndexes(StringBuilder preparedSide, char parenthesis, int minNumber){//')'
        List<Integer> indexes = new ArrayList<Integer>();
        int length=preparedSide.length();
        for(int i=0;i<length;i++){
            if(preparedSide.charAt(i)==parenthesis){
                //if there is only one sole parenthesis it can be added to indexes
                if(i+1<length && preparedSide.charAt(i+1)!=parenthesis) {
                    indexes.add(i);
                    i++; //we can increment, because we know the next character isn't parenthesis we are looking for
                }
                //in case of group of parentheses we decide based on their count - if it is less or equal than minNumber, we add all - else only the first one
                else {
                    int count=0;
                    List<Integer> newIndexes = new ArrayList<Integer>();
                    while(i<length && preparedSide.charAt(i)==parenthesis){
                        newIndexes.add(i);
                        count++;
                        i++;
                    }
                    if(count<=minNumber) indexes.addAll(newIndexes);
                    else indexes.add(newIndexes.get(0));
                }
            }
        }
        return indexes;
    }
    
    public List<String> generate(StringBuilder sb,int minNumber, List<Integer> indexes){ //"((m(())()",2,[0,1,3,4,7]
        List<String> solutions=new ArrayList<String>();
        StringBuilder original=new StringBuilder(sb);
        if(minNumber==1){ //base case
            for (int i=0;i<indexes.size();i++){
                int position=indexes.get(i);
                if(position==0 || position-1>=0 && sb.charAt(position)!=sb.charAt(position-1)){
                    sb.deleteCharAt(position);
                    solutions.add(sb.toString());
                    sb=new StringBuilder(original);
                }
            }
            return solutions;
        }
        else { //recursive 
            int isize=indexes.size();//5
            int position=indexes.get(0);//0
            sb.deleteCharAt(position); //(m(())()
            indexes=indexes.subList(1,isize);//[1,3,4,7]
            List<Integer> indexes2=new ArrayList<Integer>(indexes);//[1,3,4,7]
            isize=isize-1;//the same as indexes.size()//4
            for(int j=0;j<isize;j++){
                indexes.set(j, indexes.get(j)-1);
            } //[0,2,3,6]
            solutions.addAll(generate(sb,minNumber-1,indexes));//[]
            if(isize>=minNumber){ //why?
                if(position+1==indexes2.get(0)){
                    indexes2=indexes2.subList(1,indexes2.size());
                }
                solutions.addAll(generate(original,minNumber,indexes2));  
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
        int lastRightIndex=(Integer)fromLeft[1];
        int minRightNumber=(Integer)fromLeft[2];
        //now the decision tree
        if(lastRightIndex==sb.lastIndexOf(")") && sb.lastIndexOf("(")<sb.lastIndexOf(")")){ //we don't need to prepare right side
            if(minRightNumber==0) return Arrays.asList(sb.toString()); //means there is only one solution
            else return generate(sb, minRightNumber, getIndexes(sb, ')', minRightNumber)); //if there are more solutions
        }
        else{ //we need to also prepare right side
            Object[] fromBoth=prepare(sb,')');//now it will be prepared from both sides, because we already prepared it from left side
            sb=(StringBuilder)fromBoth[0];
            int firstLeftIndex=(Integer)fromBoth[1];
            int minLeftNumber=(Integer)fromBoth[2];
            if (minRightNumber==0 && minLeftNumber==0) return Arrays.asList(sb.toString());//there is only one solution
            else{
                if(minLeftNumber!=0 && minRightNumber==0) { //there are only left solutions
                    StringBuilder rightSide=new StringBuilder(sb.substring(firstLeftIndex, sb.length()));
                    String middle =sb.substring(lastRightIndex+1, firstLeftIndex);
                    List<String> rightSideSolutions = generate(rightSide,minLeftNumber,getIndexes(rightSide,'(',minLeftNumber));
                    List<String> solutions = new ArrayList<String>();
                    for(String r:rightSideSolutions) solutions.add(middle+r);
                    return solutions;
                } 
                if(minLeftNumber==0 && minRightNumber!=0) {
                    StringBuilder leftSide=new StringBuilder(sb.substring(0, lastRightIndex+1));
                    return generate(sb, minRightNumber, getIndexes(leftSide, ')', minRightNumber));
                }
                if (minLeftNumber!=0 && minRightNumber!=0){//we divide the sb on three parts left, middle and right based on lastRightIndex & firstLeftIndex
                    StringBuilder leftSide=new StringBuilder(sb.substring(0, lastRightIndex+1));
                    String middle =sb.substring(lastRightIndex+1, firstLeftIndex);
                    StringBuilder rightSide=new StringBuilder(sb.substring(firstLeftIndex, sb.length()));
                    List<String> leftSideSolutions=generate(leftSide, minRightNumber, getIndexes(leftSide, ')', minRightNumber));
                    List<String> rightSideSolutions=generate(rightSide, minLeftNumber, getIndexes(rightSide, '(', minLeftNumber));
                    return connect(leftSideSolutions, middle, rightSideSolutions);
                }
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        System.out.println("indexes "+p.getIndexes(new StringBuilder("n(i()"), '(', 1));
        List<String> result = p.removeInvalidParentheses("))n((i()");
        System.out.println("result "+result);
    }
}
