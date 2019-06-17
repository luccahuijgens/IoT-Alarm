/* ClassTitle: CalendarLoader
 * Description: This class is responsible for parsing the ical-files and turning them into event objects.
 * Used by: iotalarm.dataaccess.EventReader
 * Uses: iotalarm.domain.Event
 */

package iotalarm.dataaccess;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
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
		for(int i = 0; i < calendarevents.size(); i++) {
			VEvent event = (VEvent) calendarevents.get(i);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
			//Get the Unix Epoch time by making a divide with 1000.
			long unixEpoch = (sdf.parse(event.getStartDate().getValue()).getTime() /1000);
			Event parsedEvent = new Event(i+1,event.getSummary().getValue(),event.getLocation().getValue(),unixEpoch);
			events.add(parsedEvent);
		}
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Event> getEvents(){
		return events;
	}
}