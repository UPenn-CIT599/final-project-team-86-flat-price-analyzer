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
	 * (in years) using the PMT formula
	 */
	
	public static String getInstallment(long price, double annualRate, int year) {
		
		//converts the interest rate to a float between 0 to 1
		
		double monthlyRate = (annualRate / 12) / 100;		
		
		//number of payments = num of years * num of months in a year
		int numOfPayments = 12 * year;
		
		double compoundInterest = Math.pow((1 + monthlyRate), numOfPayments);
		
		//implement PMT formula to calculate monthly interest
		double installment = price * ( monthlyRate * compoundInterest / (compoundInterest - 1)) ;
		
		return String.format("$%.2f", installment); 
	}
	
//	
//	public static void main(String[] args) {
//		System.out.println(LoanCalculator.getInstallment(200000, 2.6, 10));
//	}
	
}
