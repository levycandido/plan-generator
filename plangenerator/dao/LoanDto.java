package com.levycandido.plangenerator.dao;

import java.io.Serializable;
import java.util.Date;

public class LoanDto implements Serializable {

	private static final long serialVersionUID = 6057740672261101971L;
	private int loanAmount;
	private double nominalRate;
	private int duration;
	private Date startDate;

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getNominalRate() {
		return nominalRate;
	}

	public void setNominalRate(double nominalRate) {
		this.nominalRate = nominalRate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
