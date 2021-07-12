/**
 * Author: Mohamed Aly
 * Revised: March 29, 2021
 * 
 * Description: Unit testing all the functionalities of AttributeT.java
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestAttributeT
{
	private AttributeT att_1;
	private AttributeT att_2;
	private AttributeT att_3;
	private AttributeT att_4;

	@Before
	public void setUp(){
		att_1 = new AttributeT("att_1", new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt});
		att_2 = new AttributeT("att_2", new IndicatorT[] {IndicatorT.ideaGeneration});
		att_3 = new AttributeT("att_3", new IndicatorT[] {});
		att_4 = new AttributeT("", new IndicatorT[] { IndicatorT.math, IndicatorT.math, IndicatorT.assumpt, IndicatorT.assumpt, IndicatorT.math});
	}

	@After
	public void tearDown(){
		att_1 = null;
		att_2 = null; 
		att_3 = null;
		att_4 = null;
	}

    @Test
    public void testgetName_Case1(){
        assertEquals(att_1.getName(), "att_1");
    }

    @Test
    public void testgetName_Case2(){
        assertEquals(att_2.getName(), "att_2");
    }

    @Test
    public void testgetName_Case3(){
        assertEquals(att_3.getName(), "att_3");
    }

    @Test
    public void testgetName_Case4_empty(){
        assertEquals(att_4.getName(), "");
    }

    @Test
    public void testgetIndicatorsCase1(){
    	assertTrue(Arrays.asList(att_1.getIndicators()).contains(IndicatorT.math) &&
    	 Arrays.asList(att_1.getIndicators()).contains(IndicatorT.specEngKnow) &&
    	  Arrays.asList(att_1.getIndicators()).contains(IndicatorT.assumpt));
    }

    @Test
    public void testgetIndicatorsSingleIndicator(){
    	assertEquals(att_2.getIndicators(), new IndicatorT[] {IndicatorT.ideaGeneration});
    }

    @Test
    public void testgetIndicatorsEmptyIndicators(){
    	assertEquals(att_3.getIndicators(), new IndicatorT[] {});
    }

    @Test
    public void testgetIndicatorsRepeats(){
    	assertTrue(Arrays.asList(att_4.getIndicators()).contains(IndicatorT.math) &&
    	 		  Arrays.asList(att_4.getIndicators()).contains(IndicatorT.assumpt));
    }




}
