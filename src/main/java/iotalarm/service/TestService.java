/* ClassTitle: TestService
 * Description: This service is responsible for retrieving the test events from TestReader for EventResource.
 * Used by: iotalarm.service.ServiceProvider, iotalarm.webservices.EventResource (through iotalarm.service.ServiceProvider)
 * Uses: TestReader
 */
package iotalarm.service;

import java.io.IOException;
import java.util.List;

import iotalarm.dataaccess.TestReader;
import iotalarm.domain.Event;
import net.fortuna.ical4j.data.ParserException;

public class TestService implements CalendarService {

	@Override
	public List<Event> getEvents(String url) throws IOException, ParserException {
		TestReader.getInstance().updateUrl(url);
		return TestReader.getEvents();
	}

	@Override
	public List<Event> getTodaysEvents(String url) throws IOException, ParserException {
		TestReader.getInstance().updateUrl(url);
		return TestReader.getTodaysEvents();
	}

	@Override
	public Event getTodaysFirstEvent(String url) throws IOException, ParserException {
		TestReader.getInstance().updateUrl(url);
		List<Event> todaysevents = TestReader.getTodaysEvents();
		if (!todaysevents.isEmpty()) {
			return todaysevents.get(0);
		}
		return null;
	}
}
