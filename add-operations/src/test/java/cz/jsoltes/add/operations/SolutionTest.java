/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes.add.operations;

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
    public void testAddOperators01() {
        assertThat(s.addOperators("123", 6)).isEqualTo(newArrayList("1+2+3", "1*2*3"));
    }

    @Test
    public void testAddOperators02() {
        assertThat(s.addOperators("232", 8)).isEqualTo(newArrayList("2*3+2", "2+3*2"));
    }

    @Test
    public void testAddOperators03() {
        assertThat(s.addOperators("105", 5)).isEqualTo(newArrayList("1*0+5", "10-5"));
    }

    @Test
    public void testAddOperators04() {
        assertThat(s.addOperators("00", 0)).isEqualTo(newArrayList("0+0", "0-0", "0*0"));
    }

    @Test
    public void testAddOperators05() {
        assertThat(s.addOperators("3456237490", 99)).isEqualTo(newArrayList());
    }
}
