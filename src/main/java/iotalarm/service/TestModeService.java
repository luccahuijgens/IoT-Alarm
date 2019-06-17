/* ClassTitle: TestModeService
 * Description: This is a simple service responsible for retrieving and changing the test mode of the API,
 * from iotalarm.config.TestMode to iotalarm.webservices.TestModeResource. This is essential for testing a alarm using the API.
 * Used by: iotalarm.service.ServiceProvider, iotalarm.webservices.TestModeResource (through iotalarm.service.ServiceProvider)
 * Uses: TestMode
 */
package iotalarm.service;

import iotalarm.config.TestMode;

public class TestModeService {
	
	public void changeTestMode(){
		TestMode.getInstance().changeTestMode();
	}
	
	public boolean getTestMode(){
		return TestMode.getInstance().getTestModes();
	}
}
