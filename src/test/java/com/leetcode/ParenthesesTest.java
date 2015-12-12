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
    
    public void testGetMinNumber1 ()
    {
        int result = p.getMinNumber("()())()");
        int expected = 1;
        assertEquals(expected, result);
    }
    
    public void testGetMinNumber2 ()
    {
        int result = p.getMinNumber("))))))))");
        int expected = 0;
        assertEquals(expected, result);
    }
    
    public void testGetMinNumber3 ()
    {
        int result = p.getMinNumber("");
        int expected = 0;
        assertEquals(expected, result);
    }
    
     public void testGetMinNumber4 ()
    {
        int result = p.getMinNumber("((())())()))");
        int expected = 2;
        assertEquals(expected, result);
    }
     
    public void testGetExceedingParenthesis ()
    {
        char result =p.getExceedingParenthesis("()())()");
        char expected=')';
        assertEquals(expected, result);
    }
    
    public void testRemoveParenthesis ()
    {
        String result = p.removeParenthesis("()())()", 3);
        String expected = "()()()";
        assertEquals(expected, result);
    }

    public void testAddToList1 ()
    {
        p.setS("()())()");   
        List<String> result =new ArrayList<String>();
        p.addToList("()())()",result, p.getMinNumber("()())()"));
        List<String> expected = new ArrayList<String>(Arrays.asList("()()()", "(())()"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testAddToList2 ()
    {
        p.setS("");   
        List<String> result =new ArrayList<String>();
        p.addToList("",result, p.getMinNumber(""));
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testAddToList3 ()
    {
        p.setS("(");   
        List<String> result =new ArrayList<String>();
        p.addToList("(",result, p.getMinNumber("("));
        List<String> expected = new ArrayList<String>(Arrays.asList(""));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
    public void testAddToList4 () //brutal
    {
        p.setS("((())())()))");   
        List<String> result =new ArrayList<String>();
        p.addToList(p.getS(),result, 2);
        List<String> expected = new ArrayList<String>(Arrays.asList("((())())()","((()()()))","(((())()))","((()())())","((())()())","((())(()))"));
        assertTrue(expected.containsAll(result) && result.containsAll(expected));
    }
    
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
}
