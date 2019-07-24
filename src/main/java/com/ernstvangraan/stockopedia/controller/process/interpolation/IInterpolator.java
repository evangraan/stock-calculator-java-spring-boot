package com.ernstvangraan.stockopedia.controller.process.interpolation;

import javax.naming.NameNotFoundException;

public interface IInterpolator {
	public String interpolate(String expression, String security);
	public String InterpolateEntityAttributes(String expression, String security) throws NameNotFoundException;
}
