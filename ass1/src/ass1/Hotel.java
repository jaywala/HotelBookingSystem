/**
 * 
 */
package ass1;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.Name;

/**
 * @author jymot
 *
 */
public class Hotel {

	private String name;
	private ArrayList<Room> rooms;
	
	/**
	 * @param hotelName
	 */
	public Hotel(String hotelName) {
		// TODO Auto-generated constructor stub
		this.name =  hotelName;
		this.rooms = new ArrayList<Room>();
	}


	/**
	 * @param hotelName
	 * @return
	 */
	public boolean compareName(String hotelName) {
		// TODO Auto-generated method stub
		if (hotelName.equals(this.name)) {
			return true;
		}
		return false;
	}
	
	public void addRoom(int roomNumber, int capacity) {
		Room room = new Room(roomNumber, capacity);
		rooms.add(room);
	}
	
	public Room findRoom(int capacity) {
		Room room = null;
		return room;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}


//	public boolean checkAvailability() {
//		// TODO Auto-generated method stub
//		
//	}
	
	
}
