import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.util.HashMap;



public class ChartData {
	
	PropertyData pd; 
	MathsAnalysis m;
	HashMap<String, String> userInputs;
	
	// Constructor
	public ChartData(PropertyData pd, HashMap<String, String> userInputs) {
		this.pd = pd;
		this.m = new MathsAnalysis(pd, userInputs);
		this.userInputs = userInputs;
	}
	
	/**
	 * Output data points of historical prices for the type of flat, location the user is interested in.
	 * The data will be used in Widget class to plot graph.
	 * @param numOfYearOfData
	 * @return histPrices
	 */
	public HashMap<String, Double> dataPointsForHistoricalPrices (int numOfYearOfData){
		// Get subset of data according to user input preferences
		this.m.filterAccordingToUserInputPreference();
		
		
		// Get subset of data for only the specific numOfYearOfData 
		// (eg, 1 year, 2 years or 5 years)
		
		// pending to complete the code
		HashMap<String, Double> histPrices = new HashMap<String, Double>();
		
		return histPrices;
	}
	
	/**
	 * Output data points of average prices across different towns. 
	 * The data will be used in Widget class to plot graph.
	 * @return avgPriceOverLocation
	 */
	public HashMap<String, Double> dataPointsForAvgPricesOverLocation(){
		HashMap<String, Double> avgPriceOverLocation = this.m.q2calculateTownAverage();
		
		return avgPriceOverLocation;
	}

}
