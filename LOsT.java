package src;



public class LOsT implements Measures{

	private String name;
	private int n_blw;
	private int n_mrg;
	private int n_mts;
	private int n_exc;

	public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc){

		if (!(nblw > 0 && nmrg > 0 && nmts > 0 && nexc > 0)){
			throw new IllegalArgumentException("Must be natural numbers");
		}	

		this.name = topic;
		this.n_blw = nblw;
		this.n_mrg = nmrg;
		this.n_mts = nmts;
		this.n_exc = nexc;
	}

	public String getName(){
		return this.name;
	}

	@Override
	public int hashCode() {
		final int p = 31;
		int out = 1;
		out = p * out + ((this.name == null) ? 0 : this.name.hashCode());
		return out;
	}


	@Override
	public boolean equals(Object o){
		LOsT temp = (LOsT) o;
		return this.name == temp.getName();
	}

	public double[] measures(){

		double[] vals = {(double) this.n_blw, (double) this.n_mrg, (double) this.n_mts, (double) this.n_exc};

		if (!(Norm.getNLOs())) {
			return vals;
		}

		return Services.normal(vals);
	}

	public double[] measures(IndicatorT ind){
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	public double[] measures(AttributeT att){
		throw new UnsupportedOperationException("Unsupported Operation");
	}
}