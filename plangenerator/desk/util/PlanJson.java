package com.levycandido.plangenerator.desk.util;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.levycandido.plangenerator.desk.service.Payment;
import com.levycandido.plangenerator.desk.service.Plan;


public class PlanJson {

	Plan paymentPlan;
	List<PaymentJson> jsonList = new ArrayList<>();
	
	static class PaymentJson{
		public String paymentAmount;
		public Date date;
		public String initial;
		public String interest;
		public String principal;
		public String remaining;
		@Override
		public String toString() {
			return "PaymentJson [borrowerPaymentAmount=" + paymentAmount + ", date=" + date
					+ ", initialOutstandingPrinciple=" + initial + ", interest=" + interest
					+ ", principal=" + principal + ", remainingOutstandingPrinciple=" + remaining
					+ "]";
		}
	}
	
	public PlanJson( Plan paymentPlan ){
		this.paymentPlan = paymentPlan;
	}
	
	public Object getJsonPaymentPlan() {
		List<Payment> paymentsList = paymentPlan.getPayments();
		for( Payment payment : paymentsList ) {
			PaymentJson paymentJson = convertToJson(payment);
			jsonList.add(paymentJson);
		}
		return jsonList;
	}
	
	private PaymentJson convertToJson(Payment mpd ) {
		PaymentJson mpdJson = new PaymentJson();
        DecimalFormat df = new DecimalFormat("#.##");
		mpdJson.paymentAmount = df.format(mpd.getPaymentAmount());
		mpdJson.date = mpd.getPaymentDate();
		mpdJson.initial = df.format(mpd.getInitial());
		mpdJson.interest = df.format(mpd.getInterest());
		mpdJson.principal = df.format( mpd.getPrincipal() );
		mpdJson.remaining = df.format( Math.abs(mpd.getRemaining()) );
		return mpdJson;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for( PaymentJson mpdJson : jsonList ) {
			sb.append(mpdJson.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}