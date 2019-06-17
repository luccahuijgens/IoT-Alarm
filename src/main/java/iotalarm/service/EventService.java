/* ClassTitle: EventService
 * Description: This service is responsible for calling the proper methods in the iotalarm.dataaccess.EventReader and returning the results to the iotalarm.webservices.EventResource.
 * Used by: iotalarm.service.ServiceProvider, iotalarm.webservices.EventResource (through iotalarm.service.ServiceProvider)
 * Uses: iotalarm.dataaccess.EventReader
 */
package iotalarm.service;

import java.io.IOException;
import java.util.List;
import iotalarm.dataaccess.EventReader;
import iotalarm.domain.Event;
import net.fortuna.ical4j.data.ParserException;

public class EventService implements CalendarService{

	@Override
public List<Event> getEvents(String url) throws IOException, ParserException{
	EventReader.getInstance().updateUrl(url);
	return EventReader.getEvents();
}

	@Override
public List<Event> getTodaysEvents(String url) throws IOException, ParserException{
	EventReader.getInstance().updateUrl(url);
	return EventReader.getTodaysEvents();
}

@Override
public Event getTodaysFirstEvent(String url) throws IOException, ParserException {
	EventReader.getInstance().updateUrl(url);
	List<Event>todaysevents=EventReader.getTodaysEvents();
	if (!todaysevents.isEmpty()) {
		return todaysevents.get(0);
		}
	return null;
}
}
