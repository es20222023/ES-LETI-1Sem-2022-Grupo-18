

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
	@JsonProperty("dtstamp")
	private String dtstamp;
	@JsonProperty("dtstart")
	private String dtstart;
	@JsonProperty("dtend")
	private String dtend;
	@JsonProperty("summary")
	private String summary;
	@JsonProperty("uid")
	private String uid;
	@JsonProperty("description")
	private String description;
	@JsonProperty("location")
	private String location;
	
	public Event(){
	}

	public String getDtstamp() {
		return dtstamp;
	}

	public void setDtstamp(String dtstamp) {
		this.dtstamp = dtstamp;
	}

	public String getDtstart() {
		return dtstart;
	}

	public void setDtstart(String dtstart) {
		this.dtstart = dtstart;
	}

	public String getDtend() {
		return dtend;
	}

	public void setDtend(String dtend) {
		this.dtend = dtend;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Event [dtstamp=" + dtstamp + ", dtstart=" + dtstart + ", dtend=" + dtend + ", summary=" + summary
				+ ", uid=" + uid + ", description=" + description + ", location=" + location + "]";
	}
	
	
}
