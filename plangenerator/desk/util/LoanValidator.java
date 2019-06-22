package com.levycandido.plangenerator.desk.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author levyc
 *
 */
/**
 * @author levyc
 *
 */
public class LoanValidator {
	boolean valid;
	String loanAmount;
	String nominalRate;
	String duration;
	String startDate;
	Date dateStart;
	String error;

	public LoanValidator(String loanAmount, String nominalRate, String duration, String startDate) {
		this.loanAmount = loanAmount;
		this.nominalRate = nominalRate;
		this.duration = duration;
		this.startDate = startDate;
	}
	
	public LoanValidator(int loanAmount, double nominalRate, int duration, Date startDate) {
		this.loanAmount = String.valueOf(loanAmount);
		this.nominalRate = String.valueOf(nominalRate);
		this.duration = String.valueOf(duration);
		this.startDate = startDate.toString();
	}

	/** Valid all Field to continue the process*/
	/**
	 * @return
	 */
	public boolean validate() {
		if (!loanAmount.matches("[0-9]+")) {
			this.error = "Loan amount must be a valid Number: " + loanAmount;
			this.valid = false;
			return this.valid;
		}
		if (Integer.valueOf(loanAmount) <= 0) {
			this.error = "Loan amount must be a positive Number: " + loanAmount;
			this.valid = false;
			return this.valid;
		}
		if ((!nominalRate.matches("[0-9].+")) && (!nominalRate.matches("[0-9]+"))) {
			this.error = "Rate must be a valid Number: " + nominalRate;
			this.valid = false;
			return this.valid;
		}
		if (Double.valueOf(nominalRate) <= 0) {
			this.error = "Rate must must be a positive Number: " + nominalRate;
			this.valid = false;
			return this.valid;
		}
		if (!duration.matches("[0-9]+")) {
			this.error = "Duration must be a valid Number: " + duration;
			this.valid = false;
			return this.valid;
		}
		if (Integer.valueOf(duration) <= 0) {
			this.error = "Duration must must be a positive Number: " + duration;
			this.valid = false;
			return this.valid;
		}
		DateFormat df;
		/** Check if the date come from Web or Desktop*/
		if (startDate.length() > 10) {
			df = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
		} else {
			df = new SimpleDateFormat("dd.MM.yyyy");
			if (!startDate.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}")) {
				this.error = "Start Date must be a valid Date: " + startDate;
				this.valid = false;
				return this.valid;
			}
		}
		try {
			df.parse(startDate);
		} catch (ParseException e) {
			this.error = "Start Date must be a valid Date : " + startDate;
			this.valid = false;
			return this.valid;
		}
		this.valid = true;
		return this.valid;
	}
	
	public String getErrorMsg() {
		return error;
	}

}