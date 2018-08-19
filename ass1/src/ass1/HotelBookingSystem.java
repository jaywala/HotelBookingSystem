/**
 * 
 */
package ass1;

import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.corba.se.impl.protocol.giopmsgheaders.CancelRequestMessage;
import com.sun.glass.ui.TouchInputSupport;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.xml.internal.bind.v2.runtime.Name;
import com.sun.xml.internal.ws.api.Cancelable;

import javafx.scene.shape.Line;

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

	public void addHotelRoom(String hotelName,int roomNumber , int capacity) {
//		Check if Current Hotel Exists
		for (Hotel hotel : hotels) {
			if (hotel.compareName(hotelName) == true) {
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
		// TODO Auto-generated method stub
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
				if (!cancelRequest(words[1])) {
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
				return;
				
			case "Cancel":
				if (cancelRequest(words[1])) {
					System.out.println("Cancel " + words[1]);
				} else {
					System.out.println("Cancel rejected");
				}
				return;
				
			case "Print":
				String name = words[1];
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
		
	
	private String bookingRequest(String name, LocalDate startDate, LocalDate endDate, int[] roomTypes) {
		// TODO Auto-generated method stub
		ArrayList<Room> foundRooms = null;
		Hotel foundHotel = null;
		for (Hotel hotel : hotels) {
			foundRooms = hotel.findRooms(startDate, endDate, roomTypes);
			if (foundRooms != null) {
				foundHotel = hotel;
				hotel.bookRooms(foundRooms, name, startDate, endDate);
				break;
			}
		}
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
	
	public boolean cancelRequest(String name) {
		for (Hotel hotel : hotels) {
			if (hotel.cancelBooking(name)) {
				return true;
			}
		}
		return false;
	}

	//	tesing Function	
	public void printAllHotelsAndRooms() {
		for (Hotel hotel : hotels) {
			System.out.println(hotel.getName());
			ArrayList<Room> rooms = hotel.getRooms();
			for (Room room : rooms) {
				System.out.println(String.format("Hotel: %s: Room: %s", hotel.getName(), room.getRoomNumber()));
			}
		}
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
