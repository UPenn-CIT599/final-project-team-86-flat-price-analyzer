/**
 * PropertyData class stores data of the resold flats in an arraylist.
 * It contains an add method to add new properties into the arraylist, 
 *  a getProperty method to return a specific property listing,
 *  and a getSize method to return the number of property sales data
 * in the arraylist
 * 
 * Version 1.0
 * 
 * Added variable propertyList; added methods addProperty, getProproperty, getSize  
 * 
 * Version 2.0
 * 
 * Added helper functions: filterByFlatType, getLastYear, getLastMonthOfYear, ...
 * 
 * @author Jiamin Lua
 * @version 1.0
 * 
 */

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    public PropertyData filterByFlatType(String flatType) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : propertyList) {
			if (property.getFlatType().equals(flatType)) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
    
    public PropertyData filterByDate(int year, int month) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : propertyList) {
			if (property.getYear() == year & property.getMonth() == month) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
    
    public int getMinYear() {
		int minYear = this.getProperty(0).getYear();
		
		for (Property property : propertyList) {
			int year = property.getYear();
			minYear = year < minYear ? year : minYear; 
		}
		
		return minYear;
	}

    public int getMaxYear() {
		int maxYear = 0;
		
		for (Property property : propertyList) {
			int year = property.getYear();
			maxYear = year > maxYear ? year : maxYear; 
		}
		
		return maxYear;
	}

    public int getMinMonthInYear(int year) {
		int minMonth = this.getProperty(0).getMonth();
		
		for (Property property : propertyList) {
			if (property.getYear() == year) {
				int month = property.getMonth();
				minMonth = month < minMonth ? month : minMonth; 
			}
		}
		
		return minMonth;
	}
    	
	public int getMaxMonthInYear(int year) {
		int maxMonth = 0;
		
		for (Property property : propertyList) {
			if (property.getYear() == year) {
				int month = property.getMonth();
				maxMonth = month > maxMonth ? month : maxMonth; 
			}
		}
		
		return maxMonth;
	}
    
	public ObservableList<String> getUniqueStreetNames() {
		ObservableList<String> streetNames = FXCollections.observableArrayList();
		
		for (Property property : propertyList) {
			String streetName =  property.getTown();
			if (!streetNames.contains(streetName)) {
				streetNames.add(streetName);
			}
		}
		
		return streetNames;
	}
}
