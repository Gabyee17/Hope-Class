import java.util.*;
public class SubwySurfers {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int playerLane = 1;
    Random rand = new Random();
    while(true){
        boolean jumped = false;
        boolean roll = false;
        int obstacleLane = rand.nextInt(3);
        int obstacleType = rand.nextInt(3);
        for(int i = 0; i < 3; i++){
            if(playerLane == obstacleLane){
                System.out.println("__X__");
                System.out.println("GAME OVER!");
                break;
            }  
            else if(i == playerLane)
                System.out.print("__P__ ");
            else if(i == obstacleLane)
                System.out.print(" __O__ ");
            else
                System.out.print(" ____ ");
        }
        System.out.println();
        switch (obstacleType) {
            case 0 -> System.out.println("Roll Down");
            case 1 -> System.out.println("Jump");
            case 2 -> System.out.println("Make a Move");
            default -> {
            }
        }

        char move = sc.next().toUpperCase().charAt(0);
        switch(move){
            case 'A':
                if(playerLane > 0)
                    playerLane--;
                else
                    System.out.println("No Lane!");
            case 'D':
                if(playerLane < 2)
                    playerLane++;
                else
                    System.out.println("No Lane");
            case 'W':
                jumped = true;
                break;
            case 'S':
                roll = true;
                break;
            default:
                System.out.println("restricted Move!");
        }
          // Collision logic
            if (playerLane == obstacleLane) {

                if (obstacleType == 1 && jumped) {
                    System.out.println("Jumped!");
                } else if (obstacleType == 2 && roll) {
                    System.out.println("Rolled!");
                } else if (obstacleType == 0) {
                    System.out.println("Hit obstacle!");
                    break;
                } else {
                    System.out.println("Wrong move!");
                    break;
                }
            }

        
    }

    }
}
