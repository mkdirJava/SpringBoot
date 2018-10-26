package com.wilson.feelings.util;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utility {
	
	public static Date dateTimeToDate(LocalDate dateTime) {
		return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
