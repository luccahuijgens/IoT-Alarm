/* ClassTitle: TestReader
 * Description: This class is responsible for calling iotalarm.dataaccess.CalendarLoader with the user's schedule url, 
 * sorting out the events in a order and making a check to see if a event is today.
 * Used by: iotalarm.service.EventService
 * Uses: iotalarm.dataaccess.CalendarLoader
 */

package iotalarm.dataaccess;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import iotalarm.domain.Event;
import net.fortuna.ical4j.data.ParserException;

public class EventReader {
	private static EventReader instance;
	private static List<Event> list=new ArrayList<Event>();
	private static String url="";
	private static CalendarLoader calendar;
	
	private EventReader() {
	}
	
	public static EventReader getInstance() {
		if (instance==null) {
			instance=new EventReader();
		}
		return instance;
	}
	
	public void updateUrl(String newurl) throws IOException, ParserException {
		if (!url.equals(newurl)) {
			url=newurl;
		}
		calendar=new CalendarLoader(url);
		list=calendar.getEvents();
	}

	
	public static List<Event> getEvents(){
		return list;
	}
	
	public static List<Event> getTodaysEvents(){
		List<Event>result=new ArrayList<Event>();
		for (Event e : list) {
			if (isToday(e.getDate())) {
				result.add(e);
			}
		}
		Collections.sort(result, new Comparator<Event>() {
			  public int compare(Event o1, Event o2) {
			      return Long.valueOf(o1.getDate()).compareTo(Long.valueOf(o2.getDate()));
			  }
			});
		return result;
	}

	/* FunctionTitle: isToday()
	 * Description: This function makes a check if the event falls on the local date.
	 * @SuppressWarnings is used to suppress the warnings that pope-up by tweaking the time units
	 * Used by: getTodaysEvents()
	 */
@SuppressWarnings("deprecation")
private static boolean isToday(long l) {
	LocalDate today=LocalDate.now(); 
	Date lDate = new Date(Long.parseLong(String.valueOf(l)) * 1000);
	LocalDate parsedEventDate=LocalDate.of(lDate.getYear()+1900, lDate.getMonth()+1, lDate.getDate());
		if (parsedEventDate.isEqual(today)) {
			return true;
		}
		return false;
	}
}