package com.ernstvangraan.stockopedia.controller.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ernstvangraan.stockopedia.model.schema.QueryDTO;
import com.ernstvangraan.stockopedia.model.schema.ResultDTO;
import com.ernstvangraan.stockopedia.view.Constants;
import com.ernstvangraan.stockopedia.controller.process.interpolation.Interpolator;
import com.ernstvangraan.stockopedia.controller.process.strategy.IProcessStrategy;

@Component("queryProcessor")
public class QueryProcessor {	
	@Autowired
	Interpolator interpolator;
	
	@Autowired
	IProcessStrategy strategy;
	
	public ResultDTO process(QueryDTO query) {
		ResultDTO result = new ResultDTO();
		result.setQuery(query);
		String expression = interpolator.interpolate(strategy.buildExpression(query.getExpression()), query.getSecurity());
		setResultValue(result, expression);
		
		return result;
	}

	private boolean ableToEvaluate(String expression) {
		if ((expression == null) || expression.contains(Constants.MALFORMED)){
			return false;
		}
		return true;
	}
	
	private boolean isInError(String expression) {
		return expression.contains("Error");
	}
		
	private void setResultValue(ResultDTO result, String expression) {
		if (ableToEvaluate(expression) && !isInError(expression)) {
			result.setValue(strategy.evaluate(expression).toString());
		} else {
			SetResultFailure(result, expression);
		}
	}
	
	private void SetResultFailure(ResultDTO result, String expression) {
		if (isInError(expression)) {
			result.setValue(expression);
		} else {
		    result.setValue("Error: Could not evaluate the expression");
		}
	}
}
