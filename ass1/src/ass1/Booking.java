/**
 * 
 */
package ass1;

import java.beans.Customizer;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;

/**
 * @author jymot
 *
 */
public class Booking {
	private String customer;
	private LocalDate startDate;
	private LocalDate endDate;
	private ArrayList<Room> rooms;
	
	
	/**
	 * 
	 */
	public Booking(String customer, ArrayList<Room> rooms, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated constructor stub
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rooms = rooms;
	}


	public boolean roomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		boolean roomInBooking = false;
		for (Room room2 : rooms) {
			if(room.getRoomNumber() == room2.getRoomNumber()) {
				roomInBooking = true;
				break;
			}
		}
//		Is room in this booking
		if (!roomInBooking) {
			return true;
		}
//		If room is in this booking is it booked on the dates we are looking for?
		if (startDate.isBefore(this.startDate) && endDate.isBefore(this.startDate)) {
			return true;
		} else if (startDate.isAfter(this.endDate) && endDate.isAfter(this.endDate)) {
			return true;
		}
		return false;
	}


	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}


	/**
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}


	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}


	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}



}
