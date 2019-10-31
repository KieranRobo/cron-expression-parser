public class Main {

    public static void main(String[] args) {
        if (args.length < 6) {
            System.err.println("Invalid cron.");
            System.exit(0);
        }

        // Get schedule info from args
	    String[] schedule = new String[5];
        System.arraycopy(args, 0, schedule, 0, 5);

        // All args after schedule must be command
	    String command = getCommandFromArgs(args);

	    try {
            Cron cron = new Cron(schedule, command);
            System.out.println(cron.toString());
        } catch (InvalidCronException ex) {
	        System.err.println("Cannot process invalid cron string.");
        }
    }

    /**
     * Gets command from cron string.
     * @param args cron arguments provided to program, including schedule.
     * @return cron command.
     */
    private static String getCommandFromArgs(String[] args) {
        StringBuilder command = new StringBuilder();
        for (int i=5; i<args.length; i++) {
            command.append(args[i]);
            command.append(" ");
        }
        return command.toString();
    }
}
