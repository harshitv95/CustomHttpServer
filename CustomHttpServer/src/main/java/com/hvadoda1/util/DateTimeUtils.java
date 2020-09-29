package com.hvadoda1.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {
	private static final SimpleDateFormat logFileNameFormat = new SimpleDateFormat("yyyyMMdd_HHmmss"),
			httpHeaderDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

	static {
		httpHeaderDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public static String getLogFileNameDateString() {
		return logFileNameFormat.format(new Date());
	}

	public static String getServerTimeString(Date d) {
		return httpHeaderDateFormat.format(d);
	}
}
