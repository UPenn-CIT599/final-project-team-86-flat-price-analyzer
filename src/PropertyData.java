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
 */

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PropertyData {
    ArrayList<Property> propertyList = new ArrayList<Property>();    
    
    
    /**
     * This method adds new properties into the PropertyData array
     */
    
    public void addProperty(Property property) {
        propertyList.add(property);
    }

    /**
     * This method returns details of a particular property by using
     * its index in the array as argument
     */    
    
    public Property getProperty(int index) {
        return propertyList.get(index); 
    }

    /**
     * This method returns the number of properties in the 
     * propertyList array
     */
    
    public int getSize() {
        return propertyList.size();
    }

    /**
     * This method creates a filter that takes in the name of a 
     * town as argument and returns all the properties 
     *  in that town
     */    
    
    public PropertyData filterByTown(String town) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : propertyList) {
			if (property.getTown().equals(town)) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
    

   /**
     * This method creates a filter that takes in the name of a 
     * flat type as argument and returns all the properties 
     * of that flat type - eg 3 room/ 4 room
     */
    
    
    public PropertyData filterByFlatType(String flatType) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : propertyList) {
			if (property.getFlatType().equals(flatType)) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
    
    /**
     * This method creates a filter that takes in a year (int)
     * and a month (int) and returns all properties that were
     *  bought/ sold during that period of time
     */
    
    public PropertyData filterByDate(int year, int month) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : propertyList) {
			if (property.getYear() == year & property.getMonth() == month) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
    
    
    /**
     * This method returns the earliest year where a
     * property was bought/sold from the propertyList array in int
     */
    
    public int getMinYear() {
		int minYear = this.getProperty(0).getYear();
		
		for (Property property : propertyList) {
			int year = property.getYear();
			minYear = year < minYear ? year : minYear; 
		}
		
		return minYear;
	}
    
    /**
     * This method returns the latest year where a
     * property was bought/sold from the propertyList array in int
     */
    
    public int getMaxYear() {
		int maxYear = 0;
		
		for (Property property : propertyList) {
			int year = property.getYear();
			maxYear = year > maxYear ? year : maxYear; 
		}
		
		return maxYear;
	}
    
    /**
     * This method returns the earliest year where a
     * property was bought/sold from the propertyList array as a double
     */
    
    public double getMinDateInYears() {
    	double minDate = this.getProperty(0).getYear() + (this.getProperty(0).getMonth() / 12.0);
    	
    	for (Property property : propertyList) {
			double date = property.getYear() + (property.getMonth() / 12.0);
			minDate = date < minDate ? date : minDate; 
		}
		
		return minDate;
    }
    
    /**
     * This method returns the latest year where a
     * property was bought/sold from the propertyList array as a double
     */
    
    public double getMaxDateInYears() {
    	double maxDate = 0;
		
		for (Property property : propertyList) {
			double date = property.getYear() + (property.getMonth() / 12.0);
			maxDate = date > maxDate ? date : maxDate; 
		}
		
		return maxDate;
    }

    /**
     * This method returns the earliest month where a
     * property was bought/sold from a specific year
     */
    
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
    
    /**
     * This method returns the latest month where a
     * property was bought/sold from a specific year
     */
    
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
	
    /**
     * This method returns a list of names of the towns
     * from the data set
     */
    
	public ObservableList<String> getUniqueTowns() {
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
