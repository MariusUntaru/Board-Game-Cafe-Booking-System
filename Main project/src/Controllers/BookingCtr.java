package Controllers;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.*;
import GUI.BookingWindow;
import ModelLayer.*;

public class BookingCtr {
   
	static Boardgame selectedBoardgame = new Boardgame(null, "Small", 0, 0, null, 0);
	static GameTable selectedTable = new GameTable(null, null, 0);
	private static Person selectedPerson = new Person(null, null, null, null, null, 0);
	static Booking booking = null;

	public BookingCtr() {
        
    }
    public static void addBookingToDatabase() throws Exception
    {
        Database.BookingDB.createBooking(booking);
    }
    
	
	public static ArrayList<Booking> searchBookingTables(String date, ArrayList<GameTable> gameListTable) throws SQLException
	{

		ArrayList<Booking> bookingList = BookingDB.BookingChecker(date, gameListTable);
		
		
		return bookingList;
		
		
	}


	public static void createNewBooking(int amountOfPlayers, String date, int startHour, int endHour, int tableID)
	{
		try {
			booking = new Booking(amountOfPlayers, date, startHour, endHour, GameTableCtr.getGameTableList().get(tableID).getGameTableID(), selectedBoardgame.getBoardGameID(), selectedPerson.getPersonID());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookingWindow.setBoardGameAndBookingInfo(selectedBoardgame.getName(), booking.getDate(), booking.getGameTableID());


	}
	
	
	
	public static void addPerson(String inputText) throws Exception
	{
		setSelectedPerson(Controllers.PersonCtr.searchPerson(inputText));
	}
	
	
	
	public static Boardgame getSelectedBoardgame() {
		return selectedBoardgame;
	}
	public static void setSelectedBoardgame(Boardgame selectedBoardgame) {
		BookingCtr.selectedBoardgame = selectedBoardgame;
	}
	public static GameTable getSelectedTable() {
		return selectedTable;
	}
	public static void setSelectedTable(GameTable selectedTable) {
		BookingCtr.selectedTable = selectedTable;
	}

	public static void setBoardGame(Boardgame boardgame) {
		
		selectedBoardgame = boardgame;
	}
	
	public static void setGameTable(GameTable gametable)
	{
		
	}
	public static Person getSelectedPerson() {
		return selectedPerson;
	}
	public static void setSelectedPerson(Person selectedPerson) {
		BookingCtr.selectedPerson = selectedPerson;
	}
    }