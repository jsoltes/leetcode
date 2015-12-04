package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    public void testCountLeft1 ()
    {
        int result = p.countLeft("(()())(()");
        int expected = 5;
        assertEquals(expected, result);
    }
    
    public void testCountLeft2 ()
    {
        int result = p.countLeft("");
        int expected = 0;
        assertEquals(expected, result);
    }
    
     public void testCountRight1 ()
    {
        int result = p.countRight("(()())(()");
        int expected = 4;
        assertEquals(expected, result);
    }
    
    public void testCountRight2 ()
    {
        int result = p.countRight("");
        int expected = 0;
        assertEquals(expected, result);
    }
    
    public void testIsValid1 ()
    {
        boolean result = p.isValid("(()()(()))");
        boolean expected = true;
        assertEquals(expected, result);
    }
    
    public void testIsValid2 ()
    {
        boolean result = p.isValid("((()()(()))");
        boolean expected = false;
        assertEquals(expected, result);
    }
    
    public void testIsValid3 ()
    {
        boolean result = p.isValid("");
        boolean expected = true;
        assertEquals(expected, result);
    }
    
    public void testParentheses1 ()
    {
        List<String> result = p.removeInvalidParentheses("()())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        assertEquals(expected, result);
    }
    
    public void testParentheses2 ()
    {
        List<String> result = p.removeInvalidParentheses("(a)())()");
        List<String> expected = new ArrayList<String>(Arrays.asList("(a)()()", "(a())()"));
        assertEquals(expected, result);
    }
    
    public void testParentheses3 ()
    {
        List<String> result = p.removeInvalidParentheses(")(");
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        assertEquals(expected, result);
    }
    
    public void testParentheses4 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()");
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        assertEquals(expected, result);
    }
}
