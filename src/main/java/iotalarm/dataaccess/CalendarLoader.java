/* ClassTitle: CalendarLoader
 * Description: This class is responsible for parsing the ical-files and turning them into event objects.
 * Used by: iotalarm.dataaccess.EventReader
 * Uses: iotalarm.domain.Event
 */

package iotalarm.dataaccess;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import iotalarm.domain.Event;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.component.VEvent;

public class CalendarLoader {
	private ArrayList<Event> events=new ArrayList<Event>();
	Calendar cal;
	
	/* FunctionTitle: CalendarLoader(String url)
	 * Description: This function is responsible for connecting to the provided url with the ical-file. Converting the result into a Calendar type.
	 * Used by: iotalarm.dataaccess.EventReader and iotalarm.dataaccess.EventReader.updateUrl(String newurl)
	 * Uses: fillEvents()
	 */
	public CalendarLoader(String url) {
			CalendarBuilder builder = new CalendarBuilder();
			try{ 
				InputStream is = new URL(url).openStream();
				cal = builder.build(is);
				is.close();
				fillEvents();
				}
			catch (Exception e){
				e.printStackTrace();
				}
			}


	/* FunctionTitle: fillEvents()
	 * Description: This function is responsible for converting the components in the Calendar type into events.
	 * To prevent the API from using its own local timezone (this is a issue because the servers can be located in another country) the date is converted to Unix Epoch.
	 * Used by: iotalarm.dataaccess.EventReader and iotalarm.dataaccess.EventReader.updateUrl(String newurl)
	 * Uses: fillEvents()
	 */
	private boolean fillEvents() {
		try {
		ComponentList calendarevents = cal.getComponents().getComponents(
	            Component.VEVENT);
		ZonedDateTime now=ZonedDateTime.now();
		for(int i = 0; i < calendarevents.size(); i++) {
			VEvent event = (VEvent) calendarevents.get(i);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
			//Get the Unix Epoch time by making a divide with 1000.
			ZonedDateTime eventDate=convertStringToDate(event.getStartDate().getValue());
			Event parsedEvent = new Event(i+1,event.getSummary().getValue(),event.getLocation().getValue(),eventDate);
			if (parsedEvent.getDate().isAfter(now)) {
			events.add(parsedEvent);
			}
		}
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private ZonedDateTime convertStringToDate(String dateString) {
		System.out.println(dateString);
		int year=Integer.parseInt(dateString.substring(0,4));
		int month=Integer.parseInt(dateString.substring(4,6));
		int day=Integer.parseInt(dateString.substring(6,8));
		if (dateString.length()<=8) {
			LocalDateTime date=LocalDateTime.of(year, month, day, 0, 0,0);
			return ZonedDateTime.of(date, ZoneOffset.UTC);
		}else {
			int hour=Integer.parseInt(dateString.substring(9,11));
			int minutes=Integer.parseInt(dateString.substring(11,13));
			int seconds=Integer.parseInt(dateString.substring(13,15));
			LocalDateTime date = LocalDateTime.of(year,month,day,hour,minutes,seconds);
			return ZonedDateTime.of(date, ZoneOffset.UTC);
		}
		
	}
	
	public ArrayList<Event> getEvents(){
		return events;
	}
}