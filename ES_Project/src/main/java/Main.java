public class Main {


	public static void main(String [] args) {
		ICalendar icalendar = new ICalendar("https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=ajcoa@iscte.pt&password=vdUlwNxIno1kfyt9Bb8xlmucoNeh9kpodtkz7Ar909kSBNABXxgOqYDV40KA5DbQ9KRjj55ViqbfZurzgxp8k7LIjmfQgPe5LXftA8hrDr3UzlTW9QRl6F3WVwGdSslZ");
		String jsonCalendar = icalendar.getJson();
		System.out.println(jsonCalendar);
	}
	
}	