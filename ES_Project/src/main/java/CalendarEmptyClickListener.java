

import java.util.EventListener;
/**
 * 
 * Create single EmptyClickListener interface
 *
 */
public interface CalendarEmptyClickListener extends EventListener {
    // Event dispatch methods
    void calendarEmptyClick(CalendarEmptyClickEvent e);
}
