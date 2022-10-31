import java.lang.module.ModuleDescriptor.Version;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.*;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.ProdId;
//import javax.servlet.http;
public class Main {

	public static void main(String [] args) {
		final Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		//calendar.getProperties().add(Version.VERSION_2_0);
		//calendar.getProperties().add(CalScale.GREGORIAN);
		
		/*final List events = new ArrayList();
		for (Iterator i = minutesList.iterator(); i.hasNext();) {
			final TimeSheetEntry entry = (TimeSheetEntry) i.next();
			final VEvent event = new VEvent(new DateTime(entry.getStartTime()), entry.getName());
			event.getProperties().add(new DtEnd(new DateTime(entry.getEndTime())));
			event.getProperties().add(new Description(entry.getDescription()));
			events.add(event);
		}
		calendar.getComponents().addAll(events);*/
		response.setContentType("text/calendar");
		final CalendarOutputter output = new CalendarOutputter();
		output.output(calendar, response.getOutputStream());
	}
}
