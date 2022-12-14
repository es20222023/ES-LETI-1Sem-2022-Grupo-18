import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class newEventFrame extends JFrame implements ActionListener {

// Components of the Form
	private Container c;
	private JLabel title;
	private JLabel nameLabel;
	private JTextField nameText;
	private JLabel descriptionLabel;
	private JTextArea descriptionText;

	private JLabel dateLabel;
	private JComboBox daysValues;
	private JComboBox monthsValues;
	private JComboBox yearsValues;
	private JButton submitButton;
	private JButton resetButton;
	private JTextArea outputText;
	private JLabel res;
	private JTextArea resadd;
	private JComboBox startTime;
	private JComboBox startMinutes;
	private JLabel startTimeLabel;
	private JComboBox endTime;
	private JComboBox endMinutes;
	private JLabel endTimeLabel;
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String descriptionString;
	

	private String dates[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private String months[] = { "Jan", "feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sup", "Oct", "Nov", "Dec" };
	private String years[] = { "2022", "2023" };

	private String startingHours[] = { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00",
			"12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30" };
	private String endingHours[] = { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00",
			"12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30",
			"18:00" };

// constructor, to initialize the components
// with default values.
	public newEventFrame() {
		setTitle("Marcar Novo Evento");
		setBounds(300, 90, 900, 600);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		c = getContentPane();
		c.setLayout(null);
		title = new JLabel("Marcar Novo Evento");
		title.setFont(new Font("Arial", Font.PLAIN, 30));
		title.setSize(300, 30);
		title.setLocation(300, 30);
		c.add(title);

		nameLabel = new JLabel("Nome");
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		nameLabel.setSize(100, 20);
		nameLabel.setLocation(100, 100);
		c.add(nameLabel);

		nameText = new JTextField();
		nameText.setFont(new Font("Arial", Font.PLAIN, 15));
		nameText.setSize(190, 20);
		nameText.setLocation(200, 100);
		c.add(nameText);

		dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dateLabel.setSize(100, 20);
		dateLabel.setLocation(100, 150);
		c.add(dateLabel);

		daysValues = new JComboBox(dates);
		daysValues.setFont(new Font("Arial", Font.PLAIN, 15));
		daysValues.setSize(50, 20);
		daysValues.setLocation(200, 150);
		c.add(daysValues);

		monthsValues = new JComboBox(months);
		monthsValues.setFont(new Font("Arial", Font.PLAIN, 15));
		monthsValues.setSize(60, 20);
		monthsValues.setLocation(250, 150);
		c.add(monthsValues);

		yearsValues = new JComboBox(years);
		yearsValues.setFont(new Font("Arial", Font.PLAIN, 15));
		yearsValues.setSize(60, 20);
		yearsValues.setLocation(320, 150);
		c.add(yearsValues);

		startTimeLabel = new JLabel("Inicio");
		startTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		startTimeLabel.setSize(100, 20);
		startTimeLabel.setLocation(100, 200);
		c.add(startTimeLabel);

		startTime = new JComboBox(startingHours);
		startTime.setFont(new Font("Arial", Font.PLAIN, 15));
		startTime.setSize(60, 20);
		startTime.setLocation(200, 200);
		c.add(startTime);

		endTimeLabel = new JLabel("Fim");
		endTimeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		endTimeLabel.setSize(100, 20);
		endTimeLabel.setLocation(100, 250);
		c.add(endTimeLabel);

		endTime = new JComboBox(endingHours);
		endTime.setFont(new Font("Arial", Font.PLAIN, 15));
		endTime.setSize(60, 20);
		endTime.setLocation(200, 250);
		c.add(endTime);

		descriptionLabel = new JLabel("Descrição");
		descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		descriptionLabel.setSize(100, 20);
		descriptionLabel.setLocation(100, 300);
		c.add(descriptionLabel);

		descriptionText = new JTextArea();
		descriptionText.setFont(new Font("Arial", Font.PLAIN, 15));
		descriptionText.setSize(200, 140);
		descriptionText.setLocation(200, 300);
		c.add(descriptionText);

		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
		submitButton.setSize(100, 20);
		submitButton.setLocation(150, 450);
		submitButton.addActionListener(this);
		c.add(submitButton);
		
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
		resetButton.setSize(100, 20);
		resetButton.setLocation(270, 450);
		resetButton.addActionListener(this);
		c.add(resetButton);

		outputText = new JTextArea();
		outputText.setFont(new Font("Arial", Font.PLAIN, 15));
		outputText.setSize(300, 400);
		outputText.setLocation(500, 100);
		outputText.setLineWrap(true);
		outputText.setEditable(false);
		c.add(outputText);

		res = new JLabel("");
		res.setFont(new Font("Arial", Font.PLAIN, 20));
		res.setSize(500, 25);
		res.setLocation(100, 500);
		c.add(res);

		resadd = new JTextArea();
		resadd.setFont(new Font("Arial", Font.PLAIN, 15));
		resadd.setSize(200, 75);
		resadd.setLocation(580, 175);
		resadd.setLineWrap(true);
		c.add(resadd);
		setVisible(true);
	}

	// method actionPerformed()
	// to get the action performed
	// by the user and act accordingly
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			int a = toTime((String) endTime.getSelectedItem()).compareTo(toTime((String) startTime.getSelectedItem()));
			if (!(startTime.getSelectedItem().equals(endTime.getSelectedItem())) && !(a < 0)) {
				String data = "Name : " + nameText.getText() + "\n";
				String data1 = "\nData : " + (String) daysValues.getSelectedItem() + "/" + (String) monthsValues.getSelectedItem()
						+ "/" + (String) yearsValues.getSelectedItem() + "\n";

				String data2 = "\nHora de Inicio: " + (String) startTime.getSelectedItem() + "\n";

				String data3 = "\nHora de fim: " + (String) endTime.getSelectedItem() + "\n";

				String data4 = "\nDescrição : " + descriptionText.getText();
				outputText.setText(data + data1 + data2 + data3 + data4);
				outputText.setEditable(false);
				res.setText("Evento Agendado Com Sucesso");
				name = nameText.getText();
				LocalTime lt1 = toTime((String )startTime.getSelectedItem());
				LocalTime lt2 = toTime((String) endTime.getSelectedItem());
				startDate = LocalDateTime.of((int) yearsValues.getSelectedItem(), (int) monthsValues.getSelectedItem(), (int) daysValues.getSelectedItem(), lt1.getHour(), lt1.getMinute());
				endDate = LocalDateTime.of((int) yearsValues.getSelectedItem(), (int) monthsValues.getSelectedItem(), (int) daysValues.getSelectedItem(), lt2.getHour(), lt2.getMinute());
				descriptionString = descriptionText.getText();	
			} else if(startTime.getSelectedItem().equals(endTime.getSelectedItem())){
				newErrorMessage("Hora de inicio e fim não podem coincidir.");
			}else if (toTime((String) endTime.getSelectedItem()).compareTo(toTime((String) startTime.getSelectedItem())) < 0) {
				newErrorMessage("Hora de fim tem de ser menor do que a de inicio.");
			}

		} else if (e.getSource() == resetButton) {
			String def = "";
			nameText.setText(def);
			descriptionText.setText(def);
			res.setText(def);
			outputText.setText(def);
			daysValues.setSelectedIndex(0);
			monthsValues.setSelectedIndex(0);
			yearsValues.setSelectedIndex(0);
			startTime.setSelectedIndex(0);
			endTime.setSelectedIndex(0);
			resadd.setText(def);
		}
	}
	
	public static LocalTime toTime(String a) {
		LocalTime lt = LocalTime.of(untilDots(a), afterDots(a));
		return lt;
	}
	
	public static int untilDots(String a) {
		int i = a.indexOf(":");
		String b = a.substring(0,i);
		return Integer.parseInt(b);
	}
	
	public static int afterDots(String a){
		int i = a.indexOf(":");
		String b = a.substring(i+1,a.length());
		return Integer.parseInt(b);
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getDate() {
		return LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
	}
	
	public LocalTime getStartTime() {
		return LocalTime.of(startDate.getHour(), startDate.getMinute());
	}
	
	public LocalTime getEndTime() {
		return LocalTime.of(endDate.getHour(), endDate.getMinute());
	}
	
	public String getDescription() {
		return descriptionString;
	}
	
	public JButton getSubmitButton() {
		return submitButton;
	}
	
	public void newErrorMessage(String errorMessage) {
		outputText.setText("");
		resadd.setText("");
		res.setText(errorMessage);
	}
}