package com.levycandido.plangenerator.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Payment {
	private double paymentAmount;
	private double principal;
	private double interest;
	private Date paymentDate;
	private double initial;
	private double remaining;

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getInitial() {
		return initial;
	}

	public void setInitial(double initial) {
		this.initial = initial;
	}

	public double getRemaining() {
		return remaining;
	}

	public void setRemaining(double remaining) {
		this.remaining = remaining;
	}

	public void calcPaymentDate(Date startDate, int monthNum) {
		LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		startLocalDate = startLocalDate.plusMonths(monthNum);
		Date date = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.paymentDate = date;
	}

//	public boolean equals(Object o) {
//		if (o == this) {
//			return true;
//		}
//		if (!(o instanceof Payment)) {
//			return false;
//		}
//		Payment p = (Payment) o;
//		if (!p.getPaymentDate().equals(this.paymentDate))
//			return false;
//		if (p.getPaymentAmount() - this.paymentAmount >= 0.01 )
//			return false;
//		if (p.getPrincipal() - this.principal >= 0.01 )
//			return false;
//		if (p.getInterest() - this.interest >= 0.01 )
//			return false;
//		if (p.getInitial() - this.initial >= 0.01 )
//			return false;
//		if (p.getRemaining() - this.remaining >= 0.01 )
//			return false;
//		return true;
//	}
//	
	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
		;
		DecimalFormat df = new DecimalFormat("#.##");
		return "MonthlyPaymentDetail " + "[" + "paymentDate=" + f.format(paymentDate) + "| " + "paymentAmount="
				+ df.format(paymentAmount) + "| " + "principal=" + df.format(principal) + "| " + "interest="
				+ df.format(interest) + "| " + "initial=" + df.format(initial) + "| " + "remaining="
				+ df.format(Math.abs(remaining)) + "]";
	}
}
