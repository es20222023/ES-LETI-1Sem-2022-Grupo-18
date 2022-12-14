

import java.awt.*;
import java.time.LocalDateTime;
/**
 * 
 *Creates a single Event Click Listener
 *
 */
public class CalendarEmptyClickEvent extends AWTEvent {
    private LocalDateTime dateTime;

    /**
     * Creates a single Event Click Listener
     * @param source
     * @param dateTime
     */
    public CalendarEmptyClickEvent(Object source, LocalDateTime dateTime) {
        super(source, 0);
        this.dateTime = dateTime;
    }

    /**
     * Gets the LocalDateTime
     * @return dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
