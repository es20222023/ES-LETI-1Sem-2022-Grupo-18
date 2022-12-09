import javax.swing.*;

import com.itextpdf.text.DocumentException;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WeekCalendarTest {
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<CalendarEvent> events = new ArrayList<>();
	public static WeekCalendar cal;

	public static void main(String[] args) throws ParseException, IOException, DocumentException {

		User andre = new User("André", "andre.json", Color.GRAY);
		User madalena = new User("Madalena", "madalena.json", Color.PINK);
		users.add(andre);
		users.add(madalena);
		JFrame frm = new JFrame();

		for (User u : users) {
			events.addAll(u.getCalendarEventList());
		}

		cal = new WeekCalendar(events);

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
		toPDFButton.addActionListener(e -> {
			try {
				JFrameToPDF.JFrameToPDF(frm);
			} catch (IOException | DocumentException e1) {
				e1.printStackTrace();
			}
		});

		JButton newEventButton = new JButton("Novo Evento");
		newEventButton.addActionListener(e -> scheduleEvent());

		JPanel southButtons = new JPanel();
		southButtons.add(toPDFButton);
		southButtons.add(newEventButton);

		JPanel checkBoxes = new JPanel();
		checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.Y_AXIS));
		for (User u : users) {
			JCheckBox jc = new JCheckBox(u.getUserName());
			jc.setSelected(true);
			checkBoxes.add(jc);
		}

		for (Component c : checkBoxes.getComponents()) {
			((JCheckBox) c).addActionListener(e -> {
				try {
					checkBoxPressed((JCheckBox) c);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			});
		}

		JPanel weekControls = new JPanel();
		weekControls.add(prevMonthBtn);
		weekControls.add(prevWeekBtn);
		weekControls.add(goToTodayBtn);
		weekControls.add(nextWeekBtn);
		weekControls.add(nextMonthBtn);

		frm.add(weekControls, BorderLayout.NORTH);
		frm.add(southButtons, BorderLayout.SOUTH);
		frm.add(checkBoxes, BorderLayout.WEST);

		frm.add(cal, BorderLayout.CENTER);
		frm.setSize(1500, 800);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void checkBoxPressed(JCheckBox jc) throws ParseException {
		User user = null;
		for (User u : users) {
			if (u.getUserName().equals(jc.getText()))
				user = u;
		}

		if (!jc.isSelected()) {
			events.removeAll(user.getCalendarEventList());
		} else {
			events.addAll(user.getCalendarEventList());
		}

		cal.repaint();
	}

	public static void scheduleEvent() {
		JFrame chooseUser = new JFrame();
		JPanel user = new JPanel();
		JPanel buttons = new JPanel();
		JComboBox<String> combo = new JComboBox<String>(userNamesToArray());
		JLabel label = new JLabel("Selecione o utilizador para o qual pretende marcar um evento");
		JButton ok = new JButton("Ok");
		JButton cancelar = new JButton("Cancelar");
		String userName = (String) combo.getSelectedItem();
		User us = null;
		for (User u : users) {
			if (u.getUserName().equals(userName))
				us = u;
		}
		final User user2 = us;
		ok.addActionListener(e -> newEventFrame(user2));
		ok.addActionListener(e -> chooseUser.dispose());
		cancelar.addActionListener(e -> chooseUser.dispose());
		buttons.add(ok);
		buttons.add(cancelar);
		user.add(label);
		user.add(combo);
		chooseUser.add(user);
		chooseUser.add(buttons, BorderLayout.SOUTH);
		chooseUser.setVisible(true);
		chooseUser.setSize(400, 150);
	}

	public static void newEventFrame(User user) {
		String dates[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		String months[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		String years[] = { "2022", "2023" };
		String startingHours[] = { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30" };
		String endingHours[] = { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00" };

		JFrame frame = new JFrame();
		frame.setTitle("Marcar Novo Evento");
		frame.setBounds(300, 90, 900, 600);
		frame.setResizable(false);

		Container c = frame.getContentPane();
		c.setLayout(null);

		JLabel title = new JLabel("Marcar Novo Evento");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(300, 30);
		title.setLocation(300, 30);
		c.add(title);

		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		nameLabel.setSize(100, 20);
		nameLabel.setLocation(100, 100);
		c.add(nameLabel);

		JTextField nameText = new JTextField();
		nameText.setFont(new Font("Arial", Font.PLAIN, 15));
		nameText.setSize(190, 20);
		nameText.setLocation(200, 100);
		c.add(nameText);

		JLabel descriptionLabel = new JLabel("Descrição");
		descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		descriptionLabel.setSize(100, 20);
		descriptionLabel.setLocation(100, 300);
		c.add(descriptionLabel);

		JTextArea descriptionText = new JTextArea();
		descriptionText.setFont(new Font("Arial", Font.PLAIN, 15));
		descriptionText.setSize(200, 140);
		descriptionText.setLocation(200, 300);
		c.add(descriptionText);

		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dateLabel.setSize(100, 20);
		dateLabel.setLocation(100, 150);
		c.add(dateLabel);

		JComboBox<String> daysValues = new JComboBox<String>(dates);
		daysValues.setFont(new Font("Arial", Font.PLAIN, 15));
		daysValues.setSize(50, 20);
		daysValues.setLocation(200, 150);
		c.add(daysValues);

		JComboBox<String> monthsValues = new JComboBox<String>(months);
		monthsValues.setFont(new Font("Arial", Font.PLAIN, 15));
		monthsValues.setSize(60, 20);
		monthsValues.setLocation(250, 150);
		c.add(monthsValues);

		JComboBox<String> yearsValues = new JComboBox<String>(years);
		yearsValues.setFont(new Font("Arial", Font.PLAIN, 15));
		yearsValues.setSize(60, 20);
		yearsValues.setLocation(320, 150);
		c.add(yearsValues);

		JButton submitButton = new JButton("Submeter");
		submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
		submitButton.setSize(100, 20);
		submitButton.setLocation(150, 450);
		c.add(submitButton);

		JButton resetButton = new JButton("Limpar");
		resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
		resetButton.setSize(100, 20);
		resetButton.setLocation(270, 450);
		c.add(resetButton);

		JTextArea outputText = new JTextArea();
		outputText.setFont(new Font("Arial", Font.PLAIN, 15));
		outputText.setSize(300, 400);
		outputText.setLocation(500, 100);
		outputText.setLineWrap(true);
		outputText.setEditable(false);
		c.add(outputText);

		JLabel res = new JLabel("");
		res.setFont(new Font("Arial", Font.PLAIN, 20));
		res.setSize(500, 25);
		res.setLocation(100, 500);
		c.add(res);

		JTextArea resadd = new JTextArea();
		resadd.setFont(new Font("Arial", Font.PLAIN, 15));
		resadd.setSize(200, 75);
		resadd.setLocation(580, 175);
		resadd.setLineWrap(true);
		c.add(resadd);

		JComboBox<String> startTime = new JComboBox<String>(startingHours);
		startTime.setFont(new Font("Arial", Font.PLAIN, 15));
		startTime.setSize(60, 20);
		startTime.setLocation(200, 200);
		c.add(startTime);

		JLabel startTimeLabel;
		startTimeLabel = new JLabel("Inicio");
		startTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		startTimeLabel.setSize(100, 20);
		startTimeLabel.setLocation(100, 200);
		c.add(startTimeLabel);

		JComboBox<String> endTime = new JComboBox<String>(endingHours);
		endTime.setFont(new Font("Arial", Font.PLAIN, 15));
		endTime.setSize(60, 20);
		endTime.setLocation(200, 250);
		c.add(endTime);

		JLabel endTimeLabel = new JLabel("Fim");
		endTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		endTimeLabel.setSize(100, 20);
		endTimeLabel.setLocation(100, 250);
		c.add(endTimeLabel);

		frame.setVisible(true);

		submitButton.addActionListener(e -> {
			try {
				submitPressed(user, nameText, daysValues, monthsValues, yearsValues, startTime, endTime,
						descriptionText, outputText, res, resadd);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		});
		resetButton.addActionListener(e -> resetPressed(nameText, daysValues, monthsValues, yearsValues, startTime,
				endTime, descriptionText, outputText, res, resadd));
	}

	public static void submitPressed(User user, JTextField nameText, JComboBox<String> day, JComboBox<String> month,
			JComboBox<String> year, JComboBox<String> startHour, JComboBox<String> endHour, JTextArea descriptionArea,
			JTextArea output, JLabel res, JTextArea resadd) throws ParseException {
		LocalDate date = LocalDate.of(Integer.parseInt((String) year.getSelectedItem()),
				Integer.parseInt((String) month.getSelectedItem()), Integer.parseInt((String) day.getSelectedItem()));
		LocalTime start = toTime((String) startHour.getSelectedItem());
		LocalTime end = toTime((String) endHour.getSelectedItem());
		if (!(nameText.getText() == null || descriptionArea.getText() == null || nameText.getText().equals("") || descriptionArea.getText().equals(""))) {
			if (!(start).equals(end) && !(end.compareTo(start) < 0)) {
				String data = "Name : " + nameText.getText() + "\n";
				String data1 = "\nData : " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear()
						+ "\n";
				String data2 = "\nHora de Inicio: " + startHour.getSelectedItem() + "\n";
				String data3 = "\nHora de fim: " + endHour.getSelectedItem() + "\n";
				String data4 = "\nDescrição : " + descriptionArea.getText();

				output.setText(data + data1 + data2 + data3 + data4);
				output.setEditable(false);
				CalendarEvent event = new CalendarEvent(user, date, start, end, descriptionArea.getText(),
						user.getColorPreference());
				if (checkAvaliability(user, event)) {
					events.add(event);
					cal.repaint();
					res.setText("Evento Agendado Com Sucesso");
				} else {
					output.setText("");
					resadd.setText("");
					res.setText("Horário não disponível, por favor selecione outro horário");
				}
			} else if (start.equals(end)) {
				output.setText("");
				resadd.setText("");
				res.setText("Hora de inicio e fim não podem coincidir");
			} else if (end.compareTo(start) < 0) {
				output.setText("");
				resadd.setText("");
				res.setText("Hora de fim tem de ser supeior à hora de inicio");
			}
		}else {
			output.setText("");
			resadd.setText("");
			res.setText("Formulário incompleto");
		}
	}

	public static void addToJson(User user, CalendarEvent ce) {

	}

	public static void resetPressed(JTextField nameText, JComboBox<String> day, JComboBox<String> month,
			JComboBox<String> year, JComboBox<String> startHour, JComboBox<String> endHour, JTextArea descriptionArea,
			JTextArea output, JLabel res, JTextArea resadd) {
		String def = "";
		nameText.setText(def);
		descriptionArea.setText(def);
		res.setText(def);
		output.setText(def);
		day.setSelectedIndex(0);
		month.setSelectedIndex(0);
		year.setSelectedIndex(0);
		startHour.setSelectedIndex(0);
		endHour.setSelectedIndex(0);
		resadd.setText(def);
	}

	public static boolean checkAvaliability(User user, CalendarEvent ce) throws ParseException {
		ArrayList<CalendarEvent> userEvents = user.getCalendarEventList();
		ArrayList<CalendarEvent> dayEvents = new ArrayList<>();
		for (CalendarEvent e : userEvents) {
			if (e.getDate().equals(ce.getDate()))
				dayEvents.add(e);
		}
		for (CalendarEvent e : dayEvents) {
			if (ce.getStart().compareTo(e.getStart()) >= 0 && ce.getEnd().compareTo(e.getEnd()) <= 0)
				return false;
			if (ce.getStart().compareTo(e.getStart()) <= 0 && ce.getEnd().compareTo(e.getStart()) > 0
					&& ce.getEnd().compareTo(e.getEnd()) <= 0)
				return false;
		}
		return true;
	}

	public static LocalTime toTime(String a) {
		LocalTime lt = LocalTime.of(untilDots(a), afterDots(a));
		return lt;
	}

	public static int untilDots(String a) {
		int i = a.indexOf(":");
		String b = a.substring(0, i);
		return Integer.parseInt(b);
	}

	public static int afterDots(String a) {
		int i = a.indexOf(":");
		String b = a.substring(i + 1, a.length());
		return Integer.parseInt(b);
	}

	public static String[] userNamesToArray() {
		String[] userNames = new String[users.size()];
		int i = 0;
		for (User u : users) {
			userNames[i] = u.getUserName();
			i++;
		}
		return userNames;
	}
}
