package com.levycandido.plangenerator.desk;

import java.io.File;

import com.levycandido.plangenerator.desk.model.Loan;
import com.levycandido.plangenerator.desk.service.MonthPlanGenerator;
import com.levycandido.plangenerator.desk.service.Plan;
import com.levycandido.plangenerator.desk.service.PlanPrinter;
import com.levycandido.plangenerator.desk.util.DialogParameter;

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