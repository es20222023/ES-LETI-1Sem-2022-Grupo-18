

import java.util.EventListener;

/**
 * 
 * Creates a Event Click listener interface
 *
 */
public interface CalendarEventClickListener extends EventListener {
    // Event dispatch methods
    void calendarEventClick(CalendarEventClickEvent e);
}
