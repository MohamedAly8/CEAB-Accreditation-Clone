/**
 * Author: Mohamed Aly
 * Revised: March 29, 2021
 * 
 * Description: Unit testing all the functionalities of CourseT
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestCourseT
{
	private CourseT course1;
	private CourseT course2;
	private CourseT course3;
	
	private LOsT l1 = new LOsT("topic1", 1,1,1,1);
	private LOsT l2 = new LOsT("topic2", 1,1,1,1);
	private LOsT l3 = new LOsT("topic3", 1,1,1,1);
	private LOsT l4 = new LOsT("topic4", 13,14,15,16);

	private LOsT[] outcome_list = new LOsT[] {l1, l2, l3, l4};

	@Before
	public void setUp(){
		Norm.setNorms(true,true,true);
		course1 = new CourseT("Course 1", new IndicatorT[] {IndicatorT.math});
		course2 = new CourseT("Course 2", new IndicatorT[]{IndicatorT.math, IndicatorT.assumpt});
		course3 = new CourseT("", new IndicatorT[0]);
	}

	@After
	public void tearDown(){
		course1 = null;
		course2 = null;
		course3 = null;
	}

	@Test
    public void testgetName1(){
        assertTrue(course1.getName() == "Course 1");
    }

    @Test
    public void testgetName2(){
    	assertTrue(course2.getName() == "Course 2");
    }

    @Test
    public void testgetName3(){
    	assertTrue(course3.getName() == "");
    }

    @Test
    public void testgetIndicators1(){
    	HashSet<IndicatorT> o_inCourse = new HashSet<IndicatorT>(Arrays.asList(course1.getIndicators()));
    	assertTrue(o_inCourse.contains(IndicatorT.math) && o_inCourse.size() == 1);
    }

    @Test
    public void testgetIndicators2(){
    	HashSet<IndicatorT> o_inCourse = new HashSet<IndicatorT>(Arrays.asList(course2.getIndicators()));
    	assertTrue(o_inCourse.contains(IndicatorT.math) &&
    			   o_inCourse.contains(IndicatorT.assumpt) &&
    			   o_inCourse.size() == 2);
    }

    @Test
    public void testgetIndicators3(){
    	HashSet<IndicatorT> o_inCourse = new HashSet<IndicatorT>();
    	assertTrue(o_inCourse.size() == 0);
    }

    @Test
    public void testaddLO_empty(){
    	course3.addLO(IndicatorT.math, l1);
    	assertTrue(course3.getIndicators().length == 0);
    }

    @Test 
    public void testaddLO_getLOs(){
    	course1.addLO(IndicatorT.math, l1);
    	course1.addLO(IndicatorT.math, l2);

    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course1.getLOs(IndicatorT.math)));
    	assertTrue(temp.contains(l1) && temp.contains(l2) && temp.size() == 2);
    }

    @Test 
    public void testaddLO_getLOs2_noValidInd(){
    	course3.addLO(IndicatorT.math, l1);
    	course3.addLO(IndicatorT.math, l2);

    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course3.getLOs(IndicatorT.math)));
    	assertTrue(temp.size() == 0);
    }

    @Test
    public void testaddLO_getLOs3(){
    	course2.addLO(IndicatorT.math, l1);
    	course2.addLO(IndicatorT.desProcess, l1);
      	course2.addLO(IndicatorT.assumpt, l2);
    	course2.addLO(IndicatorT.tools, l3);

    	HashSet<LOsT> temp = new HashSet<LOsT>();

    	for(LOsT l: Arrays.asList(course2.getLOs(IndicatorT.math))){
    		temp.add(l);
    	}

    	
    	for(LOsT l: Arrays.asList(course2.getLOs(IndicatorT.assumpt))){
    		temp.add(l);
    	}

    	assertTrue(temp.size() == 2 && temp.contains(l1) && temp.contains(l2));
    }

    @Test
    public void testaddLO_getLOs4(){
    	course3.addLO(IndicatorT.math, l1);
    	course3.addLO(IndicatorT.desProcess, l1);
      	course3.addLO(IndicatorT.assumpt, l2);

    	HashSet<LOsT> temp = new HashSet<LOsT>();

    	for(LOsT l: Arrays.asList(course3.getLOs(IndicatorT.math))){
    		temp.add(l);
    	}

    	for(LOsT l: Arrays.asList(course3.getLOs(IndicatorT.desProcess))){
    		temp.add(l);
    	}

    	for(LOsT l: Arrays.asList(course3.getLOs(IndicatorT.assumpt))){
    		temp.add(l);
    	}

    	assertTrue(temp.size() == 0);
    }


    @Test
    public void testdelLO1(){
    	course1.addLO(IndicatorT.math, l1);
    	course1.addLO(IndicatorT.math, l2);   
    	course1.delLO(IndicatorT.math, l1);

    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course1.getLOs(IndicatorT.math)));


    	assertTrue(temp.size() == 1 && temp.contains(l2));
    }

    @Test
    public void testdelLO2empty(){
    	course3.delLO(IndicatorT.math, l1);
    	course3.delLO(IndicatorT.assumpt, l2);
    	course3.delLO(IndicatorT.openEnded, l3);

    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course1.getLOs(IndicatorT.math)));

    	for(LOsT l: Arrays.asList(course3.getLOs(IndicatorT.assumpt))){
    		temp.add(l);
    	}

    	for(LOsT l: Arrays.asList(course3.getLOs(IndicatorT.openEnded))){
    		temp.add(l);
    	}

    	assertTrue(temp.size() == 0);
    }

    @Test
    public void testdelLO3(){
    	course1.addLO(IndicatorT.math, l1);
    	course1.delLO(IndicatorT.math, l1);

    	course1.addLO(IndicatorT.math, l2);
    	course1.delLO(IndicatorT.math, l2);

    	course1.addLO(IndicatorT.math, l1);

    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course1.getLOs(IndicatorT.math)));

    	assertTrue(temp.size() == 1 && temp.contains(l1));
    }


    @Test
    public void testmember1(){
    	course1.addLO(IndicatorT.math, l1);
    	course1.addLO(IndicatorT.math, l2);

    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course1.getLOs(IndicatorT.math)));

    	assertTrue(course1.member(IndicatorT.math,new LOsT[] {l1, l2}));
    }


    @Test
    public void testmember2(){
    	course1.addLO(IndicatorT.math, l1);
    	course1.addLO(IndicatorT.math, l2);
    	course1.delLO(IndicatorT.math, l1);
    	course1.delLO(IndicatorT.math, l2);


    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course1.getLOs(IndicatorT.math)));

    	assertTrue(!course1.member(IndicatorT.math,new LOsT[] {l1, l2}));
    }

    @Test
    public void testmember3(){
    	course1.addLO(IndicatorT.math, l1);
    	course1.addLO(IndicatorT.math, l2);
    	course1.delLO(IndicatorT.math, l1);
    	course1.delLO(IndicatorT.math, l2);


    	HashSet<LOsT> temp = new HashSet<LOsT>(Arrays.asList(course1.getLOs(IndicatorT.math)));

    	assertTrue(!course1.member(IndicatorT.math,new LOsT[] {l1, l2}));
    }

    @Test
    public void testmember4(){
    	course3.addLO(IndicatorT.math, l1);
    	course3.addLO(IndicatorT.tools, l2);
    	course3.delLO(IndicatorT.openEnded, l3);
    	course3.delLO(IndicatorT.standards, l3);

    	assertTrue(!(course3.member(IndicatorT.math, new LOsT[] {l1}) ||
    				 course3.member(IndicatorT.tools, new LOsT[] {l2})||
    				 course3.member(IndicatorT.openEnded, new LOsT[] {l3})||
    				 course3.member(IndicatorT.standards, new LOsT[] {l3})));
    }

	@Test (expected = UnsupportedOperationException.class)
	public void testmeasures_1_noparam(){
		course1.measures();
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testmeasures_2_noparam(){
		course3.measures();
	}


	@Test
	public void testmeasures_1_ind_empty(){
		assert(course3.measures(IndicatorT.math) == new double[] {0,0,0,0});
	}

	@Test
	public void testmeasures_2_ind(){
		Norm.setNInd(false);
		course1.addLO(IndicatorT.math, l1);
		course1.addLO(IndicatorT.math, l2);

		assert(course1.measures(IndicatorT.math) == new double[] {2,2,2,2});
	}

	@Test 
	public void testmeasures_3_ind(){
		Norm.setNInd(true);
		course2.addLO(IndicatorT.assumpt, l1);
		course2.addLO(IndicatorT.assumpt, l2);
		course2.addLO(IndicatorT.assumpt, l3);

		assert(course2.measures(IndicatorT.assumpt) == new double[] {0.25,0.25,0.25,0.25});
	}

	
	@Test
	public void testmeasures_1_att(){
		AttributeT att = new AttributeT("att", new IndicatorT[] {IndicatorT.math});	

		assert(course3.getIndicators().length == 0 &&
			   course3.measures(att) == new double[] {0,0,0,0});
	}

	@Test
	public void testmeasures_2_att_NIndFalse(){
		Norm.setNInd(false);
		AttributeT att = new AttributeT("att", new IndicatorT[] {IndicatorT.math, IndicatorT.assumpt});

		course2.addLO(IndicatorT.math, l1);
		course2.addLO(IndicatorT.assumpt, l2);
		course2.addLO(IndicatorT.math, l2);

		assert(course2.measures(att) == new double[] {3,3,3,3});
	}

	@Test
	public void testmeasures_3_att_NIndTrue(){
		Norm.setNInd(true);
		AttributeT att = new AttributeT("att", new IndicatorT[] {IndicatorT.math, IndicatorT.assumpt});

		course2.addLO(IndicatorT.math, l1);
		course2.addLO(IndicatorT.assumpt, l2);
		course2.addLO(IndicatorT.math, l2);

		assert(course2.measures(att) == new double[] {0.75,0.75,0.75,0.75});
	}
}