package com.ernstvangraan.stockopedia.model.schema;

import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class SecuritiesDTO {
    @NotBlank(message = "Data integrity violation: id is blank")
    @NumberFormat(style = Style.NUMBER, pattern = "#")
	private int id;
    @NotBlank(message = "Data integrity violation: symbol is blank")
	private String symbol;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
