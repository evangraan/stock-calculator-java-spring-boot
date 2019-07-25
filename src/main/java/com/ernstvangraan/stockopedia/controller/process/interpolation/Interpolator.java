package com.ernstvangraan.stockopedia.controller.process.interpolation;

import java.util.Map.Entry;

import javax.naming.NameNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ernstvangraan.stockopedia.model.orm.DAL;
import com.ernstvangraan.stockopedia.model.orm.entity.Security;

@Component("interpolator")
public class Interpolator implements IInterpolator {
	@Autowired
	private DAL dal;
	
	public String interpolate(String expression, String security) {
		try {
			return InterpolateEntityAttributes(expression, security);
		} catch (NameNotFoundException e) {
			return "Error: could not find: [" + security + "]";
		} catch (Exception e) {
			return "Error: could not build security entity using: [" + security + "]";
		}
	}
	
	public String InterpolateEntityAttributes(String expression, String security) throws NameNotFoundException {
		Security relationalEntity = dal.buildSecurity(security);
		
		if (relationalEntity.getId() == -1)
			throw new NameNotFoundException("Security [" + security + "] not found");
		
		for (Entry<String, Float> entry : relationalEntity.getAttributes().entrySet()) {
		    expression = expression.replace(entry.getKey(), String.format("%.6f", entry.getValue()));
		}
		
		return expression;
	}
}
