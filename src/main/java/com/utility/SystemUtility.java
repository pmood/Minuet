package com.utility;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class SystemUtility {

	public String getCurrentDateTimeWithDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// get current date time with Date()
		Date date = new Date();
		return (dateFormat.format(date));
	}

	public static String getCurrentDateTimeWithDat() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		// get current date time with Date()
		Date date = new Date();
		return (dateFormat.format(date));
	}

	public String getCurrentDateTimeWithCalendar() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		return (dateFormat.format(cal.getTime()));
	}
	
	

	public static String getCurrentDateMMDDYYYY() {
		LocalDate localDate = LocalDate.now();
		String mydate = DateTimeFormatter.ofPattern("MM/dd/YYYY").format(localDate); //DD
		return "02/02/2020";
	}

	public static PrintStream DateString() {
		Calendar c = Calendar.getInstance();
		PrintStream date = System.out.format("%tB %te, %tY%n", c, c, c); // -->
																			// "May
																			// 29,
																			// 2006"

		return date;
	}

	public static PrintStream hourString() {
		Calendar c = Calendar.getInstance();
		PrintStream time = System.out.format("%tl:%tM %tp%n", c, c, c); // -->
																		// "2:34
																		// am"
		return time;
	}

	public static PrintStream concathourString() {
		Calendar c = Calendar.getInstance();
		PrintStream time = System.out.format("%tl_%tM_%tp%n", c, c, c); // -->
																		// "2:34
																		// am"
		return time;
	}

	public static PrintStream simpleDate() {
		Calendar c = Calendar.getInstance();
		PrintStream date = System.out.format("%tD%n", c); // --> "05/29/06"
		return date;

	}

	/**
	 * Grace T: this returns the name of a method in a class this is to be
	 * implemented for tracking current executing method
	 */

	public static String doit() {
		// output : doit
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}

	/**
	 * Grace T: Array of Random Number ... Should be the key/serial correlation
	 * number // for flow tracking *
	 */

	public static final int SET_SIZE_REQUIRED = 10;
	public static final int NUMBER_RANGE = 10000000;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set arrayOfRandomNumber() {
		Random random = new Random();

		Set set = new HashSet<Integer>(SET_SIZE_REQUIRED);

		while (set.size() < SET_SIZE_REQUIRED) {
			while (set.add(random.nextInt(NUMBER_RANGE)) != true)
				;
		}
		assert set.size() == SET_SIZE_REQUIRED;
		System.out.println(set);
		return set;
	}

	/**
	 * Grace T: this can return one to multiple random numbers depending on how many
	 * randomNumbersNeeded is required in the parameter : randomNumbersNeeded this
	 * method is intensively used to generate most of the random data in this
	 * project
	 */

	public static Integer getRandomNumberNeeded(int randomNumbersNeeded) {
		int max = 1999999999;
		int numbersNeeded = randomNumbersNeeded;
		if (max < numbersNeeded) {
			throw new IllegalArgumentException("Can't ask for more numbers than are available");
		}
		Random rng = new Random(); // Ideally just create one instance globally
		// Note: use LinkedHashSet to maintain insertion order
		Set<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < numbersNeeded) {
			Integer next = rng.nextInt(max) + 1;
			// As we're adding to a set, this will automatically do a
			// containment check
			generated.add(next);
			// TODO:: replace syso by loggers Special
			// System.out.println("Track Needed Successfully Created on
			// "+Thread.currentThread().getStackTrace()[1].getMethodName());

			return next;
		}
		return null;

	}

	/**
	 * Grace T: this will return a random serial number
	 */

	public static UUID getSerialNumber() {
		UUID idOne = UUID.randomUUID();
		return idOne;
	}

	/**
	 * Grace T: this generate and return a random number data type string ...stamped
	 * with current date and time
	 */

	public static String myCorrelationIdTrack() {

		UUID idOne = UUID.randomUUID();
		System.out.println(idOne);
		int myNumber = getRandomNumberNeeded(1);
		String mydate = getCurrentDateTimeWithDat();

		String mySerial = idOne.toString() + "-" + myNumber + "-" + mydate.toString();
		// TODO:: replace syso by loggers Special
		// System.out.println("Track Needed Successfully Created on
		// "+Thread.currentThread().getStackTrace()[1].getMethodName());
		return mySerial;

	}

	/**
	 * Grace T: this is only for test need to make sure method is working correctly
	 */

	public static void main(String[] args) {
		//System.out.println(myCorrelationIdTrack());
		System.out.println(getCurrentDateMMDDYYYY());
	}

}
