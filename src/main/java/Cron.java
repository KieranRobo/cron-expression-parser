public class Cron {

    private CronSchedule schedule;
    private String command;

    public Cron(CronSchedule schedule, String command) {
        this.schedule = schedule;
        this.command = command;
    }

    public Cron(String[] schedule, String command) throws InvalidCronException {
        this.schedule = new CronSchedule(schedule[0], schedule[1], schedule[2], schedule[3], schedule[4]);
        this.command = command;

    }

    /*
     * @return main.java.CronSchedule this main.java.Cron follows.
     */
    public CronSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(CronSchedule schedule) {
        this.schedule = schedule;
    }

    /*
     * @return command this cron should run in the frequency defined by schedule.
     */
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return String representation of this main.java.Cron in this format:
     * minute:        [NUM] [NUM] ....
     * hour:          [NUM] [NUM] ....
     * day of month:  [NUM] [NUM] ....
     * month:         [NUM] [NUM] ....
     * day of week:   [NUM] [NUM] ....
     * command:       command
     */
    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append("minute:       ");
        for (int freq : schedule.getMinutes()) {
            outputString.append(" ");
            outputString.append(freq);
        }
        outputString.append("\nhour:         ");
        for (int freq : schedule.getHours()) {
            outputString.append(" ");
            outputString.append(freq);
        }
        outputString.append("\nday of month: ");
        for (int freq : schedule.getMonthDays()) {
            outputString.append(" ");
            outputString.append(freq);
        }
        outputString.append("\nmonth:        ");
        for (int freq : schedule.getMonths()) {
            outputString.append(" ");
            outputString.append(freq+1);
        }
        outputString.append("\nday of week:  ");
        for (int freq : schedule.getWeekDays()) {
            outputString.append(" ");
            outputString.append(freq);
        }
        outputString.append("\ncommand:       ");
        outputString.append(command);

        return outputString.toString();
    }
}
