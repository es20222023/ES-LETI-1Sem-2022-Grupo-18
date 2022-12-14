

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public CalendarEvent(User user,LocalDate date, LocalTime start, LocalTime end, String text, Color color) {
    	this.user = user;
        this.date = date;
        this.start = start;
        this.end = end;
        this.text = text;
        this.color = color;
    }
    
    public boolean isVisible() {
    	return visible;
    }
    
    public void setVisible(boolean a) {
    	this.visible = a;
    }
    
    public User getUser() {
    	return user;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return getUser() + " " + getDate() + " " + getStart() + "-" + getEnd() + ". " + getText();
    }
    
    public void setColor(Color color) {
    	this.color = color;
    }

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
}
