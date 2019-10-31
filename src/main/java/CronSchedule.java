import java.util.ArrayList;
import java.util.List;

public class CronSchedule {

    private int[] minute, hours, monthDays, months, weekDays;

    public CronSchedule(String minute, String hour, String monthDay, String month, String weekDay) throws InvalidCronException {
        try {
            this.minute = convertToFrequency(minute, 60);
            this.hours = convertToFrequency(hour, 24);
            this.monthDays = convertToFrequency(monthDay, 31);
            this.months = convertToFrequency(month, 12);
            this.weekDays = convertToFrequency(weekDay, 7);
        } catch (NumberFormatException ex) {
            throw new InvalidCronException("Cannot parse invalid cron string.");
        }
    }

    /**
     * Converts a cron schedule element to a list of all times to be ran.
     * @param scheduleElement singular element of a cron schedule (ie, "*")
     * @param maxFrequency maximum frequency scheduleElement is allowed (7 for the days element, for example)
     * @return list of all integer representations of when this part of cron element is ran.
     */
    private int[] convertToFrequency(String scheduleElement, int maxFrequency) {
        char[] elementChars = scheduleElement.toCharArray();
        if (elementChars[0] == '*' && elementChars.length == 1) {
            return parseEveryElement(maxFrequency);
        }
        else if (scheduleElement.contains("/")) {
            return parseAfterEveryElement(scheduleElement, maxFrequency);
        }
        else if (scheduleElement.contains("-")) {
           return parseFromToElement(scheduleElement);
        }
        else if (scheduleElement.contains(",")) {
            return parseEachElement(scheduleElement);
        }
        else {
            int value = Integer.valueOf(scheduleElement);
            return new int[] { value };
        }
    }

    /**
     * Parses the "*" element into a list of integer representation of all times to be ran.
     * @param maxFrequency maximum frequency allowed (7 for the days element, for example)
     * @return list of all integer representations of when this part of cron element is ran.
     */
    private int[] parseEveryElement(int maxFrequency) {
        int[] frequency = new int[maxFrequency];
        for (int i=0; i<maxFrequency; i++) {
            frequency[i] = i;
        }
        return frequency;
    }

    /**
     * Parses the "/[NUM]" element into a list of integer representation of all times to be ran.
     * @param element singular element of a cron schedule (ie, "*")
     * @param maxFrequency maximum frequency allowed (7 for the days element, for example)
     * @return list of all integer representations of when this part of cron element is ran.
     */
    private int[] parseAfterEveryElement(String element, int maxFrequency) {
        List<Integer> frequency = new ArrayList<>();
        char[] elementChars = element.toCharArray();

        StringBuilder divisionChars = new StringBuilder();
        for (int i=2; i<elementChars.length; i++) divisionChars.append(elementChars[i]);
        int divisionValue = Integer.valueOf(divisionChars.toString());

        for (int currentFrequency = 0; currentFrequency<maxFrequency; currentFrequency += divisionValue) {
            frequency.add(currentFrequency);
        }
        return frequency.stream().mapToInt(i->i).toArray();
    }

    /**
     * Parses the "[NUM]-[NUM]" element into a list of integer representation of all times to be ran.
     * @param element singular element of a cron schedule (ie, "*")
     * @return list of all integer representations of when this part of cron element is ran.
     */
    private int[] parseFromToElement(String element) {
        List<Integer> frequency = new ArrayList<>();
        String[] fromToExpression = element.split("-");

        int fromValue = Integer.valueOf(fromToExpression[0]);
        int toValue = Integer.valueOf(fromToExpression[1]);

        for (int currentFrequency = fromValue; currentFrequency <= toValue; currentFrequency++) {
            frequency.add(currentFrequency);
        }
        return frequency.stream().mapToInt(i->i).toArray();
    }

    /**
     * Parses the "[NUM],[NUM]" element into a list of integer representation of all times to be ran.
     * @param element singular element of a cron schedule (ie, "*")
     * @return list of all integer representations of when this part of cron element is ran.
     */
    private int[] parseEachElement(String element) {
        List<Integer> frequency = new ArrayList<>();
        String[] allFrequency = element.split(",");
        for (String frequencyElement : allFrequency) {
            int value = Integer.valueOf(frequencyElement);
            frequency.add(value);
        }
        return frequency.stream().mapToInt(i->i).toArray();
    }

    /**
     * @return list of all minutes this cron schedule should run.
     */
    public int[] getMinutes() {
        return minute;
    }

    /**
     * @return list of all hours this cron schedule should run.
     */
    public int[] getHours() {
        return hours;
    }

    /**
     * @return list of all days in the months this cron schedule should run.
     */
    public int[] getMonthDays() {
        return monthDays;
    }

    /**
     * @return list of all months this cron schedule should run.
     */
    public int[] getMonths() {
        return months;
    }

    /**
     * @return list of all days in week this cron schedule should run.
     */
    public int[] getWeekDays() {
        return weekDays;
    }


}
