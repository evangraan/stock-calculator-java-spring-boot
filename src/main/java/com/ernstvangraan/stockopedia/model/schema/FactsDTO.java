package com.ernstvangraan.stockopedia.model.schema;

import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class FactsDTO {
    @NotBlank(message = "Data integrity violation: security_id is blank")
    @NumberFormat(style = Style.NUMBER, pattern = "#")
	private int security_id;
    @NotBlank(message = "Data integrity violation: attribute_id is blank")
    @NumberFormat(style = Style.NUMBER, pattern = "#")
	private int attribute_id;
    @NotBlank(message = "Data integrity violation: value is blank")
    @NumberFormat(style = Style.NUMBER)
	private float value;
	
	public int getSecurity_id() {
		return security_id;
	}
	
	public void setSecurity_id(int security_id) {
		this.security_id = security_id;
	}
	
	public int getAttribute_id() {
		return attribute_id;
	}
	
	public void setAttribute_id(int attribute_id) {
		this.attribute_id = attribute_id;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
}
