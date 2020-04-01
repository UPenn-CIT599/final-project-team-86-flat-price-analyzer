/**
 * @author Jiamin Lua
 * PropertyData class stores data of the resold flats in an arraylist.
 * It contains an add method to add new properties into the arraylist, 
 *  a getProperty method to return a specific property listing,
 *  and a getSize method to return the number of property sales data
 * in the arraylist
 */

import java.util.ArrayList;

public class PropertyData {
    ArrayList<Property> propertyList = new ArrayList<Property>();    
    
    public void addProperty(Property property) {
        propertyList.add(property);
    }

    public Property getProperty(int index) {
        return propertyList.get(index);
    }

    public int getSize() {
        return propertyList.size();
    }
    
}
