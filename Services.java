package src;


public class Services{


	public static double[] normal(double v[]){

		double[] normals = new double[v.length];


		for (int i = 0;i < v.length ;i++) {
			normals[i] = v[i] / sum(v);
		}

		return normals;
	}

	private static double sum(double x[]){

		double summation = 0;

		for (int i = 0; i < x.length ;i++) {
			summation += x[i];
		}

		return summation;
	}

}