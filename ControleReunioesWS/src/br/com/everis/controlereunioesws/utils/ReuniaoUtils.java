package br.com.everis.controlereunioesws.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.internal.Primitives;

public class ReuniaoUtils {

	public static Date stringToDateTime(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATETIME_PATTERN, Locale.US);
		return new Date(sdf.parse(data).getTime());
	}

	public static boolean isEmptyOrNull(String value) {
		return "".equals(value) || value == null;
	}

	public static Long stringToLong(String value) {
		if (isEmptyOrNull(value)) {
			value = "0";
		}
		return Long.valueOf(value);
	}

	public static <T> T cloneObject(Object objOrigem, Object objDestino, Class<T> clazz)throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Method mOrigem : clazz.getMethods()) {
			if (mOrigem.getName().startsWith("get")) {
				String mNameOrigem = mOrigem.getName();
				for (Method mDestino : clazz.getMethods()) {
					String mNameDestino = mDestino.getName();
					if (mDestino.getName().startsWith("set")
							&& mNameOrigem.substring(3).equals(mNameDestino.substring(3))) {
						mDestino.invoke(objDestino, mOrigem.invoke(objOrigem));
					}
				}
			}
		}
		return Primitives.wrap(clazz).cast(objDestino);
	}
	
}
