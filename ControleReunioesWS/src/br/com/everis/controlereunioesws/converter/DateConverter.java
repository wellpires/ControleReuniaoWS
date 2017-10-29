package br.com.everis.controlereunioesws.converter;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import br.com.everis.controlereunioesws.utils.Constants;

public class DateConverter implements JsonSerializer<Date>, JsonDeserializer<Date> {

	private final DateFormat dateFormat;

	public DateConverter() {
		dateFormat = new SimpleDateFormat(Constants.DATETIME_PATTERN, Locale.US);
	}

	@Override
	public Date deserialize(JsonElement jsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext) throws JsonParseException {
		try {
			return dateFormat.parse(jsonElement.getAsString());
		} catch (ParseException e) {
			throw new JsonParseException(e);
		}
	}

	@Override
	public JsonElement serialize(Date date, Type paramType, JsonSerializationContext paramJsonSerializationContext) {
		return new JsonPrimitive(dateFormat.format(date));
	}

}
