/**
 * 
 */
package ass1;


/**
 * @author jymot
 *
 */
public class Room {
	private int roomNumber;
	private int capacity;

	/**
	 * @param Capacity
	 */
	public Room(int roomNumber, int capacity) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
	}

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	
}
