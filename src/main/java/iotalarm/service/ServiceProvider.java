/* ClassTitle: ServiceProvider
 * Description: This service is responsible for allowing access to all the services provided in this package. 
 * It also makes a check if the test mode is active or not and returns the relevant service for it.
 * Uses: iotalarm.service.EventService, iotalarm.service.TestService, iotalarm.service.TestModeService, iotalarm.service.TravelTimeService
 * Used by: EventResource, TestModeResource, TravelTimeResource
 */
package iotalarm.service;

public class ServiceProvider {
	private static CalendarService eventService=new EventService();
	private static TestModeService testmoduesService=new TestModeService();
	private static TravelTimeService traveltimeService=new TravelTimeService();

	private ServiceProvider() {}
	
	/* FunctionTitle: getCalendarService
	 * Description: This function is responsible for switching between the TestService and EventService,
	 * allow a connected device to be tested, for this purpose it retrieves a boolean through the TestModeService.
	 * This Service returns true if the test mode is active and false if it isn't.
	 * Uses: iotalarm.service.TestService, iotalarm.service.EventService, iotalarm.service.TestModeService.getTestMode()
	 */
	public static CalendarService getCalendarService() {
			return eventService;
	}
	
	public static TravelTimeService getTravelTimeService (){
		return traveltimeService;
	}
	
	public static TestModeService getTestModeService(){
		return testmoduesService;
	}
	
}
