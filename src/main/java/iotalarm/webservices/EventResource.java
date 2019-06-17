/* ClassTitle: EventResource
 * Description: This class is responsible for handling requests regarding events.
 * Its main purpose is to allow events to be retrieved from the service package/layer.
 * Uses: iotalarm.webservices.BasicResource, iotalarm.service.BasicResourceServiceProvider, iotalarm.service.CalendarService
 */

package iotalarm.webservices;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import iotalarm.domain.Event;
import iotalarm.service.CalendarService;
import iotalarm.service.ServiceProvider;


@Path("events")
public class EventResource extends BasicResource {
	private CalendarService service = ServiceProvider.getCalendarService();

	@GET
	@Produces("application/json")
	/* FunctionTitle: getEvents
	 * Description: This function is responsible handling requests for events based on a calendarurl.
	 * Example: https://mijnrooster.sharepoint.hu.nl/ical?[various numbers and letters]&group=false&eu=[various numbers and letters]=&h=[various numbers and letters]=.
	 * If this fails, it assumes the given url is faulty and returns a message requesting an correct one. 
	 * Uses: iotalarm.webservices.BasicResource.NotFoundJSON(), convertJson(Event e), iotalarm.service.ServiceProvider.getEvents(String url)
	 */
	public String getEvents(@HeaderParam("calendarurl") String url) {
		try {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Event e : service.getEvents(url)) {
			jab.add(convertJson(e));
		}
		return jab.build().toString();
		}
	catch(Exception e) {
		return NotFoundJSON();
	}}

	@Path("today")
	@GET
	@Produces("application/json")
	/* FunctionTitle: getTodaysEvents
	 * Description: This function is responsible for handling requests for the events of the current day.
	 * Similar to the function getEvents it is based on the calendarurl (see mentioned function for a example).
	 * If this fails, it assumes the given url is faulty and returns a message requesting an correct one.
	 * Uses: iotalarm.webservices.BasicResource.NotFoundJSON(), convertJson(Event e), iotalarm.service.ServiceProvider.getTotdaysEvents(String url)
	 */
	public String getTodaysEvents(@HeaderParam("calendarurl") String url) {
		try {
			JsonArrayBuilder jab = Json.createArrayBuilder();
			for (Event e : service.getTodaysEvents(url)) {
				jab.add(convertJson(e));
			}
			return jab.build().toString();
		} catch (Exception e) {
			return NotFoundJSON();
		}
	}

	@Path("today/first")
	@GET
	@Produces("application/json")
	/* FunctionTitle: getTodaysEvents
	 * Description: This function is responsible for handling requests for the first event of the current day.
	 * Similar to the function getEvents it is based on the calendarurl (see mentioned function for a example).
	 * If this fails, it assumes the given url is faulty and returns a message requesting an correct one.
	 * Uses: iotalarm.webservices.BasicResource.NotFoundJSON(), convertJson(Event e), iotalarm.service.ServiceProvider.getTodaysFirstEvent(String url)
	 */
	public String getTodaysFirstEvent(@HeaderParam("calendarurl") String url) {
		try {
			Event todaysevent = service.getTodaysFirstEvent(url);
			JsonObjectBuilder job = Json.createObjectBuilder();
			if (todaysevent != null) {
				job = convertJson(todaysevent);
			}
			return job.build().toString();
		} catch (Exception e) {
			return NotFoundJSON();
		}
	}
	
	private JsonObjectBuilder convertJson(Event e) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("id", e.getId());
		job.add("title", e.getTitle());
		job.add("location", e.getLocation());
		job.add("date",e.getDate());
		return job;
	}

}