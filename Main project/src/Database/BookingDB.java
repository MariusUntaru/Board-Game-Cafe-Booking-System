package Database;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controllers.BookingCtr;
import Database.ConnectionDB;
import ModelLayer.*;

public abstract class BookingDB {
	
	public BookingDB() throws SQLException {
		}
	
	public static void createBooking(Booking booking) throws Exception
	{
			int bookingID = 0;
		try{
			ConnectionDB.startTransaction();
			
			String query = "SELECT max(BookingID) FROM Booking";
			bookingID = ConnectionDB.getID(query);
		}
		catch(Exception e)
		{ ConnectionDB.rollbackTransaction();
		
		Component frame = null;
		JOptionPane.showMessageDialog((Component) frame, "Booking was not made because ID could not be found, talk with your administrator");

		}

		String sqlCheck = "SELECT * FROM Booking WHERE GameTableID =" + booking.getGameTableID() + "AND date = '" + booking.getDate()+ "'";
		
		ResultSet results = ConnectionDB.showInfo(sqlCheck);	 
		
		while (results.next())
		{
			String BookingDate = results.getString(2);
			int startHour = results.getInt(3); 
			int endHour = results.getInt(4); 
			
			if(BookingDate.equalsIgnoreCase(booking.getDate()) && ( startHour > booking.getEndHour() ^ endHour < booking.getStartHour()))			
			{
				
			}
			else
			{

				Component frame = null;
				JOptionPane.showMessageDialog((Component) frame, "conflicting booking found, booking was not made");
				ConnectionDB.rollbackTransaction();
				throw new Exception();
			}


		}
		

		

	    String sqlAdd = "INSERT INTO Booking VALUES ("+ booking.getAmountOfPlayers()+",'"+booking.getDate()+"',"+booking.getStartHour()+","+booking.getEndHour()+","+bookingID+","+0+","+booking.getGameTableID()+","+booking.getBoardgameID()+","+BookingCtr.getSelectedPerson().getPersonID()+")";
	    
	    ConnectionDB.addInfo(sqlAdd);
	    ConnectionDB.commitTransaction();
		Component frame = null;
		JOptionPane.showMessageDialog((Component) frame, "Succesfully booked");

	    
	}	    
	

	
	public static ArrayList<Booking> BookingChecker (String date, ArrayList<GameTable> gameListTable) throws SQLException
	{
		ArrayList<Booking> BookingList = new ArrayList<Booking>();

		for(int i = 0; i < gameListTable.size(); i++)
		{

			String sql = "SELECT * FROM Booking WHERE GameTableID = " + gameListTable.get(i).getGameTableID() + " AND date = '" + date + "'";
			ResultSet results = ConnectionDB.showInfo(sql);	 
			Booking booking = new Booking(0, null, 0, 0, 0, 0, 0, 0);

			if(results != null){
			while (results.next())
			{
				int amountOfPlayers = results.getInt(1);
				String BookingDate = results.getString(2);
				int startHour = results.getInt(3); 
				int endHour = results.getInt(4); 
				int bookingID = results.getInt(5); 
				int GameTableID = results.getInt(7);
				int PersonID = results.getInt(9);
				
				
				booking = new Booking(amountOfPlayers, BookingDate, startHour, endHour, bookingID, GameTableID, PersonID);
				

			}
			}
			BookingList.add(booking);	

			
			
		}
		
		return BookingList;
	}
	
	
}
	


