package src;
import java.util.*;

public class ProgramT extends HashSet<CourseT> implements Measures{

	private HashSet<CourseT> s = this;

	public double[] measures(){
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	public double[] measures(IndicatorT ind){
		throw new UnsupportedOperationException("Unsupported Operation");
	}


	public double[] measures(AttributeT att){
		double[] sum = new double[4];

		for (CourseT c: s) {
				sum = sumMeas(sum, c.measures(att));
		}
		return Services.normal(sum);
	}

	private double[] sumMeas(double[] a, double[] b){
		double[] sum = new double[4];

		for (int i = 0; i < a.length; i++ ) {
			sum[i] = a[i] + b[i];
		}
		return sum;
	}
}