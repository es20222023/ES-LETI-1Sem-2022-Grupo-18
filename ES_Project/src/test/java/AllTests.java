import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BarChartTest.class, CalendarEngineTest.class, CalendarEventTest.class, CalendarTest.class,
		EventTest.class, FillCalendarTest.class, IcsToJsonTest.class, JFrameToPDFTest.class, MainTest.class,
		MongoDBTest.class, WeekCalendarTest.class })
public class AllTests {

}
