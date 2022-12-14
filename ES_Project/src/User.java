import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class User {

	private String userName;
	private String link;
	private Color colorPreference;
	private File userFile;

	public User(String userName, String link, Color colorPreference) {
		this.link = link;
		this.colorPreference = colorPreference;
		this.userName = userName;
	}
	
	public User(String userName, File userFile, Color colorPreference) {
		this.userName = userName;
		this.userFile = userFile;
		this.colorPreference = colorPreference;
	}
	
	public File getUserFile() {
		return userFile;
	}
	
	public void setUserFile(File f) {
		this.userFile = f;
	}

	public String getFileName() {
		return link;
	}

	public Color getColorPreference() {
		return colorPreference;
	}
	
	public String getLink() {
		return link;
	}

	public ArrayList<Event> getEventListFromLink() throws ParseException, IOException {
		return FillCalendar.returnEventListFromLink(this.link, this.userName);
	}
	
	public ArrayList<Event> getEventListFromFile() throws ParseException, IOException {
		return FillCalendar.returnEventListFromFile(this.userFile, this.userName);
	}

	public ArrayList<CalendarEvent> getCalendarEventList() throws ParseException, IOException {
		ArrayList<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>();
		ArrayList<Event> events = new ArrayList<>();
		if(this.link == null && this.userFile != null) {
			events = getEventListFromFile();
		}else if(this.userFile == null && this.link != null) {
			events = getEventListFromLink();
		}else {
			events = getEventListFromFile();
		}
		for (Event e : events) {
			LocalDateTime StartDate = toDate(e.getDateStart());
			LocalDateTime EndDate = toDate(e.getDateEnd());

			if (!(StartDate.getYear() == 0 || StartDate.getMonthValue() == 0 || StartDate.getDayOfMonth() == 0
					|| EndDate.getYear() == 0 || EndDate.getMonthValue() == 0 || EndDate.getDayOfMonth() == 0)) {

				calendarEvents.add(new CalendarEvent(this, StartDate.toLocalDate(), StartDate.toLocalTime(), EndDate.toLocalTime(),
						cutAfterTrace(insertPeriodically(e.getName(), "\n", 34))));

			}
		}
		for(CalendarEvent ce: calendarEvents) {
			ce.setColor(colorPreference);
		}
		return calendarEvents;
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
	
	public String toString() {
		return userName;
	}

}
