package com.ernstvangraan.stockopedia.model.orm;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.ernstvangraan.stockopedia.model.schema.SecuritiesDTO;
import com.ernstvangraan.stockopedia.aspects.configuration.DALConfiguration;
import com.ernstvangraan.stockopedia.model.schema.AttributesDTO;
import com.ernstvangraan.stockopedia.model.schema.FactsDTO;
import java.io.File;
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
	Logger logger = LoggerFactory.getLogger(DAL.class);
	
	@Autowired
	private DALConfiguration configuration;
	
	private List<SecuritiesDTO> securities;
	private List<AttributesDTO> attributes;
	private List<FactsDTO> facts;
	private boolean loaded = false;
	
	private String getAttributeName(int id) {
		AttributesDTO dto = attributes.stream().filter(a -> id == a.getId())
				  .findFirst()
				  .orElse(null);
				if (dto == null) {
					logger.error("Attribute not found: [" + id + "]");
					return null;
				}
		return dto.getName();
	}
	
	public Security buildSecurity(String symbol) {
		lazyLoad();
		Security security = new Security();
		security.setSymbol(symbol);
		addSecurityIdIfFound(security, symbol);
		addSecurityAttributes(security);
		
		return security;
	}
	
	private void addSecurityIdIfFound(Security security, String symbol) {
		SecuritiesDTO dto = securities.stream().filter(s -> symbol.equals(s.getSymbol()))
		  .findFirst()
		  .orElse(null);
		if (dto == null) {
			logger.error("Symbol not found: [" + symbol + "]");
			security.setId(-1);
			return;
		}
		security.setId(dto.getId());	
	}
	
	private void addSecurityAttributes(Security security) {
		List<FactsDTO> found = facts.stream().filter(f -> security.getId() == f.getSecurity_id())
          .collect(Collectors.toList());

		for (int i = 0; i < found.size(); i++) {
			security.addAttribute(getAttributeName(found.get(i).getAttribute_id()), new Float(found.get(i).getValue()));
		}
	}
	
	public <T> List<T> loadCSV(Class<T> type, String fileName) {
	    try {
	        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
	        CsvMapper mapper = new CsvMapper();
	        File file = new ClassPathResource(fileName).getFile();
	        MappingIterator<T> readValues = 
	          mapper.reader(type).with(bootstrapSchema).readValues(file);
	        return readValues.readAll();
	    } catch (Exception e) {
			logger.error(e.getMessage());
	        return Collections.emptyList();
	    }
	}
	
	private void loadData() {
		try {
			securities = loadCSV(SecuritiesDTO.class, configuration.getSecurities());
			attributes = loadCSV(AttributesDTO.class, configuration.getAttributes());
			facts = loadCSV(FactsDTO.class, configuration.getFacts());
			loaded = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			loaded = false;
		}
	}
	
	private void lazyLoad() {
		if (!loaded) {
			loadData();
		}
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
