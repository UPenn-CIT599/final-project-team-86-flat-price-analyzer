import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class CalculatorController {
	
	private PropertyData myProperties;
	
	
	@FXML
    public void initialize() {
		
		myProperties = PropertyReader.readFile("resale-prices-2005.csv", true);
		
		// initialize elements in various tabs
		
		initializeTabIndices();
		initializeTabInsights();
		initializeTabCalculator();
		initializeTabAbout();
    }
	
	private void initializeTabIndices() {
		
		PropertyData threeRooms = myProperties.filterByFlatType("3");
		PropertyData fourRooms = myProperties.filterByFlatType("4");
		PropertyData fiveRooms = myProperties.filterByFlatType("5");
		
		XYChart.Series<Number, Number> seriesAll = ChartData.createXYSeries(myProperties, Frequency.MONTHLY, "All", AggOp.MEAN, 1000);
		XYChart.Series<Number, Number> series3rm = ChartData.createXYSeries(threeRooms, Frequency.MONTHLY, "3 Room", AggOp.MEAN, 1000);
		XYChart.Series<Number, Number> series4rm = ChartData.createXYSeries(fourRooms, Frequency.MONTHLY, "4 Room", AggOp.MEAN, 1000);
		XYChart.Series<Number, Number> series5rm = ChartData.createXYSeries(fiveRooms, Frequency.MONTHLY, "5 Room", AggOp.MEAN, 1000);
		
		if (myProperties.getSize() > 0) { chartAll.getData().add(seriesAll); }
		if (threeRooms.getSize() > 0) { 
			chartAll.getData().add(ChartData.createXYSeries(threeRooms, Frequency.MONTHLY, "3 Room", AggOp.MEAN, 1000));
			chart3Rm.getData().add(ChartData.createXYSeries(threeRooms, Frequency.MONTHLY, "3 Room", AggOp.MEAN, 1000));
		}
		if (fourRooms.getSize() > 0) { 
			chartAll.getData().add(ChartData.createXYSeries(fourRooms, Frequency.MONTHLY, "4 Room", AggOp.MEAN, 1000));
			chart4Rm.getData().add(ChartData.createXYSeries(fourRooms, Frequency.MONTHLY, "4 Room", AggOp.MEAN, 1000));
		}
		if (fiveRooms.getSize() > 0) { 
			chartAll.getData().add(ChartData.createXYSeries(fiveRooms, Frequency.MONTHLY, "5 Room", AggOp.MEAN, 1000));
			chart5Rm.getData().add(ChartData.createXYSeries(fiveRooms, Frequency.MONTHLY, "5 Room", AggOp.MEAN, 1000));
		}
		
		answersToAnalysisQnAllTab();
		answersToAnalysisQn3RoomTab();
		answersToAnalysisQn4RoomTab();
		answersToAnalysisQn5RoomTab();
		
		//chartAll.getXAxis().setLabel("Date");
		//chartAll.getYAxis().setLabel("SGD '000");
		//chartAll.setCreateSymbols(false);
		
		//yAxisAll.setAutoRanging(true);
		//yAxisAll.setForceZeroInRange(false);		
	}
	
	private void initializeTabInsights() {
		// initialize filter options
		
		ObservableList<String> towns = myProperties.getUniqueTowns();
		locationSelection.getItems().addAll(towns);
		
	}
	
	private void initializeTabCalculator() {
		// ADD here
	}
	
	private void initializeTabAbout() {
		// ADD here
	}
	
	// [TAB] Property Index --------------------------------------------------
	
	@FXML
	private LineChart<Number, Number> chartAll;
	
	@FXML
	private LineChart<Number, Number> chart3Rm;
	
	@FXML
	private LineChart<Number, Number> chart4Rm;
	
	@FXML
	private LineChart<Number, Number> chart5Rm;
	
	@FXML
	private NumberAxis yAxisAll;
	
	// [TAB] Location Insights --------------------------------------------------
	
	//@FXML
	//private ComboBox<String> cbLocation;
	
	@FXML
	private MenuButton mbFlatType;
	
	@FXML
	private MenuButton mbRemainingLease;
	
	@FXML
	private MenuButton mbMinPrice;
	
	@FXML
	private MenuButton mbMaxPrice;
	
	@FXML
	private LineChart<Number, Number> chartLocation;
	
	public void refreshChartOnSelection() {
		String town = locationSelection.getValue();
		PropertyData propertiesInSelectedTown = myProperties.filterByTown(town);
		if (propertiesInSelectedTown.getSize() > 0) { 
			chartLocation.getData().clear();;
			chartLocation.getData().add(ChartData.createXYSeries(propertiesInSelectedTown, Frequency.MONTHLY, town, AggOp.MEAN, 1000)); 
		}
		answersToAnalysisQnLocation();
	}
		
	// [TAB] Calculator --------------------------------------------------
	
	@FXML
	private TextField flatPrice;
	
	@FXML
	private TextField paymentPeriod;
	
	@FXML
	private TextField annualRate;
	
	@FXML
	private Label statusLabel;
	
	@FXML
	private Label monthlyInstallment;

	@FXML
	private Button calculatePrice;

	/**
	 * Helper function to check if text input is an integer
	 */
	
	  public static boolean isInteger(String s) {
	      try
	      {
	         Integer.parseInt(s);	 
	      }
	      catch (NumberFormatException e)
	      {
	    	  return false;
	      }
	      return true;
	   }
	  
		
	/**
	 * Helper function to check if text input is a float
	 */	  
		
	  public static boolean isFloat(String s) {
	      try
	      {
	         Double.parseDouble(s);
	      }
	      catch (NumberFormatException e)
	      {
	    	  return false;
	      }
	      return true;
	   }
	
	
	/**
	 * Method that implements the loanCalculator when the "Calculate!" button is pressed
	 */

	  public void calculate(ActionEvent event) {  
			
		int price = -1;
		double rate = -1;
		int numOfYears = -1;
		boolean validPrice;
		boolean validRate;
		boolean validYears;				
		
		//checks if price, rate and numOfYears are valid
		
		  validPrice = isInteger(flatPrice.getText());
		  validRate = isFloat(annualRate.getText());
		  validYears = isInteger(paymentPeriod.getText());
			
		if (validPrice) {
			price = Integer.parseInt(flatPrice.getText());
			
			if (price <= 0) {
				validPrice = false;
				statusLabel.setText("Enter a positive number for price.");
			}
			
		}
		
		else {
			statusLabel.setText("Please enter integers for price.");
		}
		
		if (validYears) {
			numOfYears = Integer.parseInt(paymentPeriod.getText());	
			
			if ((numOfYears < 1) || (numOfYears > 25)) {
				validYears = false;
				statusLabel.setText("Enter a year between 1 and 25.");
			}
		}
		
		else {
			statusLabel.setText("Enter numbers only for repayment period.");
		}
		
		if (validRate) {
			rate = Double.parseDouble(annualRate.getText());	

			if ((rate <= 0) || (rate > 100)) {
				validRate = false;
				statusLabel.setText("Enter an interest rate between > 0 and < 100.");
			} 
		}
		
		else {
			statusLabel.setText("Please enter numbers only for interest rate.");
		}
		
		if (validPrice && validRate && validYears) {
			String installment = LoanCalculator.getInstallment(price, numOfYears, rate);
			statusLabel.setText(" ");			
			monthlyInstallment.setText(installment);			
		}
		
	}
	 
	  
	  
	// START OF Maths Analysis
		@FXML
		private Label q1All;
		
		@FXML
		private Label q3All;
		
		@FXML
		private Label q1Room3;
		
		@FXML
		private Label q3Room3;
		
		@FXML
		private Label q4Room3;
		
		@FXML
		private Label q1Room4;
		
		@FXML
		private Label q3Room4;
		
		@FXML
		private Label q4Room4;
		
		@FXML
		private Label q1Room5;
		
		@FXML
		private Label q3Room5;
		
		@FXML
		private Label q4Room5;
		
		@FXML
		private Label q1RoomExe;
		
		@FXML
		private Label q3RoomExe;
		
		@FXML
		private Label q4RoomExe;
		
		@FXML
		private ComboBox<String> locationSelection;
		
		@FXML
		private Label q2Location;
		
		@FXML
		private Label q3Location;
		
	
	 // Methods to be used with Maths Analysis 
	 /**
	  * This method is to provide text content for the "All" tab under "Property Index"
	  */
	 public void answersToAnalysisQnAllTab() {
		  HashMap<String, String> inForTab = new HashMap<String, String>();
		  inForTab.put("flatType", "0");
		  inForTab.put("town", "ALL");
		  
		  MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		 
		  q1All.setText("Average Price for All Properties (SGD): " + Math.round(m1.answersToInsightQuestions().get("q1").get("ALL")));
		  q3All.setText("Median Price for All Properties (SGD): " + Math.round(m1.answersToInsightQuestions().get("q3").get("q3")));
	  }
	 
	 
	 /**
	  * This is a helper method to check if the answers from the dataset is null 
	  * @param answersToBeChecked
	  * @return outcomes
	  */
	 public HashMap<String, String> helperCheckIfAnswerIsNull(MathsAnalysis m1, String flatType, String town){
		 HashMap<String, String> outcomes = new HashMap<String, String>();
		 
		 String ans1 = "";
		 String ans2 = "";
		 String ans3 = "";
		 String ans4 = "";
		 
		 
		 // Answer to Q1
		 if (!(m1.answersToInsightQuestions().get("q1").get(flatType) == null)) {
			 Double A1 = m1.answersToInsightQuestions().get("q1").get(flatType);
			 ans1 = Long.toString(Math.round(A1));
			 
		 }
		 else {
			 ans1 = "N.A.";
		}
		 
		 // Answer to Q2
		 if (!(m1.answersToInsightQuestions().get("q2").get(town) == null)) {
			 Double A2 = m1.answersToInsightQuestions().get("q2").get(town);
			 ans2 = Long.toString(Math.round(A2));
			 
		 }
		 else {
			 ans2 = "N.A.";
		}
		 
		// Answer to Q3
		 if (m1.answersToInsightQuestions().get("q3").get("q3") == 0.0) {
			 ans3 = "N.A.";
		 }
		 else {
			 Double A3 = m1.answersToInsightQuestions().get("q3").get("q3");
			 ans3 = Long.toString(Math.round(A3));
		}
		 
		// Answer to Q4
		 if (!flatType.contentEquals("0") ) {
			 if (m1.answersToInsightQuestions().get("q4").get(flatType) == 0.0) {
				 ans4 = "N.A.";
			 }
			 else {
				 Double A4 = m1.answersToInsightQuestions().get("q4").get(flatType);
				 ans4 = Long.toString(Math.round(A4));
			}
		}
		 
		  outcomes.put("ans1", ans1);
		  outcomes.put("ans2", ans2);
		  outcomes.put("ans3", ans3);
		  outcomes.put("ans4", ans4);
		  
		 
		 return outcomes;
	 }
	 
	 /**
	  * This method is to provide text content for the "3-room" tab under "Property Index"
	  */
	 public void answersToAnalysisQn3RoomTab() {
		  HashMap<String, String> inForTab = new HashMap<String, String>();
		  inForTab.put("flatType", "3");
		  inForTab.put("town", "ALL");
		  
		  MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		  HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "3", "ALL");		 
		  
		  
		  q1Room3.setText("Average Price for 3-Room Properties (SGD): " + outcomes.get("ans1"));
		  q3Room3.setText("Median Price for 3-Room Properties (SGD): " + outcomes.get("ans3"));
		  q4Room3.setText("Depreciation for 3-room properties is highest when the property has " + outcomes.get("ans4") + " years of remaining lease.");
	  }
	 
	 /**
	  * This method is to provide text content for the "4-room" tab under "Property Index"
	  */
	 public void answersToAnalysisQn4RoomTab() {
		  HashMap<String, String> inForTab = new HashMap<String, String>();
		  inForTab.put("flatType", "4");
		  inForTab.put("town", "ALL");
		  
		  MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		  HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "4", "ALL");	
		  
		  
		  q1Room4.setText("Average Price for 4-Room Properties (SGD): " + outcomes.get("ans1"));	  
		  q3Room4.setText("Median Price for 4-Room Properties (SGD): " + outcomes.get("ans3"));
		  q4Room4.setText("Depreciation for 4-room property is the highest when the property has " + outcomes.get("ans4") + " years of remaining lease.");
	  }
	 
	 /**
	  * This method is to provide text content for the "5-room" tab under "Property Index"
	  */
	 public void answersToAnalysisQn5RoomTab() {
		  HashMap<String, String> inForTab = new HashMap<String, String>();
		  inForTab.put("flatType", "5");
		  inForTab.put("town", "ALL");
		  
		  MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		  HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "5", "ALL");	
		  
		  
		  q1Room5.setText("Average Price for 5-Room Properties (SGD): " + outcomes.get("ans1"));	  
		  q3Room5.setText("Median Price for 5-Room Properties (SGD): " + outcomes.get("ans3"));
		  q4Room5.setText("Depreciation for 5-room property is the highest when the property has " + outcomes.get("ans4") + " years of remaining lease.");
	  }
	 
	 
	 
	 /**
	  * This method is to get the user's selection of the location information 
	  */
	 public String helperComboChanged() {
		 String town = locationSelection.getValue();
		 
		 return town;
	 }
	 
	 /**
	  * This method is to provide text content for the "Location Insights" sheet
	  */
	 public void answersToAnalysisQnLocation() {
	  
		  HashMap<String, String> inForTab = new HashMap<String, String>();
		  inForTab.put("flatType", "0"); // for all flat types
		  //inForTab.put("town", this.helperComboChanged());
		  inForTab.put("town", locationSelection.getValue());
		  
		  MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		  HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "0", inForTab.get("town"));	
		  
		  q2Location.setText("Average Price for All Properties in the selected town (SGD): " + outcomes.get("ans2"));	  
		  q3Location.setText("Median Price for All Properties in the selected town (SGD): " + outcomes.get("ans3"));
		 
	  }
	 
	 

}
