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
    public boolean isValid(String s){ // @TODO update to exclude ["())(()"] 
        //the empty string is valid
        if ("".equals(s)) return true;
        //the lonely character is valid
        if (s.length()==1 && s.charAt(0)!='(' && s.charAt(0)!=')') return true;
        //expressions like this a invalid ())(()
        int balance =0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') balance++;
            if(s.charAt(i)==')') balance--;
            if(balance<0) return false;
        }
        balance=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)==')') balance++;
            if(s.charAt(i)=='(') balance--;
            if(balance<0) return false;
        }       
        //besides it has to start and end with () or characters and the number of left must be the same as the number of right
        if (((s.charAt(0)=='(' || s.charAt(0)!=')') && (s.charAt(s.length()-1)==')' || s.charAt(s.length()-1)!='(')) && countLeft(s) == countRight(s)) return true;
        else return false;
    }
    
    //removes stupid parentheses at the beginning or the end
    public String removeBeginningEnd(String s){//")(f"
        while(!"".equals(s) && s.charAt(0)!='(') {
            if (s.charAt(0)!=')')
                if (s.length()>=2 && s.charAt(1)==')') s=removeParenthesis(s, 1);
                else break;
            else s=removeParenthesis(s,0);//(f
        }
        while(!"".equals(s) && s.charAt(s.length()-1)!=')') {
            if (s.charAt(s.length()-1)!='(')
                if (s.length()>=2 && s.charAt(s.length()-2)=='(') s=removeParenthesis(s, s.length()-2);
                else break;
            else s=removeParenthesis(s,s.length()-1);
        }
        return s;
    }
    
    //returns minimum number of invalid parentheses
    public int getMinNumber(String s){
        s=removeBeginningEnd(s);
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
 
    public void addToList(String s,List<String> solutions,int minNumber){//2
        s=removeBeginningEnd(s);
        if ("".equals(s)) solutions.add("");
        else{
            String original =s;  
        
            char parenthesis=getExceedingParenthesis(s);//)
        
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)==parenthesis){
                    //if the char at the previous index was the same, we would just generate more of the same solutions
                    if(i==0 || s.charAt(i)!=s.charAt(i-1)){
                        s=removeParenthesis(s,i);
                    
                        if(minNumber>1){
                            addToList(s,solutions,minNumber-1);
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
    }
            
    public List<String> removeInvalidParentheses(String s){
        List<String> solutions =new ArrayList<String>();
        s=removeBeginningEnd(s);
        //the input String is already ok
        if(isValid(s)) return Arrays.asList(s); 
        //some parentheses have to be removed
        else{ 
            addToList(s,solutions,getMinNumber(s));
            return solutions;
        }
    }
    public static void main(String[] args) {
        Parentheses p =new Parentheses();
        System.out.println(p.removeBeginningEnd("())))()v(k"));
        System.out.println(p.removeInvalidParentheses("())))()v(k"));
    }
            
}
