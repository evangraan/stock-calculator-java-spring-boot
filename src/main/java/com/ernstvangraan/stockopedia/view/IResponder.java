package com.ernstvangraan.stockopedia.view;

import com.ernstvangraan.stockopedia.model.schema.ResultDTO;

public interface IResponder {
	public String respondToQuery(ResultDTO result);
}
