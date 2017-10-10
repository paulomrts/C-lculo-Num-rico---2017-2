
import static java.lang.Math.pow;


public class EDORungeKutta {
    
    public static double g(double x, double y)
        {
        
            return (y - pow(x,2) + 1);
     
        }
   
    public static void main (String[] args){
        
        double h, y0, x0, n, x1, f1, f2, f3, f4;
        
        x0 = 0;
        y0 = 0.5;
        x1 = 1;
        n = 5;
        h = (x1 - x0)/n;
        
        while(x0 < x1){
            
            f1 = g(x0,y0);
            f2 = g(x0+(h/2),y0+((h/2)*f1));
            f3 = g(x0+(h/2),y0+((h/2)*f2));
            f4 = g(x0+h,y0+(h*f3));
            
            x0 = x0 + h;
            y0 = y0 + (h/6)*(f1 + 2*f2 + 2*f3 + f4);
            
            
        }
        
        System.out.println("Y(1) = "+y0);
    }
    
}
