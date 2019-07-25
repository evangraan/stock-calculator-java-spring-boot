package com.ernstvangraan.stockopedia.model.orm;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.ernstvangraan.stockopedia.model.schema.SecuritiesDTO;
import com.ernstvangraan.stockopedia.aspects.configuration.DALConfiguration;
import com.ernstvangraan.stockopedia.model.orm.entity.Security;
import com.ernstvangraan.stockopedia.model.orm.entity.SecurityFactory;
import com.ernstvangraan.stockopedia.model.orm.loader.IDataLoader;
import com.ernstvangraan.stockopedia.model.schema.AttributesDTO;
import com.ernstvangraan.stockopedia.model.schema.FactsDTO;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("dataAbstractionLayer")
public class DAL {
	private Logger logger = LoggerFactory.getLogger(DAL.class);
	
	@Autowired
	private DALConfiguration configuration;

	@Autowired
	private IDataLoader loader;

	@Autowired
	private SecurityFactory factory;

	private List<SecuritiesDTO> securities;
	private List<AttributesDTO> attributes;
	private List<FactsDTO> facts;
	private boolean loaded = false;
			
	private void loadDataOrDefer() {
		try {
			loadData();
		} catch (Exception e) {
			logger.error(e.getMessage());
			loaded = false;
		}
	}
		
	private void loadData() {
		securities = loader.loadOrEmpty(SecuritiesDTO.class, configuration.getSecurities());
		attributes = loader.loadOrEmpty(AttributesDTO.class, configuration.getAttributes());
		facts = loader.loadOrEmpty(FactsDTO.class, configuration.getFacts());
		loaded = true;
	}
	
	private void lazyLoad() {
		if (!loaded) {
			loadDataOrDefer();
		}
	}

	public Security buildSecurity(String symbol) {
		lazyLoad();
		return factory.buildSecurity(securities, attributes, facts, symbol);
	}

	public List<SecuritiesDTO> getSecurities() {
		lazyLoad();
		return securities;
	}

	public List<AttributesDTO> getAttributes() {
		lazyLoad();
		return attributes;
	}

	public List<FactsDTO> getFacts() {
		lazyLoad();
		return facts;
	}
	
	public DALConfiguration getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(DALConfiguration configuration) {
		this.configuration = configuration;
	}
}
