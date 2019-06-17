/* ClassTitle: TravelTime
 * Description: This class is responsible for storing and providing the current travel time.
 * Note that this only stored in a session and is lost after deploy instance is closed, for this reason any device making use of this needs to store the travel time locally.
 * Used by: iotalarm.service.TravelTimeService
 */

package iotalarm.config;

public class TravelTime {
	private static TravelTime instance;
	private static int travelTime = 0;
	
	private TravelTime(){}
		
	static{
		
        try{
            instance = new TravelTime();
        }
        catch(Exception e){
            throw new RuntimeException("Exception occured in creating TravelTime instance");
        }
    }
	
	public static TravelTime getInstance(){
        return instance;
    }
	
	public void setTravelTime(int travelTime){
		TravelTime.travelTime = travelTime;
	}
	
	public static int getTravelTime(){
		return travelTime;
	}
	
}