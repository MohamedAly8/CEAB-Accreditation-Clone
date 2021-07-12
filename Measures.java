package src;


import src.IndicatorT;
import src.AttributeT;


public interface Measures {

	public double[] measures() 
		throws UnsupportedOperationException;
	

	public double[] measures(IndicatorT ind) 
		throws UnsupportedOperationException;
	

	public double[] measures(AttributeT att) 
		throws UnsupportedOperationException;
	
}


