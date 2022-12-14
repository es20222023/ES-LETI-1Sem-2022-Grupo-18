import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BarChartTest.class, CalendarTest.class, MainTest.class, WeekCalendarTest.class })
public class AllTests {
	public static void main(String [] args) {
		
	}
}
