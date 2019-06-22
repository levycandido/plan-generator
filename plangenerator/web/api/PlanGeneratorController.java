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
@Controller
public class PlanGeneratorController {

	private final String url = "/generate-plan";
	
	@PostMapping(url)
	@ResponseBody
	public Object sendPaymentPlan(
			@RequestBody LoanDto loanDto) {
		/** The Controller call the AS class to process the business logic*/
		PlanGeneratorAS planGeneratorAs = new PlanGeneratorAS();
		/**the AS method retorn the payments to send the result as JSON*/
		Object jsonPaymentPlan = planGeneratorAs.getJsonPaymentPlan(loanDto);
		return jsonPaymentPlan;
		
	}
}