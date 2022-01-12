
import java.util.*;

public class HealthyHearts {

    public void hearts(){
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your age?");
        int age = scan.nextInt(); //get age from user

        int maxH =220 -age; //calculate heartbeat per minute
        System.out.println("Your maximum heart rate should be " + maxH + " beats per minute");

        int lower = Math.round(maxH/2); //calculate lower range of heartbeat
        int upper =(int) Math.ceil(maxH*0.85); //calculate upper range of heartbeat
        System.out.printf("\nYour target HR Zone is %d - %d beats per minute\n",lower,upper);
    }//end of hearts()

    public static void main(String[] args){
        HealthyHearts hh = new HealthyHearts();
        hh.hearts();

    }
}
