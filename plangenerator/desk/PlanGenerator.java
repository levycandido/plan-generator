package com.levycandido.plangenerator.desk;

import java.io.File;

import com.levycandido.plangenerator.desk.model.Loan;
import com.levycandido.plangenerator.desk.service.MonthPlanGenerator;
import com.levycandido.plangenerator.desk.service.Plan;
import com.levycandido.plangenerator.desk.service.PlanPrinter;
import com.levycandido.plangenerator.desk.util.DialogParameter;

public class PlanGenerator {
	public static void main(String[] args) {
		/**Getting the Loan the Parameters*/
		DialogParameter dialogParameter = new DialogParameter();
		/**Open the Dialog*/
		Loan loan = dialogParameter.getParameter();
		MonthPlanGenerator monthPlanGenerator = new MonthPlanGenerator(loan);
		/**Generate the PlanLoan with the user Information*/
		Plan paymentPlan = monthPlanGenerator.getPaymentCal();
		PlanPrinter ppp = new PlanPrinter(paymentPlan);
		/**Creat the CSV File*/
		File file = dialogParameter.fileSave();
		/**Export the File*/
		ppp.printPaymentPlan(file);
	}

}