import java.util.ArrayList;
import java.util.HashMap;

public class MathsAnalysis {
	
	PropertyData pd; // arraylist<Property>
	HashMap<String, String> userInputs; // key-value pairs of user input info
	
	// Constructor
	public MathsAnalysis(PropertyData pd, HashMap<String, String> userInputs) {
		this.pd = pd;
		this.userInputs = userInputs;
	}
	
	
	/**
	 * This is a helper method for calculating the average of a given arraylist<Integer> of prices
	 * @param prices
	 * @return averagePrice
	 */
	private Double helperCalculateAveragePrice (ArrayList<Integer> prices) {
		Double averagePrice = 0.0;
		Double sum = 0.0;
		
		for (Integer i: prices) {
			sum += i;
		}
		
		averagePrice = sum / prices.size();
		return averagePrice;
	}
	
	/**
	 * This is a helper method for filter out a subset of data from the full PropertyData based on Room Type.
	 * @param roomType
	 * @return subsetPd
	 */
	private PropertyData helperFilterOutRelevantDataRoomType(Integer roomType) {
		PropertyData subsetPd = new PropertyData();
		
		// pending to complete the code
		
		return subsetPd;
	}
	
	/**
	 * This is a helper method for filter out a subset of data from the full PropertyData based on location.
	 * @param location
	 * @return subsetPd
	 */
	private PropertyData helperFilterOutRelevantDataLocation(String location) {
		PropertyData subsetPd = new PropertyData();
		
		// pending to complete the code
		
		return subsetPd;
	}
	
	/**
	 * This is a helper method to arrange the propertyData into different flat area groups.
	 * @return diffFlatAreaGroupPd
	 */
	private HashMap<String, PropertyData> helperArrangeIntoDifferentFlatGroup() {
		// Arrange this.pd into different flat area groups 
		// (<=50 sqm, 50-70 sqm, 70-90 sqm, 90-110 sqm, 110-130 sqm, >130sqm)
		
		// ALTERNATIVE: use flat type instead of flat area
		
		// pending to complete the code
		
		HashMap<String, PropertyData> diffFlatAreaGroupPd = new HashMap<String, PropertyData>();
		
		return diffFlatAreaGroupPd;
	}
	
	/**
	 * This is to calculate the national average property price of all the flats in Singapore.
	 * Output the average prices for different ranges of flat floor area 
	 * @return nationalAvg
	 */
	private HashMap<String, Double> q1calculateNationalAverage() {
		// Arrange this.pd into different flat area groups 
		this.helperArrangeIntoDifferentFlatGroup();
		
		// Calculate the average of each group 
		
		// Output into a Hashmap, with key = flatAreaGroup, value = averagePrice
		
		// pending to complete the code
		HashMap<String, Double> nationalAvg = new HashMap<String, Double>();
		
		
		return nationalAvg;
	}
	
	
	/**
	 * This is to calculate the town average property price of the flats in Singapore.
	 * Output the average prices for different towns
	 * @return townAvg
	 */
	public HashMap<String, Double> q2calculateTownAverage() {
		// Arrange this.pd into different towns
		
		// Calculate the average of each town
		
		// Output into a Hashmap, with key = townName, value = averagePrice
		
		// pending to complete the code
		HashMap<String, Double> townAvg = new HashMap<String, Double>();
		
		
		return townAvg;
		
	}
	
	/**
	 * This is to calculate the median price based on what user input for flat floor area and location.
	 * Output the median price
	 * @return median
	 */
	private Double q3calculateMedianPrice() {
		
		// get user input flat area and user input location from this.userInput
		
		// filter this.pd based on user input flat area and location 
		
		// Calculate the median of the subset from this.pd
		
		// pending to complete the code
		Double median = 0.0;
		
		return median;
	}
	
	/**
	 * This is to calculate the highest depreciation over the remaining lease.
	 * Output the remaining lease left for the lowest price flat
	 * @return remainingLease
	 */
	private HashMap<String, Double> q4calculatHighestDepreciationYear(){
		// Arrange this.pd into different flat area groups 
		this.helperArrangeIntoDifferentFlatGroup();
		
		// For each group, get subsets of different towns
		
		// Rank according to the lowest price 
		
		// Get corresponding remaining lease 
		
		// pending to complete the code
		
		HashMap<String, Double> remainingLease = new HashMap<String, Double>();
		
		return remainingLease;	
	}
	
	public PropertyData filterAccordingToUserInputPreference() {
		Integer roomType = Integer.parseInt(this.userInputs.get("roomType"));
		String location = this.userInputs.get("location");
		
		helperFilterOutRelevantDataRoomType(roomType);
		helperFilterOutRelevantDataLocation(location);
		
		// pending to complete the code
		
		PropertyData filteredPd = new PropertyData();
		
		return filteredPd;
	}
	
	/**
	 * This is the main method to be called outside the class, it returns all the answers to 
	 * the insightful questions we want to find out through the set of property data.
	 * @return answers
	 */
	public HashMap<String, HashMap<String, Double>> answersToInsightQuestions(){
		HashMap<String, HashMap<String, Double>> answers = new HashMap<String, HashMap<String, Double>>();
		
		// call calculation method for Q1 (refer to the question list)
		// add answer1 to answers with key = Q1, value = answer1
		q1calculateNationalAverage();
		
		
		// call calculation method for Q2
		q2calculateTownAverage();
		
		// call calculation method for Q3
		q3calculateMedianPrice();
		
		// call calculation method for Q4
		q4calculatHighestDepreciationYear();
		
		
		// pending to complete the code
		
		return answers;
	}
	
	

}
