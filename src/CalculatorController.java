import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {
	
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
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(s);	 
	         isValidInteger = true;
	      }
	      catch (NumberFormatException e)
	      {
	    	  //do nothing
	      }
	 
	      return isValidInteger;
	   }
	  
		
	/**
	 * Helper function to check if text input is a float
	 */	  
		
	  public static boolean isFloat(String s) {
	      boolean isValidFloat = false;
	      try
	      {
	         Double.parseDouble(s);	 
	         isValidFloat = true;
	      }
	      catch (NumberFormatException e)
	      {
	    	  //do nothing
	      }
	 
	      return isValidFloat;
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

}
