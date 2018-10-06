package ModelLayer;

import java.sql.SQLException;

import Database.ConnectionDB;

public class Boardgame {
String name, category, size;

int boardGameID, minAmountOfPlayers, maxAmountOfPlayers;



public Boardgame(String name, String category, int minAmountOfPlayers, int maxAmountOfPlayers) throws SQLException {
	this.name = name;
	this.category = category;
	this.minAmountOfPlayers = minAmountOfPlayers;
	this.maxAmountOfPlayers = maxAmountOfPlayers;
	this.boardGameID = ConnectionDB.getID("SELECT max(boardGameID) FROM Boardgame");
}


public Boardgame(String name, String size, int maxPlayers, int minPlayers, String category, int boardGameID) {
	this.name = name;
	this.category = category;
	this.minAmountOfPlayers = minPlayers;
	this.maxAmountOfPlayers = maxPlayers;
	this.boardGameID = boardGameID;
	this.size = size;
	
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public int getBoardGameID() {
	return boardGameID;
}
public void setBoardGameID(int boardGameID) {
	this.boardGameID = boardGameID;
}
public int getMinAmountOfPlayers() {
	return minAmountOfPlayers;
}
public void setMinAmountOfPlayers(int minAmountOfPlayers) {
	this.minAmountOfPlayers = minAmountOfPlayers;
}
public int getMaxAmountOfPlayers() {
	return maxAmountOfPlayers;
}
public void setMaxAmountOfPlayers(int maxAmountOfPlayers) {
	this.maxAmountOfPlayers = maxAmountOfPlayers;
}

public String getSize() {
	return size;
}


public void setSize(String size) {
	this.size = size;
}

}
