/* ClassTitle: CalendarService
 * Description: This is a interface implemented by TestService and EventService. Ensuring that the proper data is returned to the /api/event endpoints.
 * Used by: iotalarm.service.TestService, iotalarm.service.EventService
 */

package iotalarm.service;

import java.io.IOException;
import java.util.List;
import iotalarm.domain.Event;
import net.fortuna.ical4j.data.ParserException;

public interface CalendarService {
	public List<Event> getEvents(String url) throws IOException, ParserException;

	public List<Event> getTodaysEvents(String url) throws IOException, ParserException;

	public Event getTodaysFirstEvent(String url) throws IOException, ParserException;
}
