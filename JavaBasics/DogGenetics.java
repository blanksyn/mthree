import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DogGenetics {

    public void dogBreed(){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        String[] breeds = {"German Shepherd","Labrador Retriever", "Pomeranian", "Siberian Husky", "Bulldog", "Poodle","Golden Retriever","Chihuahua","Shiba Inu","Dobermann","Pug"};
        int max = 100; // max percentage

        //assigning the % for each breed by deducting the %each time a random num is generated
        int one = rand.nextInt(max-1)+1;
        max-=one;
        int two = rand.nextInt(max-1)+1;
        max-=two;
        int three = rand.nextInt(max-1)+1;
        max-=three;
        int four = rand.nextInt(max-1)+1;
        max-=four;
        int five = max;

        System.out.println("What is your dog name?");
        String name = scan.nextLine();
        System.out.println("Well then, I have this highly reliable report on " +  name + "'s prestigious background right here.\n");
        System.out.println(name + " is:\n");

        List<Integer> num = new ArrayList<Integer>();
        while(num.isEmpty() || num.size()<5 ){
            int x = rand.nextInt(breeds.length-1);
            boolean checkNum = false;
            //check if the breed has already been chosen
            for(int j = 0; j<num.size();j++){
                if(x == num.get(j)){
                    checkNum = true;
                }
            }
            if(!checkNum){
                num.add(x);
            }
        }

        System.out.println(one +"% "+ breeds[num.get(0)]);
        System.out.println(two +"% "+ breeds[num.get(1)]);
        System.out.println(three +"% "+ breeds[num.get(2)]);
        System.out.println(four +"% "+ breeds[num.get(3)]);
        System.out.println(five +"% "+ breeds[num.get(4)]);

    }// end of dogbreed()

    public static void main(String[] args){
        DogGenetics dg = new DogGenetics();
        dg.dogBreed();
    }
}
