package com.ernstvangraan.stockopedia.controller.process.math;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ernstvangraan.stockopedia.model.schema.ExpressionDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Infix {
	private Logger logger = LoggerFactory.getLogger(Infix.class);
	public String infixIterativelyOrReportMalformed(String expression) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return infixIteratively(mapper, expression);
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
			return "Error: MALFORMED";
		}
	}
	
	private String infixIteratively(ObjectMapper mapper, String expression) throws JsonParseException, JsonMappingException, IOException {
		ExpressionDTO exp = mapper.readValue(expression, ExpressionDTO.class);
		
		String anotherExpression = getValidEbmeddedExpression(exp.getA());
		String a = (anotherExpression == null ? exp.getA() : "(" + infixIterativelyOrReportMalformed(anotherExpression) + ")");

		anotherExpression = getValidEbmeddedExpression(exp.getB());
		String b = (anotherExpression == null ? exp.getB() : "(" + infixIterativelyOrReportMalformed(anotherExpression) + ")");
		
		return a + exp.getFn() + b;
	}

	private String getValidEbmeddedExpression(String value) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.readValue(value, ExpressionDTO.class);
			return value;
		} catch (Exception e) {
			return null;
		}
	}
}
