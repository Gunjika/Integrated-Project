package com.capgemini.hms.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	public static LocalDateTime convertStringToDate(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime localDateTime =  LocalDateTime.parse(dateTime,formatter);
		return localDateTime;
	}

}
