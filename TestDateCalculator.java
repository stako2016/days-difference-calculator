/**
 *
 * @author Sunil Tako
 */
import java.util.Scanner;
import java.util.regex.*;

public class TestDateCalculator {

    /*
    Date regex to check if the date is in the format of "dd/MM/YYYY" and between 1901 to 2999
     */
    final static String DATE_REGEX = "^((?!00)([0-2][0-9]|(3)[0-1])|[1-9])(\\/)((?!00)((0)[0-9])|((1)[0-2])|[1-9])(\\/)(190[1-9]|19[1-9][0-9]|2[0-9]{3})$";

    public static void main(String[] args) {

        TestDateCalculator tdc = new TestDateCalculator();

        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Date difference calculator -----");
        System.out.println("Enter date in this format: dd/MM/YYY (e.g. 25/12/2000) and between 1901 and 2999");

        try {

            /*
            	Taking first date input
             */
            Date firstDate = null;
            do {
                System.out.print("Enter valid first date: ");
                firstDate = tdc.getDateFromConsole(scanner);
            } while (firstDate == null);

            /*
            Taking second date input
             */
            Date secondDate = null;
            do {
                System.out.print("Enter valid second date: ");
                secondDate = tdc.getDateFromConsole(scanner);
            } while (secondDate == null);

            long numberOfDaysDifference = tdc.getDateDifference(firstDate, secondDate);

            tdc.printNumberOfDays(firstDate, secondDate, numberOfDaysDifference);

            /*
            Test Cases
             */
            System.out.println("\nTest Cases: ");
            tdc.testNotFullyElapseDates();
            tdc.testDatesWithinAyear();
            tdc.testDatesMoreThanAYear();
            tdc.testLargeDateAsFirstDate();
            tdc.testLeapYearDates();
            tdc.testFutureDates();
            tdc.testZeroDates();
            tdc.testNegativeDate();
            tdc.testBelowRangeDate();
            tdc.testAboveRangeDate();
            tdc.testInvalidDay();
            tdc.testInvalidMonth();
            tdc.testInvalidYear();

        } catch (NumberFormatException e) {

            System.out.println(e.toString());
        }
    }

    private Date getDateFromConsole(Scanner sc) {

        int day = 0;
        int month = 0;
        int year = 0;

        String dateString = sc.nextLine();

        boolean isValidDate = isValidDate(dateString);

        if (isValidDate) {
            String[] dateStringArray = dateString.split("/");

            day = Integer.valueOf(dateStringArray[0]);
            month = Integer.valueOf(dateStringArray[1]);
            year = Integer.valueOf(dateStringArray[2]);
        } else {
            System.out.println("Entered date is wrong or in wrong format. Please enter a date in the format of 'dd/MM/yyy' and between 1901 to 2999.");
            return null;
        }

        return new Date(day, month, year);

    }

    private boolean isValidDate(String dateString) {

        /*
        Return true if date is in "dd/MM/YYY" format and between 1901 to 2999 else return false
         */
        return Pattern.matches(DATE_REGEX, dateString);

    }

    private boolean isValidDate(Date date) {

        String dateString = String.valueOf(date.day) + "/" + String.valueOf(date.month) + "/" + String.valueOf(date.year);

        /*
        Return true if date is in "dd/MM/YYY" format and between 1901 to 2999 else return false
         */
        return Pattern.matches(DATE_REGEX, dateString);

    }

    private long getDateDifference(Date firstDate, Date secondDate) {

        DateCalculator dateCalculator = new DateCalculator();

        long numberOfDays = dateCalculator.getDateDifference(firstDate, secondDate);

        return numberOfDays;

    }

    private boolean areBothDatesValid(Date firstDate, Date secondDate) {
        boolean isfirstDateValid = isValidDate(firstDate);
        boolean isSecondDateValid = isValidDate(secondDate);

        if (isfirstDateValid && isSecondDateValid) {
            return true;
        }

        return false;
    }

    private void printNumberOfDays(Date firstDate, Date secondDate, Long numberOfDaysDifference) {

        System.out.println("Number of days between " + firstDate.toString() + " and " + secondDate.toString() + ": " + numberOfDaysDifference);
    }


    /*
    Test Case: 1
     */
    private void testNotFullyElapseDates() {

        Date firstDate = new Date(2, 3, 2000);
        Date secondDate = new Date(3, 3, 2000);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 1: Not Fully Elapse Dates ");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format not valid.");
        }

    }

    /*
    Test Case: 2
     */
    private void testDatesWithinAyear() {

        Date firstDate = new Date(5, 7, 1988);
        Date secondDate = new Date(25, 11, 1988);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 2: Dates within a year");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format not valid.");
        }
    }

    /*
    Test Case: 3
     */
    private void testDatesMoreThanAYear() {

        Date firstDate = new Date(2, 3, 2002);
        Date secondDate = new Date(3, 3, 2005);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 3: Dates more than a year");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format not valid.");
        }
    }

    /*
    Test Case: 4
     */
    private void testLargeDateAsFirstDate() {

        Date firstDate = new Date(3, 1, 1989);
        Date secondDate = new Date(3, 8, 1983);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 4: Large date as first date");

        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format not valid.");
        }
    }

    /*
    Test Case: 5
     */
    private void testLeapYearDates() {

        Date firstDate = new Date(2, 2, 2012);
        Date secondDate = new Date(1, 3, 2012);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 5: Leap year dates");

        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format not valid.");
        }
    }

    /*
    Test Case: 6
     */
    private void testFutureDates() {

        Date firstDate = new Date(12, 12, 2020);
        Date secondDate = new Date(13, 6, 2056);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 6: Futrue dates");

        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format not valid.");
        }
    }

    /*
    Test Case: 7
     */
    private void testZeroDates() {

        Date firstDate = new Date(0, 0, 0000);
        Date secondDate = new Date(0, 0, 0000);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 7: Zero Dates");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format not valid. First Date: " + firstDate.toString() + ", Second Date: " + secondDate.toString());
        }
    }

    /*
    Test Case: 8
     */
    private void testNegativeDate() {

        int day = Integer.valueOf("-10");
        int month = Integer.valueOf("-6");
        int year = Integer.valueOf("2003");

        Date firstDate = new Date(day, month, year);
        Date secondDate = new Date(13, 6, 2000);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 8: Negative Dates");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date format is negative. First Date: " + firstDate.toString() + ", Second Date: " + secondDate.toString());
        }
    }

    /*
    Test Case: 9
     */
    private void testBelowRangeDate() {

        Date firstDate = new Date(11, 02, 1900);
        Date secondDate = new Date(1, 5, 2003);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 9: Below 1901");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date is below the range. First Date: " + firstDate.toString() + ", Second Date: " + secondDate.toString());
        }
    }

    /*
    Test Case: 10
     */
    private void testAboveRangeDate() {

        Date firstDate = new Date(10, 12, 3256);
        Date secondDate = new Date(1, 1, 2006);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 10: Above 2999");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Date is above the range. First Date: " + firstDate.toString() + ", Second Date: " + secondDate.toString());
        }
    }

    /*
    Test Case: 11
     */
    private void testInvalidDay() {

        Date firstDate = new Date(88, 12, 2012);
        Date secondDate = new Date(8, 7, 2019);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 11: Invalid day");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Invalid day. First Date: " + firstDate.toString() + ", Second Date: " + secondDate.toString());
        }
    }

    /*
    Test Case: 12
     */
    private void testInvalidMonth() {

        Date firstDate = new Date(9, 63, 2563);
        Date secondDate = new Date(3, 7, 2000);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 12: Invalid month");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Invalid month. First Date: " + firstDate.toString() + ", Second Date: " + secondDate.toString());
        }
    }

    /*
    Test Case: 13
     */
    private void testInvalidYear() {

        Date firstDate = new Date(7, 8, 22222);
        Date secondDate = new Date(13, 8, 2300);

        boolean areDatesValid = areBothDatesValid(firstDate, secondDate);

        System.out.println("\nTest Case 13: Invalid year");
        if (areDatesValid) {
            long numberOfDays = getDateDifference(firstDate, secondDate);
            printNumberOfDays(firstDate, secondDate, numberOfDays);
        } else {
            System.out.println("Invalid year. First Date: " + firstDate.toString() + ", Second Date: " + secondDate.toString());
        }
    }

}
