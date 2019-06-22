package com.levycandido.plangenerator.desk.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author levyc
 *
 */
public class Loan {

	private int loanAmount;
	private double nominalRate;
	private int duration;
	private Date startDate;

	
	public Loan(String loanAmount, String nominalRate, String duration, String startDate) throws ParseException {
		super();
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");

		this.loanAmount = Integer.valueOf(loanAmount);
		this.nominalRate = Integer.valueOf(nominalRate);
		this.duration = Integer.valueOf(duration);
		this.startDate = f.parse(startDate);
	}

	public Loan(int loanAmount, double nominalRate, int duration, String dateStr) throws ParseException {
		super();

		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");

		this.loanAmount = loanAmount;
		this.nominalRate = nominalRate;
		this.duration = duration;
		this.startDate = f.parse(dateStr);
	}

	public Loan(int loanAmount, double nominalRate, int duration, Date startDate) {
		super();
		this.loanAmount = loanAmount;
		this.nominalRate = nominalRate;
		this.duration = duration;
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Loan [loanAmount=" + loanAmount + ", nominalRate=" + nominalRate + "%, duration=" + duration
				+ " months, startDate=" + startDate + "]";
	}

	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		result = prime * result + loanAmount;
		long temp;
		temp = Double.doubleToLongBits(nominalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (duration != other.duration)
			return false;
		if (loanAmount != other.loanAmount)
			return false;
		if (Double.doubleToLongBits(nominalRate) != Double.doubleToLongBits(other.nominalRate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	

}
