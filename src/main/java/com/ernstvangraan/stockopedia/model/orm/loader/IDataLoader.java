package com.ernstvangraan.stockopedia.model.orm.loader;

import java.io.IOException;
import java.util.List;

public interface IDataLoader {
	public <T> List<T> loadOrEmpty(Class<T> type, String fileName);
	public <T> List<T> load(Class<T> type, String fileName) throws IOException;
}
