/**
 * @author Jiamin Lua 
 * PropertyReader reads from the input data file to create
 * property objects.
 */

import java.io.*;
import java.util.*;


public class PropertyReader {
	
    public static PropertyData readFile(String filename, boolean hasHeader) {
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

}