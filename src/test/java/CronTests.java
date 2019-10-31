import org.junit.Assert;
import org.junit.Test;

public class CronTests {

    @Test
    public void basicCronScheduleTest() {
        CronSchedule cronSchedule = new CronSchedule("*", "*", "*", "*", "*");
        Assert.assertEquals(60, cronSchedule.getMinutes().length);
        Assert.assertEquals(24, cronSchedule.getHours().length);
        Assert.assertEquals(31, cronSchedule.getMonthDays().length);
        Assert.assertEquals(12, cronSchedule.getMonths().length);
        Assert.assertEquals(7, cronSchedule.getWeekDays().length);
    }

    @Test
    public void basicEveryScheduleTest() {
        CronSchedule cronSchedule = new CronSchedule("*", "*", "*", "*", "*");
        Assert.assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5, 6 }, cronSchedule.getWeekDays());
    }

    @Test
    public void basicAfterEveryScheduleTest() {
        CronSchedule cronSchedule = new CronSchedule("*/15", "*", "*", "*", "*");
        Assert.assertArrayEquals(new int[] { 0, 15, 30, 45 }, cronSchedule.getMinutes());
    }

    @Test
    public void basicFromToScheduleTest() {
        CronSchedule cronSchedule = new CronSchedule("5-10", "*", "*", "*", "*");
        Assert.assertArrayEquals(new int[] { 5, 6, 7, 8, 9, 10 }, cronSchedule.getMinutes());
    }

    @Test
    public void basicEachScheduleTest() {
        CronSchedule cronSchedule = new CronSchedule("1,2", "*", "*", "*", "*");
        Assert.assertArrayEquals(new int[] { 1, 2 }, cronSchedule.getMinutes());
    }

}
