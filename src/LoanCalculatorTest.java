import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoanCalculatorTest {

	@Test
	void testGetInstallment() {
		assertEquals("$919.79", LoanCalculator.getInstallment(200000, 20, 1));
	}
	@Test
	void testGetInstallment2() {
		assertEquals("Please enter a % that is > 0 and <=100\n", LoanCalculator.getInstallment(400000, 40, 0));
	}
	@Test
	void testGetInstallment3() {
		assertEquals("Please enter a valid number for price.\n", LoanCalculator.getInstallment(0, 50, 1));
	}
	
	@Test
	void testGetInstallment4() {
		assertEquals("Please enter a payment period that is >= 1.\n", LoanCalculator.getInstallment(350000, -2, 1));
	}
	
	@Test
	void testGetInstallment5() {
		assertEquals("Please enter a % that is > 0 and <=100\n", LoanCalculator.getInstallment(400000, 40, 200));
	}
}
