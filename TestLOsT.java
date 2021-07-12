/**
 * Author: Mohamed Aly
 * Revised: March 29, 2021
 * 
 * Description: Unit testing all the functionalities of LOsT.java
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestLOsT
{

	private LOsT l1;
	private LOsT l2;
	private LOsT l3;
	private LOsT l4;

	@Before
	public void setUp(){

		Norm.setNorms(true,true,true);
		l1 = new LOsT("topic1", 10,20,30,40);
		l2 = new LOsT("topic2", 5,5,5,5);
		l3 = new LOsT("", 2,4,6,8);
	}

	@After
	public void tearDown(){
		l1 = null;
		l2 = null;
		l3 = null;
		l4 = null;
	}

    @Test (expected = IllegalArgumentException.class)
    public void testForExceptionConstructor1(){
        LOsT const_test_1 = new LOsT("Exception", 0,0,0,0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testForExceptionConstructor2(){
        LOsT const_test_2 = new LOsT("Exception", 4,3,-2,1);
    }


	@Test
	public void testgetName1(){
		assertEquals(l1.getName(), "topic1");
	}


	@Test
	public void testgetName2(){
		assertEquals(l2.getName(), "topic2");
	}

	@Test
	public void testgetName3(){
		assertEquals(l3.getName(), "");
	}

	@Test
	public void testequals1(){
		LOsT att_1 = new LOsT("topic1", 5,5,5,5);
		assertTrue(l1.equals(att_1));
	}

	@Test
	public void testequals2(){
		LOsT att_2 = new LOsT("topic2",5,5,5,5);
		assertTrue(l2.equals(att_2));
	}

	@Test
	public void testequals3(){
		LOsT att_3 = new LOsT("",5,5,5,5);
		assertTrue(l3.equals(att_3));
	}

	@Test
	public void testMeasures1_normal(){
		double[] a = new double[] {0.10,0.20,0.30,0.40};
		assertTrue(Arrays.equals(l1.measures(), a));
	}

	@Test
	public void testMeasures2_normal(){
		double[] a = new double[] {0.25,0.25,0.25,0.25};
		assertTrue(Arrays.equals(l2.measures(), a));
	}

	@Test
	public void testMeasures3_not_normal(){
		Norm.setNLOs(false);
		assertTrue(Arrays.equals(l3.measures(), new double[] {2.0,4.0,6.0,8.0}));
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testMeasuresIndicator_1(){
		l1.measures(IndicatorT.math);
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testMeasuresIndicator_2(){
		l2.measures(IndicatorT.tools);
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testMeasuresIndicator_3(){
		l3.measures(IndicatorT.healthSafety);
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testMeasuresAttribute_1(){
		l1.measures(new AttributeT("test", new IndicatorT[] {IndicatorT.tools}));
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testMeasuresAttribute_2(){
		l2.measures(new AttributeT("test", new IndicatorT[] {}));
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testMeasuresAttribute_3(){
		l3.measures(new AttributeT("test", new IndicatorT[] {IndicatorT.assumpt}));
	}
}
