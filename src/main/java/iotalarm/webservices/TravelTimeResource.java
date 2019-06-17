/* ClassTitle: TravelTimeResource
 * Description: This class is responsible for handling requests regarding the travel time.
 * Its main purpose is to retrieve and set the current travel time.
 * Uses: iotalarm.webservices.BasicResource, iotalarm.service.TravelTimeService, iotalarm.service.ServiceProvider
 */

package iotalarm.webservices;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import iotalarm.service.ServiceProvider;
import iotalarm.service.TravelTimeService;

@Path("timeoffset")
public class TravelTimeResource extends BasicResource{
	private TravelTimeService service =  ServiceProvider.getTravelTimeService();

	@GET
	@Produces("application/json")
	public String getTravelTime() {
		try {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("traveltime", service.getTravelTime()*60);
			return job.build().toString();
		}
	catch(Exception e) {
		return NotFoundJSON();
	}}
	
	@PUT
	@Produces("application/json")
	// Note that the traveltime value is seen as a amount of minutes in the API. Example a value of "5" is seen as 5 minutes.
	public String setTravelTime(@FormParam("traveltime") int traveltime){
		try {
			service.setTravelTime(traveltime);
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("succes", true);
			return job.build().toString();
		}
		catch (Exception e){
			return UnknownError(e);
		}
	}
}
