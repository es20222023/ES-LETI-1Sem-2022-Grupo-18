import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mnode.ical4j.serializer.JCalSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;

public class ICalendar {

	private String URL;

	ICalendar(String theURL) {
		this.URL = theURL;
	}

	public String getJson(){  
		String json = "";

	    try {  
	      URL url = new URL(this.URL); // creating a url object  
	      URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  

	      CalendarBuilder builder = new CalendarBuilder();
	      Calendar calendar = builder.build(urlConnection.getInputStream());

	      SimpleModule module = new SimpleModule();
	      module.addSerializer(Calendar.class, new JCalSerializer(Calendar.class));
	      ObjectMapper mapper = new ObjectMapper();
	      mapper.registerModule(module);
	      json = mapper.writeValueAsString(calendar);

	    }  
	    catch(Exception e)  
	    {  
	      e.printStackTrace();
	    }  
		return json; 
	  }  
}