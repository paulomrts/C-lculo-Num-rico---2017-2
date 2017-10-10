public class QRMethod {
    
    
    /* Definição dos paramentros*/
        final static int MAX = 3;
        final static double E = 0.0001;

        /* Matrizes Q e R*/
        
        public static class QR
        {
                public double r[][] = new double[MAX][MAX];
                public double q[][] = new double[MAX][MAX];

        }
        
        /* Matriz transposta de A*/
        
        public static double[][] transposta(double a[][]) 
        {
                double at[][] = new double[MAX][MAX];
                int i, j;
                for (i = 0; i < MAX; i++)
                        for (j = 0; j < MAX; j++)
        /* Troca de posição: linhas <> colunas*/
                                at[j][i] = a[i][j];
                return at;
        }
        
        /* Recebe -> [A*Transposta]*/
        
        public static double[][] multA(double at[][], double a[][])
        {
                double U[][] = new double[MAX][MAX];
                double s;
                
                for (int i = 0; i < MAX; i++) 
                {
                        for (int k = 0; k < MAX; k++) 
                        {
                                s = 0;
                                for (int j = 0; j < MAX; j++)
                                {
                                        s += a[j][k] * at[i][j];
                                }
                                U[i][k] = s;
                        }
                }
                return U;
        }
        
        /* Matriz de Rotação [U] (ortogonal: transposta == inversa)*/

        static void ObterU(double u[][], double a[][], int i, int j) 
        {
                for (int p = 0; p < MAX; p++) 
                {
                        for (int q = 0; q < MAX; q++) 
                        {
                                if (p == q)
                                        u[p][q] = 1;
                                else
                                        u[p][q] = 0;

                        }
                }
                if (a[i][j] != 0) /*Elementos da diagonal principal de A*/
                {
                    /* cos = a[p][p]/sqrt(a²[p][p]+a²[q][p]) se upp = uqq*/
                        u[j][j] = a[j][j]/ Math.sqrt(Math.pow(a[j][j], 2) + Math.pow(a[i][j], 2));              
                        u[i][i] = u[j][j];
                    /* sen = a[q][p]/sqrt(a²[p][p]+a²[q][p]) se upq = -uqp*/
                        u[j][i] = a[i][j]/ Math.sqrt(Math.pow(a[j][j], 2) + Math.pow(a[i][j], 2));              
                        u[i][j] = -u[j][i];
                }
        }
        
        /* A = QR, onde Q = [Ut * U1t]*/
        /* Decompor a matriz A, em um produto Ak+1 = RkQk*/
        
        public static QR DecomporAk(double a[][]) 
        {
                QR QR = new QR();
                
                double u[][] = new double[MAX][MAX];
                double u1[][] = new double[MAX][MAX];
                
                for (int i = 1; i < MAX; i++) 
                {
                        for (int j = 0; j < MAX; j++) 
                        {
                                if (i == j) 
                                        break;
                                ObterU(u, a, i, j);
                                u1 = transposta(u);
                                a = multA(u, a);
                                /*Primeira iteração gera a ortogonal Q*/
                                if (j > 0 || i > 1) 
                                {
                                        QR.q = multA(QR.q, u1);
                                } else 
                                {
                                        QR.q = u1;
                                }
                        }
                }
                /*Triangular superior R*/
                QR.r = a;
                return QR;
        }

        
        static double ElementosA(double a[][])
        {   
            /*temp recebe o primeiro elemento abaixo da diagonal principal*/
                double temp = Math.abs(a[1][0]);
                for (int i = 1; i < MAX; i++) 
                {
                        for (int j = 0; j < MAX; j++) 
                        {       /*Se i==j estamos percorrendo na dp*/
                                if (i == j)
                                        break;
                                /*Compara os elementos abaixo da dp, e retorna o maior entre 
                                eles*/
                                temp = Math.max(temp, Math.abs(a[i][j]));
                        }
                }
                return temp;
        }

        static double[][] TestaA(double a[][])
        {
                QR QR;
                /*Condição de parada: maior valor absoluto de Ak < precisão*/
                while (ElementosA(a) > E) {
                        QR = DecomporAk(a);
                        /*Ordem do produto invertida*/
                        a = multA(QR.r, QR.q);
                }
                return a;
        }

        public static void main(String args[]) 
        {
                String vetor = "";
                //String matriz = "";
                double a[][] ={{5,2,1},{2,3,1},{1,1,1}};
                a = TestaA(a);
                
                for (int i = 0; i < MAX; i++) {
                    //for (int j = 0; j < MAX; j++) {
                        
                
                        vetor += " [" + i + " ] " + a[i][i] + "\n\n";
                       //matriz += " [" + i + " ] [" + j + " ] "" + a[i][j] + "\n\n";
                    }
                //}
                        
                
                System.out.println(vetor);
                //System.out.println(matriz);

        }
}

