/**
 * Author: Mohamed Aly
 * Revised: March 29, 2021
 * 
 * Description: Implementing the functionalities of CourseT from the MIS
*/

package src;
import java.util.*;

/**
 * @brief A template module that inherits Measures and uses IndicatorT,
 		  AttributeT, Services, and Norm.
*/
public class CourseT implements Measures{

	private String name;
	private HashMap<IndicatorT, HashSet<LOsT>> m;

	/**
	 * @brief Initializes an CourseT object
	 * @param courseName A string that is the course name of the CourseT object
	 * @param indicators An array of type IndicatorT that state the indicators associated with the Course object
	*/
	public CourseT(String courseName, IndicatorT[] indicators){
		HashMap<IndicatorT, HashSet<LOsT>> map = new HashMap<IndicatorT, HashSet<LOsT>>();

		this.name = courseName;

		for (IndicatorT i: indicators) {
			map.put(i,new HashSet<LOsT>());
		}
		this.m = map;
	}


	/**
	 * @brief Gets the name of the CourseT object
	 * @return Returns the name of the CourseT object
	*/
	public String getName(){
		return this.name;
	}


	/**
	 * @brief Gets the indicators of the CourseT object
	   @details The indicators are returned as an array of type IndicatorT
	   			by utilizing the keySet method associated with Hash map.
	   @return Returns an array of the indicators of the CourseT object.
	*/
	public IndicatorT[] getIndicators(){
		return this.m.keySet().toArray(new IndicatorT[0]);
	}


	/**
	 * @brief Gets the Learning Outcomes associated with an indicator for an object CourseT
	 *   @details The object CourseT can contains many indicators, and each indicator has an associated
	   array of Learning outcomes that are returned in this method.
	 * @param indicator The indicator for the object CourseT where its array of Learning Outcomes are being returned
	 * @return Returns the list of Learning outcomes for the indicator in the parameter in CourseT object.
	*/
	public LOsT[] getLOs(IndicatorT indicator){

		if (this.m.containsKey(indicator)) {
			return this.m.get(indicator).toArray(new LOsT[0]);
		}
		return new LOsT[0];
	}

	/**
	* @brief Adds a learning outcome to an indicator for a Course
	* @param indicator The indicator where the Learning outcome will be added
	* @param outcome The learning outcome that will be added to an indicator in CourseT
	*/
	public void addLO(IndicatorT indicator, LOsT outcome){
		if (this.m.containsKey(indicator)) {

			this.m.get(indicator).add(outcome);	
		}
	}

	/**
	* @brief Deletes a learning outcome to an indicator for a Course
	* @param indicator The indicator where the Learning outcome will be deletes
	* @param outcome The learning outcome that will be deleted in an indicator in CourseT
	*/
	public void delLO(IndicatorT indicator, LOsT outcome){
		if (this.m.containsKey(indicator) && this.m.get(indicator).contains(outcome)) {
			this.m.get(indicator).remove(outcome);	
		}
	}

	/**
	* @brief Checks if an array of learning outcomes are in a specific indicator in CourseT
	* @param indicator The indicator where the the learning outcomes are being searched for
	* @param outcomes The array of learning outcomes that are being checked if the are a member of indicator in CourseT
	* @return Returns true if all the outcomes are in the specified indicator in CourseT, false otherwise.
	*/
	public boolean member(IndicatorT indicator, LOsT[] outcomes){

		if (this.m.containsKey(indicator)) {
			for (int i = 0; i < outcomes.length; i++) {
				if (!(this.m.get(indicator).contains(outcomes[i]))) {
					return false;
				}		
			}
			return true;		
		}
		else{
			return false;
		}
	}


	/**
	* @brief Measures learning outcomes in CourseT
	* @throws UnsupportedOperationException is thrown since the module does not support this method.
	*/
	public double[] measures(){
		throw new UnsupportedOperationException("Unsupported Operation");
	}


	/**
	* @brief Measures the learning outcomes of students in the course CourseT using a specific indicator.
	* @param ind The indicator that is being used to measure the learning outcomes of students in the course CourseT
	* @returns Returns an array of 4 doubles that show how the student in the course CourseT is performing.
	*/
	public double[] measures(IndicatorT ind){

		if (this.getLOs(ind).length == 0) {
			return new double[] {0,0,0,0};
		}

		double[] measureInd = new double[4];

		for (LOsT o: this.getLOs(ind)) {
				measureInd = sumMeas(measureInd, o.measures());
			}

		if (Norm.getNInd()) {
			return Services.normal(measureInd);
		}
		return measureInd;
	}

	/**
	* @brief Measures the learning outcomes of students in the course CourseT using a specific attribute.
	* @param att The attribute that is being used to measure the learning outcomes of students in the course CourseT
	* @returns Returns an array of 4 doubles that show how the student in the course CourseT is performing.
	*/
	public double[] measures(AttributeT att){

		if (this.getIndicators().length == 0) {
			return new double[] {0,0,0,0};
		}

		double[] measureInd = new double[4];

		for (IndicatorT i: this.getIndicators()) {
				measureInd = sumMeas(measureInd, this.measures(i));
		}

		if (Norm.getNInd()) {
			return Services.normal(measureInd);
		}
		return measureInd;
	}


	private double[] sumMeas(double[] a, double[] b){

		double[] sum = new double[4];

		for (int i = 0; i < a.length; i++ ) {
			sum[i] = a[i] + b[i];
		}
		return sum;
	}
	
}