package com.kaola.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.getSerializationConfig().with(new SimpleDateFormat() {
			private static final long serialVersionUID = 727686277241170700L;

			public Date parse(String source, ParsePosition pos) {
				return null;
			}
		});
		mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
	}
	
	private static final Map<String, JsonEncoding> encodings = new HashMap<String, JsonEncoding>();
	static {
		encodings.put("UTF-8", JsonEncoding.UTF8);
		encodings.put("UTF16-BE", JsonEncoding.UTF16_BE);
		encodings.put("UTF16-LE", JsonEncoding.UTF16_LE);
		encodings.put("UTF32-BE", JsonEncoding.UTF32_BE);
		encodings.put("UTF32-LE", JsonEncoding.UTF32_LE);
	}
	
	public static byte[] jsonFromObject(Object object, String encoding) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JsonGenerator json = null;
		try {
			JsonEncoding enc = encodings.get(encoding);
			if(enc == null)
				throw new IllegalStateException("unsupport encoding: " + encoding);
			json = mapper.getFactory().createGenerator(out, enc);
			json.writeObject(object);
			json.flush();
			//mapper.writeValue(writer, object);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			System.out.println("Unable to serialize to json: " + object);
			return null;
		} finally {
			if(json!=null)
				try {
					json.close();
				} catch (IOException e) {
					//
				}
		}
		return out.toByteArray();
	}

	public static <T> T objectFromJson(String json, Class<T> klass) {
		T object;
		JsonParser parser = null;
		try {
			parser = mapper.getFactory().createParser(json);
			object = parser.readValueAs(klass);
			//object = mapper.readValue(json, klass);
		} catch (RuntimeException e) {
			System.out.println("Runtime exception during deserializing " + klass.getSimpleName() + " from " + abbreviate(json, 80));
			throw e;
		} catch (Exception e) {
			System.out.println("Exception during deserializing " + klass.getSimpleName() + " from " + abbreviate(json, 80));
			return null;
		} finally {
			if(parser != null)
				try {
					parser.close();
				} catch (IOException e) {
					//
				}
		}
		return object;
	}

	static String abbreviate(String str, int len) {
		if (str == null || str.length() < len)
			return str;
		return str.substring(0, len) + " ...";
	}

}