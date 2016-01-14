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
        assertThat(s.countSmaller(new int[]{5,2,6,1})).hasSameElementsAs(newArrayList(2,1,1,0));
    }
}
