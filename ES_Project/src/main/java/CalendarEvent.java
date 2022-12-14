

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single Calendar Event that later can be added to 
 * an event list to form a new Calendar.
 *
 */
public class CalendarEvent {

	
    private static final Color DEFAULT_COLOR = Color.PINK;
    
    private LocalDate date;
    
    private LocalTime start;
    
    private LocalTime end;
    
    private String text;
    
    private Color color;
    
    private User user;
    
    private boolean visible = true;

    public CalendarEvent(User user,LocalDate date, LocalTime start, LocalTime end, String text) {
        this(user, date, start, end, text, DEFAULT_COLOR);
    }

    /**
     * 
     * @param user
     * @param date
     * @param start
     * @param end
     * @param text
     * @param color
     * 
     * Creates a new CalendarEvent with a especific color
     */
    public CalendarEvent(User user,LocalDate date, LocalTime start, LocalTime end, String text, Color color) {
    	this.user = user;
        this.date = date;
        this.start = start;
        this.end = end;
        this.text = text;
        this.color = color;
    }
    /**
     * Returns if the event is to be visualized or not
     * @return visibility of the event
     */
    public boolean isVisible() {
    	return visible;
    }
    /**
     * Sets the visibility
     * @param a
     */
    public void setVisible(boolean a) {
    	this.visible = a;
    }
    /**
     * Gets the user
     * @return User
     */
    public User getUser() {
    	return user;
    }
    /**
     * Gets the event Date
     * @return the Event date
     */
    public LocalDate getDate() {
        return date;
    }
/**
 * Sets a new Date for the event
 * @param date
 */
    public void setDate(LocalDate date) {
        this.date = date;
    }
/**
 * Gets the event start time
 * @return Event startTime
 */
    public LocalTime getStart() {
        return start;
    }
    /**
     * Sets the new event start time
     * @param start
     */
    public void setStart(LocalTime start) {
        this.start = start;
    }
/**
 * Gets Event end time
 * @return Events end time
 */
    public LocalTime getEnd() {
        return end;
    }
    /**
     * Sets Event end Time
     * @param end
     */
    public void setEnd(LocalTime end) {
        this.end = end;
    }

    /**
     * Gets Event text
     * @return text
     */
    public String getText() {
        return text;
    }
    
    /**
     * Sets new Text for Event
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Puts Event to String
     */
    public String toString() {
        return getUser() + " " + getDate() + " " + getStart() + "-" + getEnd() + ". " + getText();
    }
    
    /**
     * Set the new Color for event
     * @param color
     */
    public void setColor(Color color) {
    	this.color = color;
    }

    /**
     * Gets the event color
     * @return
     */
    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvent that = (CalendarEvent) o;

        if (!date.equals(that.date)) return false;
        if (!start.equals(that.start)) return false;
        return end.equals(that.end);

    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    /**
     * Adds Event to a json file and export fuul document to DB
     * @throws IOException
     */
	public void addToJson() throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime ldtStart = LocalDateTime.of(getDate(), getStart());
		LocalDateTime ldtEnd = LocalDateTime.of(getDate(), getEnd());
		String dateStart = ldtStart.format(formatter);
		String dateEnd = ldtEnd.format(formatter);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Event e = new Event(getText(), dateStart, dateEnd, getUser().getUserName());
		String json = gson.toJson(e);
		String s = getUser().getUserName().toLowerCase();
		String fileName = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") + ".json";
		FileWriter writer = new FileWriter(fileName, true);
		BufferedWriter out = new BufferedWriter(writer);
		out.write(json);
		out.close();
		MongoDB.importData(fileName);
	}
	
}
