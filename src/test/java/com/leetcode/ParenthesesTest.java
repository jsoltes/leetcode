package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ParenthesesTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ParenthesesTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ParenthesesTest.class );
    }

    Parentheses p = new Parentheses();
    
    public void testPrepareLeftSide01(){
        Object[] result = p.prepare(new StringBuilder("())v)(()(((((())"),'(');
        String result0= ((StringBuilder) result[0]).toString(); //sb
        String expected0="()v)(()(((((())";
        List<Integer> result1=(List<Integer>) result[1]; //rightIndexes
        List<Integer> expected1=Arrays.asList(3);
        int result2=(Integer) result[2]; //minRightNumber
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareRightSide01(){
        Object[] result = p.prepare(new StringBuilder("())v)(()(((((())"),')');
        String result0= ((StringBuilder) result[0]).toString(); //sb
        String expected0="())v)(()(())";
        List<Integer> result1=(List<Integer>) result[1]; //leftIndexes
        List<Integer> expected1=Arrays.asList(5);
        int result2=(Integer) result[2]; //minLeftNumber
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareLeftSide02(){
        Object[] result = p.prepare(new StringBuilder("()())()"),'(');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="()())()";
        List<Integer> result1=(List<Integer>) result[1]; //rightIndexes
        List<Integer> expected1=Arrays.asList(4);
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareRightSide02(){
        Object[] result = p.prepare(new StringBuilder("()())()"),')');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="()())()";
        List<Integer> result1=(List<Integer>) result[1]; //leftIndexes
        List<Integer> expected1=Arrays.asList(-1);
        int result2=(Integer) result[2];
        int expected2=0;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
    public void testPrepareLeftSide03(){//middle part m()v
        Object[] result = p.prepare(new StringBuilder("())v)m()v(()(())"),'(');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="()v)m()v(()(())";
        List<Integer> result1=(List<Integer>) result[1]; //rightIndexes
        List<Integer> expected1=Arrays.asList(3);
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareRightSide03(){
        Object[] result = p.prepare(new StringBuilder("())v)m()v(()(())"),')');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="())v)m()v(()(())";
        List<Integer> result1=(List<Integer>) result[1]; //leftIndexes
        List<Integer> expected1=Arrays.asList(9);
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //"((()))))())("
    public void testPrepareLeftSide04(){
        Object[] result = p.prepare(new StringBuilder("((()))))())("),'(');
        String result0= ((StringBuilder) result[0]).toString(); //sb
        String expected0="((()))())("; 
        List<Integer> result1=(List<Integer>) result[1]; //rightIndexes
        List<Integer> expected1=Arrays.asList(8);
        int result2=(Integer) result[2]; //minRightNumber
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //"((()))))())("
     public void testPrepareRightSide04(){
        Object[] result = p.prepare(new StringBuilder("((()))))())("),')');
        String result0= ((StringBuilder) result[0]).toString();//sb
        String expected0="((()))))())"; 
        List<Integer> result1=(List<Integer>) result[1]; //leftIndexes
        List<Integer> expected1=Arrays.asList(-1);
        int result2=(Integer) result[2]; //minLeftNumber
        int expected2=0;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
     
    public void testGetIndexes01(){
        List<Integer> result=p.getIndexes(new StringBuilder("()())"), ')',1);
        List<Integer> expected=Arrays.asList(1,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes02(){
        List<Integer> result=p.getIndexes(new StringBuilder("(()c))"), ')',1);
        List<Integer> expected=Arrays.asList(2,4);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes03(){
        List<Integer> result=p.getIndexes(new StringBuilder("(()(())"), '(',1);
        List<Integer> expected=Arrays.asList(0,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes04(){
        List<Integer> result=p.getIndexes(new StringBuilder("((i()"), '(',2);
        List<Integer> expected=Arrays.asList(0,1,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes05(){
        List<Integer> result=p.getIndexes(new StringBuilder("())v)"), ')',2);
        List<Integer> expected=Arrays.asList(1,2,4);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes06(){
        List<Integer> result=p.getIndexes(new StringBuilder("((i()"), '(',2);
        List<Integer> expected=Arrays.asList(0,1,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes07(){
        List<Integer> result=p.getIndexes(new StringBuilder("((m(())()"), '(',2);
        List<Integer> expected=Arrays.asList(0,1,3,4,7);
        assertEquals(expected,result);
    }
    //"((()))))())("
    public void testGetIndexes08(){
        List<Integer> result=p.getIndexes(new StringBuilder("((()))))())"), ')',3);
        List<Integer> expected=Arrays.asList(3,9,10);
        assertEquals(expected,result);
    }
    //
    public void testGetIndexes09(){
        List<Integer> result=p.getIndexes(new StringBuilder("l(((())((z))"), '(',2);
        List<Integer> expected=Arrays.asList(1,7,8);
        assertEquals(expected,result);
    }
    //
    public void testGetIndexes10(){
        List<Integer> result=p.getIndexes(new StringBuilder("((())(()()"), '(',2);
        List<Integer> expected=Arrays.asList(0,5,6,8);
        assertEquals(expected,result);
    }
    
    public void testGenerate01() {
        List<String> result =p.generate(new StringBuilder("((i()"), 2, Arrays.asList(0,1,3));
        List<String> expected = Arrays.asList("(i)","i()");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //")(v)((m(())()("
    public void testGenerate02() {
        List<String> result =p.generate(new StringBuilder("((m(())()"), 2, Arrays.asList(0,1,3,4,7));
        List<String> expected = Arrays.asList("m(())()","(m())()","(m(()))","((m))()","((m()))");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //"()())()"
    public void testGenerate03() {
        List<String> result =p.generate(new StringBuilder("()())()"), 1, Arrays.asList(1,3));
        List<String> expected = Arrays.asList("()()()", "(())()");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //"(a)())()"
    public void testGenerate04() {
        List<String> result =p.generate(new StringBuilder("(a)())()"), 1, Arrays.asList(2,4));
        List<String> expected = Arrays.asList("(a)()()", "(a())()");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses01 ()
    {
        List<String> result = p.removeInvalidParentheses("()())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses02 ()
    {
        List<String> result = p.removeInvalidParentheses("(a)())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("(a)()()", "(a())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses03 ()
    {
        List<String> result = p.removeInvalidParentheses(")(");
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses04 ()
    {
        List<String> result = p.removeInvalidParentheses("((");
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses05 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses06 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses07 ()
    {
        List<String> result = p.removeInvalidParentheses("n");
        List<String> expected = new ArrayList<String>(Arrays.asList("n"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses08 ()
    {
        List<String> result = p.removeInvalidParentheses("(()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses09 ()
    {
        List<String> result = p.removeInvalidParentheses(")(f");
        List<String> expected = new ArrayList<String>(Arrays.asList("f"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses10 ()
    {
        List<String> result = p.removeInvalidParentheses("x(");
        List<String> expected = new ArrayList<String>(Arrays.asList("x"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses11 ()
    {
        List<String> result = p.removeInvalidParentheses("())(())(");
        List<String> expected = new ArrayList<String>(Arrays.asList("()(())"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses12 ()
    {
        List<String> result = p.removeInvalidParentheses("())))()v(k");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()vk"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses13 ()
    {
        List<String> result = p.removeInvalidParentheses("())(((()m)(");
        List<String> expected = new ArrayList<String>(Arrays.asList("()(()m)"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses14 ()
    {
        List<String> result = p.removeInvalidParentheses(")(()c))(");
        List<String> expected = new ArrayList<String>(Arrays.asList("((c))","(()c)"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses15 ()
    {
        List<String> result = p.removeInvalidParentheses("))n((i()");
        List<String> expected = new ArrayList<String>(Arrays.asList("ni()","n(i)"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses16 ()
    {
        List<String> result = p.removeInvalidParentheses("r)(p()q)ux)((()");
        List<String> expected = new ArrayList<String>(Arrays.asList("r(p(q)ux)()","r(p()qux)()","r(p()q)ux()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
     public void testRemoveParentheses17 ()
    {
        List<String> result = p.removeInvalidParentheses("())v)(()(((((())");
        List<String> expected = new ArrayList<String>(Arrays.asList("(v)()(())","(v)(()())","()v()(())","()v(()())"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
     
     public void testRemoveParentheses18 ()
    {
        List<String> result = p.removeInvalidParentheses(")()m)(((()((()((((");
        List<String> expected = new ArrayList<String>(Arrays.asList("(m)()()","(m)(())","()m()()","()m(())"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
      public void testRemoveParentheses19 ()
    {
        List<String> result = p.removeInvalidParentheses("()v)(()(())");
        List<String> expected = new ArrayList<String>(Arrays.asList("()v()(())","(v)()(())","()v(()())","(v)(()())"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
      
       public void testRemoveParentheses20 ()
    {
        List<String> result = p.removeInvalidParentheses("()m)(((()()");
        List<String> expected = new ArrayList<String>(Arrays.asList("(m)()()","(m)(())","()m()()","()m(())"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
       
    public void testRemoveParentheses21 ()
    {
        List<String> result = p.removeInvalidParentheses("())((()))x)(v()(h");
        List<String> expected = new ArrayList<String>(Arrays.asList("(((()))x)v()h","(((()))x)(v)h","()((())x)v()h","()((())x)(v)h","()((()))xv()h","()((()))x(v)h"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }   
    
    public void testRemoveParentheses22 ()
    {
        List<String> result = p.removeInvalidParentheses("(r(()()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("r()()","r(())","(r)()","(r())"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    } 
    
    public void testRemoveParentheses23 ()
    {
        List<String> result = p.removeInvalidParentheses("((()())p");
        List<String> expected = new ArrayList<String>(Arrays.asList("(()())p","((()))p"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses24 ()
    {
        List<String> result = p.removeInvalidParentheses("((()))))())(");
        List<String> expected = new ArrayList<String>(Arrays.asList("((())())","((()))()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses25 ()
    {
        List<String> result = p.removeInvalidParentheses("((())(()(()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("(())()()","(())(())","((()))()","((())())"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses26 ()
    {
        List<String> result = p.removeInvalidParentheses("l(((())((z))((");
        List<String> expected = new ArrayList<String>(Arrays.asList("l(())((z))","l((())(z))","l(((())z))"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses27 ()
    {
        List<String> result = p.removeInvalidParentheses(")(v)((m(())()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("(v)m(())()","(v)(m())()","(v)(m(()))","(v)((m))()","(v)((m()))"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }   
}
