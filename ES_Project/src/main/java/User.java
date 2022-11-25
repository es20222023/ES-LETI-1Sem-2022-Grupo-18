import java.awt.Color;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {

	private String userName;
	private String fileName;
	private Color colorPreference;

	public User(String userName, String fileName, Color colorPreference) {
		this.fileName = fileName;
		this.colorPreference = colorPreference;
		this.userName = userName;
	}

	public String getFileName() {
		return fileName;
	}

	public Color getColorPreference() {
		return colorPreference;
	}

	public ArrayList<Event> getEventList() throws ParseException {
		return FillCalendar.returnEventList(fileName);
	}

	public ArrayList<CalendarEvent> getCalendarEventList() throws ParseException {
		ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();
		for (Event e : getEventList()) {
			LocalDateTime StartDate = toDate(e.getDtstart());
			LocalDateTime EndDate = toDate(e.getDtend());

			if (!(StartDate.getYear() == 0 || StartDate.getMonthValue() == 0 || StartDate.getDayOfMonth() == 0
					|| EndDate.getYear() == 0 || EndDate.getMonthValue() == 0 || EndDate.getDayOfMonth() == 0)) {

				events.add(new CalendarEvent(StartDate.toLocalDate(), StartDate.toLocalTime(), EndDate.toLocalTime(),
						cutAfterTrace(insertPeriodically(e.getSummary(), "\n", 15))));

			}
		}
		for(CalendarEvent ce: events) {
			ce.setColor(colorPreference);
		}
		return events;
	}

	public static String removeTZ(String a) {
		a = a.replaceAll("T", "");
		a = a.replaceAll("Z", "");
		return a;
	}

	public static LocalDateTime toDate(String a) throws ParseException {
		String date = removeTZ(a);
		String pattern = "yyyyMMddHHmmss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime d = LocalDateTime.parse(date, formatter);
		return d;
	}

	public static String insertPeriodically(String text, String insert, int period) {
		StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length() / period) + 1);

		int index = 0;
		String prefix = "";
		while (index < text.length()) {
			// Don't put the insert in the very first iteration.
			// This is easier than appending it *after* each substring
			builder.append(prefix);
			prefix = insert;
			builder.append(text.substring(index, Math.min(index + period, text.length())));
			index += period;
		}
		return builder.toString();
	}

	public static String cutAfterTrace(String str) {
		int index = str.indexOf("-");
		if (index >= 0)
			str = str.substring(0, index);
		return str;
	}
	
	public String getUserName() {
		return this.userName;
	}

}
