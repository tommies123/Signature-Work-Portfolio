import java.util.Random;
import java.util.Scanner;

/**
 * This program should ask the user for a date, display this date in the format of month, day, and year,
 * and then convert what that date is in Unix Epoch time format. It then should display one random date
 * from that same year. If the user ever enters a bad date, then the program should immediately stop 
 * with a goodbye message.
 * 
 * @author Maddie McGreevy
 *
 */
public class EpochConverter {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		long todaysDateInSeconds;
		int inputMonth;
		int inputDay;
		int inputYear;
		int[] userInputDate = new int[3];
		
		// Welcome the user to the Epoch Time Converter.
		System.out.println("Welcome to the Epoch Converter.");
		
		// Convert the current time in milliseconds to time in seconds. Then convert that time to
		// the current date and display it to the user.
		todaysDateInSeconds = (System.currentTimeMillis()/1000);
		System.out.println("Today is "+dateToString(epochTimeToDate(todaysDateInSeconds)));
		
		// Ask the user for a month and scan it into the system.
		System.out.print("Enter a month: ");
		inputMonth = keyboard.nextInt();
		
		// Ask the user for a day and scan it into the system.
		System.out.print("Enter a day: ");
		inputDay = keyboard.nextInt();
		
		// Ask the user for a year and scan it into the system.
		System.out.print("Enter a year: ");
		inputYear = keyboard.nextInt();
		
		userInputDate[0] = inputMonth;
		userInputDate[1] = inputDay;
		userInputDate[2] = inputYear;
		
		// If the date that the user put into the system is not valid, then end the program and output a 
		// goodbye message. If the date is valid, then move onto the rest of the program.
		if(isValidDate(userInputDate) == false) {
			System.out.println("Sorry, invalid date. Goodbye.");
		} else {
			// Convert the user input date to epoch time and display it to the user.
			System.out.println("That date on Epoch time is "+dateToEpochTime(userInputDate));
			
			// Generate a random date within the user input year and display it to the user. 
			System.out.println("A randomly generated date from that year is "+dateToString(randomDate(inputYear)));
			
		}
		
		//test();

	}

	/**
	 * This method should take the year as input and return boolean true if it is leap year and false otherwise.
	 * @param year The date to be tested.
	 * @return True if the year is a leap year and false otherwise.
	 */
	public static boolean isLeapYear(int year) {
		// If the year is evenly divisible by 4, then move onto the next if statement.
		if(year % 4 == 0) {
			// If the year is evenly divisible by 400, then return that it is a leap year.
			if (year % 400 == 0) {
				return true;
			// If the year is evenly divisible by 100, then return that it is not a leap year.
			} else if (year % 100 == 0) {
				return false;
			}
			return true;
		// If the year is not evenly divisible by 4, then return that it is not a leap year.
		} else {
			return false;				
		}
	}

	/**
	 * This method should take an integer in the array size of 3 and return true if the date is valid and 
	 * false otherwise.
	 * @param date The date to be tested.
	 * @return True if the date is valid and false otherwise.
	 */
	public static boolean isValidDate (int[] date) {
		int month = date[0];
		int day = date[1];
		int year = date[2];

		// Testing for months April, June, September, and November.
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			// If these months display days between 1 and 30, then return that it is a valid date.
			if (day <= 30 && day >= 1) {
				return true;
			// If these months display days outside of these bounds, then return that it is not a valid date.
			} else {
				return false;
			}
		}
		// Testing for months January, March, May, July, August, October, and December.
		else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			// If these months display days between 1 and 31, then return that it is a valid date.
			if (day <= 31 && day >= 1) {
				return true;
			// If these months display days outside of these bounds, then return that it is not a valid date.
			} else {
				return false;
			}
		}
		// Testing for the month of February.
		else if (month==2) {
			// If the year is a leap year, then February should display a day between 1 and 29.
			if (isLeapYear(year) == true ) {
				// If this month displays a day between 1 and 29, then return that it is a valid date.
				if (day <= 29 && day >= 1) {
					return true;
				// If this month displays a day outside of these bounds, then return that it is not a valid date.
				} else {
					return false;
				}
			// If the year is not a leap year, then February should display a day between 1 and 28.
			} else {
				// If this month displays a day between 1 and 28, then return that it is a valid date.
				if (day <= 28 && day >= 1) {
					return true;
				// If this month displays a day outside of these bounds, then return that it is not a valid date.
				} else {
					return false;
				}

			}
		}
		
		return false;

	}

	/**
	 * This method should take a date and return the next valid date.
	 * @param date The date to be inputed.
	 * @return The date of the day after the inputed value.
	 */
	public static int[] getDayAfter(int[] date) {
		int[] nextDate = new int[3];

		// try to add one to the day
		nextDate[0] = date[0];
		nextDate[1] = date[1]+1;
		nextDate[2] = date[2];

		// If the next date is valid, then the date should be returned.
		if(isValidDate(nextDate) == true) {
			return nextDate;
		// If the next date is not valid, then try another solution.
		} else {
			// try to go to first of next month
			nextDate[0] = date[0]+1;
			nextDate[1] = 1;
			nextDate[2] = date[2];
		}
		// If the next date is valid, then the date should be returned.
		if(isValidDate(nextDate) == true) {
			return nextDate;
		// If this method does not work, then try another solution again.
		} else {
			// try to go to first of next year
			nextDate[0] = 1;
			nextDate[1] = 1;
			nextDate[2] = date[2]+1;
		}
		// If the next date is valid, then the date should be returned.
		if(isValidDate(nextDate) == true) {
			return nextDate;
		}
		// The next date should be valid by now and should be returned.
		return nextDate;
	}


	/**
	 * This method should take two dates (date1 and date2), and return -1 if date1 comes before date2, 
	 * return 1 if date1 comes after date2, and return 0 if date1 is the same date as date2.
	 * @param date The two dates to be tested.
	 * @return -1 if date1 comes before date2, 1 if date1 comes after date2, and 0 if the dates
	 * are the same. 
	 */
	public static int compareDates(int[] date1, int[] date2) {
		int[] compareDate1 = new int[3];
		int[] compareDate2 = new int[3];

		compareDate1[0] = date1[0];
		compareDate1[1] = date1[1];
		compareDate1[2] = date1[2];
		compareDate2[0] = date2[0];
		compareDate2[1] = date2[1];
		compareDate2[2] = date2[2];

		// If the year of date 1 is greater than the year of date 2, then return 1.
		if(compareDate1[2] > compareDate2[2]) {
			return 1;
		// If the year of date 1 is less than the year of date 2, then return -1.
		} else if (compareDate1[2] < compareDate2[2]) {
			return -1;
		// If the years are the same, then you should move on to compare the months of each year.
		} else {
			// If the month of date 1 is greater than the month of date 2, then return 1.
			if(compareDate1[0] > compareDate2[0]) {
				return 1;
			// If the month of date 1 is less than the month of date 2, then return -1.
			} else if(compareDate1[0] < compareDate2[0]) {
				return -1;
			// If the months are the same, then you should move on to compare the days of each year.
			} else {
				// If the day of date 1 is greater than the day of date 2, then return 1.
				if(compareDate1[1] > compareDate2[1]) {
					return 1;
				// If the day of date 1 is less than the day of date 2, then return -1.
				} else if(compareDate1[1] < compareDate2[1]) {
					return -1;
				// If the year, month, and day are the same for date 1 and date 2, then return 0.
				} else {
					return 0;
				}
			}

		}

	}

	/**
	 * This method should take two dates and return how many days elapse from the start of the first 
	 * date to the start of the second.
	 * @param date The two dates to be converted to days in between.
	 * @return The number of days that are in between the days being tested.
	 */
	public static int daysBetweenDates(int[] date1, int[] date2) {
		int dayCount = 0;
		
		// Loop until date 1 is equal to date 2.
		while(compareDates(date1,date2) != 0) {
			// Set date1 to the next date in that year.
			date1 = getDayAfter(date1);
			// Use dayCount as a counter for the number of days between the two dates.
			dayCount = dayCount + 1;
				}
		
		// return the number of days that were counted while in the while loop.
		return dayCount;
	}

	/**
	 * This method should take a date with the integer array of size 3 and return a long representing
	 * 00:00:00 UTC in that date converted to epoch time.
	 * @param date The date to be converted to epoch time.
	 * @return The 00:00:00 UTC time
	 */
	public static long dateToEpochTime(int[] date) {
		int[] referenceDate = {1,1,1970};
		long days;
		long epoch;
		
		// If the input date comes before 01/01/1970, then multiply the number of days between 
		// the two dates by -86400L.
		if(compareDates(date, referenceDate) == -1) {
		days = daysBetweenDates(date, referenceDate);
		epoch = days * -86400L;
		// If the input date comes after 01/01/1970, the multiply the number of days between the 
		// two dates by 86400L.
		} else if(compareDates(date, referenceDate) == 1) {
			days = daysBetweenDates(referenceDate, date);
			epoch = days * 86400L;
		// If the input date is 01/01/1970, then return 0 since it is the same date at the reference
		// date.
		} else {
			epoch = 0;
		}
		
		// Return the Epoch time that has been converted from the input date.
		return epoch;
		
	}

	/**
	 * This method should take an epoch time in long integer and return the corresponding date. It only
	 * needs to work for dates 1970 and later.
	 * @param date The time that will be converted to a date.
	 * @return The date that corresponds to the long integer time.
	 */
	public static int[] epochTimeToDate(long seconds) {
		int[] referenceDate = {1,1,1970};
		int[] previousDay = referenceDate;
	
		// While the integer is less than the epoch time, it should loop until it has found the date
		// corresponding to that epoch time. 
		for(long i = 0; i < seconds; i = i + 86400) {
			previousDay = referenceDate;
			referenceDate = getDayAfter(referenceDate);
			
		}
		// Return the date corresponding to that Epoch time.
		return previousDay;
			
	}

	/**
	 * This method should take a date and return a string representation of that date in the form of
	 * month/day/year.
	 * @param date The date that is going to be converted.
	 * @return The string that is in the form of month/day/year.
	 */
	public static String dateToString(int[] date) {
		// Use string concatenation to take the date and return it as a string.
		return date[0]+"/"+date[1]+"/"+date[2];

	}

	/**
	 * This method should take a year and a day number and return the date corresponding to that day
	 * number in that year.
	 * @param date The year and date that is going to be converted.
	 * @return The corresponding day number to that year and day number.
	 */
	public static int[] dayNumberToDate(int year, int dayNumber) {
		int[] correspondingDate = {1,0,year};
		
		// While the integer is less than the day number, it should loop until it has found the date 
		// corresponding to that day number in that year.
		for(int i = 0; i < dayNumber; i++) {
			correspondingDate = getDayAfter(correspondingDate);
			
		}
		// Return the corresponding date for that day number. 
		return correspondingDate;

	}

	/**
	 * This method should take a year as input and return a random date from that year, with each day
	 * equally likely.
	 * @param date The year to be randomized.
	 * @return The random date that is generated. 
	 */
	public static int[] randomDate(int year) {
		Random generator = new Random();
		int randomDay;
		
		// If it is a leap year, then the method should generate a day between and including 1 and 366.
		if(isLeapYear(year)== true) {
			randomDay = generator.nextInt(1,367);
		// If it is not a leap year, then the method should generate a day between and including 1 and 365.
		} else {
			randomDay = generator.nextInt(1,366);
			
		}
		// The random date should be returned in date format using the dayNumberToDate method.
		return dayNumberToDate(year, randomDay);
				
			}


	/**
	 * This method tests the code.
	 */
	public static void test() {
	
		System.out.println("Testing isLeapYear...");
		System.out.println("isLeapYear(2020) should be true, yours gives "+isLeapYear(2020));
		System.out.println("isLeapYear(1901) should be false, yours gives "+isLeapYear(1901));
		System.out.println("isLeapYear(1800) should be false, yours gives "+isLeapYear(1800));
		System.out.println("isLeapYear(2000) should be true, yours gives "+isLeapYear(2000));

		System.out.println("Testing isValidDate...");
		System.out.println("30 day months....");
		System.out.println("isValidDate([9,30,1990]) should be true, yours gives "+isValidDate(new int[] {9,30,1990}));
		System.out.println("isValidDate([9,31,1990]) should be false, yours gives "+isValidDate(new int[] {9,31,1990}));
		System.out.println("isValidDate([11,30,1950]) should be true, yours gives "+isValidDate(new int[] {11,30,1950}));
		System.out.println("isValidDate([11,31,1950]) should be false, yours gives "+isValidDate(new int[] {11,31,1950}));
		System.out.println("isValidDate([4,30,1900]) should be true, yours gives "+isValidDate(new int[] {4,30,1900}));
		System.out.println("isValidDate([4,31,1900]) should be false, yours gives "+isValidDate(new int[] {4,31,1900}));
		System.out.println("isValidDate([6,30,1800]) should be true, yours gives "+isValidDate(new int[] {6,30,1800}));
		System.out.println("isValidDate([6,31,1800]) should be false, yours gives "+isValidDate(new int[] {6,31,1800}));
		System.out.println("February....");
		System.out.println("isValidDate([2,28,2000]) should be true, yours gives "+isValidDate(new int[] {2,28,2000}));
		System.out.println("isValidDate([2,29,2000]) should be true, yours gives "+isValidDate(new int[] {2,29,2000}));
		System.out.println("isValidDate([2,30,2000]) should be false, yours gives "+isValidDate(new int[] {2,30,2000}));
		System.out.println("isValidDate([2,28,2001]) should be true, yours gives "+isValidDate(new int[] {2,28,2001}));
		System.out.println("isValidDate([2,29,2001]) should be false, yours gives "+isValidDate(new int[] {2,29,2001}));
		System.out.println("isValidDate([2,30,2001]) should be false, yours gives "+isValidDate(new int[] {2,30,2001}));
		System.out.println("isValidDate([2,28,1900]) should be true, yours gives "+isValidDate(new int[] {2,28,1900}));
		System.out.println("isValidDate([2,29,1900]) should be false, yours gives "+isValidDate(new int[] {2,29,1900}));
		System.out.println("isValidDate([2,30,1900]) should be false, yours gives "+isValidDate(new int[] {2,30,1900}));
		System.out.println("October - 31 day month");
		System.out.println("isValidDate([10,30,2020]) should be true, yours gives "+isValidDate(new int[] {10,30,2020}));
		System.out.println("isValidDate([10,31,2020]) should be true, yours gives "+isValidDate(new int[] {10,31,2020}));
		System.out.println("Very invalid dates...");
		System.out.println("isValidDate([14,20,2005]) should be false, yours gives "+isValidDate(new int[] {14,20,2005}));
		System.out.println("isValidDate([5,40,2010]) should be false, yours gives "+isValidDate(new int[] {5,40,2010}));
		System.out.println("isValidDate([0,15,2000]) should be false, yours gives "+isValidDate(new int[] {0,15,2000}));
		System.out.println("isValidDate([2,31,2004]) should be false, yours gives "+isValidDate(new int[] {2,31,2004}));


		System.out.println("Testing compareDates...");
		System.out.println("compareDates([12,5,2010], [12,5,2010]) should give 0, yours gives "
				+compareDates(new int[] {12,5,2010},new int[] {12,5,2010}));
		System.out.println("compareDates([10,5,2005], [10,5,2010]) should give -1, yours gives "
				+compareDates(new int[] {10,5,2005},new int[] {10,5,2010}));
		System.out.println("compareDates([6,5,2005], [6,5,1910]) should give 1, yours gives "
				+compareDates(new int[] {6,5,2005},new int[] {6,5,1910}));
		System.out.println("compareDates([6,20,2004], [7,5,2004]) should give -1, yours gives "
				+compareDates(new int[] {6,20,2004},new int[] {7,5,2004}));
		System.out.println("compareDates([6,20,2004], [6,5,2004]) should give 1, yours gives "
				+compareDates(new int[] {6,20,2004},new int[] {6,5,2004}));
		System.out.println("Testing getDayAfter...");
		System.out.println("getDayAfter([10,14,2004]) should be 10/15/2004, yours gives "
				+dateToString(getDayAfter(new int[] {10,14,2004})));
		System.out.println("getDayAfter([10,31,2004]) should be 11/1/2004, yours gives "
				+dateToString(getDayAfter(new int[] {10,31,2004})));
		System.out.println("getDayAfter([12,31,2004]) should be 1/1/2005, yours gives "
				+dateToString(getDayAfter(new int[] {12,31,2004})));
		System.out.println("getDayAfter([2,28,2004]) should be 2/29/2004, yours gives "
				+dateToString(getDayAfter(new int[] {2,28,2004})));
		System.out.println("getDayAfter([2,29,2004]) should be 3/1/2004, yours gives "
				+dateToString(getDayAfter(new int[] {2,29,2004})));

		System.out.println("Testing dateToEpochTime...");
		System.out.println("dateToEpochTime([1,5,1970]) should be 345600, yours gives "
				+dateToEpochTime(new int[] {1,5,1970}));
		System.out.println("dateToEpochTime([12,20,1970]) should be 30499200, yours gives "
				+dateToEpochTime(new int[] {12,20,1970}));
		System.out.println("dateToEpochTime([6,5,1971]) should be 44928000, yours gives "
				+dateToEpochTime(new int[] {6,5,1971}));
		System.out.println("dateToEpochTime([8,20,1985]) should be 493344000, yours gives "
				+dateToEpochTime(new int[] {8,20,1985}));
		System.out.println("dateToEpochTime([10,10,1996]) should be 844905600, yours gives "
				+dateToEpochTime(new int[] {10,10,1996}));
		System.out.println("dateToEpochTime([3,5,2000]) should be 952214400, yours gives "
				+dateToEpochTime(new int[] {3,5,2000}));
		System.out.println("dateToEpochTime([4,28,2011]) should be 1303948800, yours gives "
				+dateToEpochTime(new int[] {4,28,2011}));
		System.out.println("dateToEpochTime([11,25,2040]) should be 2237414400, yours gives "
				+dateToEpochTime(new int[] {11,25,2040}));


		System.out.println("dateToEpochTime([12,30,1969]) should be -172800, yours gives "
				+dateToEpochTime(new int[] {12,30,1969}));
		System.out.println("dateToEpochTime([1,2,1969]) should be -31449600, yours gives "
				+dateToEpochTime(new int[] {1,2,1969}));
		System.out.println("dateToEpochTime([10,20,1910]) should be -1868227200, yours gives "
				+dateToEpochTime(new int[] {10,20,1910}));
		System.out.println("dateToEpochTime([1,7,1899]) should be -2240006400, yours gives "
				+dateToEpochTime(new int[] {1,7,1899}));
		System.out.println("dateToEpochTime([7,27,1810]) should be -5031244800, yours gives "
				+dateToEpochTime(new int[] {7,27,1810}));

	
        System.out.println("Testing epochTimeToDate on dates after 1/1/1970...");

        System.out.println("epochTimeToDate(844905650) should give 10/10/1996, yours gives "
                +dateToString(epochTimeToDate(844905650)));
        System.out.println("epochTimeToDate(1303948700) should give 4/27/2011, yours gives "
                +dateToString(epochTimeToDate(1303948700)));
        System.out.println("epochTimeToDate(2237415800) should give 11/25/2040, yours gives "
                +dateToString(epochTimeToDate(2237415800L)));

        System.out.println("Testing dayNumberToDate...");
        System.out.println("dayNumberToDate(2004,1) should give  1/1/2004, yours gives "
                +dateToString(dayNumberToDate(2004,1)));
        System.out.println("dayNumberToDate(2004,60) should give  2/29/2004, yours gives "
                +dateToString(dayNumberToDate(2004,60)));
        System.out.println("dayNumberToDate(2004,61) should give  3/1/2004, yours gives "
                +dateToString(dayNumberToDate(2004,61)));
        System.out.println("dayNumberToDate(2004,366) should give  12/31/2004, yours gives "
                +dateToString(dayNumberToDate(2004,366)));

	}



}
