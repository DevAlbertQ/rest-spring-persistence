package br.com.albert.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

	public static Integer yearsBetween(Date from, Date to) {
		if(from == null) {
			return null;
		}
		if(to == null) { 
			to = new Date();
		}
		LocalDate fromLocal = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate toLocal = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int between = Period.between(fromLocal, toLocal).getYears();
		return between;
	}
}
