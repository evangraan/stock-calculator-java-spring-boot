package com.ernstvangraan.stockopedia.model.schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ResultDTO {
    @NotNull(message = "System error: query is null")
	private QueryDTO query;
    @NotBlank(message = "System error: value is blank")
	private String value;
	
	public QueryDTO getQuery() {
		return query;
	}
	
	public void setQuery(QueryDTO query) {
		this.query = query;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
