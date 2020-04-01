/**
 * @author Jiamin Lua
 * PropertyData class stores data of the resold flats in an arraylist.
 * It contains an add method to add new properties into the arraylist.
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
    
}
