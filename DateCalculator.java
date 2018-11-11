/**
 *
 * @author Sunil Tako
 */

public class DateCalculator {

    /*
    Number of days in all months
     */
    final static int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /*
    Calculating number of days between two given dates
     */
    protected long getDateDifference(Date firstDate, Date secondDate) {

        long numberOfDaysOfFirstDate = getNumberOfDays(firstDate);
        long numberOfDaysOfSecondDate = getNumberOfDays(secondDate);

        /*
        Calculate absolute number of days between first data and second date
         */
        long numberOfDays = Math.abs(numberOfDaysOfSecondDate - numberOfDaysOfFirstDate) - 1;

        return numberOfDays;
    }

    private long getNumberOfDays(Date date) {

        /*
        Calculate total number of days of before a particular date: Year * 365 * Day + MONTH_DAY[month)
         */
        long numberOfDays = date.year * 365 + date.day;

        /*
        Adding days of month to total number of days
         */
        for (int i = 0; i < date.month - 1; i++) {
            numberOfDays += MONTH_DAYS[i];
        }

        /*
        Add a day for every leep year. Since leap year has 366 days
         */
        numberOfDays += getTotalLeapYears(date);

        return numberOfDays;
    }

    /*
    Counting the number of leap years before the given date
     */
    private int getTotalLeapYears(Date date) {

        int years = date.year;

        /*
        Check if the year need to be considered for couting leap years or not
         */
        if (date.month <= 2) {
            years--;
        }

        /*
        A year is called leap year if it is a multiple of 4, a multiple of 400, and not a multiple of 100
         */
        return (years / 4) + (years / 400) - (years / 100);
    }

}
