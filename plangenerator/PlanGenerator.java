package com.levycandido.plangenerator;

import java.io.File;

import com.levycandido.plangenerator.model.Loan;
import com.levycandido.plangenerator.service.MonthPlanGenerator;
import com.levycandido.plangenerator.service.Plan;
import com.levycandido.plangenerator.service.PlanPrinter;
import com.levycandido.plangenerator.util.DialogParameter;

public class PlanGenerator {
	public static void main(String[] args) {
		DialogParameter dialogParameter = new DialogParameter();
		Loan loan = dialogParameter.getParameter();
		MonthPlanGenerator monthPlanGenerator = new MonthPlanGenerator(loan);
		Plan paymentPlan = monthPlanGenerator.getPaymentCal();
		PlanPrinter ppp = new PlanPrinter(paymentPlan);
		File file = dialogParameter.fileSave();
		ppp.printPaymentPlan(file);
	}

}