/**
 * PropertyReader reads from the input data file to create
 * property objects.
 */

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class PropertyReader {
	
    public static PropertyData readFileLocal(String filename, boolean hasHeader) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            PropertyData propertyData = new PropertyData();

            if (hasHeader && sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                String next = sc.nextLine();
				Property flat = Property.toFlat(next);
				propertyData.addProperty(flat);
            }
            sc.close();
            return propertyData;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return new PropertyData();
        }
    }

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