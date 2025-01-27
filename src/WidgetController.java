import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class WidgetController {
	
	private PropertyData myProperties;
	
	// UI elements in Property Index tab --------------------------------------------------
	
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
	
	@FXML
	private NumberAxis yAxis3Rm;
	
	@FXML
	private NumberAxis yAxis4Rm;
	
	@FXML
	private NumberAxis yAxis5Rm;
	
	@FXML
	private NumberAxis xAxisAll;
	
	@FXML
	private NumberAxis xAxis3Rm;
	
	@FXML
	private NumberAxis xAxis4Rm;
	
	@FXML
	private NumberAxis xAxis5Rm;
	
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
	
	// UI elements in Location Insights tab --------------------------------------------------
	
	@FXML
	private NumberAxis yAxisLocation;
	
	@FXML
	private NumberAxis xAxisLocation;
	
	@FXML
	private LineChart<Number, Number> chartLocation;
	
	@FXML
	private ComboBox<String> locationSelection;
	
	@FXML
	private Label q2Location;
	
	@FXML
	private Label q3Location;
	
	// UI elements in Calculators tab --------------------------------------------------
	
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
	
	// initialization methods for UI -------------------------------------------------- 
	
	/**
	 * main initialization method that calls other methods organized by tabs
	 */
	@FXML
    public void initialize() {
		
		myProperties = PropertyReader.readFileUrl("https://raw.githubusercontent.com/UPenn-CIT599/final-project-team-86-flat-price-analyzer/master/resale-prices-2005.csv?token=AO7LZBU6J6VYWVA6LCTTA626VVMLQ", true);
		
		// initialize elements in various tabs
		initializeTabIndices();
		initializeTabInsights();
    }
	
	// [TAB] Property Index --------------------------------------------------
	
	/**
	 * Initializes UI elements in Property Index Tab
	 */
	private void initializeTabIndices() {
		initializeCharts();
		answersToAnalysisQnAllTab();
		answersToAnalysisQn3RoomTab();
		answersToAnalysisQn4RoomTab();
		answersToAnalysisQn5RoomTab();
	}
	
	/**
	 * Initializes chart elements in Property Index Tab
	 */
	private void initializeCharts() {
		PropertyData threeRooms = myProperties.filterByFlatType("3");
		PropertyData fourRooms = myProperties.filterByFlatType("4");
		PropertyData fiveRooms = myProperties.filterByFlatType("5");
		
		if (myProperties.getSize() > 0) { 
			chartAll.getData().add(ChartData.createXYSeries(myProperties, Frequency.MONTHLY, "All", AggOp.MEAN, 1000));
			xAxisAll.setAutoRanging(false);
			xAxisAll.setLowerBound(Math.round(myProperties.getMinDateInYears()));
			xAxisAll.setUpperBound(Math.round(myProperties.getMaxDateInYears()));
			xAxisAll.setTickUnit(1.0);
			xAxisAll.setTickLabelFormatter(new StringConverter<Number>() {
		        @Override
		        public String toString(Number object) {
		        	return xAxisFormatter().format(object);
		        }

		        @Override
		        public Number fromString(String string) {
		            return 0;
		        }
		    });
		}
		if (threeRooms.getSize() > 0) { 
			chartAll.getData().add(ChartData.createXYSeries(threeRooms, Frequency.MONTHLY, "3 Room", AggOp.MEAN, 1000));
			chart3Rm.getData().add(ChartData.createXYSeries(threeRooms, Frequency.MONTHLY, "3 Room", AggOp.MEAN, 1000));
			
			xAxis3Rm.setAutoRanging(false);
			xAxis3Rm.setLowerBound(Math.round(threeRooms.getMinDateInYears()));
			xAxis3Rm.setUpperBound(Math.round(threeRooms.getMaxDateInYears()));
			xAxis3Rm.setTickUnit(1.0);
			xAxis3Rm.setTickLabelFormatter(new StringConverter<Number>() {
		        @Override
		        public String toString(Number object) {
		        	return xAxisFormatter().format(object);
		        }

		        @Override
		        public Number fromString(String string) {
		            return 0;
		        }
		    });
		}
		if (fourRooms.getSize() > 0) { 
			chartAll.getData().add(ChartData.createXYSeries(fourRooms, Frequency.MONTHLY, "4 Room", AggOp.MEAN, 1000));
			chart4Rm.getData().add(ChartData.createXYSeries(fourRooms, Frequency.MONTHLY, "4 Room", AggOp.MEAN, 1000));
			
			xAxis4Rm.setAutoRanging(false);
			xAxis4Rm.setLowerBound(Math.round(fourRooms.getMinDateInYears()));
			xAxis4Rm.setUpperBound(Math.round(fourRooms.getMaxDateInYears()));
			xAxis4Rm.setTickUnit(1.0);
			xAxis4Rm.setTickLabelFormatter(new StringConverter<Number>() {
		        @Override
		        public String toString(Number object) {
		        	return xAxisFormatter().format(object);
		        }

		        @Override
		        public Number fromString(String string) {
		            return 0;
		        }
		    });
		}
		if (fiveRooms.getSize() > 0) { 
			chartAll.getData().add(ChartData.createXYSeries(fiveRooms, Frequency.MONTHLY, "5 Room", AggOp.MEAN, 1000));
			chart5Rm.getData().add(ChartData.createXYSeries(fiveRooms, Frequency.MONTHLY, "5 Room", AggOp.MEAN, 1000));
			
			xAxis5Rm.setAutoRanging(false);
			xAxis5Rm.setLowerBound(Math.round(fiveRooms.getMinDateInYears()));
			xAxis5Rm.setUpperBound(Math.round(fiveRooms.getMaxDateInYears()));
			xAxis5Rm.setTickUnit(1.0);
			xAxis5Rm.setTickLabelFormatter(new StringConverter<Number>() {
		        @Override
		        public String toString(Number object) {
		        	return xAxisFormatter().format(object);
		        }

		        @Override
		        public Number fromString(String string) {
		            return 0;
		        }
		    });
		}
	}
	
	/**
	 * Initializes UI elements in Location Insights Tab:
	 *    > location filter with list of unique towns in dataset
	 *    > set x-Axis range and label formatting
	 */
	private void initializeTabInsights() {
		ObservableList<String> towns = myProperties.getUniqueTowns();
		locationSelection.getItems().addAll(towns);
		
		xAxisLocation.setAutoRanging(false);
		xAxisLocation.setLowerBound(Math.round(myProperties.getMinDateInYears()));
		xAxisLocation.setUpperBound(Math.round(myProperties.getMaxDateInYears()));
		xAxisLocation.setTickUnit(1.0);
		xAxisLocation.setTickLabelFormatter(new StringConverter<Number>() {
	        @Override
	        public String toString(Number object) {
	        	return xAxisFormatter().format(object);
	        }

	        @Override
	        public Number fromString(String string) {
	            return 0;
	        }
	    });
		
	}
	
	/**
	 * Gets the string formatter object to format xAxis labels (no comma separation, whole numbers) 
	 * 
	 * Example: 
	 * Number year = 2019.5 
	 * xAxisFormatter().format(year) == 2019
	 * 
	 * @return string formatter object
	 */
	private DecimalFormat xAxisFormatter() {
		Locale locale  = new Locale("en", "SG");
    	String pattern = "#";

    	DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
    	decimalFormat.applyPattern(pattern);
    	
    	return decimalFormat;
	}
	
	// [TAB] Location Insights --------------------------------------------------
	
	/**
	 * Refreshes chart based on town selected by user under locationSelection
	 */
	public void refreshChartOnSelection() {
		String town = locationSelection.getValue();
		PropertyData propertiesInSelectedTown = myProperties.filterByTown(town);
		if (propertiesInSelectedTown.getSize() > 0) { 
			chartLocation.getData().clear();;
			chartLocation.getData().add(ChartData.createXYSeries(propertiesInSelectedTown, Frequency.MONTHLY, town, AggOp.MEAN, 1000));
			
			xAxisLocation.setAutoRanging(false);
			xAxisLocation.setLowerBound(Math.round(propertiesInSelectedTown.getMinDateInYears()));
			xAxisLocation.setUpperBound(Math.round(propertiesInSelectedTown.getMaxDateInYears()));
			xAxisLocation.setTickUnit(1.0);
		}
		answersToAnalysisQnLocation();
	}
		
	// [TAB] Calculator --------------------------------------------------
	
	/**
	 * Helper function to check if text input is an integer
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Helper function to check if text input is a float
	 */
	public static boolean isFloat(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method that implements the loanCalculator when the "Calculate!" button is
	 * pressed
	 */
	public void calculate(ActionEvent event) {

		int price = -1;
		double rate = -1;
		int numOfYears = -1;
		boolean validPrice;
		boolean validRate;
		boolean validYears;

		// checks if price, rate and numOfYears are valid

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
	
	// Methods to be used with Maths Analysis 
	/**
	 * This method is to provide text content for the "All" tab under "Property
	 * Index"
	 */
	public void answersToAnalysisQnAllTab() {
		HashMap<String, String> inForTab = new HashMap<String, String>();
		inForTab.put("flatType", "0");
		inForTab.put("town", "ALL");

		MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);

		q1All.setText("Average Price for All Properties (SGD): "
				+ Math.round(m1.answersToInsightQuestions().get("q1").get("ALL")));
		q3All.setText("Median Price for All Properties (SGD): "
				+ Math.round(m1.answersToInsightQuestions().get("q3").get("q3")));
	}
	 
	/**
	 * This is a helper method to check if the answers from the dataset is null
	 * 
	 * @param answersToBeChecked
	 * @return outcomes
	 */
	public HashMap<String, String> helperCheckIfAnswerIsNull(MathsAnalysis m1, String flatType, String town) {
		HashMap<String, String> outcomes = new HashMap<String, String>();

		String ans1 = "";
		String ans2 = "";
		String ans3 = "";
		String ans4 = "";

		// Answer to Q1
		if (!(m1.answersToInsightQuestions().get("q1").get(flatType) == null)) {
			Double A1 = m1.answersToInsightQuestions().get("q1").get(flatType);
			ans1 = Long.toString(Math.round(A1));

		} else {
			ans1 = "N.A.";
		}

		// Answer to Q2
		if (!(m1.answersToInsightQuestions().get("q2").get(town) == null)) {
			Double A2 = m1.answersToInsightQuestions().get("q2").get(town);
			ans2 = Long.toString(Math.round(A2));

		} else {
			ans2 = "N.A.";
		}

		// Answer to Q3
		if (m1.answersToInsightQuestions().get("q3").get("q3") == 0.0) {
			ans3 = "N.A.";
		} else {
			Double A3 = m1.answersToInsightQuestions().get("q3").get("q3");
			ans3 = Long.toString(Math.round(A3));
		}

		// Answer to Q4
		if (!flatType.contentEquals("0")) {
			if (m1.answersToInsightQuestions().get("q4").get(flatType) == 0.0) {
				ans4 = "N.A.";
			} else {
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
	 * This method is to provide text content for the "3-room" tab under "Property
	 * Index"
	 */
	public void answersToAnalysisQn3RoomTab() {
		HashMap<String, String> inForTab = new HashMap<String, String>();
		inForTab.put("flatType", "3");
		inForTab.put("town", "ALL");

		MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "3", "ALL");

		q1Room3.setText("Average Price for 3-Room Properties (SGD): " + outcomes.get("ans1"));
		q3Room3.setText("Median Price for 3-Room Properties (SGD): " + outcomes.get("ans3"));
		q4Room3.setText("Depreciation for 3-room properties is highest when the property has " + outcomes.get("ans4")
				+ " years of remaining lease.");
	}

	/**
	 * This method is to provide text content for the "4-room" tab under "Property
	 * Index"
	 */
	public void answersToAnalysisQn4RoomTab() {
		HashMap<String, String> inForTab = new HashMap<String, String>();
		inForTab.put("flatType", "4");
		inForTab.put("town", "ALL");

		MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "4", "ALL");

		q1Room4.setText("Average Price for 4-Room Properties (SGD): " + outcomes.get("ans1"));
		q3Room4.setText("Median Price for 4-Room Properties (SGD): " + outcomes.get("ans3"));
		q4Room4.setText("Depreciation for 4-room property is the highest when the property has " + outcomes.get("ans4")
				+ " years of remaining lease.");
	}

	/**
	 * This method is to provide text content for the "5-room" tab under "Property
	 * Index"
	 */
	public void answersToAnalysisQn5RoomTab() {
		HashMap<String, String> inForTab = new HashMap<String, String>();
		inForTab.put("flatType", "5");
		inForTab.put("town", "ALL");

		MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "5", "ALL");

		q1Room5.setText("Average Price for 5-Room Properties (SGD): " + outcomes.get("ans1"));
		q3Room5.setText("Median Price for 5-Room Properties (SGD): " + outcomes.get("ans3"));
		q4Room5.setText("Depreciation for 5-room property is the highest when the property has " + outcomes.get("ans4")
				+ " years of remaining lease.");
	}

	/**
	 * This method is to get the user's selection of the location information
	 */
	public String helperComboChanged() {
		return locationSelection.getValue();
	}

	/**
	 * This method is to provide text content for the "Location Insights" sheet
	 */
	public void answersToAnalysisQnLocation() {

		HashMap<String, String> inForTab = new HashMap<String, String>();
		inForTab.put("flatType", "0"); // for all flat types
		inForTab.put("town", locationSelection.getValue());

		MathsAnalysis m1 = new MathsAnalysis(myProperties, inForTab);
		HashMap<String, String> outcomes = this.helperCheckIfAnswerIsNull(m1, "0", inForTab.get("town"));

		q2Location.setText("Average Price for All Properties in the selected town (SGD): " + outcomes.get("ans2"));
		q3Location.setText("Median Price for All Properties in the selected town (SGD): " + outcomes.get("ans3"));

	}
}
