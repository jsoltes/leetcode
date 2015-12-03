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

    public void testParentheses1 ()
    {
        Parentheses p = new Parentheses();
        List<String> result = p.removeInvalidParentheses("()())()");
        List<String> expected = new ArrayList(Arrays.asList("()()()", "(())()"));
        assertTrue(result==expected);
    }
}
