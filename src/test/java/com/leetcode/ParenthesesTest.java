package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Ignore;

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
    
    public void testParentheses1 ()
    {
        List<String> result = p.removeInvalidParentheses("()())()");
        List<String> expected = new ArrayList(Arrays.asList("()()()", "(())()"));
        assertTrue(result==expected);
    }
    
    public void testParentheses2 ()
    {
        List<String> result = p.removeInvalidParentheses("(a)())()");
        List<String> expected = new ArrayList(Arrays.asList("(a)()()", "(a())()"));
        assertTrue(result==expected);
    }
    
    public void testParentheses3 ()
    {
        List<String> result = p.removeInvalidParentheses(")(");
        List<String> expected = new ArrayList(Arrays.asList(""));
        assertTrue(result==expected);
    }
    
    public void testParentheses4 ()
    {
        List<String> result = p.removeInvalidParentheses("()()))()");
        List<String> expected = new ArrayList(Arrays.asList("()()()", "(())()"));
        assertTrue(result==expected);
    }
}
