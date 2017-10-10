
package ludecomp;

public class LUdecomp {

    public static void main(String[] args) {
 
        double[][] a = {{1, 2, -2, 1}, {2, 5, -2, 3}, {-2, -2, 5, 3}, {1, 3, 3, 2}}; // a representa a matrizes dos coeficientes
        double [] b = {4,7,-1,0};//b representa o vetor com os resultados
        System.out.println("========== Decomposição LU ==========");
        System.out.println();
        System.out.printf("Matriz dos Termos Independentes \n" + "\r\n");
	imprimeMatriz(a);
        System.out.println("\n======================");
        System.out.printf("Vetor Solução \n" + "\r\n");
        System.out.println("======================");
        imprimeVetor(b);
        System.out.println();
	LU lu = new LU();
	double[] X = lu.decomposicao(a, b);
        imprimirResultados(X);
    }
    
   public static void imprimeMatriz(double[][]a){
        for(int i=0; i<a.length;i++){
            for(int j=0;j<a.length;j++){
                System.out.printf("%.2f ",a[i][j]);
            }
        }
   }
            
   public static void imprimeVetor(double []b){
       for(int i=0;i<b.length;i++){
           System.out.printf("%.2f \n",b[i]);
       }
    }
    

    public static void imprimirResultados(double[] x) {
        System.out.println("\n==================================");
	System.out.println("Solucoes do Sistema Linear:" + "\r\n");
            for (int i=0; i<x.length;i++) {
                 System.out.println(" " + x[i]);
            }
    }
}
    

    
