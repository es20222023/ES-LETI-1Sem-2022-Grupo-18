import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ JsonToPdfTest.class, MainTest.class })
public class AllTests {

}
