

import java.awt.*;
/**
 * 
 * Creates a Click Event Listener
 *
 */
public class CalendarEventClickEvent extends AWTEvent {

    private CalendarEvent calendarEvent;

    /**
     * 
     * @param source
     * @param calendarEvent
     */
    public CalendarEventClickEvent(Object source, CalendarEvent calendarEvent) {
        super(source, 0);
        this.calendarEvent = calendarEvent;
    }

    /**
     * gets thet Calendar Event
     * @return a calendarEvent object
     */
    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }
}
