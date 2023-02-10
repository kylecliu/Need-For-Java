import java.util.Scanner;
/**
*This class manages the highway in which the gameplay is occuring. 
*@author Chun-Hsien Liu
*@version ver 1.0.0
*/
public class Highway
{   
    /**
    *Default constructor which creates the object of the class Highway.
    */
    public Highway()
    {

    }

    /**
    *method which vallidates the lose condition.
    *
    *@param vehicle     accepts the player's vehicle as an object of the class Vehicle.
    *@param i           accepts the player's current index position on the highway as an integer.
    */
    public void checkLose(Vehicle vehicle, int i)
    {
        
        if (vehicle.getLife() <= 0 || vehicle.getFuel() <= 0)
        {
            FileIO file = new FileIO();
            String output = "You have cleared " + i + " sections of highway, but unfortunately failed the mission!";
            file.writeFile(output, "output.txt");
            System.out.println("GAME OVER");
            
            System.exit(0);
        }

    }

    /**
    *Method which validates the win condition.
    *
    *@param i                   accepts the player's current index position on the highway as an integer.
    *@param highwayLength       accepts the length of the highway as an integer.
    *
    *@return                    the validation result as a boolean value.
    */  
    public boolean checkWin(int i, int highwayLength)
    {
            if(i == (highwayLength - 1))
            {
                FileIO file = new FileIO();
                String output = "Well done! You have cleared " + highwayLength + " sections of the highway and successfully delivered the special character to Java City! Mission complete!";
                file.writeFile(output, "output.txt");
                System.out.println("YOU WIN!");
                return false;
            }
            else
            {
                return true;
            }
    }

    /**
    *Method that creates a highway.
    *
    *@param highwayLength       accepts the length of the highway as an integer.
    *
    *@return                    the highway map as a two dimensional array.
    */ 
    public char[][] createHighway(int highwayLength)
    {
         char[][] theHighway = new char[3][highwayLength];

        for (int i = 0; i < theHighway.length;i++)
        {
            for(int j = 0;j < theHighway[i].length;j++)
            {
                theHighway[i][j] = '=';
            }      
        }

        return theHighway;
    }

    /**
    *Method which displays the menu during gameplay.
    */
    public void displayMenu()
    {
        System.out.println("\n1.Move Forward");
        System.out.println("2.Swerve up");
        System.out.println("3.Swerve down");
        System.out.println("4.Boost");
    }

    /**
    *Method which displays the highway during gameplay.
    *
    *@param theHighway      accepts the highway as a two dimensional array of characters.
    *@param a               accepts the variable as an integer which limits the display of the highway to only 10 sections. 
    */
    public void displayHighway(char[][] theHighway, int a)
    {
        for(int j = 0; j < theHighway.length; j++)
        {
            for(int k = a;k < a + 10; k++)
            {
                System.out.print(theHighway[j][k] + " ");
            }

            System.out.print("\n");
        }
    }

    /**
    *Method which generates the length of the highway according to the difficulty level selected by the player.
    *
    *@param difficultyLevel     accepts the selected difficulty level as a String. 
    *
    *@return                    a randomly generated length of the highway as an integer.
    */ 
    public int setHighwayLength(String difficultyLevel)
    {
        int highwayLength = 0;

        switch(difficultyLevel)
        {
            case("1"): highwayLength = (int) (Math.random() * 6 + 10); break;
            case("2"): highwayLength = (int) (Math.random() * 16 + 15); break;
            case("3"): highwayLength = (int) (Math.random() * 21 + 30); break;
            default: highwayLength = 0; break;
        }

        return highwayLength;
    }

    /**
    *Method that places obstacles on the highway map. 
    *
    *@param theHighway          accepts the highway as a two dimensional array of characters.
    *@param highwayLength       accepts the length of the highway as an integer.
    *@param difficultyLevel     accepts the difficulty level as a String.
    */
    public void setObstacles(char[][] theHighway, int highwayLength, String difficultyLevel)
    {
        int numOfObstacles = 0;

        switch (difficultyLevel)
        {
            case("1"): numOfObstacles = 12; break;
            case("2"): numOfObstacles = 24; break;
            case("3"): numOfObstacles = 45; break;
        }
        
        for(int i = 0; i < numOfObstacles;i++)
        {
            boolean temp1 = true;

            while(temp1)
            {
                int chance = (int) (Math.random() * 10 + 1);

                char obstacle = '*';
                
                if (chance >= 7)
                {
                    obstacle = 'B';
                }
                else if (chance == 4 || chance == 5 || chance == 6)
                {
                    obstacle = 'F';
                }
                else if (chance == 2 || chance == 3)
                {
                    obstacle = 'S';
                }
                else
                {
                    obstacle = 'O';
                }

                int lane = (int) (Math.random() * theHighway.length);
                int index = (int) (Math.random() * highwayLength);

                if(theHighway[lane][index] == '=' && index > 2)
                {
                    theHighway[lane][index] = obstacle;
                    temp1 = false;
                }

            }
            
        }
    }

    /**
    *Method which facilitates the dynamics of the highway during the gameplay.
    *
    *@param theHighway          accepts the highway as a two dimensional array of characters.
    *@param highwayLength       accepts the length of the highway as an integer. 
    *@param difficultyLevel     accepts the difficulty level as a String.
    *@param vehicle             accepts the player's vehicle of choice as an object of the class Vehicle.
    */
    public void traverseHighway(char[][] theHighway, int highwayLength, String difficultyLevel, Vehicle vehicle)
    {
        Scanner scan = new Scanner(System.in);

        setObstacles(theHighway, highwayLength, difficultyLevel);

        boolean temp2 = true;
        int x = (int) (Math.random() * 3);
        int i = 0;
        int a = 0;

        while(temp2)
        {

            if(theHighway[x][i] == 'F')
            {
                vehicle.setFuel(vehicle.fuelUp());
            }
            else if(theHighway[x][i] == 'B')
            {
                vehicle.setLife(vehicle.roadBlock());
            }
            else if(theHighway[x][i] == 'S')
            {
                vehicle.setLife(vehicle.tyreSpikes());
            }
            else if(theHighway[x][i] == 'O')
            {
                vehicle.setLife(vehicle.openManhole());
            }

            theHighway[x][i] = '@';

            vehicle.display();
            System.out.print("\n");
            displayHighway(theHighway, a);
        
            theHighway[x][i] = '=';

            displayMenu();

            checkLose(vehicle, i);

            String option = scan.nextLine();

                switch(option)
                {
                    case("1"): vehicle.setFuel(vehicle.getFuel() - 1); break;
                    case("2"): if (x > 0) 
                                {x -= 1; vehicle.setFuel(vehicle.getFuel() - 2); break;}
                                else
                                {
                                    i -= 1; 
                                    a -= 1; 
                                    System.out.println("Unable to swerve up. You are in the uppermost lane!");
                                    break;
                                }
                                
                    case("3"): if(x < 2)
                                {x += 1; vehicle.setFuel(vehicle.getFuel() - 2); break;}
                                else
                                {
                                    i -= 1; 
                                    a -= 1;
                                    System.out.println("Unable to swerve down. You are in the lowermost lane!"); 
                                    break;
                                }
                                
                    case("4"): if (i + vehicle.getSpeed() >= (highwayLength - 1))
                                {
                                    vehicle.setFuel(vehicle.getFuel()- (vehicle.getSpeed() * 3));

                                    for(int c = i; c < highwayLength - 1; c++)
                                    {
                                        if (theHighway[x][c] == 'F')
                                        {
                                            vehicle.setFuel(vehicle.fuelUp());
                                            theHighway[x][c] = '=';
                                        }
                                        else if(theHighway[x][c] == 'B')
                                        {
                                            vehicle.setLife(vehicle.roadBlock());
                                            theHighway[x][c] = '=';
                                        }
                                        else if(theHighway[x][c] == 'S')
                                        {
                                            vehicle.setLife(vehicle.tyreSpikes());
                                            theHighway[x][c] = '=';
                                        }
                                        else if(theHighway[x][c] == 'O')
                                        {
                                            vehicle.setLife(vehicle.openManhole());
                                            theHighway[x][c] = '=';
                                        }
                                    }

                                    i = highwayLength - 2; break;
                                }

                                else
                                {                
                                    vehicle.setFuel(vehicle.getFuel()- (vehicle.getSpeed() * 3));

                                    for(int b = i; b < i + vehicle.getSpeed(); b++)
                                    {
                                        if (theHighway[x][b] == 'F')
                                        {
                                            vehicle.setFuel(vehicle.fuelUp());
                                            theHighway[x][b] = '=';
                                        }
                                        else if(theHighway[x][b] == 'B')
                                        {
                                            vehicle.setLife(vehicle.roadBlock());
                                            theHighway[x][b] = '=';
                                        }
                                        else if(theHighway[x][b] == 'S')
                                        {
                                            vehicle.setLife(vehicle.tyreSpikes());
                                            theHighway[x][b] = '=';
                                        }
                                        else if(theHighway[x][b] == 'O')
                                        {
                                            vehicle.setLife(vehicle.openManhole());
                                            theHighway[x][b] = '=';
                                        }
                                    }
                                    

                                    i += (vehicle.getSpeed() - 1); 
                                    if(a + 10 >= highwayLength)
                                    {break;}
                                    else
                                    {a += (vehicle.getSpeed() - 1); break;}
                                }
                    default: i -= 1; a -= 1;System.out.print("Please only enter 1, 2, 3 or 4\n");

                }

            i += 1;

            temp2 = checkWin(i, highwayLength);
            
                    if(a + 10 < highwayLength)
                    { 
                        a += 1;
                    }
                    else if(a + 10 == highwayLength)
                    {
                        a = highwayLength - 10;
                    }
                    else
                    {
                        a = highwayLength - 10;
                    }

        }
    }
 } 


