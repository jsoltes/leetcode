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
    
    public void testPrepareLeftSide1(){
        Object[] result = p.prepare(new StringBuilder("())v)(()(((((())"),'(');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="()v)(()(((((())";
        int result1=(Integer) result[1];
        int expected1=3;
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareRightSide1(){
        Object[] result = p.prepare(new StringBuilder("())v)(()(((((())"),')');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="())v)(()(())";
        int result1=(Integer) result[1];
        int expected1=5;
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareLeftSide2(){
        Object[] result = p.prepare(new StringBuilder("()())()"),'(');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="()())()";
        int result1=(Integer) result[1];
        int expected1=4;
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareRightSide2(){
        Object[] result = p.prepare(new StringBuilder("()())()"),')');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="()())()";
        int result1=(Integer) result[1];
        int expected1=0;
        int result2=(Integer) result[2];
        int expected2=0;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
    public void testPrepareLeftSide3(){//middle part m()v
        Object[] result = p.prepare(new StringBuilder("())v)m()v(()(())"),'(');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="()v)m()v(()(())";
        int result1=(Integer) result[1];
        int expected1=3;
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    
     public void testPrepareRightSide3(){
        Object[] result = p.prepare(new StringBuilder("())v)m()v(()(())"),')');
        String result0= ((StringBuilder) result[0]).toString();
        String expected0="())v)m()v(()(())";
        int result1=(Integer) result[1];
        int expected1=9;
        int result2=(Integer) result[2];
        int expected2=1;
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
     
    public void testGetIndexes1(){
        List<Integer> result=p.getIndexes(new StringBuilder("()())"), ')',1);
        List<Integer> expected=Arrays.asList(1,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes2(){
        List<Integer> result=p.getIndexes(new StringBuilder("(()c))"), ')',1);
        List<Integer> expected=Arrays.asList(2,4);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes3(){
        List<Integer> result=p.getIndexes(new StringBuilder("(()(())"), '(',1);
        List<Integer> expected=Arrays.asList(0,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes4(){
        List<Integer> result=p.getIndexes(new StringBuilder("((i()"), '(',2);
        List<Integer> expected=Arrays.asList(0,1,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes5(){
        List<Integer> result=p.getIndexes(new StringBuilder("())v)"), ')',2);
        List<Integer> expected=Arrays.asList(1,2,4);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes6(){
        List<Integer> result=p.getIndexes(new StringBuilder("((i()"), '(',2);
        List<Integer> expected=Arrays.asList(0,1,3);
        assertEquals(expected,result);
    }
    
    public void testGetIndexes7(){
        List<Integer> result=p.getIndexes(new StringBuilder("((m(())()"), '(',2);
        List<Integer> expected=Arrays.asList(0,1,3,4,7);
        assertEquals(expected,result);
    }
    
    public void testGenerate1() {
        List<String> result =p.generate(new StringBuilder("((i()"), 2, Arrays.asList(0,1,3));
        List<String> expected = Arrays.asList("(i)","i()");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //")(v)((m(())()("
    public void testGenerate2() {
        List<String> result =p.generate(new StringBuilder("((m(())()"), 2, Arrays.asList(0,1,3,4,7));
        List<String> expected = Arrays.asList("m(())()","(m())()","(m(()))","((m))()","((m()))");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //"()())()"
    public void testGenerate3() {
        List<String> result =p.generate(new StringBuilder("()())()"), 1, Arrays.asList(1,3));
        List<String> expected = Arrays.asList("()()()", "(())()");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
 
    public void testRemoveParentheses1 ()
    {
        List<String> result = p.removeInvalidParentheses("()())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses2 ()
    {
        List<String> result = p.removeInvalidParentheses("(a)())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("(a)()()", "(a())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses3 ()
    {
        List<String> result = p.removeInvalidParentheses(")(");
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses4 ()
    {
        List<String> result = p.removeInvalidParentheses("((");
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses5 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses6 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses7 ()
    {
        List<String> result = p.removeInvalidParentheses("n");
        List<String> expected = new ArrayList<String>(Arrays.asList("n"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses8 ()
    {
        List<String> result = p.removeInvalidParentheses("(()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    
    public void testRemoveParentheses9 ()
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
