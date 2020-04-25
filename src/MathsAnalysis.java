import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javafx.collections.ObservableList;

public class MathsAnalysis {

	PropertyData pd; 
	HashMap<String, String> userInputs; // key-value pairs of user input info
										// flatType - 1,2,3,4,5, 0 (String)
										// town - CAPITAL LETTER OF TOWN NAME or ALL(String)
	ObservableList<String> townList;
	

	// Constructor
	public MathsAnalysis(PropertyData pd, HashMap<String, String> userInputs) {
		this.pd = pd;
		this.userInputs = userInputs;
		this.townList = pd.getUniqueTowns();
	}

	/**
	 * This is a helper method for calculating the average of a given PropertyData
	 * 
	 * @param prices
	 * @return averagePrice
	 */
	private Double helperCalculateAveragePrice(PropertyData pd) {
		Double averagePrice = 0.0;
		Double sum = 0.0;

		for (int i = 0; i < pd.getSize(); i++) {
			double priceAUnit = pd.getProperty(i).getPrice();
			sum += priceAUnit;
		}

		averagePrice = sum / pd.getSize();
		return averagePrice;
	}

	/**
	 * This is a helper method to arrange the propertyData into different flat room
	 * type groups.
	 * 
	 * @return diffFlatSizeGroupPd
	 */
	private HashMap<String, PropertyData> helperArrangeIntoDifferentFlatSizeGroup() {
		// Arrange this.pd into room type groups

		HashMap<String, PropertyData> diffFlatSizeGroupPd = new HashMap<String, PropertyData>();

		for (int i = 0; i < this.pd.getSize(); i++) {
			Property currentP = this.pd.getProperty(i);
			String currentFlatType = currentP.getFlatType();

			if (!diffFlatSizeGroupPd.containsKey(currentFlatType)) {
				PropertyData roomP = new PropertyData();
				roomP.addProperty(currentP);

				diffFlatSizeGroupPd.put(currentFlatType, roomP);
			}

			else {
				diffFlatSizeGroupPd.get(currentFlatType).addProperty(currentP);
			}
		}

		return diffFlatSizeGroupPd;
	}

	/**
	 * This is a helper method to arrange the propertyData into different location
	 * groups.
	 * 
	 * @return diffFlatLocationGroupPd
	 */
	private HashMap<String, PropertyData> helperArrangeIntoDifferentFlatLocationGroup() {

		// Arrange this.pd into location groups
		HashMap<String, PropertyData> diffFlatLocationGroupPd = new HashMap<String, PropertyData>();

		for (int i = 0; i < this.pd.getSize(); i++) {
			Property currentP = this.pd.getProperty(i);
			String currentFlatLocation = currentP.getTown();

			if (!diffFlatLocationGroupPd.containsKey(currentFlatLocation)) {
				PropertyData townP = new PropertyData();
				townP.addProperty(currentP);

				diffFlatLocationGroupPd.put(currentFlatLocation, townP);
			}

			else {
				diffFlatLocationGroupPd.get(currentFlatLocation).addProperty(currentP);
			}
		}

		return diffFlatLocationGroupPd;
	}

	/**
	 * This is a helper method for filter out a subset of data from the full
	 * PropertyData based on flat Type. 
	 * 
	 * @param flatType (1,2,3,4,5, ALL = 0)
	 * @return subsetPd
	 */
	private PropertyData helperFilterOutRelevantData(Integer intFlatType) {

		HashMap<String, PropertyData> diffFlatSizeGroupPd = this.helperArrangeIntoDifferentFlatSizeGroup();
		PropertyData subsetPd = new PropertyData();
		
		// convert intFlatType into String
		String flatSize = "";
		
		flatSize = intFlatType.toString();
		
		// get the subset 
		if (intFlatType != 0) {
			subsetPd = diffFlatSizeGroupPd.get(flatSize);
		}
		
		else {
			subsetPd = this.pd;
		}

		return subsetPd;

	}

	
	/**
	 * This is to calculate the national average property price of all the flats in
	 * Singapore. Output the average prices for different flat room types
	 * 
	 * @return nationalAvg
	 */
	private HashMap<String, Double> q1calculateNationalAverage() {
		// Arrange this.pd into different room type groups
		HashMap<String, PropertyData> diffFlatSizeGroupPd = this.helperArrangeIntoDifferentFlatSizeGroup();

		// Output into a HashMap, with key = flatType, value = averagePrice
		HashMap<String, Double> nationalAvg = new HashMap<String, Double>();

		// Calculate the average of each group
		String type = "";
		
		for (int i = 1; i < 8; i++) {
			
			type = Integer.toString(i);
			if (diffFlatSizeGroupPd.get(type) != null) {
				double avgPrice = this.helperCalculateAveragePrice(diffFlatSizeGroupPd.get(type));
				nationalAvg.put(type, avgPrice);
			}
		}
		
		double allAvgPrice = this.helperCalculateAveragePrice(this.pd);
		nationalAvg.put("ALL", allAvgPrice);
		
		return nationalAvg;
	}

	/**
	 * This is to calculate the town average property price of the flats in
	 * Singapore. Output the average prices for different towns
	 * 
	 * @return townAvg
	 */
	public HashMap<String, Double> q2calculateTownAverage() {
		// Arrange this.pd into different towns
		HashMap<String, PropertyData> diffFlatLocationGroupPd = this.helperArrangeIntoDifferentFlatLocationGroup();

		// Output into a HashMap, with key = townName, value = averagePrice
		HashMap<String, Double> townAvg = new HashMap<String, Double>();

		// Calculate the average of each town
		for (int i = 0; i < this.townList.size(); i++) {
			String town = this.townList.get(i);
			if (diffFlatLocationGroupPd.get(town) != null) {
				double avgPrice = this.helperCalculateAveragePrice(diffFlatLocationGroupPd.get(town));
				townAvg.put(town, avgPrice);
			}
		}

		return townAvg;
	}

	/**
	 * This is to calculate the median price based on what user input for flat room type and town.
	 * Output the median price
	 * @return median
	 */
	private Double q3calculateMedianPrice() {
		
		// filter this.pd based on user input flat area and town 
		PropertyData filteredResult = this.filterAccordingToUserInputPreference();
		
		ArrayList<Double> priceArray = new ArrayList<Double>();
		Double median = 0.0;
		if (filteredResult == null) {
			median = 0.0;
		}
		else {
			for (int i = 0; i < filteredResult.getSize(); i++) {
				priceArray.add(filteredResult.getProperty(i).getPrice());
				
			}
			
			// Calculate the median of the resulted price arrayList
			Collections.sort(priceArray);
			int length = priceArray.size();
			
			if (length > 0) {
				if (length % 2 == 0) {
					
					median = (priceArray.get(length / 2 - 1) + priceArray.get(length / 2 )) / 2.0;
				}
				
				else {
					median = (double) priceArray.get(length / 2);
				}
			}
		}
		
		return median;
	}

	/**
	 * This is to calculate the highest depreciation over the remaining lease.
	 * Output the remaining lease left for the lowest price flat
	 * 
	 * @return remainingLease
	 */
	private HashMap<String, Double> q4calculatHighestDepreciationYear() {
		HashMap<String, Double> remainingLease = new HashMap<String, Double>();
		
		// Arrange this.pd into different flatType groups
		HashMap<String, PropertyData> diffFlatSizeGroupPd = this.helperArrangeIntoDifferentFlatSizeGroup();
		
		// For each flatType group, group into remaining lease year
		String type = "";
		
		for (int i = 1; i < 8; i++) {
			
			type = Integer.toString(i);
			// roomPd: contains data for one specific flatType
			PropertyData roomPd = diffFlatSizeGroupPd.get(type);
		
			// Group again according to the remaining lease year
			HashMap<Integer, ArrayList<Double>> priceForEachRemainingLeaseYear = new HashMap<Integer, ArrayList<Double>>();
				
			if (roomPd != null) {
			for (int m = 0; m < roomPd.getSize(); m++) {
				Property p = roomPd.getProperty(m);
				int currentLease = p.getRemainingLease();
				double currentPrice = p.getPrice();
				
				if (!priceForEachRemainingLeaseYear.containsKey(currentLease)) {
					ArrayList<Double> price = new ArrayList<Double>();
					price.add(currentPrice);
					
					priceForEachRemainingLeaseYear.put(currentLease,price);
				}
				
				else {
					priceForEachRemainingLeaseYear.get(currentLease).add(currentPrice);
				}
			}
			}
			
			// Calculate the average price for each lease year group 
			// Get the lease year with the lowest average price
			Double lowest = 9999999.99;
			Double lowestLeaseYear = 0.0;
			
			for (int j = 45; j < 99; j++) {
				ArrayList<Double> priceArrayList = priceForEachRemainingLeaseYear.get(j);
				Double sum = 0.0;
				Double avg = 0.0;
				
				if(priceArrayList != null) {
				
					for (Double d : priceArrayList) {
						sum += d;
					}
					
					avg = sum / priceArrayList.size();
					
					if (avg < lowest) {
						lowest = avg;
						lowestLeaseYear = (double) j;
					}
				}
			}
			
			remainingLease.put(type, lowestLeaseYear);
			
		}
		return remainingLease;
	}

	/**
	 * This is a method that returns the filtered data based on user's input:
	 * flatType:  <1,2,3,4,5,6,7,0> (String)
	 * town: <CAPITAL LETTER OF TOWN NAME or ALL> (String)
	 * @return
	 */
	public PropertyData filterAccordingToUserInputPreference() {
		Integer flatType = Integer.parseInt(this.userInputs.get("flatType"));
		String town = this.userInputs.get("town");

		PropertyData filteredFromFlatType = this.helperFilterOutRelevantData(flatType);
		PropertyData filteredPd = new PropertyData();
		
		if (!town.contentEquals("ALL")) {
			for (int i = 0; i < filteredFromFlatType.getSize(); i++) {
				Property currentP = filteredFromFlatType.getProperty(i);
				String currentTown = currentP.getTown();
				
				if (currentTown.contentEquals(town)) {
					filteredPd.addProperty(currentP);
				}
			}
		}
		
		else {
			filteredPd = filteredFromFlatType;
		}
		
		return filteredPd;
	}

	/**
	 * This is the main method to be called outside the class, it returns all the
	 * answers to the insightful questions we want to find out through the set of
	 * property data.
	 * 
	 * @return answers
	 */
	public HashMap<String, HashMap<String, Double>> answersToInsightQuestions() {
		HashMap<String, HashMap<String, Double>> answers = new HashMap<String, HashMap<String, Double>>();

		// call calculation method for Q1 (refer to the question list)
		// add answer1 to answers with key = Q1, value = answer1
		HashMap<String, Double> q1 = q1calculateNationalAverage();
		answers.put("q1", q1);

		// call calculation method for Q2
		HashMap<String, Double> q2 = q2calculateTownAverage();
		answers.put("q2", q2);
		
		// call calculation method for Q3
		// answerToQ3 is Double, convert to HashMap<String, Double> by using key = "q3", value = median
		Double median = q3calculateMedianPrice();
		HashMap<String, Double> q3 = new HashMap<String, Double>();
		q3.put("q3", median);
		answers.put("q3", q3);

		// call calculation method for Q4
		HashMap<String, Double> q4 = q4calculatHighestDepreciationYear();
		answers.put("q4", q4);

		return answers;
	}

}
