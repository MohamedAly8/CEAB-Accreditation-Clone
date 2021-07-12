/**
 * Author: Mohamed Aly
 * Revised: March 29, 2021
 * 
 * Description: Testing functionalities of ProgramT. Unit tested all methods and scenarios
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestProgramT{

	
	private LOsT l1 = new LOsT("topic1", 1,1,1,1);
	private LOsT l2 = new LOsT("topic2", 2,2,2,2);
	private LOsT l3 = new LOsT("topic3", 3,3,3,3);

	private ProgramT program1;


	@Before
	public void setUp(){

		CourseT c1 = new CourseT("course1", new IndicatorT[] {IndicatorT.math, 
															  IndicatorT.assumpt,
															  IndicatorT.tools});

		c1.addLO(IndicatorT.math, l1);
		c1.addLO(IndicatorT.assumpt, l2);
		c1.addLO(IndicatorT.tools, l3);


		CourseT c2 = new CourseT("course2", new IndicatorT[] {IndicatorT.openEnded,
															  IndicatorT.awarePEO,
															  IndicatorT.recogTheory});


		c2.addLO(IndicatorT.openEnded, l1);
		c2.addLO(IndicatorT.awarePEO, l2);
		c2.addLO(IndicatorT.recogTheory, l3);

		program1 = new ProgramT();

		program1.add(c1);
		program1.add(c2);
	}


	@After
	public void tearDown(){
		program1 = null;
	}

	@Test (expected=UnsupportedOperationException.class)
    public void testmeasures_noparam(){
        program1.measures();
    }

    @Test (expected=UnsupportedOperationException.class)
    public void testmeasures_ind_param(){
    	program1.measures(IndicatorT.math);
    }

    @Test
    public void testmeasures_att_param(){


    	IndicatorT[] inds = new IndicatorT[] {IndicatorT.math, 
    										  IndicatorT.assumpt,
											  IndicatorT.tools,
							   			      IndicatorT.openEnded,
											  IndicatorT.awarePEO,
											  IndicatorT.recogTheory};

    	AttributeT attribute = new AttributeT("att", inds);

    	double[] result = program1.measures(attribute);
    	double[] expected = new double[] {0.25,0.25,0.25,0.25};

    	assertTrue(Arrays.equals(result, expected));
    }
}
