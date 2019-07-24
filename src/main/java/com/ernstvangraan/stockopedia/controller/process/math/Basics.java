package com.ernstvangraan.stockopedia.controller.process.math;

public class Basics {
	public float operate(char operator, float b, float a)
	{
	    switch (operator)
	    {
	    case '+':
	        return a + b;
	    case '-':
	        return a - b;
	    case '*':
	        return a * b;
	    case '/':
	        if (b == 0)
	            throw new UnsupportedOperationException("Error: Division by zero");
	        return a / b;
	    }
	    return 0;
	}
	
	public boolean operator2HasHigherPrecedence(char operator1, char operator2)
	{
	    if (operator2 == '(' || operator2 == ')')
	        return false;
	    if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
	        return false;
	    else
	        return true;
	}	
	
	public boolean isPartOfAFloat(char value) {
		return (value >= '0' && value <= '9') || (value == '.');
	}
	
	public boolean isAnOperator(char value) {
		return value == '+' || value == '-' || value == '*' || value == '/';
	}
}
