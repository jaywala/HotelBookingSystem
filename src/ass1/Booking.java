/**
 * 
 */
package ass1;

import java.time.LocalDate;
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
	private Hotel hotel;
	
	

	/**
	 * @param customer
	 * @param rooms
	 * @param startDate
	 * @param endDate
	 */
	public Booking(String customer, ArrayList<Room> rooms, LocalDate startDate, LocalDate endDate, Hotel hotel) {
		// TODO Auto-generated constructor stub
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rooms = rooms;
		this.hotel = hotel;
	}


	/**
	 * @param room
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public boolean roomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		boolean roomInBooking = false;
		for (Room room2 : rooms) {
			if(room.getRoomNumber() == room2.getRoomNumber()) {
				roomInBooking = true;
				break;
			}
		}
//		If room is not in this booking, room is not occupied in this booking
		if (!roomInBooking) {
			return true;
		}
//		If room is in this booking check if it is available on the required dates
		if (startDate.isBefore(this.startDate) && endDate.isBefore(this.startDate)) {
			return true;
		} else if (startDate.isAfter(this.endDate) && endDate.isAfter(this.endDate)) {
			return true;
		}
//		If room is in booking and not available on the required dates return false
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


	/**
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}



}
