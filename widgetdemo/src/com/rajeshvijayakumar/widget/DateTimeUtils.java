package com.rajeshvijayakumar.widget;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeUtils {
	private static Calendar cal;

	public static void setTimeZoneForApp(String timezoneId) {
		cal = new GregorianCalendar(TimeZone.getTimeZone(timezoneId));
	}

	public static Calendar getTimeZoneForApp() {
		return cal;
	}

	public static String getCountryTime(String timeZoneId, boolean countryTime) {

		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
		
		int minutes = cal.get(Calendar.MINUTE); // 0..59
		int seconds = cal.get(Calendar.SECOND); // 0..59
		String min12 = (minutes < 10) ? "0" + minutes : "" + minutes;
		String sec12 = (seconds < 10) ? "0" + seconds : "" + seconds;
		boolean am = cal.get(Calendar.AM_PM) == Calendar.AM;
		String amorpm = (am == true) ? "AM" : "PM";
		String hour = "";
		if (countryTime == true) {
			int hour12 = cal.get(Calendar.HOUR); // 0..11
			int hr12 = (hour12 == 0) ? 12 : hour12;
			hour = hr12 + ":" + min12.trim()+ " " + amorpm;
		} else {
			cal.setTimeZone(TimeZone.getTimeZone("GMT"));
			int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
			String hr24 = (hour24 < 0) ? "0" + hour24 : "" + hour24;
			hour = hr24 + ":" + min12;
		}
		return hour;
	}

	public static String getCurrentDate(String format,
			boolean displayCurrentDate) {
		if (displayCurrentDate == true) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat(format);
			String formatted = df.format(c.getTime());
			return formatted;
		} else {
			return "";
		}
	}

	public static String getCountryDate(String timeZoneId,
			boolean displayCountryDate) {

		if (displayCountryDate == true) {
			Calendar cal = new GregorianCalendar(
					TimeZone.getTimeZone(timeZoneId));
			int date = cal.get(Calendar.DATE);
			int month = cal.get(Calendar.MONTH);
			String monthName = getMonthName(month);
			int year = cal.get(Calendar.YEAR);
			return +date + "-" + monthName + "-" + year;
		} else {
			return "";
		}
	}

	public static String getDayName(int dayNo) {
		String dayName = "";
		switch (dayNo) {
		case 0:
			dayName = "Sun";
			break;
		case 1:
			dayName = "Mon";
			break;
		case 2:
			dayName = "Tue";
			break;
		case 3:
			dayName = "Wed";
			break;
		case 4:
			dayName = "Thu";
			break;
		case 5:
			dayName = "Fri";
			break;
		case 6:
			dayName = "Sat";
			break;

		}
		return dayName;

	}

	public static String getMonthName(int month) {
		String monthName = "";
		switch (month) {
		case 0:
			monthName = "Jan";
			break;
		case 1:
			monthName = "Feb";
			break;
		case 2:
			monthName = "Mar";
			break;
		case 3:
			monthName = "Apr";
			break;
		case 4:
			monthName = "May";
			break;
		case 5:
			monthName = "Jun";
			break;
		case 6:
			monthName = "Jul";
			break;
		case 7:
			monthName = "Aug";
			break;
		case 8:
			monthName = "Sep";
			break;
		case 9:
			monthName = "Oct";
			break;
		case 10:
			monthName = "Nov";
			break;
		case 11:
			monthName = "Dec";
			break;
		}
		return monthName;
	}

}
