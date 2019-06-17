/* ClassTitle: TestMode
 * Description: This class is responsible for storing and providing the current test mode
 * Note that this only stored in a session and is lost after deploy instance is closed, because the value of testmode is normally false and the default value is false as well
 * this should not be a issue in normal use of the API.
 * Used by: iotalarm.service.TestModeService
 */
package iotalarm.config;

public class TestMode {
	private static TestMode instance;
	private static boolean testModes = false;
	
	private TestMode(){}
		
	static{
		
        try{
            instance = new TestMode();
        }
        catch(Exception e){
            throw new RuntimeException("Exception occured in creating TestModes instance");
        }
    }
	
	public static TestMode getInstance(){
        return instance;
    }
	
	public void changeTestMode () {
		if (testModes == false){
			testModes = (!testModes);
			}
		
		else if (testModes == true) {
			testModes = (!testModes);
			}
		
		else {
			System.out.println("A unknown error occurred");
		}
	}
	
	public boolean getTestModes(){
		return testModes;
	}
	
}