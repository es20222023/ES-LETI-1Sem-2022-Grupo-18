

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Class to fill the calendar event lists
 *
 */
public class FillCalendar {
	/**
	 * Returns the Event List when a link is given
	 * @param link link to use
	 * @param user user
	 * @return Events List 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static ArrayList<Event> returnEventListFromLink(String link, String user) throws ParseException, IOException {

		final ObjectMapper objectMapper = new ObjectMapper();

		ArrayList<Event> eventList = null;
		
		IcsToJson icalendar = new IcsToJson(link);
		File file = icalendar.getJson(user);
		try {
			eventList = objectMapper.readValue(file, new TypeReference<ArrayList<Event>>() {
			
			});
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileName = Normalizer.normalize(user, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") + ".json";
		MongoDB.importData(fileName);
		return eventList;
	}
	
	/**
	 * Returns a Event list when a file is given
	 * @param file user file
	 * @param user username
	 * @return Events list
	 */
	public static ArrayList<Event> returnEventListFromFile(File file, String user){
		final ObjectMapper objectMapper = new ObjectMapper();

		ArrayList<Event> eventList = null;
		
		try {
			eventList = objectMapper.readValue(file, new TypeReference<ArrayList<Event>>() {
			
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
