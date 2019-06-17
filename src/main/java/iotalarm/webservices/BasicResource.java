/* ClassTitle: BasicResource
 * Description: This class is responsible for allowing the resource classes access to universal methods.
 * Used in: iotalarm.webservices.EventResource, iotalarm.webservices.TestModesResource, iotalarm.webservices.TravelTimeResource
 */
package iotalarm.webservices;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
public class BasicResource {

	protected String NotFoundJSON() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("error", "Exception");
		job.add("message", "We could not find your schedule. Maybe the url you provided is wrong.");
		return job.build().toString();
	}
	
	/* FunctionTitle: UnknownError
	 * Description: This function is responsible for returning the error message from an unknown error that occurred in the API. 
	 * Allowing for easier bug- tracking and fixing 
	 * Used in: iotalarm.webservices.TestModesResource.getTestModes(), iotalarm.webservices.TravelTimeResource.setTravelTime(int traveltime)
	 */
	protected String UnknownError(Exception e){
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("error", "Exception");
		job.add("message", e.getMessage());			
		return job.build().toString();
	}

}
