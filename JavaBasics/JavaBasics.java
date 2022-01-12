import java.util.Random;
import java.util.Scanner;

public class JavaBasics {

    boolean checkPlay = true;

    public static void main(String[] args){
        JavaBasics jb = new JavaBasics();
        while(jb.checkPlay ==true){
            jb.rockPaperScissors();
        }

    }

    public void rockPaperScissors(){
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();//for rockPaperScissors
        int min = 1;
        int max = 10;

        int tie=0;
        int win= 0;
        int lose=0;

        try{
            System.out.println("How many rounds do you want to play?");
            int rounds = scan.nextInt();
            scan.nextLine();
            if(rounds>=min && rounds <=max){
                for(int i = 0; i<rounds;i++) {
                    System.out.println("Please enter your choice:");
                    System.out.println("1. Scissors");
                    System.out.println("2. Paper");
                    System.out.println("3. Rock");
                    int choice = scan.nextInt();
                    int rChoice = rand.nextInt(3-1)+1;
                    switch (choice){
                        case 1:{
                            //Scissors
                            if (rChoice==1){
                                System.out.println("Your choice: Scissors");
                                System.out.println("Computer: Scissors");
                                System.out.println("Tie");
                                tie++;
                            }else if(rChoice==2){
                                System.out.println("Your choice: Scissors");
                                System.out.println("Computer: Paper");
                                System.out.println("You win");
                                win++;
                            }else if(rChoice==3){
                                System.out.println("Your choice: Scissors");
                                System.out.println("Computer: Rock");
                                System.out.println("You lose");
                                lose++;
                            }
                            break;
                        }
                        case 2:{
                            //Paper
                            if (rChoice==1){
                                System.out.println("Your choice: Paper");
                                System.out.println("Computer: Scissors");
                                System.out.println("You lose");
                                lose++;
                            }else if(rChoice==2){
                                System.out.println("Your choice: Paper");
                                System.out.println("Computer: Paper");
                                System.out.println("Tie");
                                tie++;
                            }else if(rChoice==3){
                                System.out.println("Your choice: Paper");
                                System.out.println("Computer: Rock");
                                System.out.println("You win");
                                win++;
                            }
                            break;
                        }
                        case 3:{
                            //Rock
                            if (rChoice==1){
                                System.out.println("Your choice: Rock");
                                System.out.println("Computer: Scissors");
                                System.out.println("You win");
                                win++;
                            }else if(rChoice==2){
                                System.out.println("Your choice: Rock");
                                System.out.println("Computer: Paper");
                                System.out.println("You lose");
                                lose++;
                            }else if(rChoice==3){
                                System.out.println("Your choice: Rock");
                                System.out.println("Computer: Rock");
                                System.out.println("Tie");
                                tie++;
                            }
                            break;
                        }
                        default:
                            System.out.println("Invalid input");
                            break;
                    }//end of switch case
                }//end of for loop
                System.out.println("--------------------");
                System.out.println("Wins: " +win);
                System.out.println("Lose: " +lose);
                System.out.println("Tie: " +tie);
                if(win >lose){
                    System.out.println("You are the winner!");
                }else if(lose > win){
                    System.out.println("You lose");
                }else{
                    System.out.println("It's a tie!");
                }

            }
            scan.nextLine();
            System.out.println("Would you like to play again? y/n");
            String ans = scan.nextLine();
            if(ans.equalsIgnoreCase("n")){
                System.out.println("Thanks for playing!");
                System.exit(0);
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }
}
