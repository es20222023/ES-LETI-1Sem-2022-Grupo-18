import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import com.itextpdf.text.DocumentException;

public class Main {
	public static void main(String [] args) throws ParseException, IOException, DocumentException, InvocationTargetException, InterruptedException {
		CalendarEngine calendar = new CalendarEngine();
		calendar.startCalendar();
	}
}
