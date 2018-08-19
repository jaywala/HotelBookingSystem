/**
 * 
 */
package ass1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * @author jymot
 *
 */
public class Hotel {

	private String name;
	private ArrayList<Room> rooms;
	private ArrayList<Booking> bookings;
	
	/**
	 * @param hotelName
	 */
	public Hotel(String hotelName) {
		// TODO Auto-generated constructor stub
		this.name =  hotelName;
		this.rooms = new ArrayList<Room>();
		this.bookings = new ArrayList<Booking>();
	}

	
	/**
	 * @param roomNumber
	 * @param capacity
	 */
	public void addRoom(int roomNumber, int capacity) {
		Room room = new Room(roomNumber, capacity);
		rooms.add(room);
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param startDate
	 * @param endDate
	 * @param roomTypes
	 * @return
	 */
	public ArrayList<Room> findRooms(LocalDate startDate, LocalDate endDate, int[] roomTypes) {
		// TODO Auto-generated method stub
		ArrayList<Room> foundRooms = new ArrayList<Room>();
		for (Room room : rooms) {
//			Check if room is available from bookings
			if (!this.roomAvailable(room, startDate, endDate)) {
				continue;
			}
//			if available check if it matches capacity criteria
			if (room.getCapacity()==1 && roomTypes[0] > 0 ) {
				roomTypes[0]--;
				foundRooms.add(room);
			} else if (room.getCapacity()==2 && roomTypes[1] > 0) {
				roomTypes[1]--;
				foundRooms.add(room);
			} else if (room.getCapacity()==3 && roomTypes[2] > 0) {
				roomTypes[2]--;
				foundRooms.add(room);
			} else if(roomTypes[0] == 0 && roomTypes[1] == 0 && roomTypes[2] == 0) {
				break;
			}
		}
		for (int i = 0; i < roomTypes.length; i++) {
			if (roomTypes[i] != 0) {
				return null;
			}
			
		}
		return foundRooms;
	}


	/**
	 * @param room
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private boolean roomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		for (Booking booking : bookings) {
			if (!booking.roomAvailable(room, startDate, endDate)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * @param foundRooms
	 * @param name
	 * @param startDate
	 * @param endDate
	 */
	public void bookRooms(ArrayList<Room> foundRooms, String name, LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		Booking booking = new Booking(name, foundRooms, startDate, endDate, this);
		bookings.add(booking);
	}


	/**
	 * @param name
	 * @return
	 */
	public Booking cancelBooking(String name) {
		// TODO Auto-generated method stub
		for (Booking booking : bookings) {
			if (name.equals(booking.getCustomer())) {
				bookings.remove(booking);
				return booking;
			}
		}
		return null;
	}


	/**
	 * 
	 */
	public void printRoomsOccupancy() {
		// TODO Auto-generated method stub
		ArrayList<Booking> bookings2 = bookings;
//		Sorted arraylist of bookings by Dates
		bookings2.sort((b1, b2) -> b1.getStartDate().compareTo(b2.getStartDate()));
		for (Room room : rooms) {
			StringJoiner output = new StringJoiner(" ");
			output.add(this.name);
			output.add(Integer.toString(room.getRoomNumber()));
			for (Booking booking : bookings) {
				ArrayList<Room> roomsInBooking = booking.getRooms();
				for(Room room2 : roomsInBooking) {
					if(room.equals(room2)) {
						output.add(dateToString(booking.getStartDate(),booking.getEndDate()));
					}
				}
			}
			System.out.println(output);
		}
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String dateToString(LocalDate startDate, LocalDate endDate) {
		StringJoiner dateString = new StringJoiner(" ");
//		Add Month of booking
		dateString.add(monthToString(startDate.getMonthValue()));
//		Add date of Booking
		dateString.add(Integer.toString(startDate.getDayOfMonth()));
//		Add number of days in booking
		dateString.add(Long.toString(startDate.until(endDate,ChronoUnit.DAYS)+1));
		return dateString.toString();
		
	}
	
	public void appendBooking(Booking booking) {
		bookings.add(booking);
	}


	/**
	 * @param monthValue
	 * @return
	 */
	public String monthToString(int monthValue) {
		// TODO Auto-generated method stub
		switch (monthValue) {
		case 1:
			return "Jan";
			
		case 2:
			return "Feb";
			
		case 3:
			return "Mar";
			
		case 4:
			return "Apr";
			
		case 5:
			return "May";
			
		case 6:
			return "Jun";
			
		case 7:
			return "Jul";
			
		case 8:
			return "Aug";
			
		case 9:
			return "Sep";
			
		case 10:
			return "Oct";
			
		case 11:
			return "Nov";
			
		case 12:
			return "Dec";

		default:
			break;
		}
		return null;
	}
	
}
