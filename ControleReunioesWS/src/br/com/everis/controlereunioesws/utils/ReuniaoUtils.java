package br.com.everis.controlereunioesws.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReuniaoUtils {
	
    public static Date stringToDateTime(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATETIME_PATTERN);
        return new Date(sdf.parse(data).getTime());
    }	

}
