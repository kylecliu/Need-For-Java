
import java.util.Scanner;

/**
*This class facilitates the gameplay.
*@author Chun-Hsien Liu
*@version ver 1.0.0
*/
public class Game
{
    /**
    *Default constructor which creates the object of the class Game.
    */
    public Game()
    {

    }

    /**
    *Method which displays the introduction of the game.
    */
    public static void intro()
    {
        System.out.println("Welcome to Need for Java!");
        System.out.println("We are recruiting a brave courier to deliver a special character '@' to Java City!");
        System.out.println("You will be able to select a vehicle to travel!");
        System.out.println("But! This trip is full of danger! Look out for the evil capital letters! Most of them will cause harm!");
        System.out.println("Meanwhile, keep an eye out for fuel that will help you along the journey!");
        System.out.println("Now, let's hit the road!\n");
        System.out.println("Press any key to start");
    }
    
    /**
    *Main method which begins the gameplay.
    *
    *@param args    command-line arguments as an array of Strings.
    */ 
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        intro();
        
        scan.nextLine();

        String difficultyLevel = setDifficulty();

        Vehicle vehicle = new Vehicle();
        vehicle = vehicle.createVehicle(difficultyLevel);

        String name = setName();
              
        Highway highway = new Highway();

        int highwayLength = highway.setHighwayLength(difficultyLevel);
        
        System.out.println("\nYou have " + vehicle.getFuel() + " points of fuel, and " + highwayLength + " sections of highway to traverse!" );
        System.out.println("Good luck, " + name + "!\n");
        
        char[][] theHighway = highway.createHighway(highwayLength);
  
        highway.traverseHighway(theHighway, highwayLength, difficultyLevel, vehicle);
    }

    /**
    *Method which allows the player to select a difficulty level. 
    *
    *@return    the chosen difficulty level as a String.
    */
    public static String setDifficulty()
    {
        Scanner scan = new Scanner(System.in);

        Validation val = new Validation();

        String difficultyLevel = "";
           
        boolean flag = true;

        while(flag)
        {
            System.out.println("\nThere are 3 routes you can take in order to get to Java City, which one would you like to take? ");
            System.out.println("\n1.Variable Highway: shortest, least dangerous, and you will start with a full tank of fuel!");
            System.out.println("2.Encapsulation Highway: a little bit longer, a little bit more obstacles, 80% of fuel to start with!");
            System.out.println("3.OOP Highway: the most difficult, and to make it even more challenging, you start with only 50% of fuel!");
            System.out.println("\nNow, make your choice: ");
            difficultyLevel = scan.nextLine();
            if (val.difficultyValidation(difficultyLevel))
            {
                flag = false;
            }
            else
            {
                System.out.println("Please enter only 1, 2 or 3!");
            }            
        }

        return difficultyLevel;
    }

    /**
    *Method which allows the player to enter the name. 
    *
    *@return    player's name as a String.
    */
    public static String setName()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter your name:");
        Validation val = new Validation();
        String name = scan.nextLine();
        while (val.nameValidation(name))
        {
            System.out.println("Your name must not be blank, and must be between 3 and 12 characters");
            System.out.println("Enter again:");
            name = scan.nextLine();
        }

        return name;
    }
}