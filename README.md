# Cron Expression Parser

This cron expression editor will convert a a singular cron string to the frequency in which the cron will run.


## Running The Parser

Simply run the main method within the 'Main' class using your IDE, with the argument in your run configuration set to the cron string.

## Example

Cron string as argument:

	 */15 0 1,15 * 1-5 /usr/bin/find

Console Output:

	 minute:        0 15 30 45
	 hour:          0
	 day of month:  1 15
	 month:         1 2 3 4 5 6 7 8 9 10 11 12
	 day of week:   1 2 3 4 5
	 command:       /usr/bin/find 
