package com.ernstvangraan.stockopedia.controller.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ernstvangraan.stockopedia.model.schema.QueryDTO;
import com.ernstvangraan.stockopedia.model.schema.ResultDTO;
import com.ernstvangraan.stockopedia.controller.process.interpolation.Interpolator;
import com.ernstvangraan.stockopedia.controller.process.math.Dijkstra;
import com.ernstvangraan.stockopedia.controller.process.math.Infix;

@Component("queryProcessor")
public class QueryProcessor {
	@Autowired
	Interpolator interpolator;
	
	private boolean ableToEvaluate(String expression) {
		if ((expression == null) || expression.contains("MALFORMED")){
			return false;
		}
		return true;
	}
	
	private boolean isInError(String expression) {
		return expression.contains("Error");
	}
	
	public ResultDTO process(QueryDTO query) {
		Infix infix = new Infix();
		ResultDTO result = new ResultDTO();
		result.setQuery(query);
		String expression = interpolator.interpolate(infix.infixIterativelyOrReportMalformed(query.getExpression()), query.getSecurity());
		setResultValue(expression, result);
		
		return result;
	}
	
	private void setResultValue(String expression, ResultDTO result) {
		Dijkstra dijkstra = new Dijkstra();
		if (ableToEvaluate(expression) && !isInError(expression)) {
			result.setValue(dijkstra.evaluateShuntingYardPostFix(expression).toString());
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
