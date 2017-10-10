
package ludecomp;

public class LU {
  
    double[] decomposicao(double[][] a, double[] b) {
       int i, j, k, l,c;
       l=a.length;//l corresponde ao numero de linhas da matriz
       c=a[0].length;
       /*
        1 - Decompor a matriz [a] em matrizes triangulares superiores[u] e
            inferiores[l]
            
            * Triangular inferior: diagonal principal = 1, elementos acima = 0
            * Triangular superior: todos elementos da diagonal principal = 0
       
            Obter a matriz triangular superior, k = 1, elementos acima = 0
            Gerar um vetor intermediário para chegarmos a forma: [L]*{k}={b},
            onde i e j, percorrem a matriz [L] e k, o vetor intermediário.E tenha
            a seguinte propriedade: [l]{[u]{k}-{d}}=[a]{x}-{b}
    
       */
       
       /*
        decomposição nas diagonais superiores 
       
       */
       
       
       for(j=0;j<l;j++){
           for(i=0;i<=j;i++){
               for(k=0;k<i;k++){
                   a[i][j]-=a[i][k]*a[k][j];
                   //System.out.printf("%.2f ",a[i][j]);
               }
                
           }
           /*
             decomposição nas elemntos inferiores
           */
           
           for(i=j+1;i<l;i++){//Inicia-se em j+1, elemento a22
               for(k=0;k<j;k++){
                   a[i][j]-=a[i][k]*a[k][j];
                   //System.out.printf("%.2f ",a[i][j]);
               }              
           }
           
           /*
                Pivo para a coluna
           */
           Pivo p = new Pivo();
           p.pivo(a,b);
           
           
           for(i=j+1;i<l;i++){
               a[i][j]=a[i][j]/a[j][j];
               //System.out.printf("%.2f ",a[i][j]);
           }
           
           
       }
       
       /*
      
            Substituição progressiva
       
       */
       
       for(i=1;i<l;i++){
           for(j=0;j<i;j++){
               b[i]-=a[i][j]*b[j];
               //System.out.printf("%.2f ",b[i]);
           }
           
       }
       /*
        
         Substituição regressiva
       
       */
       
       b[l-1]=b[l-1]/a[l-1][l-1];
       for(i=l-2;i>=0;i--){
           for(j=i+1;j<c;j++){
               b[i]-=a[i][j]*b[j];
               //System.out.printf("%.2f ",b[i]);
           }
           b[i]=b[i]/a[i][i];
           //System.out.printf("%.2f ",b[i]);
       }
       return b;
    }
    
}
