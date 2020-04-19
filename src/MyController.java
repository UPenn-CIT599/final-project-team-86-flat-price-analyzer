package application;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

enum Frequency {
	DAILY, WEEKLY, MONTHLY, YEARLY
}

enum AggOp {
	SUM, MIN, MAX, MEDIAN, MEAN
}

public class MyController {
	
	@FXML
	private LineChart<Number, Number> chartAll;
	
	@FXML
	private LineChart<Number, Number> chart3Rm;
	
	@FXML
	private LineChart<Number, Number> chart4Rm;
	
	@FXML
	private LineChart<Number, Number> chart5Rm;
	
	@FXML
	private LineChart<Number, Number> chartExec;
	
	@FXML
	private ComboBox<String> cbLocation;
	
	@FXML
	private MenuButton mbFlatType;
	
	@FXML
	private MenuButton mbRemainingLease;
	
	@FXML
	private MenuButton mbMinPrice;
	
	@FXML
	private MenuButton mbMaxPrice;
	
	public MyController() {
        System.out.println("first");
    }
	
	@FXML
    public void initialize() {
		System.out.println("second");
		PropertyData myProperties = PropertyReader.readFile("jan2019_dec2019.csv", true);
		PropertyData threeRooms = this.filterByFlatType(myProperties, "3");
		PropertyData fourRooms = this.filterByFlatType(myProperties, "4");
		PropertyData fiveRooms = this.filterByFlatType(myProperties, "5");
		
		XYChart.Series<Number, Number> seriesAll = createXYSeries(myProperties, Frequency.MONTHLY, "All", AggOp.MEAN, 1000);
		XYChart.Series<Number, Number> series3rm = createXYSeries(threeRooms, Frequency.MONTHLY, "3 Room", AggOp.MEAN, 1000);
		XYChart.Series<Number, Number> series4rm = createXYSeries(fourRooms, Frequency.MONTHLY, "4 Room", AggOp.MEAN, 1000);
		XYChart.Series<Number, Number> series5rm = createXYSeries(fiveRooms, Frequency.MONTHLY, "5 Room", AggOp.MEAN, 1000);
		
		chartAll.getData().addAll(seriesAll, series3rm, series4rm, series5rm);
		
		chartAll.getXAxis().setLabel("Date");
		chartAll.getYAxis().setLabel("SGD '000");
		chartAll.setCreateSymbols(false);
		chartAll.getYAxis().setAutoRanging(true);
		
		
		cbLocation.getItems().addAll(getUniqueStreetNames(myProperties));
		
		// fillChart(myProperties, Frequency.MONTHLY, "<Some chart title>", "All", "Date", "S$'000", AggOp.MEAN);
    }
	
	
	private PropertyData filterByFlatType(PropertyData properties, String flatType) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : properties.propertyList) {
			if (property.getFlatType().equals(flatType)) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
	
	private PropertyData filterByDate(PropertyData properties, int year, int month) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : properties.propertyList) {
			if (property.getYear() == year & property.getMonth() == month) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
	
	private PropertyData filterByYear(PropertyData properties, int year) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : properties.propertyList) {
			if (property.getYear() == year) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
	
	private PropertyData filterByMonth(PropertyData properties, int month) {
		PropertyData filteredProperties = new PropertyData();
		
		for (Property property : properties.propertyList) {
			if (property.getMonth() == month) {
				filteredProperties.addProperty(property);
			}
		}
		
		return filteredProperties;
	}
	
	private void fillChart(PropertyData properties, Frequency freq, String chartTitle, String seriesName, String xLabel, String yLabel, AggOp aggOp, double scaler) {
		
		/*
		// creating axes
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		
		// creating chart
		LineChart<Number, Number>  lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle(chartTitle);
		*/
		
		
		
		// defining chart series
		XYChart.Series<Number, Number> series = createXYSeries(properties, freq, seriesName, aggOp, scaler);
		
		// add series to chart
		chartAll.getData().add(series);
		
		//return lineChart;
	}
	
	private ObservableList<String> getUniqueStreetNames(PropertyData properties) {
		ObservableList<String> streetNames = FXCollections.observableArrayList();
		
		for (Property property : properties.propertyList) {
			String streetName =  property.getTown();
			if (!streetNames.contains(streetName)) {
				streetNames.add(streetName);
			}
		}
		
		return streetNames;
	}
	
	private XYChart.Series<Number, Number> createXYSeries(PropertyData properties, Frequency freq, String seriesName, AggOp aggOp, double scaler) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>(); 
		
		series.setName(seriesName);
		
		int minYear = getOldestYear(properties);
		int maxYear = getLatestYear(properties);
		
		switch (freq) {
		case MONTHLY: {
			int minMonth = getOldestMonthInYear(properties, minYear);
			int maxMonth = getLatestMonthInYear(properties, maxYear);
			int xVal = 0;
			
			for (int i = minYear; i <= maxYear; i++) {
				if (i == minYear) {
					for (int j = minMonth; j <= 12; j++) {
						PropertyData myProps = filterByDate(properties, i, j);
						double myPx = getAggPrice(myProps, aggOp, scaler);
						series.getData().add(new XYChart.Data<Number, Number>(xVal, myPx));
						xVal++;
					}
				}
				else if (i == maxYear) {
					for (int j = 1; j <= maxMonth; j++) {
						PropertyData myProps = filterByDate(properties, i, j);
						double myPx = getAggPrice(myProps, aggOp, scaler);
						series.getData().add(new XYChart.Data<Number, Number>(xVal, myPx));
						xVal++;
					}
				}
			}
			
			break;
		}
		case YEARLY: {
			// to add
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + freq);
		}
		
		return series;
	}
	
	private int getOldestYear(PropertyData properties) {
		int minYear = properties.getProperty(0).getYear();
		
		for (Property property : properties.propertyList) {
			int propYear = property.getYear();
			minYear = propYear < minYear ? propYear : minYear; 
		}
		
		return minYear;
	}
	
	private int getOldestMonthInYear(PropertyData properties, int year) {
		int minMonth = properties.getProperty(0).getMonth();;
		
		for (Property property : properties.propertyList) {
			if (property.getYear() == year) {
				int propMonth = property.getMonth();
				minMonth = propMonth < minMonth ? propMonth : minMonth; 
			}
		}
		
		return minMonth;
	}
	
	private int getLatestYear(PropertyData properties) {
		int maxYear = 0;
		
		for (Property property : properties.propertyList) {
			int propYear = property.getYear();
			maxYear = propYear > maxYear ? propYear : maxYear; 
		}
		
		return maxYear;
	}
	
	private int getLatestMonthInYear(PropertyData properties, int year) {
		int maxMonth = 0;
		
		for (Property property : properties.propertyList) {
			if (property.getYear() == year) {
				int propMonth = property.getMonth();
				maxMonth = propMonth < maxMonth ? propMonth : maxMonth; 
			}
		}
		
		return maxMonth;
	}
	
	private double getAggPrice(PropertyData properties, AggOp aggOp, double scaler) {
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
}
