package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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
    //returns prepared string,  minNumber and type of the parenthesis to be removed
    public Object[] prepare(String s){
        
        Object[] info = new Object[4];
        
        StringBuilder sb = new StringBuilder(s);
        int minNumber=0;
        char type='n';
        List<Integer> indexes=new ArrayList<Integer>();
        
        int balance=0;
        int left=0;
        
        for(int i=0;i<s.length()-1;i++){
            if(sb.charAt(i)=='(') {
                balance++;
                left++;
            }
            if(sb.charAt(i)==')') {
                balance--;
            }
            if(balance<0){
                //in these cases we remove:((((()))))), )(), ()),)a)() in these cases not: ()()),()a)()
                //odstranit - ak left =0; ak left=1 a na i-1 pozicii nie je symbol; ak left je viac ako 1 a na i-1 pozicii je to iste ako na i-2
                if((left==0) || (left==1 && (sb.charAt(i-1)=='(' || sb.charAt(i-1)==')')) || (left>=2 && sb.charAt(i-1)==sb.charAt(i-2))){
                    sb.deleteCharAt(i);
                    i--; //because otherways we would skip one character
                    
                } else {
                    minNumber++; //()a)() this case we still have to count in minNumber
                    type=')';
                    indexes.add(i);
                } 
                balance=0;
            }
        }
        //if at the end there are extra left parentheses to be removed... 
        if(balance>0){
            balance=0;
            int right=0;
            
            for(int i=sb.length()-1;i>=0;i--){
                if(sb.charAt(i)==')') {
                    balance++;
                    right++;
                }
                if(sb.charAt(i)=='(') {
                    balance--;
                }
                if(balance<0){
                    //odstranit - ak right =0; ak right=1 a na i+1 pozicii nie je symbol; ak right je viac ako 1 a na i+1 pozicii je to iste ako na i+2
                    if((right==0) || (right==1 && (sb.charAt(i+1)=='(' || sb.charAt(i+1)==')')) || (right>=2 && sb.charAt(i+1)==sb.charAt(i+2))){
                        sb.deleteCharAt(i);
                    } else {
                        minNumber++;
                        if(type=='n') type='(';
                        if(type==')') type='b';
                        indexes.add(i);
                    } 
                    balance=0;
                }
            }
        }
        info[0]=sb.toString();
        info[1]=minNumber;
        info[2]=type;
        info[3]=indexes;
        return info;
    }
    
 /*   
    //decide if the string contains invalid parentheses
    public boolean isValid(String s){
        //the empty string is valid
        if ("".equals(s)) return true;
        //the lonely character is valid
        if (s.length()==1 && s.charAt(0)!='(' && s.charAt(0)!=')') return true;
        //expressions like this are invalid ())(()
        int balance =0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') balance++;
            if(s.charAt(i)==')') balance--;
            if(balance<0) return false;
        }
        if (balance>0) return false;
        
        //besides it has to start and end with () or characters and the number of left must be the same as the number of right
        return ((s.charAt(0)=='(' || s.charAt(0)!=')') && (s.charAt(s.length()-1)==')' || s.charAt(s.length()-1)!='(')) && countLeft(s) == countRight(s);
    }
  

    
    //removes next parentesis of particular type from the given position
    public String removeParenthesis(String s, int index){
        StringBuilder sb = new StringBuilder(s);
        sb.replace(index,index+1,"");
        return sb.toString();
    }
    
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
    
    public boolean containsOnlyParentheses(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=')'&& s.charAt(i)!='(') return false;
        }
        return true;
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
    }
}
