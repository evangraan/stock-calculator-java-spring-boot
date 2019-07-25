package com.ernstvangraan.stockopedia.controller.process.strategy;

public interface IProcessStrategy {
	public String buildExpression(String expression);
	public Float evaluate(String expression);
}
