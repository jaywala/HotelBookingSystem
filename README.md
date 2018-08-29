# cs2511-assignment1
This is the backend umbrella Hotel Booking System that manages bookings for multiple hotels in Java based on requirements of users bookings such as dates, number of guests per room and number of rooms

# Input Format
Hotel <hotelname> <roomnumber> <capacity>
// specify that hotel with <hotelname> has a room with number <roomnumber> that has the size <capacity>
Booking <name> <month> <date> <numdays> <type1> <number1> <type2> <number2> . . .
// booking under <name> starting on <month> and <date> for <numdays> of <number1> rooms of <type1>, etc.
Change <name> <month> <date> <numdays> <type1> <number1> <type2> <number2> . . .
// change booking under <name> to start on <month> and <date> for <numdays> of <number1> rooms of <type1>, etc.
Cancel <name>
// cancel booking under <name> (if it exists) and free up rooms
Print <hotelname>
// print occupancy of each room in the hotel <hotelname>

# Sample Input
Hotel Surfers 101 2 # Hotel Surfers has room 101 with capacity 2 ("double" room)
Hotel Surfers 102 2 # Hotel Surfers has room 102 with capacity 2 ("double" room)
Hotel Surfers 103 1 # Hotel Surfers has room 103 with capacity 1 ("single" room)
Hotel Burleigh 101 2 # Hotel Burleigh has room 101 with capacity 2 ("double" room)
Booking Aarthi Jan 25 2 single 1 double 1
// Aarthi's booking request is for 1 single and 1 double room starting on Jan 25 for 2 nights
// Assign rooms 101 and 103 of Hotel Surfers (output Booking Aarthi Surfers 101 103)
Booking Rob Jan 24 4 double 1
// Rob's booking request is for 1 double room starting on Jan 24 for 4 nights
// Assign room 102 of Hotel Surfers since room 101 is occupied (output Booking Rob Surfers 102)
Booking Stephanie Jan 26 1 double 1
// Stephanie's booking request is for 1 double room starting on Jan 26 for 1 night
// Assign room 101 of Hotel Burleigh (output Booking Stephanie Burleigh 101)
Change Aarthi Jan 27 3 single 1
// Change Aarthi's booking to 1 single room starting on Jan 27 for 3 nights
// Deassign rooms 101 and 103 of Hotel Surfers and assign room 103 (output Change Aarthi Surfers 103)
Booking Gary Jan 25 2 single 1
// Gary's booking request is for 1 single room starting on Jan 25 for 2 nights
// Assign room 103 of Hotel Surfers (output Booking Gary Surfers 103)
Cancel Stephanie
// Cancel booking 3
// Deassign room 101 of Hotel Burleigh (output Cancel Stephanie)
Booking Hussein Jan 26 1 single 1
// Hussein's Booking request is for 1 single room starting on Jan 26 for 1 night
// Request cannot be fulfilled (output Booking rejected)
Print Surfers
// Print out occupancy of all rooms at Hotel Surfers, in order of room declarations,
