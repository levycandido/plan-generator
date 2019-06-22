package com.levycandido.plangenerator.api;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.levycandido.plangenerator.dao.LoanDto;
import com.levycandido.plangenerator.model.Loan;
import com.levycandido.plangenerator.service.MonthPlanGenerator;
import com.levycandido.plangenerator.service.Plan;
import com.levycandido.plangenerator.util.LoanValidator;
import com.levycandido.plangenerator.util.PlanJson;


@Controller
public class ServiceController {

	private final String url = "/generate-plan";
	
	@PostMapping(url)
	@ResponseBody
	public Object sendPaymentPlan(
			@RequestBody LoanDto ld)
			 {
		
		LoanValidator loanValidator = new LoanValidator(ld.getLoanAmount(), ld.getNominalRate(), ld.getDuration(), ld.getStartDate());
		if (!loanValidator.validate()) {
			return null;
		}
		Loan loan = new Loan(ld.getLoanAmount(), ld.getNominalRate()*0.01, ld.getDuration(), ld.getStartDate());
		MonthPlanGenerator monthPlanGenerator = new MonthPlanGenerator(loan);
		Plan pp = monthPlanGenerator.getPaymentCal();
		PlanJson planJson = new PlanJson(pp);
		Object jsonPaymentPlan = planJson.getJsonPaymentPlan();
		return jsonPaymentPlan;
		
	}
}