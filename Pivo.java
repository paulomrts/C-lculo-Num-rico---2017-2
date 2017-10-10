
package ludecomp;


public class Pivo {

	double temp;
	double tL[];
	int i, j, m, qL;

	public void pivo(double a[][], double[] b) {
		qL = a.length;

		m = j;
		for (i = j + 1; i < qL; ++i) {
			if (Math.abs(a[i][j]) > Math.abs(a[m][j])) {
				m = i;
			}
		}
		if (m != j) {
			tL = a[j];
			a[j] = a[m];
			a[m] = tL;
			temp = b[j];
			b[j] = b[m];
			b[m] = temp;
		}
	}

}
    

