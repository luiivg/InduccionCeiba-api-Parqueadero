package co.parking.utils;

import java.time.LocalDateTime;

public class DateUtils {

	public static int getDiferentInHours(LocalDateTime start, LocalDateTime end) {
		return end.compareTo(start);
	}

	public static int calculateHour(int hour) {
		return hour > 24 ? (hour * 24) % 24 : hour;
	}

	public static int calculateDays(int hour) {
		return hour > 24 ? (hour * 24) / 24 : 0;
	}

}
