
public class Event {
	// Event parameters
	private String name;
	private String dateStart;
	private String dateEnd;
	private String username;

	//Constructor
	public Event(String name, String dateStart, String dateEnd, String username) {
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.username = username;
	}
 
	//Default Constructor
	public Event() {
	}

	// username getter
	public String getUsername() {
		return username;
	}

	// username setter
	public void setUsername(String username) {
		this.username = username;
	}

	//name getter
	public String getName() {
		return name;
	}

	//dateStart getter
	public String getDateStart() {
		return dateStart;
	}

	//dateEnd getter
	public String getDateEnd() {
		return dateEnd;
	}

	//toString method
	public String toString() {
		return "[" + name + " " + dateStart + " " + dateEnd + " " + username + "]";
	}
}
