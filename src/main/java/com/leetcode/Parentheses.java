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
    
    //removes all parentheses that would have to be removed in all cases
    public String prepare(String s){
        //from beginning to end
        int i=0;
        int left=0;
        int right=0;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') {
                i++;
                left++;
            }
            if(s.charAt(j)==')') {
                i--;
                right++;
            }
            if (left>0 && right>0 && s.charAt(j)!='(' && s.charAt(j)!=')') break;
            if (left>1 && right >1 && s.charAt(j)!=s.charAt(j-1)) break; //breaks for this: ()()), but doesn't for this: (()))
            if (i<0){
                s=removeParenthesis(s, j);
                i++;
                j--;
            }
        }
        //from end to beginning
  
        i=0;
        left=0;
        right=0;
        for(int j=s.length()-1;j>0;j--){
            if(s.charAt(j)==')') {
                i++;
                right++;
            }
            if(s.charAt(j)=='(') {
                i--;
                left++;
            }
            if (left>0 && right>0 && s.charAt(j)!='(' && s.charAt(j)!=')') break;
            if (left>1 && right >1 && s.charAt(j)!=s.charAt(j+1)) break; //breaks for this: ()(), but doesn't for this: (())
            if (i<0){
                s=removeParenthesis(s, j);
                i++;
            }
        }
        
        return s;
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
    
    public Set<String> addToList(String s,int minNumber){ //"()(((()m)"
        Set<String> solutions=new HashSet<String>();
        
        String p=prepare(s);
        if(s.length()-p.length()>minNumber) return solutions;//this means we don't have any valid solutions with this one
        minNumber-=s.length()-p.length();
        System.out.println(minNumber);
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
            //because of this: ()v)(()(()) and this ()m)(((()() there has to be a while loop
            while(!containsOnlyParentheses(s) && solutions.isEmpty() && minNumber+2<=s.length()){
                solutions.addAll(addToList(s,minNumber+2));
            }
            return new ArrayList(solutions);
        }
    }
    public static void main(String[] args) {
        Parentheses p = new Parentheses();
        //System.out.println(p.prepare("()(((()m)"));
        //System.out.println(p.getMinNumber("()(((()m)"));
        //System.out.println(p.getMinNumber("())(((()m)("));
        System.out.println(p.removeInvalidParentheses("())(((()m)("));
    }
}
