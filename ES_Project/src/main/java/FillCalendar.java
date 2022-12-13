

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FillCalendar {
	
	public static ArrayList<Event> returnEventList(String link, String user) throws ParseException, IOException {

		final ObjectMapper objectMapper = new ObjectMapper();

		ArrayList<Event> eventList = null;
		
		IcsToJson icalendar = new IcsToJson(link);
		File fileName = icalendar.getJson(user);
		try {
			eventList = objectMapper.readValue(fileName, new TypeReference<ArrayList<Event>>() {
			
			});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return eventList;
	}
	
	

}
