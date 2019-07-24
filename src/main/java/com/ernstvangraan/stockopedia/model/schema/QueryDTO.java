package com.ernstvangraan.stockopedia.model.schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class QueryDTO {
    @NotBlank(message = "Please provide a valid security. 3 Letters only.")
    @Pattern(regexp="^[A-Za-z]{3}$")
	private String security;
    @NotBlank(message = "Please provide a valid expression.")
	private String expression;
	
	public String getSecurity() {
		return security;
	}
	
	public void setSecurity(String security) {
		this.security = security;
	}
	
	public String getExpression() {
		return expression;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String toString() {
		return "This query has: security: [" + security + "] , expression: [" + expression + "]";
	}
}
