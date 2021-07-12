package src;


import java.util.HashSet;
import java.util.Set;

public class AttributeT{

	private String name;
	private IndicatorT[] s;

	public AttributeT(String attribName, IndicatorT[] indicators){

		HashSet<IndicatorT> all_indicators = new HashSet<IndicatorT>();

		this.name = attribName;
		
		for (IndicatorT i: indicators) {
			all_indicators.add(i);
		}

		this.s = all_indicators.toArray(new IndicatorT[0]);
	}


	public String getName(){
		return this.name;
	}

	public IndicatorT[] getIndicators(){
		return this.s;

	}
}
