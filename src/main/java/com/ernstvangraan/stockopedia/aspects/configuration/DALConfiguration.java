package com.ernstvangraan.stockopedia.aspects.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Configuration
@PropertySource("classpath:dalconfiguration.properties")
@ConfigurationProperties(prefix = "dal")
@Component("dalConfiguration")
@Validated
public class DALConfiguration {
	@Valid
	@NotBlank(message = "securities data source is mandatory. Please add the dal.securities field in dalconfiguration.properties")
    private String securities;
	@Valid
	@NotBlank(message = "attributes data source is mandatory. Please add the dal.attributes fieldin dalconfiguration.properties")
    private String attributes;
	@Valid
	@NotBlank(message = "facts data source is mandatory. Please add the dal.facts fieldin dalconfiguration.properties")
    private String facts;
    
	public String getSecurities() {
		return securities;
	}
	
	public void setSecurities(String securities) {
		this.securities = securities;
	}
	
	public String getAttributes() {
		return attributes;
	}
	
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	public String getFacts() {
		return facts;
	}
	
	public void setFacts(String facts) {
		this.facts = facts;
	}
}
