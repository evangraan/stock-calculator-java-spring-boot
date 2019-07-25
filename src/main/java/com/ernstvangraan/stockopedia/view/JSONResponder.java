package com.ernstvangraan.stockopedia.view;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import com.ernstvangraan.stockopedia.model.schema.QueryDTO;
import com.ernstvangraan.stockopedia.model.schema.ResultDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("jsonResponder")
public class JSONResponder implements IResponder{
	Logger logger = Logger.getLogger(JSONResponder.class);
	
	private String respondWithError(QueryDTO query) {
		ObjectMapper mapper = new ObjectMapper();
		ResultDTO error = new ResultDTO();
		error.setQuery(query);
		error.setValue(Constants.ERROR);
		try {
			return mapper.writeValueAsString(error);
		} catch (JsonProcessingException e) {
			logger.fatal(e.getMessage());
			return null;
		}
	}
	
	public String respondToQuery(ResultDTO result) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return respondWithError(result.getQuery());
		}
	}
}
