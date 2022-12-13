
public class Event {
	private String name;
	private String dateStart;
	private String dateEnd;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Event(String name, String dateStart, String dateEnd, String username) {
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.username = username;
	}
	public Event(){
	}
	
	public String getName() {
		return name;
	}

	public String getDateStart() {
		return dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public String toString()
	{
		return "[" + name + " " + dateStart + " " + dateEnd + " " + username + "]";
	}
}
