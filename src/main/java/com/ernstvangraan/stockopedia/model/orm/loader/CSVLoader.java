package com.ernstvangraan.stockopedia.model.orm.loader;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Component("csvLoader")
public class CSVLoader implements IDataLoader{
	private Logger logger = LoggerFactory.getLogger(CSVLoader.class);

	public <T> List<T> loadOrEmpty(Class<T> type, String fileName) {
	    try {
	    	return load(type, fileName);
	    } catch (Exception e) {
			logger.error(e.getMessage());
	        return Collections.emptyList();
	    }
	}
	
	public <T> List<T> load(Class<T> type, String fileName) throws IOException {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        File file = new ClassPathResource(fileName).getFile();
        @SuppressWarnings("deprecation")
		MappingIterator<T> readValues = 
          mapper.reader(type).with(bootstrapSchema).readValues(file);
        return readValues.readAll();
	}
}
