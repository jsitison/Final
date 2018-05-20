package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.Test;

import pkgCore.Retirement;

import org.apache.poi.ss.formula.functions.*;

public class TestFinance {

	@Test
	public void TestPV() {

		int iYearsToWork = 40;
		double dAnnualReturnWorking = 7;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 2;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;

		double PV = Retirement.PV((dAnnualReturnRetired / 100.0) / 12.0, iYearsRetired * 12.0,
				dRequiredIncome - dMonthlySSI, 0, false);

		System.out.println(PV);

		// In my calculations, in order to receive a payment of $7358 ($10000-2642), if
		// you were making 2% on your return, and you wanted it paid off
		// over a 20 year period... You'd need to save $1,454,485.55.

		// Accounting rules- PV returns a negative number if you pass in a positive PMT
		// amount. Take the absolute value

		// I want to compare a double with a double... Doubles are not precise... I have
		// to give a rounding factor.
		// Note the third argument. That says only compare the double values to the
		// hundredth place.
		assertEquals(1454485.55, Math.abs(PV), 0.01);

		Retirement retirement = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired, dAnnualReturnRetired,
				dRequiredIncome, dMonthlySSI);

		double totalAmountToSave = retirement.TotalAmountToSave();

		System.out.println(totalAmountToSave);

		assertEquals(1454485.55, Math.abs(totalAmountToSave), 0.01);

	}

	@Test
	public void TestPMT() {
		// Test PMT. Make sure PMT works as expected.

		int iYearsToWork = 40;
		double dAnnualReturnWorking = 7;
		int iYearsRetired = 20;
		double dAnnualReturnRetired = 2;
		double dRequiredIncome = 10000;
		double dMonthlySSI = 2642;

		Double FV = Retirement.PV((dAnnualReturnRetired / 100.0) / 12.0, iYearsRetired * 12.0,
				dRequiredIncome - dMonthlySSI, 0, false);

		double PMT = Retirement.PMT((dAnnualReturnWorking / 100.0) / 12.0, iYearsToWork * 12.0, 0, FV, false);

		System.out.println(PMT);

		assertEquals(554.13, Math.abs(PMT), 0.01);

		Retirement retirement = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired, dAnnualReturnRetired,
				dRequiredIncome, dMonthlySSI);

		double monthlySavings = retirement.MonthlySavings();

		System.out.println(monthlySavings);

		assertEquals(554.13, Math.abs(monthlySavings), 0.01);

	}
}
