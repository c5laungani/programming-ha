/**
 * Circle
 * by <<Chetan Laungani, 1562274>>
 * and <<Habeeb Mohammed, 1582143>
 * as group <<120>
 */
import java.util.*;
class Circle {

    public void run() {
        Scanner sc = new Scanner(System.in);
        
        //System.out.println("Input the x value of the center of Circle 1")
        double circle1CenterX = sc.nextDouble();

        //System.out.println("Input the y value of the center of Circle 1");
        double circle1CenterY = sc.nextDouble();

        //System.out.println("Input the radius of Circle 1");
        double radiusCircle1 = sc.nextDouble();

        //System.out.println("Input the x value of the center of Circle 2");
        double circle2CenterX = sc.nextDouble();

       // System.out.println("Input the y value of the center of Circle 2");
        double circle2CenterY = sc.nextDouble();

        //System.out.println("Input the radius of Circle 2");
        double radiusCircle2 = sc.nextDouble();

        //System.out.println("Input x value of a point");
        double pointX = sc.nextDouble();

        //System.out.println("Input y value of a point");
        double pointY = sc.nextDouble();

        double distanceCircle1 = Math.sqrt((Math.pow( (circle1CenterX-pointX) , 2 )) + (Math.pow((circle1CenterY-pointY), 2  )));

        double distanceCircle2 = Math.sqrt((Math.pow( (circle2CenterX - pointX) , 2)) + (Math.pow( (circle2CenterY - pointY) , 2))); 
    
        boolean isOnCircle2 = distanceCircle2 <= radiusCircle2;
        
        boolean isOnCircle1 = distanceCircle1 <= radiusCircle1;

            if(radiusCircle1>0 && radiusCircle2>0){
        
                if (isOnCircle2==true && isOnCircle1==true){
            
                    System.out.println("The point lies in both circles");

                }
            
                else if (isOnCircle1==true){
            
                    System.out.println("The point lies in the first circle");

                } 
            
                else if (isOnCircle2==true) {

                    System.out.println("The point lies on the second circle");
                }

                else if (isOnCircle1==false &&isOnCircle2==false) {
                    System.out.println("The point does not lie in either circle");
                }

            }
            else {
                System.out.println("input error");
            }
               
            }
            
            

            
    

   



    public static void main(String[] args) {
        (new Circle()).run();
    }

}

