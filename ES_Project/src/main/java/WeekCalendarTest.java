import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class WeekCalendarTest {
	public static void main(String[] args) throws ParseException {

		ArrayList<User> users = new ArrayList<User>();
		User andre = new User("andre.json", Color.GRAY);
		User madalena = new User("madalena.json", Color.PINK);
		users.add(andre);
		users.add(madalena);
		ArrayList<CalendarEvent> events = new ArrayList<>();
		JFrame frm = new JFrame();

		for (User u : users) {
			events.addAll(u.getCalendarEventList());
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

}
