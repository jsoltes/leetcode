/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jsoltes;

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
    public void countSmaller01(){
        assertThat(s.countSmaller(new int[]{5,2,6,1})).isEqualTo(newArrayList(2,1,1,0));
    }
    @Test
    public void countSmaller02(){
        assertThat(s.countSmaller(new int[]{0,2,1})).isEqualTo(newArrayList(0,1,0));
    }
    @Test
    public void countSmaller03(){
        assertThat(s.countSmaller(new int[]{2,0,1})).isEqualTo(newArrayList(2,0,0));
    }
    @Test
    public void countSmaller04(){
        assertThat(s.countSmaller(new int[]{1,9,7,8,5})).isEqualTo(newArrayList(0,3,1,1,0));
    }
    @Test
    public void countSmaller05(){
        assertThat(s.countSmaller(new int[]{26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41})).isEqualTo(newArrayList(10,27,10,35,12,22,28,8,19,2,12,2,9,6,12,5,17,9,19,12,14,6,12,5,12,3,0,10,0,7,8,4,0,0,4,3,2,0,1,0));
    }
    @Test
    public void countSmaller06(){
        assertThat(s.countSmaller(new int[]{1,1,1,-1,0,0,0})).isEqualTo(newArrayList(4,4,4,0,0,0,0));
    }
}
