package interpolacaopolinomial;

import java.util.Scanner;

/**
 *
 * @author paulo
 */
public class InterpolacaoPolinomial {
    
    public static void main(String[]args){
        
        Scanner input = new Scanner(System.in);
        
        int g=3; /*grau do polinomio de aproximacao*/ 
        int n=7; /* numero de pontos dados*/
        double soma;
        double x[] = {-2, -1, 1, 2, 2, 4, 5};/*vetor dos termos independentes*/
        double y[] = {-3, 0, 0, -3, -1, 0, 2};/*vetor resultado*/
        double[] R = new double[g+1]; /*vetor resposta para o sistema quadrado*/
        double[][] A = new double[n][g+1]; /*recebe os valores de x */
        double[][] At = new double[g+1][n]; /*matriz transposta de A*/
        double[][] An = new double[g+1][g+1];
        
        // Obtendo matriz A
        for(int i=0; i<n; i++)
            for(int j=0; j<g+1; j++)
                A[i][j] = Math.pow(x[i],(g-j));/*coeficientes*/
        
        // Matriz transposta de A -> Utilizada na transformacao do sistema
        for(int i=0; i<g+1; i++)
            for(int j=0; j<n; j++)
                At[i][j] = A[j][i];
        
        /* Transformar em matriz quadrada 
        [numero de linhas == numero de colunas]*/
        for(int i=0; i<g+1; i++){
            for(int j=0; j<g+1; j++){
                soma = 0;
                for(int k=0; k<n; k++){
                    /*Matriz transposta * Matriz A*/
                    soma = soma + At[i][k]*A[k][j]; 
                }
                An[i][j] = soma;
            }      
        }
                
        for(int i=0; i<g+1; i++){
            soma=0;
            for(int j=0; j<n; j++){
                /*Matriz tansposta * vetor resultado*/
                soma = soma + At[i][j]*y[j];
            }
            /*vetor resposta da transformacao*/
            R[i] = soma;
        }
                
        //Resolucao do Sistema Linear por Decomposição LU
        
        double[][] L = new double[g+1][g+1];
        double[][] U = new double[g+1][g+1];
        double[] Y = new double[g+1];
        double[] a = new double[g+1];
        
        //matrizes L e U
        for(int i=0; i<g+1; i++){
            for(int j=0; j<g+1; j++){
                if(i==j){
                    L[i][j] = 1;
                    U[i][j] = 0;
                }    
                else{
                    L[i][j] = 0;
                    U[i][j] = 0;
                }                 
            }
        }
            
        //Decompondo a matriz
        for(int i=0; i<g+1; i++){
            for(int j=0; j<g+1; j++){
                soma=0;  
                if(j>=i){
                    for(int k=0; k<i; k++){
                        soma = soma + L[i][k]*U[k][j];  
                    }
                    U[i][j] = An[i][j] - soma;
                }
                else{
                    for(int k=0; k<j; k++){
                        soma = soma + L[i][k]*U[k][j];  
                    }
                    L[i][j] = (An[i][j] - soma)/U[j][j]; 
                }
            }
        }
        
       
        //Retrosubstituição
        for(int i=0; i<g+1; i++){
            soma=0;
            for(int j=0; j<i; j++){
                soma = soma + Y[j]*L[i][j];
            }
            Y[i] = (R[i] - soma)/L[i][i];
        }
        
        
        for(int i=g; i>=0; i--){
            soma=0;
            for(int j=g; j>i; j--){
                soma = soma + a[j]*U[i][j];
            }
            a[i] = (Y[i] - soma)/U[i][i];
            
        }
        
        System.out.println("P(x) = "+a[0]+"*x³ "+a[1]+"*x² "+a[2]+"*x + "+a[3]);
    }
}

