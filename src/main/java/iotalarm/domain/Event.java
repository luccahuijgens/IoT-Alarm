/* ClassTitle: Event
 * Description: This class is used to store the events that are parsed from the ical file in the iotalarm.dataaccess.CalendarLoader and created from iotalarm.dataaccess.TestReader.
 * Used by: iotalarm.dataaccess.TestReader, iotalarm.dataaccess.CalendarLoader
 */

package iotalarm.domain;

public class Event {
private int id;
private String title;
private String location;
private long unixEpoch;

public Event(int id, String title, String location, long unixEpoch) {
	super();
	this.id = id;
	this.title = title;
	this.location = location;
	this.unixEpoch = unixEpoch; 
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public long getDate() {
	return unixEpoch;
}

public void setDate(long unixEpoch) {
	this.unixEpoch = unixEpoch;
}


}