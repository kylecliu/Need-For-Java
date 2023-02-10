import java.util.Scanner;

/**
*This class creats a vehicle and manages associated functionalities.
*@author Chun-Hsien Liu
*@version ver 1.0.0
*/
public class Vehicle
{
    private int speed;
    private int fuel;
    private int fuelLimit;
    private int life;

    /**
    * Default constructor which creates the object of the class Vehicle.
    */
    public Vehicle()
    {
        speed = 0;
        fuel = 0;
        fuelLimit = 0;
        life = 0;
    }

    /**
    *Non-default constructor which creats the object of the class Vehicle.
    *
    *@param speed   accepts vehicle boost speed as an integer.
    *@param fuel    accepts maximum fuel as an integer.
    *@param life    accepts maximum damage sustainable as an integer.
    */
    public Vehicle(int speed, int fuel, int life)
    {
        this.speed = speed;
        this.fuel = fuel;
        this.life = life;
    }

    /**
    *method that creates the vehicle as per the player's choice and initilises it as per the selected difficulty level.
    *
    *@param difficultyLevel     accepts the difficulty level specified by the player as a String.
    *
    *@return    a Vehicle object as the player's choice of vehicle. 
    */
    public Vehicle createVehicle(String difficultyLevel)
    {
        Scanner scan = new Scanner(System.in);
        FileIO file = new FileIO();

        System.out.println("\nWe have 3 types of vehicle available. You can take your pick! \n");
        file.readFile("vehicles.txt");

        System.out.println("\nPlease choose your vehicle: ");
        System.out.println("1.Motorcycle");
        System.out.println("2.Car");
        System.out.println("3.Garbage Truck");
        String choice = scan.nextLine();

        boolean temp = true;

        Vehicle vehicle = new Vehicle();

        do
        {
            switch(choice)
            {
                case("1"): Vehicle motorcycle = new Vehicle(4, 100, 30); vehicle = motorcycle; temp = false; break;
                case("2"): Vehicle car = new Vehicle(3, 120, 50); vehicle = car; temp = false; break; 
                case("3"): Vehicle truck = new Vehicle(2, 150, 100); vehicle = truck; temp = false; break;
                default: System.out.println("Please enter 1, 2 or 3 only"); choice = scan.nextLine();
            }

        }while(temp);

        switch(difficultyLevel)
        {
            case("1"): vehicle.setFuelLimit((int)(vehicle.getFuel())); break;
            case("2"): vehicle.setFuelLimit((int)(vehicle.getFuel())); vehicle.setFuel((int)(vehicle.getFuel() * 0.8));break;
            case("3"): vehicle.setFuelLimit((int)(vehicle.getFuel())); vehicle.setFuel((int)(vehicle.getFuel() * 0.5));break;
            default: break;
        }

        return vehicle;
    }

    /**
    *Method that displays a legend of the obstacles on the highway as well as the current status of the player's vehicle.
    */
    public void display()
    {
        System.out.println("\nF = Fuel  B = Roadblock  S = Tyre Spikes O = Open Manhole");
        System.out.println("************************************************************");
        System.out.println("Speed: " + speed + "   Fuel: " + fuel +"   Life: " + life);
    }

    /**
    *Method that updates the current fuel status of the player's vehicle when encountering fuel on the highway. 
    *
    *@return    updated fuel status of the vehicle as an integer.
    */
    public int fuelUp()
    {
        if ((fuel + 10) > fuelLimit)
        {
            return fuelLimit;
        }
        else
        {
            fuel += 10;
            return fuel;
        }
    }

    /**
    *Accessor method that returns the fuel staus.
    *
    * @return   the current fuel status as an integer. 
    */
    public int getFuel()
    {
        return fuel;
    }

    /**
    *Accessor method that returns the vehicle's life staus.
    *
    * @return   the current life status as an integer. 
    */
    public int getLife()
    {
        return life;
    }

    /**
    *Accessor method that returns the speed.
    *
    * @return   the speed as an integer. 
    */
    public int getSpeed()
    {
        return speed;
    }

    /**
    *Method that reduces the vehicle's life when encountering an open manhole on the highway.
    *
    * @return   the updated life status of the vehicle as an integer. 
    */
    public int openManhole()
    {
        life -= 60;
        return life;
    }

    /**
    *Method that reduces vehicle's life when encontering a roadblock.
    *
    *@return        updated life of the vehicle as an integer.
    */
    public int roadBlock()
    {
        life -= 20; 
        return life;
    }

    /**
    *Mutator method that sets the fuel status.
    *
    *@param fuel    accepts the fuel level as an integer. 
    */
    public void setFuel(int fuel)
    {
        this.fuel = fuel;
    }

    /**
    *Mutator method that sets the fuel limit.
    *
    *@param fuelLimit    accepts the fuel limit as an integer. 
    */
    public void setFuelLimit(int fuelLimit)
    {
        this.fuelLimit = fuelLimit;
    }

    /**
    *Mutator method that sets the vehicle's life status.
    *
    *@param life     accepts the life status as an integer. 
    */
    public void setLife(int life)
    {
        this.life = life;
    }

    /**
    *Mutator method that sets the speed.
    *
    *@param speed     accepts the speed as an integer. 
    */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
    *Method that reduces the vehicle's life when encountering tyre spikes on the highway.
    *
    * @return   the updated life status of the vehicle as an integer. 
    */
    public int tyreSpikes()

    {
        life -= 45;
        return life;
    }
}