package com.ernstvangraan.stockopedia.controller.process.strategy;

import org.springframework.stereotype.Component;
import com.ernstvangraan.stockopedia.controller.process.math.Dijkstra;
import com.ernstvangraan.stockopedia.controller.process.math.Infix;

@Component("infixPostfixStrategy")
public class InfixPostfixStrategy implements IProcessStrategy {
	@Override
	public String buildExpression(String expression) {
		Infix infix = new Infix();
		return infix.infixIterativelyOrReportMalformed(expression);
	}

	@Override
	public Float evaluate(String expression) {
		Dijkstra dijkstra = new Dijkstra();
		return dijkstra.evaluateShuntingYardPostFix(expression);
	}
}
