/**
 * PropertyReader reads from the input data file to create
 * property objects.
 */

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class PropertyReader {

	/**
	 * This method parses a .csv file from a url and it takes in a 
	 * url as string and a boolean value to check if the .csv file 
	 * contains a header
	 */
	
    public static PropertyData readFileUrl(String url, boolean hasHeader) {
    	try {
            URL rowdata = new URL(url);
            URLConnection data = rowdata.openConnection();
            Scanner input = new Scanner(data.getInputStream());
            PropertyData propertyData = new PropertyData();
            
            if (hasHeader && input.hasNextLine()) {
                input.nextLine();
            }

            while (input.hasNextLine()) {
                String line = input.nextLine();
                Property flat = Property.toFlat(line);
				propertyData.addProperty(flat);
            }
            
            input.close();
            return propertyData;
        }
        catch (Exception e) {
        	System.out.println("CSV from url not found!");
            return new PropertyData();
        }
    }
    
}