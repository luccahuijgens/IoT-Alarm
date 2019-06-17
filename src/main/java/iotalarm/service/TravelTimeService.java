/* ClassTitle: TravelTimeService
 * Description: This class is responsible for retrieving and setting the travel time from iotalarm.config.TravelTime to iotalarm.webservices.TravelTimeResource.
 * Used by: iotalarm.service.ServiceProvider, iotalarm.webservices.TravelTimeResource (through ServiceProvider)
 * Uses: iotalarm.config.TravelTime
 */
package iotalarm.service;

import iotalarm.config.TravelTime;

public class TravelTimeService {

	public int getTravelTime() {
		TravelTime.getInstance();
		return TravelTime.getTravelTime();
	}

	public void setTravelTime(int travelTime){
		TravelTime.getInstance().setTravelTime(travelTime);
	}

}
