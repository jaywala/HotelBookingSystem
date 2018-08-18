/**
 * 
 */
package ass1;

import java.util.ArrayList;

/**
 * @author jymot
 *
 */
public class Room {
	int roomNumber;
	int capacity;
	ArrayList<Booking> bookings;

	/**
	 * @param Capacity
	 */
	public Room(int roomNumber,int capacity) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
	}

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}
}
