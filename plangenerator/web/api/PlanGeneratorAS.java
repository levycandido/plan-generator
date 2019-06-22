package com.levycandido.plangenerator.web.api;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.levycandido.plangenerator.desk.dao.LoanDto;
import com.levycandido.plangenerator.desk.model.Loan;
import com.levycandido.plangenerator.desk.service.MonthPlanGenerator;
import com.levycandido.plangenerator.desk.service.Plan;
import com.levycandido.plangenerator.desk.util.LoanValidator;
import com.levycandido.plangenerator.desk.util.PlanJson;



/**
 * @author levyc
 *
 */
public class PlanGeneratorAS {

	public Object getJsonPaymentPlan(LoanDto loanDto) {
		LoanValidator loanValidator = new LoanValidator(loanDto.getLoanAmount(), loanDto.getNominalRate(), loanDto.getDuration(), loanDto.getStartDate());
		if (!loanValidator.validate()) {
			return null;
		}
		Loan loan = new Loan(loanDto.getLoanAmount(), loanDto.getNominalRate()*0.01, loanDto.getDuration(), loanDto.getStartDate());
		MonthPlanGenerator monthPlanGenerator = new MonthPlanGenerator(loan);
		Plan pp = monthPlanGenerator.getPaymentCal();
		PlanJson planJson = new PlanJson(pp);
		Object jsonPayment = planJson.getJsonPaymentPlan();
		return jsonPayment;
	}
}