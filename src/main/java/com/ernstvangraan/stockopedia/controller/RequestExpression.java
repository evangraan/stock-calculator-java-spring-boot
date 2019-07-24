package com.ernstvangraan.stockopedia.controller;

import org.springframework.web.bind.annotation.RestController;
import com.ernstvangraan.stockopedia.controller.process.QueryProcessor;
import com.ernstvangraan.stockopedia.model.schema.QueryDTO;
import com.ernstvangraan.stockopedia.view.IResponder;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RequestExpression {
	@Autowired
	QueryProcessor processor;
	
	@Autowired
	IResponder responder;
	
    @PostMapping("/")
    public String index(@Valid @RequestBody QueryDTO query) {
    	return responder.respondToQuery(processor.process(query));
    }
}
