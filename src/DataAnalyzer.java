/**
 * This is a test class to make sure Property, PropertyReader and 
 * PropertyData classes are working well
 */

public class DataAnalyzer {
	PropertyData propertyData;
	

    public DataAnalyzer(PropertyData propertyData) {
        this.propertyData = propertyData; 
    }

	public static void main(String[] args) {
		PropertyData propertyData =  PropertyReader.readFile("resale-prices.csv", true);
		System.out.println(propertyData.getProperty(1).toString());
		System.out.println(propertyData.getSize());
		
	}
	
}
