/**
*This class executes validations of user input.
*@author Chun-Hsien Liu.
*@version ver 1.0.0
*/
public class Validation
{
    /**
    *Default constructor method which creates the object of the class Validation.
    */
    public Validation()
    {

    }

    /**
    *Method which validates user input for the difficulty level.
    *
    *@param input   accepts user input as a String.
    *@return        a boolean value based on the input.
    */ 
    public boolean difficultyValidation(String input)
    {
        boolean flag = false;

        if (input.equals("1") || input.equals("2") || input.equals("3"))
        {
            flag = true;
        }

        return flag;
    }

    /**
    *Method which validates user input for the name.
    *
    *@param input   accepts user input as a String.
    *@return        a boolean value based on the input.
    */ 
    public boolean nameValidation(String input)
    {
        boolean flag = false;

        if (input.length() > 12 || input.length() < 3 || input.isBlank())
        {
            flag = true;
        }

        return flag;   
    }
}