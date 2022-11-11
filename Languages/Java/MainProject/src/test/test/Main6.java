package test.test;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class Main6 {

	public static void main(String[] args) {

		Calendar c = Calendar.getInstance();
//		c.set(c.get(YEAR), c.get(MONTH), c.get(DAY_OF_MONTH) - 1);
//		print(c);
//
////		c.set(DAY_OF_WEEK, MONDAY);
////		c.set(c.get(YEAR), c.get(MONTH), c.get(DAY_OF_MONTH), 0, 0, 0);
////
////		print(c);
//
//
//		c.setFirstDayOfWeek(MONDAY);
//		c.set(DAY_OF_WEEK, MONDAY);
//		c.set(c.get(YEAR), c.get(MONTH), c.get(DAY_OF_MONTH));
//
//		print(c);






		print(c);








	}

	private static void print(Calendar c) {
		System.out.println(c.get(HOUR_OF_DAY) + ":"
				+ c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND) + " "
				+ c.get(DAY_OF_MONTH) + "-"
				+ (c.get(MONTH) + 1) + "-"
				+ c.get(YEAR));
	}

}
