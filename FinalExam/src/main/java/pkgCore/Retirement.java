package pkgCore;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Retirement {

	private int iYearsToWork;
	private double dAnnualReturnWorking;
	private int iYearsRetired;
	private double dAnnualReturnRetired;
	private double dRequiredIncome;
	private double dMonthlySSI;

	public Retirement(int iYearsToWork, double dAnnualReturnWorking, int iYearsRetired, double dAnnualReturnRetired,
			double dRequiredIncome, double dMonthlySSI) {
		super();
		this.iYearsToWork = iYearsToWork;
		this.dAnnualReturnWorking = dAnnualReturnWorking;
		this.iYearsRetired = iYearsRetired;
		this.dAnnualReturnRetired = dAnnualReturnRetired;
		this.dRequiredIncome = dRequiredIncome;
		this.dMonthlySSI = dMonthlySSI;
	}

	public double MonthlySavings() {

		// Calculate AmountToSave

		double r = (dAnnualReturnWorking / 100.0) / 12.0;
		double n = iYearsToWork * 12.0;
		double p = 0.0;
		double f = this.TotalAmountToSave();
		boolean t = false;

		double pmt = PMT(r, n, p, f, t);
		pmt = Math.round(pmt * 100.0) / 100.0;
		return pmt;
	}

	public double TotalAmountToSave() {

		// Calculate the Total Amount Required to save

		double r = (dAnnualReturnRetired / 100.0) / 12.0;
		double n = iYearsRetired * 12.0;
		double y = dRequiredIncome - dMonthlySSI;
		double f = 0.0;
		boolean t = false;

		double pv = PV(r, n, y, f, t);
		pv = Math.round(pv * 100.0) / 100.0;
		return pv;
	}

	public static double PMT(double r, double n, double p, double f, boolean t) {
		// r = Rate
		// n = number of payments
		// p = present value
		// f = future value
		// t = boolean... when interest is calculated... we're going to use FALSE
		return FinanceLib.pmt(r, n, p, f, t);
	}

	public static double PV(double r, double n, double y, double f, boolean t) {
		// r = Rate. 7% would be expressed as... 0.07 / 12
		// n = Number of payments. Five years would be expressed as... 5 * 12... or 60
		// y = PMT amount
		// f = Future value
		// t = boolean... when interest is calculated... we're going to use FALSE
		return FinanceLib.pv(r, n, y, f, t);
	}

	public int getiYearsToWork() {
		return iYearsToWork;
	}

	public void setiYearsToWork(int iYearsToWork) {
		this.iYearsToWork = iYearsToWork;
	}

	public double getdAnnualReturnWorking() {
		return dAnnualReturnWorking;
	}

	public void setdAnnualReturnWorking(double dAnnualReturnWorking) {
		this.dAnnualReturnWorking = dAnnualReturnWorking;
	}

	public int getiYearsRetired() {
		return iYearsRetired;
	}

	public void setiYearsRetired(int iYearsRetired) {
		this.iYearsRetired = iYearsRetired;
	}

	public double getdAnnualReturnRetired() {
		return dAnnualReturnRetired;
	}

	public void setdAnnualReturnRetired(double dAnnualReturnRetired) {
		this.dAnnualReturnRetired = dAnnualReturnRetired;
	}

	public double getdRequiredIncome() {
		return dRequiredIncome;
	}

	public void setdRequiredIncome(double dRequiredIncome) {
		this.dRequiredIncome = dRequiredIncome;
	}

	public double getdMonthlySSI() {
		return dMonthlySSI;
	}

	public void setdMonthlySSI(double dMonthlySSI) {
		this.dMonthlySSI = dMonthlySSI;
	}
}
