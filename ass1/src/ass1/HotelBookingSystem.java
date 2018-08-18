/**
 * 
 */
package ass1;

import java.awt.print.Book;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.sun.glass.ui.TouchInputSupport;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;

import javafx.scene.shape.Line;

/**
 * @author Jay Motwani
 *
 */
public class HotelBookingSystem {

	private ArrayList<Hotel> hotels;
	
	/**
	 * 
	 */
	public HotelBookingSystem() {
		// TODO Auto-generated constructor stub
		hotels = new ArrayList<Hotel>();
		
		
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
	private void processRequest(String line) {
		// TODO Auto-generated method stub
		String[] words = line.split(" ");
		switch(words[0]) {
			case "Hotel":
				addHotelRoom(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));
				break;
			case "Booking":
				bookingRequest(words);
				break;
			case "Change":
				
				break;
			case "Cancel":
				System.out.println("Unfinished");
				break;
			case "Print":
				System.out.println("Unfinished");
				break;
			default :
	            System.out.println("Invalid Input");
		}
	}

	private void bookingRequest(String[] words) {
		// TODO Auto-generated method stub
		int roomsRequired = (words.length - 5)/2;
		ArrayList<String> roomType = new ArrayList<String>();
		ArrayList<Integer> numberOfRooms = new ArrayList<Integer>();
		for (int i = 0; i < roomsRequired*2; i=i+2) {
			roomType.add(words[i+5]);
			numberOfRooms.add(Integer.parseInt(words[i+6]));
			System.out.println(String.format("Room Type: %s", words[i+5]));
			System.out.println(String.format("Number of Rooms: %s", words[i+6]));
			
		}
		
//		for (Hotel hotel : hotels) {
//			if (hotel.meetsAvailability Requirements) {
//				
//			}
//		}
		
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
			System.out.println(line);
			HBS.processRequest(line);
		}
		}
		catch (FileNotFoundException e)
		{
		System.out.println(e.getMessage());
		}
		finally
		{
		if (sc != null) sc.close();
		}
		
		HBS.printAllHotelsAndRooms();
		
	}



}
