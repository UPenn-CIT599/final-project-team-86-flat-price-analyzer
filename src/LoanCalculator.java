import java.math.*;

/**
 * This LoanCalculator class computes the monthly installment for the user
 */

public class LoanCalculator {

	long price;
	double annualRate;
	int year;
	
	/**
	 * This class calculates the average monthly mortgage installment  
	 * given a flat price, interest rate (in percent) and the repayment period 
	 * (in years) using the PMT formula.
	 * 	 * 
	 */
	
	public static String getInstallment(long price, int year, double annualRate) {
		double monthlyRate;
		int numOfPayments;
		double compoundInterest;
		double installment;

		//checks if the price entered is valid
		if (price <= 0) {
			return "Please enter a valid number for price.\n";
		}
		
		//checks if the year entered is valid
		if (year < 1) {
			return "Please enter a payment period that is >= 1.\n";
		}
		
		else {
		//number of payments = num of years * num of months in a year
		numOfPayments = 12 * year;
		}				
		
		//checks if the annualRate entered is valid
		if ((annualRate <= 0) || (annualRate >= 100)) {
			return "Please enter a % that is > 0 and <=100\n";
		}
		//converts the interest rate to a float between 0 to 1
		
		else { 
		 monthlyRate = (annualRate / 12) / 100;		
		}
		
		compoundInterest = Math.pow((1 + monthlyRate), numOfPayments);
		
		//implement PMT formula to calculate monthly interest
		installment = price * ( monthlyRate * compoundInterest / (compoundInterest - 1)) ;
		
		return String.format("$%.2f", installment); 
	}
	
//	public static void main(String[] args) {
//		System.out.println(LoanCalculator.getInstallment(200000, 20, 1));
//	}
//	
}
