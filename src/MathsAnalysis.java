import java.util.ArrayList;
import java.util.HashMap;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
	 * This is a helper method for filter out a subset of data from the full PropertyData based on flat Type.
	 * Overloading
	 * @param flatType (1,2,3,4,5,Executive = 6, multi-gen = 7)
	 * @return subsetPd
	 */
	private PropertyData helperFilterOutRelevantData(Integer intFlatType) {
		// PropertyData subsetPd = new PropertyData();
		String flatType = intFlatType.toString();
		
		PropertyData oneRoom = new PropertyData();
		PropertyData twoRoom = new PropertyData();
		PropertyData threeRoom = new PropertyData();
		PropertyData fourRoom = new PropertyData();
		PropertyData fiveRoom = new PropertyData();
		PropertyData executiveRoom = new PropertyData();
		PropertyData multiGenRoom = new PropertyData();
		
		for (int i = 0; i < this.pd.getSize(); i++) {
			Property currentP = this.pd.getProperty(i);
			String roomType = currentP.getFlatType();
			 
			if (roomType.contains("1")) {
				oneRoom.addProperty(currentP);
			}
			
			else if (roomType.contains("2")) {
				twoRoom.addProperty(currentP);
			}
			
			else if (roomType.contains("3")) {
				threeRoom.addProperty(currentP);
			}
			
			else if (roomType.contains("4")) {
				fourRoom.addProperty(currentP);
			}
			
			else if (roomType.contains("5")) {
				fiveRoom.addProperty(currentP);
			}
			
			else if (roomType.contains("6")) {
				executiveRoom.addProperty(currentP);
			}
			
			else {
				multiGenRoom.addProperty(currentP);
			}
		}
		
		if (flatType.contentEquals("1")) {
			return oneRoom;
		}
		
		else if (flatType.contentEquals("2")) {
			return twoRoom;
		}
		
		else if (flatType.contentEquals("3")) {
			return threeRoom;
		}
		
		else if (flatType.contentEquals("4")) {
			return fourRoom;
		}
		
		else if (flatType.contentEquals("5")) {
			return fiveRoom;
		}
		
		else if (flatType.contentEquals("6")) {
			return executiveRoom;
		}
		
		else {
			return multiGenRoom;
		}
		
	}
	
	/**
	 * This is a helper method for filter out a subset of data from the full PropertyData based on town.
	 * Overloading 
	 * @param town
	 * @return subsetPd
	 */
	private PropertyData helperFilterOutRelevantData(String town) {
		/*
		String[] townList = {"ANG MO KIO", "BEDOK", "BISHAN", "BUKIT BATOK",
							"BUKIT MERAH", "BUKIT PANJANG", "BUKIT TIMAH",
							"CENTRAL AREA", "CHOA CHU KANG", "CLEMENTI",
							"GEYLANG", "HOUGANG", "JURONG EAST", "JURONG WEST",
							"KALLANG/WHAMPOA", "MARINE PARADE", "PASIR RIS",
							"PUNGGOL", "QUEENSTOWN", "SEMBAWANG", "SENGKANG","SERAGNOON"};
		*/
		HashMap<String, PropertyData> groupByTown = new HashMap<String, PropertyData>();
		
		for (int i = 0; i < this.pd.getSize(); i++) {
			Property currentP = this.pd.getProperty(i);
			String currentTown = currentP.getTown();
			
			if (!groupByTown.containsKey(currentTown)) {
				PropertyData townP = new PropertyData();
				townP.addProperty(currentP);
				
				groupByTown.put(currentTown, townP);
			}
			
			else {
				groupByTown.get(currentTown).addProperty(currentP);
			}
		}
		
		return groupByTown.get(town.toUpperCase());
	}
	
	/**
	 * This is a helper method to arrange the propertyData into different flat area groups.
	 * @return diffFlatAreaGroupPd
	 */
	private HashMap<String, PropertyData> helperArrangeIntoDifferentFlatGroup() {
		// Arrange this.pd into room type groups 

		
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
		// Arrange this.pd into different room type groups 
		this.helperArrangeIntoDifferentFlatGroup();
		
		// Calculate the average of each group 
		
		// Output into a Hashmap, with key = flatType, value = averagePrice
		
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
	 * This is to calculate the median price based on what user input for flat floor area and town.
	 * Output the median price
	 * @return median
	 */
	private Double q3calculateMedianPrice() {
		
		// get user input flat area and user input town from this.userInput
		
		// filter this.pd based on user input flat area and town 
		
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
		// Arrange this.pd into different flatType groups 
		this.helperArrangeIntoDifferentFlatGroup();
		
		// For each flatType group, group into different towns
		
		// Group again according to the remaining lease year
		
		// Get average price of each lease year group 
		
		// Get the lease year with the lowest average price 
		
		// pending to complete the code
		
		HashMap<String, Double> remainingLease = new HashMap<String, Double>();
		// key: flatType, value: lease remaining
		
		return remainingLease;	
	}
	
	public PropertyData filterAccordingToUserInputPreference() {
		Integer flatType = Integer.parseInt(this.userInputs.get("flatType"));
		String town = this.userInputs.get("town");
		
		helperFilterOutRelevantData(flatType);
		helperFilterOutRelevantData(town);
		
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
		// answerToQ3 is Double, convert to HashMap<String, Double> by using key = "q3", value = median
		q3calculateMedianPrice();
		
		
		// call calculation method for Q4
		q4calculatHighestDepreciationYear();
		
		
		// pending to complete the code
		
		return answers;
	}
	
	public static void main(String[] args) {
		PropertyData propertyData =  PropertyReader.readFile("resale-prices.csv", true);
		// System.out.println(propertyData.getProperty(1).toString());
		// System.out.println(propertyData.getSize());
		HashMap<String, String> in = new HashMap<String, String>();
		in.put("flatType", "3");
		in.put("town","Newton");
		
		MathsAnalysis m = new MathsAnalysis(propertyData, in);
		PropertyData test = m.helperFilterOutRelevantData("bedok");
		System.out.println(test.getSize());
	}
	

}
