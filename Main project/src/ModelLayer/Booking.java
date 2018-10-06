package ModelLayer;

import java.sql.SQLException;

import Database.ConnectionDB;

public class Booking {
	
int AmountOfPlayers;
String Date;
int StartHour;
int EndHour;
int BookingID;
int GameTableID;
int personID;
int BoardgameID;


public Booking(int amountOfPlayers, String date, int startHour, int endHour, int bookingID, int boardgameID, int GameTableID, int PersonID) {
	this.AmountOfPlayers = amountOfPlayers;
	this.Date = date;
	this.StartHour = startHour;
	this.EndHour = endHour;
	this.BookingID = bookingID;
	this.GameTableID = GameTableID;
	this.BoardgameID = boardgameID;
}

public Booking(int amountOfPlayers, String date, int startHour, int endHour, int GameTableID, int boardgameID, int PersonID) throws SQLException {
	this.AmountOfPlayers = amountOfPlayers;
	this.Date = date;
	this.StartHour = startHour;
	this.EndHour = endHour;
	this.BookingID = ConnectionDB.getID("SELECT max(BookingID) FROM Booking");
	this.GameTableID = GameTableID;
	this.BoardgameID = boardgameID;
}

public int getAmountOfPlayers() {
	return AmountOfPlayers;
}
public void setAmountOfPlayers(int amountOfPlayers) {
	AmountOfPlayers = amountOfPlayers;
}
public String getDate() {
	return Date;
}
public void setDate(String date) {
	Date = date;
}
public int getStartHour() {
	return StartHour;
}
public void setStartHour(int startHour) {
	StartHour = startHour;
}
public int getEndHour() {
	return EndHour;
}
public void setEndHour(int endHour) {
	EndHour = endHour;
}
public int getBookingID() {
	return BookingID;
}
public void setBookingID(int bookingID) {
	BookingID = bookingID;
}

public int getGameTableID() {
	return GameTableID;
}

public void setGameTableID(int gameTableID) {
	GameTableID = gameTableID;
}

public int getPersonID() {
	return personID;
}

public void setPersonID(int personID) {
	this.personID = personID;
}

public int getBoardgameID() {
	return BoardgameID;
}

public void setBoardgameID(int boardgameID) {
	BoardgameID = boardgameID;
}

}
