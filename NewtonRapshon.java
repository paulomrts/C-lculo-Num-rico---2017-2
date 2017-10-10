
package newtonrapshon;

import static java.lang.Math.pow;
/**
 *
 * @author paulo
 */
public class NewtonRapshon {
    
    /* Matriz das Derivadas parciais da função [Jacobiana]*/
  
    public static double[][] MatrizJacobiana(double x1, double x2) {
         
        double[][] jfg = new double[2][2];
        
        jfg[0][0] = x2;
        jfg[0][1] = x1;
        jfg[1][0] = 2*x1;
        jfg[1][1] = 2*x2;
        
        return jfg;
    }
  
  /* matriz inversa*/ 
    
    public static double[][] MatInv(double A[][], int n){

        double con; 

        for (int k=0; k<n; k++) { 
            con = A[k][k]; 
            A[k][k] = 1; 
            
            for (int j=0; j<n; j++) 
                A[k][j] = A[k][j] / con; 

            for (int i=0; i<n; i++) { 
                if (i!=k) { 

                    con = A[i][k]; 

                    A[i][k] = 0.0; 

                    for (int j=0; j<n; j++) 
                        A[i][j] = A[i][j] - A[k][j]*con;
                }
            } 
        } 

        return A; 
    }
 
     /* Multiplicação da jacobiana pelo vetor vfg*/
     
    public static double[][] mult (double[][] jfgI, double[][] vfg) {
     
        double[][] mult = new double[2][2];
        
        mult[0][0] = jfgI[0][0] * vfg[0][0] + jfgI[0][1] * vfg[1][0];
        mult[1][0] = jfgI[1][0] * vfg[0][0] + jfgI[1][1] * vfg[1][0];
        
        return mult;
        
    }
           
   public static void main(String[] args) {
       
        double[][] jfg = new double[2][2];
        double[][] vfg = new double[2][2];
        double[][] dx = new double[2][2];
        double x1 = 1.8;
        double x2 = 0.5;
        double e = 0.00001;
        int n =0;
          
        dx[0][0] = 1;
        dx[1][0] = 1;
        
        /* Condicao de parada*/
        
        while (Math.sqrt(Math.pow(dx[0][0],2) + Math.pow(dx[1][0], 2)) > e) {
            
           /*Vfg é preenchido com os valores fx e gx*/
            vfg[0][0] = -(x1*x2 - 1);
            vfg[1][0] = -(Math.pow(x1, 2) + Math.pow(x2, 2) - 4);
            
           /* Matriz jacobiana das funções fx e gx*/
            jfg = MatrizJacobiana(x1, x2);
            /*dx recebe a  matriz multiplicação da jacobiana inversa de ordem 2 pelo vetor vfg*/
            dx = mult(MatInv(jfg, 2), vfg);
            /*Contador de iterações*/
            n++;
            x1 = x1 + dx[0][0];
            x2 = x2 + dx[1][0];
            
        }
        
        System.out.println(x1);
        System.out.println(x2);
        System.out.println(n);
    }
    
}
    
    
