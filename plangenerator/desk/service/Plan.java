package com.levycandido.plangenerator.desk.service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author levyc
 *
 */
public class Plan {

	private List<Payment> payments = new ArrayList<Payment>();

	public void addMonthlyPayment(Payment payment) {
		payments.add(payment);
	}

	public Payment getPaymentForMonth(int monthNum) {
		return payments.get(monthNum - 1);
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Payment mpd : payments) {
			sb.append(mpd);
			sb.append("\n");
		}

		return sb.toString();
	}



}