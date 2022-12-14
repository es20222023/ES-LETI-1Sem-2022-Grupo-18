import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import com.itextpdf.text.DocumentException;
/**
 * 
 * Main Class
 *
 */
public class Main {
	/**
	 * 
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	public static void main(String [] args) throws ParseException, IOException, DocumentException, InvocationTargetException, InterruptedException {
		CalendarEngine calendar = new CalendarEngine();
		calendar.startCalendar();
	}
}
