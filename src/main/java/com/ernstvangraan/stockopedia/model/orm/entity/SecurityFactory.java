package com.ernstvangraan.stockopedia.model.orm.entity;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ernstvangraan.stockopedia.model.schema.AttributesDTO;
import com.ernstvangraan.stockopedia.model.schema.FactsDTO;
import com.ernstvangraan.stockopedia.model.schema.SecuritiesDTO;

@Component("securityFactory")
public class SecurityFactory {
	private Logger logger = LoggerFactory.getLogger(SecurityFactory.class);

	private String getAttributeName(List<AttributesDTO> attributes, int id) {
		AttributesDTO dto = attributes.stream().filter(a -> id == a.getId())
				  .findFirst()
				  .orElse(null);
				if (dto == null) {
					logger.error("Attribute not found: [" + id + "]");
					return null;
				}
		return dto.getName();
	}
	
	public Security buildSecurity(List<SecuritiesDTO> securities, List<AttributesDTO> attributes, List<FactsDTO> facts, String symbol) {
		Security security = new Security();
		security.setSymbol(symbol);
		addSecurityIdIfFound(securities, security, symbol);
		addSecurityAttributes(attributes, facts, security);
		
		return security;
	}
	
	private void addSecurityIdIfFound(List<SecuritiesDTO> securities, Security security, String symbol) {
		SecuritiesDTO dto = securities.stream().filter(s -> symbol.equals(s.getSymbol()))
		  .findFirst().orElse(null);
		if (dto == null) {
			logger.error("Symbol not found: [" + symbol + "]");
			security.setId(-1);
			return;
		}
		security.setId(dto.getId());	
	}
	
	private void addSecurityAttributes(List<AttributesDTO> attributes, List<FactsDTO> facts, Security security) {
		List<FactsDTO> found = facts.stream().filter(f -> security.getId() == f.getSecurity_id())
          .collect(Collectors.toList());

		for (int i = 0; i < found.size(); i++) {
			security.addAttribute(getAttributeName(attributes, found.get(i).getAttribute_id()), new Float(found.get(i).getValue()));
		}
	}

}
