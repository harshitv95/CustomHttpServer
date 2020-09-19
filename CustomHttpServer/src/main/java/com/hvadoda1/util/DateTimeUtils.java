package com.hvadoda1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");

	public static String logFileNameDateString() {
		return format.format(new Date());
	}
}
