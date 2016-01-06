package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Parentheses.java
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
    
    //no parentheses, no indexes
    public void testPrepare01(){
        List<Object> result = p.prepare(new StringBuilder("n"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(-1);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(-1);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="n";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //left parentheses, no indexes 
    public void testPrepare02(){
        List<Object> result = p.prepare(new StringBuilder("(("),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(-1);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(-1);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //right parentheses, no indexes 
    public void testPrepare03(){
        List<Object> result = p.prepare(new StringBuilder("))"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(-1);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(-1);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //right parentheses, minRightIndexes
    public void testPrepare04(){
        List<Object> result = p.prepare(new StringBuilder("()())()"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(4);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(-1);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="()())()";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //left parentheses, minLeftIndexes 
    public void testPrepare05(){
        List<Object> result = p.prepare(new StringBuilder("()(()()"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(-1);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(2);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="()(()()";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //both parentheses, no indexes
    public void testPrepare06(){
        List<Object> result = p.prepare(new StringBuilder("())()(()"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(-1);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(-1);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="()()()";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //both parentheses, both indexes
    public void testPrepare07(){
        List<Object> result = p.prepare(new StringBuilder("())v)(()(((((())"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(3);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(10,9,8,7,4);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="()v)(()(((((())";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    public void testPrepare08(){
        List<Object> result = p.prepare(new StringBuilder(")))(((v((())((()))"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(-1);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(4,2,1,0);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="(((v((())((()))";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    public void testPrepare09(){
        List<Object> result = p.prepare(new StringBuilder(")))(((((())((()))"),'(');
        List<Integer> result0=(List<Integer>) result.get(0); //minRightIndexes
        List<Integer> expected0=Arrays.asList(-1);
        List<Integer> result1=(List<Integer>) result.get(1); //minLeftIndexes
        List<Integer> expected1=Arrays.asList(3,2,1,0);
        String result2= ((StringBuilder) result.get(2)).toString(); //sb
        String expected2="(((((())((()))";
        assertEquals(expected0,result0);
        assertEquals(expected1,result1);
        assertEquals(expected2,result2);
    }
    //1 minRightIndex
    public void testGenerate01() {
        List<String> result =p.generate(new StringBuilder("()())"),1,1);
        List<String> expected = Arrays.asList("()()", "(())");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //1 minRightIndex
    public void testGenerate02() {
        List<String> result =p.generate(new StringBuilder("r(p()q)ux)"),4,1);
        List<String> expected = Arrays.asList("r(p(q)ux)","r(p()qux)","r(p()q)ux");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //2 minLeftIndexes
    public void testGenerate03() {
        List<String> result =p.generate(new StringBuilder("((i()").reverse(),1,2);
        List<String> expected = Arrays.asList("(i)","i()");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //2 minLeftIndexes
    public void testGenerate04() {
        List<String> result =p.generate(new StringBuilder("((m(())()").reverse(),1,2);
        List<String> expected = Arrays.asList("m(())()","(m())()","(m(()))","((m))()","((m()))");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //2 minRightIndexes 
    public void testGenerate05() {
        List<String> result =p.generate(new StringBuilder("()()))"),1,2);
        List<String> expected = Arrays.asList("()()", "(())");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //3 minLeftIndexes
    public void testGenerate06() {
        List<String> result =p.generate(new StringBuilder("((()(()()").reverse(),1,3);
        List<String> expected = Arrays.asList("((()))","(())()","(()())","()()()","()(())");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //3 minRightIndexes
    public void testGenerate07() {
        List<String> result =p.generate(new StringBuilder("()())()))"),1,3);
        List<String> expected = Arrays.asList("((()))","(())()","(()())","()()()","()(())");
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }
    //4 minRightIndexes
    public void testGenerate08() {
        List<String> result =p.generate(new StringBuilder("()())()))())"),1,4);
        List<String> expected = Arrays.asList("((())())","((()))()","(()()())","(()())()","(())(())","(())()()","()(()())","()(())()","()()(())","()()()()");
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
    //"(l))())(t("
    public void testRemoveParentheses28 ()
    {
        List<String> result = p.removeInvalidParentheses("(l))())(t(");
        List<String> expected = new ArrayList<String>(Arrays.asList("(l())t","(l)()t"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }   
    //"((()((s((((()"
    public void testRemoveParentheses29 ()
    {
        List<String> result = p.removeInvalidParentheses("((()((s((((()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()s()","()(s)","(()s)"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }   
    //"o(()()()m()((()t"
    public void testRemoveParentheses30 ()
    {
        List<String> result = p.removeInvalidParentheses("o(()()()m()((()t");
        List<String> expected = new ArrayList<String>(Arrays.asList("o()()()m()()t","o(())()m()()t","o(()())m()()t","o(()()()m)()t","o(()()()m())t"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }  
    //"()())())))())(()"
    public void testRemoveParentheses31 ()
    {
        List<String> result = p.removeInvalidParentheses("()())())))())(()");
        List<String> expected = new ArrayList<String>(Arrays.asList("((())())()","((()))()()","(()()())()","(()())()()","(())(())()","(())()()()","()(()())()","()(())()()","()()(())()","()()()()()"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }  
    //")))(((v((())((()))"
    public void testRemoveParentheses32 ()
    {
        List<String> result = p.removeInvalidParentheses(")))(((v((())((()))");
        List<String> expected = new ArrayList<String>(Arrays.asList("v(())((()))","v((())(()))","(v())((()))","(v(())(()))","(v((())()))","((v))((()))","((v())(()))","((v(())()))","((v((()))))","(((v))(()))","(((v())()))","(((v(()))))"));
        Collections.sort(result);
        Collections.sort(expected);
        assertEquals(expected,result);
    }  
}
