
public class DataAnalyzer {
	PropertyData propertyData;
	

    public DataAnalyzer(PropertyData propertyData) {
        this.propertyData = propertyData;
    }

	public static void main(String[] args) {
		PropertyData propertyData =  PropertyReader.readFile("resale-prices.csv", true);
        System.out.println(propertyData.getProperty(0).toString());
		
	}
	
}
