package br.edu.coo2015.ep2.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class Propriedades {

	private HashMap<String, String> propriedades = new HashMap<String, String>();

	public Propriedades() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("coo2015.properties");
			prop.load(input);

			for (Entry<Object, Object> entry : prop.entrySet()) {
				propriedades.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public String getPropriedade(String chave) {
		return propriedades.get(chave);
	}
}
