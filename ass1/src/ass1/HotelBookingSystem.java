/**
 * 
 */
package ass1;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;


/**
 * @author Jay Motwani
 *
 */
public class HotelBookingSystem {

	private ArrayList<Hotel> hotels;
	private Map<String, Integer> monthsMap;
	private Map<String, Integer> roomTypesMap;
	
	/**
	 * 
	 */
	public HotelBookingSystem() {
		// TODO Auto-generated constructor stub
		hotels = new ArrayList<Hotel>();
		monthsMap = new HashMap<String, Integer>();
		roomTypesMap = new HashMap<String, Integer>();
		monthsMap.put("Jan", 1);
		monthsMap.put("Feb", 2);
		monthsMap.put("Mar", 3);
		monthsMap.put("Apr", 4);
		monthsMap.put("May", 5);
		monthsMap.put("Jun", 6);
		monthsMap.put("Jul", 7);
		monthsMap.put("Aug", 8);
		monthsMap.put("Sep", 9);
		monthsMap.put("Oct", 10);
		monthsMap.put("Nov", 11);
		monthsMap.put("Dec", 12);
		roomTypesMap.put("single", 0);
		roomTypesMap.put("double", 1);
		roomTypesMap.put("triple", 2);
		
	}

	/**
	 * @param hotelName
	 * @param roomNumber
	 * @param capacity
	 */
	public void addHotelRoom(String hotelName,int roomNumber , int capacity) {
//		Check if Current Hotel Exists
		for (Hotel hotel : hotels) {
			if (hotelName.equals(hotel.getName())) {
				hotel.addRoom(roomNumber, capacity);
				return;
			}
		}
		Hotel hotel = new Hotel(hotelName);
		hotel.addRoom(roomNumber, capacity);
		hotels.add(hotel);
	}
	
	/**
	 * @param line
	 */
	public void processRequest(String line) {
		// Function for converting command line information and call approprite functions
		String[] words = line.split(" ");
		switch(words[0]) {
			case "Hotel":
				addHotelRoom(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
				return;
			case "Booking":
				int roomsRequired = (words.length - 5)/2;
				int[] roomTypes = new int[3];
				
//				Calculate start and end date in LocalDates
				LocalDate startDate = LocalDate.of(2018, Month.of(monthsMap.get(words[2]).intValue()), Integer.parseInt(words[3]));
				LocalDate endDate = startDate.plusDays(Integer.parseInt(words[4])-1);
				
//				Convert Room information into arrays
				for (int i = 0; i < roomsRequired*2; i = i + 2) {
//					roomTypesMap.get(words[i+5])
					roomTypes[roomTypesMap.get(words[i+5])] = Integer.parseInt(words[i+6]);
				}
				
//				Call function with correct information
				String outputBooking = bookingRequest(words[1], startDate, endDate,roomTypes);
				System.out.println("Booking " + outputBooking);
				return;
				
			case "Change":
//				Cancel Booking First
				Booking temp = cancelRequest(words[1]);
				if (temp == null) {
					System.out.println("Change rejected");
				}
				
				int roomsRequiredChange = (words.length - 5)/2;
				int[] roomTypesChange = new int[3];
				
				//Calculate start and end date in LocalDates
				LocalDate startDateChange = LocalDate.of(2018, Month.of(monthsMap.get(words[2]).intValue()), Integer.parseInt(words[3]));
				LocalDate endDateChange = startDateChange.plusDays(Integer.parseInt(words[4])-1);
				
				//Convert Room information into arrays
				for (int i = 0; i < roomsRequiredChange*2; i = i + 2) {
					//roomTypesMap.get(words[i+5])
					roomTypesChange[roomTypesMap.get(words[i+5])] = Integer.parseInt(words[i+6]);
				}
				
				String outputChange = bookingRequest(words[1], startDateChange, endDateChange,roomTypesChange);
				System.out.println("Change " + outputChange);
//				Add Back the previous booking if booking change can not be made
				if ("rejected".equals(outputChange)) {
					temp.getHotel().appendBooking(temp);
				}
				return;
				
			case "Cancel":
//				Cancel Request by customer name
				if (cancelRequest(words[1])!=null) {
					System.out.println("Cancel " + words[1]);
				} else {
					System.out.println("Cancel rejected");
				}
				return;
				
			case "Print":
				String name = words[1];
//				Print Occupancy for each hotel
				for (Hotel hotel : hotels) {
					if(name.equals(hotel.getName())) {
						hotel.printRoomsOccupancy();
					}
				}
				return;
			default :
	            System.out.println("Invalid Input");
		}
	}
		
	
	public String bookingRequest(String name, LocalDate startDate, LocalDate endDate, int[] roomTypes) {
		// TODO Auto-generated method stub
		ArrayList<Room> foundRooms = null;
		Hotel foundHotel = null;
//		Find correct rooms in any hotel and book them
		for (Hotel hotel : hotels) {
			foundRooms = hotel.findRooms(startDate, endDate, roomTypes);
			if (foundRooms != null) {
				foundHotel = hotel;
				hotel.bookRooms(foundRooms, name, startDate, endDate);
				break;
			}
		}
		
//		Make Output String for terminal
		if (foundRooms == null) {
			return String.format("rejected");
		} else {
			StringJoiner  roomString = new StringJoiner(" ");
			for (Room room : foundRooms) {
				roomString = roomString.add(Integer.toString(room.getRoomNumber()));
			}
			return String.format("%s %s %s", name, foundHotel.getName(), roomString);
		}
	}
	
	public Booking cancelRequest(String name) {
		for (Hotel hotel : hotels) {
			Booking booking = hotel.cancelBooking(name);
			if (booking != null) {
				return booking;
			}
		}
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = null;
		HotelBookingSystem HBS = new HotelBookingSystem();
		try
		{
		sc = new Scanner(new File(args[0])); // args[0] is the first command line argument
		// Read input from the scanner here
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
//			System.out.println(line);
			HBS.processRequest(line);
		}
		}
		catch (FileNotFoundException e)
		{
//		System.out.println(e.getMessage());
		}
		finally
		{
		if (sc != null) sc.close();
		}
		
//		HBS.printAllHotelsAndRooms();
		
	}
}
