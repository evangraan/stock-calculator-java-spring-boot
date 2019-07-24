package com.ernstvangraan.stockopedia.model.schema;

import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class AttributesDTO {
    @NotBlank(message = "Data integrity violation: id is blank")
    @NumberFormat(style = Style.NUMBER, pattern = "#")
    private int id;
    @NotBlank(message = "Data integrity violation: name is blank")
    private String name;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	} 
}
