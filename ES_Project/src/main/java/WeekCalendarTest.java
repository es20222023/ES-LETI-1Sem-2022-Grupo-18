

//import com.davidmoodie.SwingCalendar.CalendarEvent;
//import com.davidmoodie.SwingCalendar.WeekCalendar;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class WeekCalendarTest {
//	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException {
		//System.out.println(toDate("20230223T160000Z"));
		JFrame frm = new JFrame();
//
		ArrayList<Event> eventss = FillCalendar.returnEventList();
		ArrayList<CalendarEvent> events = new ArrayList<>();
//		// events.add(new CalendarEvent(LocalDate.of(2022, 11, 11), LocalTime.of(14, 0),
//		// LocalTime.of(14, 20), "Test 11/11 14:00-14:20"));
//		// events.add(new CalendarEvent(LocalDate.of(2022, 11, 14), LocalTime.of(9, 0),
//		// LocalTime.of(9, 20), "Test 14/11 9:00-9:20"));
//		// events.add(new CalendarEvent(LocalDate.of(2022, 11, 15), LocalTime.of(12, 0),
//		// LocalTime.of(13, 20), "Test 15/11 12:00-13:20"));
//		// events.add(new CalendarEvent(LocalDate.of(2022, 11, 16), LocalTime.of(9, 0),
//		// LocalTime.of(9, 20), "Test 16/11 9:00-9:20"));
//		// events.add(new CalendarEvent(LocalDate.of(2022, 11, 17), LocalTime.of(12,
//		// 15), LocalTime.of(14, 20), "Test 17/11 12:15-14:20"));
//		// events.add(new CalendarEvent(LocalDate.of(2022, 11, 18), LocalTime.of(9, 30),
//		// LocalTime.of(10, 00), "Test 18/11 9:30-10:00"));
//		// events.add(new CalendarEvent(LocalDate.of(2022, 11, 18), LocalTime.of(16,
//		// 00), LocalTime.of(16, 45), "Test 18/11 16:00-16:45"));
//		// eventList.forEach(x -> System.out.println(x.toString()));
//
//		
//		// CalendarEvent(LocalDate.of(toDate(x.getDtstart()).getYear(),) null, null,
//		// null)));
		for (Event e : eventss) {
			LocalDateTime StartDate = toDate(e.getDtstart());
			LocalDateTime EndDate = toDate(e.getDtend());

			if (!(StartDate.getYear() == 0 || StartDate.getMonthValue() == 0 || StartDate.getDayOfMonth() == 0 ||
					EndDate.getYear() == 0 || EndDate.getMonthValue() == 0 || EndDate.getDayOfMonth() == 0)) {
				
					events.add(new CalendarEvent(StartDate.toLocalDate(), StartDate.toLocalTime(), EndDate.toLocalTime(), cutAfterTrace(insertPeriodically(e.getSummary(),  "\n", 15) )));
				
			}
		}

		WeekCalendar cal = new WeekCalendar(events);

		cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
		cal.addCalendarEmptyClickListener(e -> {
			System.out.println(e.getDateTime());
			System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
		});

		JButton goToTodayBtn = new JButton("Today");
		goToTodayBtn.addActionListener(e -> cal.goToToday());

		JButton nextWeekBtn = new JButton(">");
		nextWeekBtn.addActionListener(e -> cal.nextWeek());

		JButton prevWeekBtn = new JButton("<");
		prevWeekBtn.addActionListener(e -> cal.prevWeek());

		JButton nextMonthBtn = new JButton(">>");
		nextMonthBtn.addActionListener(e -> cal.nextMonth());

		JButton prevMonthBtn = new JButton("<<");
		prevMonthBtn.addActionListener(e -> cal.prevMonth());

		JButton toPDFButton = new JButton("Generate PDF");

		JPanel PDFcontrol = new JPanel();
		PDFcontrol.add(toPDFButton);

		JPanel weekControls = new JPanel();
		weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(nextMonthBtn);

		frm.add(weekControls, BorderLayout.NORTH);
		frm.add(PDFcontrol, BorderLayout.SOUTH);

		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1000, 900);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
	
	public static String insertPeriodically (String text, String insert, int period){
		    StringBuilder builder = new StringBuilder(
		         text.length() + insert.length() * (text.length()/period)+1);

		    int index = 0;
		    String prefix = "";
		    while (index < text.length())
		    {
		        // Don't put the insert in the very first iteration.
		        // This is easier than appending it *after* each substring
		        builder.append(prefix);
		        prefix = insert;
		        builder.append(text.substring(index, 
		            Math.min(index + period, text.length())));
		        index += period;
		    }
		    return builder.toString();
		}
	
	public static String cutAfterTrace(String str) {
		int index = str.indexOf("-");
		if(index >= 0)
			str = str.substring(0, index);
		return str;
	}
}
