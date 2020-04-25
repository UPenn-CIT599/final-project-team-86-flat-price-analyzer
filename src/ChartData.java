/**
 * #Description#
 * 
 * Version 1.0
 * 
 * Added constructor; 
 * Added methods helperCalculateAveragePrice, dataPointsForHistoricalPrices, 
 * dataPointsForAvgPricesOverLocation
 * 
 * Version 2.0
 * 
 * Added methods: createXYSeries, getAggPrice
 * 
 * @author YiXin
 * 
 */

import java.util.HashMap;

import javafx.scene.chart.XYChart;



public class ChartData {
	
	PropertyData pd; 
	MathsAnalysis m;
	HashMap<String, String> userInputs;
	
	// Constructor
	public ChartData(PropertyData pd, HashMap<String, String> userInputs) {
		this.pd = pd;
		this.m = new MathsAnalysis(pd, userInputs);
		// userInput: 
		// key1:"flatType" - value1: any integer in String format from "0" to "7" 
		// "0" means select all flatType, 1 - 7 means select respective flatType
		// key2: "town" - value2: "ALL" or "ANG MO KIO" etc
		this.userInputs = userInputs;
	}
	
	/**
	 * This is a helper method for calculating the average of a given propertydata
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
	 * Output data points of historical prices for the type of flat, location the user is interested in.
	 * The data will be used in Widget class to plot graph
	 * @return histPrices
	 */
	public HashMap<String, Double> dataPointsForHistoricalPrices (){
		// Get subset of data according to user input preferences
		PropertyData subsetPd = this.m.filterAccordingToUserInputPreference();
	
		HashMap<String, PropertyData> monthlyHistData = new HashMap<String, PropertyData>();
		HashMap<String, Double> histPrices = new HashMap<String, Double>();
		
		for (int i = 0; i < subsetPd.getSize(); i++) {
			Property currentP = subsetPd.getProperty(i);
			String currentYear = Integer.toString(currentP.getYear());
			Integer month = currentP.getMonth();
			String currentMonth = "";
			
			if (month < 10) {
				currentMonth = "0"+ Integer.toString(month);
			}
			
			else {
				currentMonth = Integer.toString(month);
			}
			
			String currentYearAndMonth = currentMonth+currentYear ;
	
			if (!monthlyHistData.containsKey(currentYearAndMonth)) {
				PropertyData p = new PropertyData();
				p.addProperty(currentP);

				monthlyHistData.put(currentYearAndMonth, p);
			}

			else {
				monthlyHistData.get(currentYearAndMonth).addProperty(currentP);
			}
		}
		
		// Get average price for every month's data, output to histPrices
		
		// System.out.println(monthlyHistData.keySet());
		
		for (String key : monthlyHistData.keySet()) {
			// System.out.println(key);
			Double monthlyAvgPrice = this.helperCalculateAveragePrice(monthlyHistData.get(key));
			histPrices.put(key, monthlyAvgPrice);
		}
		
		// System.out.println(histPrices.toString());
		
		return histPrices;
	}
	
	/**
	 * Output data points of average prices across different towns. 
	 * The data will be used in Widget class to plot graph.
	 * @return avgPriceOverLocation
	 */
	public HashMap<String, Double> dataPointsForAvgPricesOverLocation(){
		HashMap<String, Double> avgPriceOverLocation = this.m.q2calculateTownAverage();
		// System.out.println(avgPriceOverLocation.toString());
		return avgPriceOverLocation;
	}
	
	public static XYChart.Series<Number, Number> createXYSeries(PropertyData properties, Frequency freq, String seriesName, AggOp aggOp, double scaler) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>(); 
		
		// if no properties, return empty series
		if (properties.getSize() > 0) {
			series.setName(seriesName);
			
			int minYear = properties.getMinYear();
			int maxYear = properties.getMaxYear();
			
			switch (freq) {
			case DAILY: {
				// ADD here
				break;
			}
			case MONTHLY: {
				int minMonth = properties.getMinMonthInYear(minYear);
				int maxMonth = properties.getMaxMonthInYear(maxYear);
				int xVal = 0;
				
				for (int currentYear = minYear; currentYear <= maxYear; currentYear++) {
					int firstMonth = 1;
					int lastMonth = 12;
					
					// exceptions for first and last year of dataset, which may not include full 12 months
					if (currentYear == minYear) {
						firstMonth = minMonth;
					}
					else if (currentYear == maxYear) {
						lastMonth = maxMonth;
					}
					
					for (int currentMonth = firstMonth; currentMonth <= lastMonth; currentMonth++) {
						PropertyData myProps = properties.filterByDate(currentYear, currentMonth);
						Double myPx = getAggPrice(myProps, aggOp, scaler);
						if (!myPx.isNaN()) { series.getData().add(new XYChart.Data<Number, Number>(xVal, myPx)); }
						xVal++;
					}
				}
				
				break;
			}
			case YEARLY: {
				// ADD here
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + freq);
			}
		}
		
		return series;
	}
	
	private static double getAggPrice(PropertyData properties, AggOp aggOp, double scaler) {
		switch (aggOp) {
		case MEAN: 
		case SUM: {
			double sum = 0;
			
			for (Property property : properties.propertyList) {
				sum += property.getPrice();
			}
			
			return (aggOp == AggOp.SUM ? sum : (sum / properties.propertyList.size())) / scaler; 
		}
		case MIN: {
			double min = properties.getProperty(0).getPrice();
			
			for (Property property : properties.propertyList) {
				double price = property.getPrice();
				min = price < min ? price : min;
			}
			
			return min / scaler;
		}
		case MAX: {
			double max = properties.getProperty(0).getPrice();
			
			for (Property property : properties.propertyList) {
				double price = property.getPrice();
				max = price > max ? price : max;
			}
			
			return max / scaler;
		}
		case MEDIAN: {
			// to add
			return 0;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + aggOp);
		}
				
	}
	
	// for testing
	public static void main(String[] args) {
		PropertyData propertyData = PropertyReader.readFile("resale-prices.csv", true);
		HashMap<String, String> in = new HashMap<String, String>();
		in.put("flatType", "0");
		in.put("town", "ALL");
		
		ChartData cd = new ChartData(propertyData, in);
		
		cd.dataPointsForHistoricalPrices();
		cd.dataPointsForAvgPricesOverLocation();
	}

}
