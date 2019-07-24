package com.ernstvangraan.stockopedia.controller.process.math;

import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dijkstra {
	private Logger logger = LoggerFactory.getLogger(Dijkstra.class);
    private Stack<Float> values = new Stack<Float>();
    private Stack<Character> operators = new Stack<Character>();
	private Basics basics = new Basics();

	public Float evaluateShuntingYardPostFix(String expression) {
		logger.info("Using: https://en.wikipedia.org/wiki/Shunting-yard_algorithm");
        char[] tokens = expression.toCharArray();
        
        convertInfixToPostFix(tokens);
        return evaluatePostFix();
	}	
	
	public void convertInfixToPostFix(char[] tokens) {
        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] == ' ')
                continue;

            i = parseNextToken(tokens, i);
        }		
	}
	
	public Float evaluatePostFix() {
        while (!operators.empty())
            values.push(basics.operate(operators.pop(), values.pop(), values.pop()));
  
    	return values.pop();
	}
	
	private int parseNextToken(char[] tokens, int i) {
        if (basics.isPartOfAFloat(tokens[i]))
        	i = buildFloat(tokens, i);
        else if (tokens[i] == '(')
            operators.push(tokens[i]);
        else if (tokens[i] == ')')
        	popParenthesisStack();
        else if (basics.isAnOperator(tokens[i]))
        	pushOperatorStack(tokens[i]);
        
        return i;
	}
	
	private int buildFloat(char[] tokens, int i) {
        StringBuffer sbuf = new StringBuffer();
        while (i < tokens.length && basics.isPartOfAFloat(tokens[i]))
            sbuf.append(tokens[i++]);
        values.push(Float.parseFloat(sbuf.toString()));
        if ((i < tokens.length) && (!basics.isPartOfAFloat(tokens[i])))
        	i--;
        return i;		
	}
	
	private void popParenthesisStack() {
        while (operators.peek() != '(')
            values.push(basics.operate(operators.pop(), values.pop(), values.pop()));
        operators.pop();
	}
	
	private void pushOperatorStack(char token) {
        while (!operators.empty() && basics.operator2HasHigherPrecedence(token, operators.peek()))
            values.push(basics.operate(operators.pop(), values.pop(), values.pop()));

          operators.push(token);
	}
}
