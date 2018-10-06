package Controllers;

import java.util.ArrayList;

import Database.GameTableDB;
import GUI.TableSearchWindow;
import ModelLayer.*;

public class GameTableCtr {
	
	static GameTable gametable;
	static ArrayList<GameTable> GameTableList = new ArrayList<GameTable>();
	static ArrayList<Booking> bookingList = new ArrayList<Booking>();

	
	public static GameTable findGameTableID(int GameTableID) throws Exception
	{
		gametable = GameTableDB.getGameTableID(GameTableID);
		return gametable;
	}
	
	public static void searchGameTable(String area, int amountOfPlayers) throws Exception
	{
		GameTableList = null;
		GameTableList = GameTableDB.searchGameTable(area, amountOfPlayers, BookingCtr.getSelectedBoardgame());
		
		for(int i = 0; i < GameTableList.size(); i++)
		{
		TableSearchWindow.addRowsFromGameTableList(GameTableList.get(i).getGameTableID());

		}	
			
	}
	
	

	public static void addBookingData(String date)
	{
	bookingList = new ArrayList<Booking>();
	
	try {

		bookingList = BookingCtr.searchBookingTables(date, GameTableList);
	} catch (Exception e) {
		// TODO Auto-generated catch block
			   e.printStackTrace();
	}
	
	for(int i = 0; i < bookingList.size(); i++)
	{
		int startHour = bookingList.get(i).getStartHour() - 13;
		int endHour = bookingList.get(i).getEndHour()-13;
		

		TableSearchWindow.addBookingStates(startHour, endHour, i);
		
	}			
	}
	
	public static ArrayList<Booking> getBookingList() {
		return bookingList;
	}

	public static void setBookingList(ArrayList<Booking> bookingList) {
		GameTableCtr.bookingList = bookingList;
	}

	public static GameTable getGametable() {
		return gametable;
	}

	public static void setGametable(GameTable gametable) {
		GameTableCtr.gametable = gametable;
	}

	public static ArrayList<GameTable> getGameTableList() {
		return GameTableList;
	}

	public static void setGameTableList(ArrayList<GameTable> gameTableList) {
		GameTableList = gameTableList;
	}
}
	
