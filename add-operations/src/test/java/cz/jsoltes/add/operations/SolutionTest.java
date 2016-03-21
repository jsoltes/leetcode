/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes.add.operations;

import java.util.Arrays;
import javax.script.ScriptException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.Lists.newArrayList;

/**
 *
 * @author jsoltes
 */
public class SolutionTest {

    Solution s = new Solution();

    @Test
    public void testAddOperators01() throws ScriptException {
        assertThat(s.addOperators("123", 6)).containsOnlyElementsOf(newArrayList("1+2+3", "1*2*3"));
    }

    @Test
    public void testAddOperators02() throws ScriptException {
        assertThat(s.addOperators("232", 8)).containsOnlyElementsOf(newArrayList("2*3+2", "2+3*2"));
    }

    @Test
    public void testAddOperators03() throws ScriptException {
        assertThat(s.addOperators("105", 5)).containsOnlyElementsOf(newArrayList("1*0+5", "10-5"));
    }

    @Test
    public void testAddOperators04() throws ScriptException {
        assertThat(s.addOperators("00", 0)).containsOnlyElementsOf(newArrayList("0+0", "0-0", "0*0"));
    }
}
