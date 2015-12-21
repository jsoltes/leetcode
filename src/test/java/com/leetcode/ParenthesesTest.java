package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
   
    public void testRemoveParentheses1 ()
    {
        List<String> result = p.removeInvalidParentheses("()())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses2 ()
    {
        List<String> result = p.removeInvalidParentheses("(a)())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("(a)()()", "(a())()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses3 ()
    {
        List<String> result = p.removeInvalidParentheses(")(");
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses4 ()
    {
        List<String> result = p.removeInvalidParentheses("((");
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses5 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses6 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses7 ()
    {
        List<String> result = p.removeInvalidParentheses("n");
        List<String> expected = new ArrayList<String>(Arrays.asList("n"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses8 ()
    {
        List<String> result = p.removeInvalidParentheses("(()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses9 ()
    {
        List<String> result = p.removeInvalidParentheses(")(f");
        List<String> expected = new ArrayList<String>(Arrays.asList("f"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses10 ()
    {
        List<String> result = p.removeInvalidParentheses("x(");
        List<String> expected = new ArrayList<String>(Arrays.asList("x"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses11 ()
    {
        List<String> result = p.removeInvalidParentheses("())(())(");
        List<String> expected = new ArrayList<String>(Arrays.asList("()(())"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses12 ()
    {
        List<String> result = p.removeInvalidParentheses("())))()v(k");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()vk"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses13 ()
    {
        List<String> result = p.removeInvalidParentheses("())(((()m)(");
        List<String> expected = new ArrayList<String>(Arrays.asList("()(()m)"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses14 ()
    {
        List<String> result = p.removeInvalidParentheses(")(()c))(");
        List<String> expected = new ArrayList<String>(Arrays.asList("((c))","(()c)"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses15 ()
    {
        List<String> result = p.removeInvalidParentheses("))n((i()");
        List<String> expected = new ArrayList<String>(Arrays.asList("ni()","n(i)"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses16 ()
    {
        List<String> result = p.removeInvalidParentheses("r)(p()q)ux)((()");
        List<String> expected = new ArrayList<String>(Arrays.asList("r(p(q)ux)()","r(p()qux)()","r(p()q)ux()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
     public void testRemoveParentheses17 ()
    {
        List<String> result = p.removeInvalidParentheses("())v)(()(((((())");
        List<String> expected = new ArrayList<String>(Arrays.asList("(v)()(())","(v)(()())","()v()(())","()v(()())"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
     
     public void testRemoveParentheses18 ()
    {
        List<String> result = p.removeInvalidParentheses(")()m)(((()((()((((");
        List<String> expected = new ArrayList<String>(Arrays.asList("(m)()()","(m)(())","()m()()","()m(())"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
      public void testRemoveParentheses19 ()
    {
        List<String> result = p.removeInvalidParentheses("()v)(()(())");
        List<String> expected = new ArrayList<String>(Arrays.asList("()v()(())","(v)()(())","()v(()())","(v)(()())"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
      
       public void testRemoveParentheses20 ()
    {
        List<String> result = p.removeInvalidParentheses("()m)(((()()");
        List<String> expected = new ArrayList<String>(Arrays.asList("(m)()()","(m)(())","()m()()","()m(())"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
       
    public void testRemoveParentheses21 ()
    {
        List<String> result = p.removeInvalidParentheses("())((()))x)(v()(h");
        List<String> expected = new ArrayList<String>(Arrays.asList("(((()))x)v()h","(((()))x)(v)h","()((())x)v()h","()((())x)(v)h","()((()))xv()h","()((()))x(v)h"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }   
    
    public void testRemoveParentheses22 ()
    {
        List<String> result = p.removeInvalidParentheses("(r(()()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("r()()","r(())","(r)()","(r())"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    } 
    
    public void testRemoveParentheses23 ()
    {
        List<String> result = p.removeInvalidParentheses("((()())p");
        List<String> expected = new ArrayList<String>(Arrays.asList("(()())p","((()))p"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses24 ()
    {
        List<String> result = p.removeInvalidParentheses("((()))))())(");
        List<String> expected = new ArrayList<String>(Arrays.asList("((())())","((()))()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testRemoveParentheses25 ()
    {
        List<String> result = p.removeInvalidParentheses("((())(()(()(");
        List<String> expected = new ArrayList<String>(Arrays.asList("(())()()","(())(())","((()))()","((())())"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
            
}
