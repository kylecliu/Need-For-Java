import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;

/**
*Class that reads and wrties files
*@author Chun-Hsien Liu
*@version ver 1.0.0
*/
public class FileIO
{

    /**
    *Default constructor which creates the object of the class FileIO.
    */
    public FileIO()
    {

    }

    /**
    *Method to read files.
    *@param fileName    file name as a string.
    */
    public void readFile(String fileName)
    {        
        try
        {   
            FileReader reader = new FileReader(fileName);
            Scanner scan = new Scanner(reader);

            try
            {
                while(scan.hasNext())
                {
                    System.out.println(scan.nextLine());
                }

            }

            catch(Exception e)
            {
                System.out.println("Error in reading file");
            }

            finally
            {
                try
                {   
                    reader.close();
                    scan.close();
                }

                catch(Exception e)
                {
                    System.out.println("Error in reading file");
                }
            }         
        }

        catch(Exception e)
        {
            System.out.println("Error in reading file");
        }
    }

    /**
    *Method to write files.
    *@param text        accepts a text as a String to write to the file.
    *@param fileName    accepts a file name as a string.
    */   
    public void writeFile(String text, String fileName)
    {
        try
        {
            FileWriter writer = new FileWriter(fileName);

            try
            {
                writer.append(text);
            }

            catch(Exception e)
            {
                System.out.println("Error in writing file");
            }
            finally
            {
                try
                {
                    writer.close();
                }
                catch(Exception e)
                {
                    System.out.println("Error in writing file");
                }
            }
        }

        catch(Exception e)
        {
            System.out.println("Error in writing file");   
        }

    }
    
}