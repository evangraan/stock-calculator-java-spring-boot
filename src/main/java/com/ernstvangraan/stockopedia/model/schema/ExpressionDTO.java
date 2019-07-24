package com.ernstvangraan.stockopedia.model.schema;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ExpressionDTO {
    @NotBlank(message = "Operator is mandatory. Please provide an operator in the 'fn' field. Can be one of '+', '-', '*', '/', 'fn'")
	private String fn;
    @NotBlank(message = "First value is mandatory. Please provice a value in the 'a' field. Can be a number, attribute name or 'fn'")
	private Object a;
    @NotBlank(message = "Second value is mandatory. Please provice a value in the 'b' field. Can be a number, attribute name or 'fn'")
	private Object b;
    
    public String getFn() {
    	return fn;
    }
    
    public void setFn(String fn) {
    	this.fn = fn;
    }
    
    public String getA() throws JsonProcessingException {
    	if (a instanceof String) {
    		return (String)a;
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsString(b);

    }
    
    public void setA(Object a) {
    	this.a = a;
    }
    
    public String getB() throws JsonProcessingException {
    	if (b instanceof String) {
    		return (String)b;
    	}
    	ObjectMapper mapper = new ObjectMapper();
    	return mapper.writeValueAsString(b);
    }
    
    public void setB(Object b) {
    	this.b = b;
    }
}
