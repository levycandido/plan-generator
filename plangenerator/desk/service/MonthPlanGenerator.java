package com.levycandido.plangenerator.desk.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.levycandido.plangenerator.desk.model.Loan;

/**
 * @author levyc
 *
 */
public class MonthPlanGenerator {

	private Loan loan;
	private double annuity;

	public MonthPlanGenerator(Loan loan) {
		this.loan = loan;
		/**Calculate the annyity*/
		this.annuity = calculateAnnuity(loan.getNominalRate(), (double) loan.getLoanAmount(), loan.getDuration());
	}
    /**Calc the installments*/
	/**
	 * @return
	 */
	public Plan getPaymentCal() {
		Plan paymentPlan = new Plan();
		double initial = (double) loan.getLoanAmount();
		for (int duration = 0; duration < loan.getDuration(); duration++) {
			double interest = calculateInterest(initial, loan.getNominalRate());
			double principal = calculatePrincipal(initial, interest, this.annuity);
			if (principal > initial) {
				principal = initial;
			}
			double remaining = initial - principal;
			Payment payment = new Payment();
			payment.calcPaymentDate(loan.getStartDate(), duration);
			payment.setPaymentAmount(principal + interest);
			payment.setPrincipal(principal);
			payment.setInterest(interest);
			payment.setInitial(initial);
			payment.setRemaining(remaining);
			paymentPlan.addMonthlyPayment(payment);
			initial = remaining;
		}
		return paymentPlan;
	}
    /**Calc the Principal*/
	/**
	 * @param initial
	 * @param interest
	 * @param annuity
	 * @return
	 */
	private double calculatePrincipal(double initial, double interest, double annuity) {
		double principal = 0;
		if (interest > initial)
			principal = initial;
		else
			principal = annuity - interest;
		BigDecimal bd = new BigDecimal(principal).setScale(2, RoundingMode.HALF_EVEN);
		return principal;
	}
	
   /**Cal the Interest*/
	/**
	 * @param initialOutstandingPrinciple
	 * @param nominalRate
	 * @return
	 */
	private double calculateInterest(double initialOutstandingPrinciple, double nominalRate) {
		return nominalRate * 30 * initialOutstandingPrinciple / 360;
	}
    /**Calc the Annuity*/
	/**
	 * @param nominalRate
	 * @param loanAmount
	 * @param duration
	 * @return
	 */
	private double calculateAnnuity(double nominalRate, double loanAmount, int duration) {
		double annuity;
		double ratePerPeriod = (double) (nominalRate) / 12;
		annuity = (ratePerPeriod * loanAmount) / (1 - Math.pow(1 + ratePerPeriod, -duration));
		BigDecimal bd = new BigDecimal(annuity).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}

}