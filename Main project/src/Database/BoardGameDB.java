package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ModelLayer.Boardgame; 

public class BoardGameDB {
	static Boardgame boardgame;

	public static Boardgame getBoardGameByID(int BoardGameID) throws Exception{
		String name = null;
		String size = null;
		int maxPlayers = 0;
		int minPlayers = 0;
		String category = null;	
		
		String sql = "SELECT * FROM Boardgame WHERE BoardGameID =" + BoardGameID;
			ResultSet results = ConnectionDB.showInfo(sql);	   
			
			while (results.next())
			{
				name = results.getString(1);
				size = results.getString(2);
				maxPlayers = results.getInt(3); 
				minPlayers = results.getInt(4); 
				category = results.getString(5); 
			}
			
			boardgame = new Boardgame(name, size, maxPlayers, minPlayers, category, BoardGameID);
			
			
		return boardgame;
		
	}
	
	
	/*Which kind of list should this be ? */
	public static ArrayList<Boardgame> getBoardGameByName(String boardGameName) throws Exception{ 
		String name = null;
		String size = null;
		int maxPlayers = 0;
		int minPlayers = 0;
		String category = null;	
		int BoardGameID = 0;
		
		ArrayList<Boardgame> boardGameList = new ArrayList<Boardgame>();

		String sql = "SELECT * FROM Boardgame WHERE Name ='" + boardGameName + "'";
			ResultSet results = ConnectionDB.showInfo(sql);	   
			
			while (results.next())
			{
				name = results.getString(1);
				size = results.getString(2);
				maxPlayers = results.getInt(3); 
				minPlayers = results.getInt(4); 
				category = results.getString(5); 
				BoardGameID = results.getInt(6);
				
				boardgame = new Boardgame(name, size, maxPlayers, minPlayers, category, BoardGameID);
				boardGameList.add(boardgame);
			}

		return boardGameList;
	}
	
	public static ArrayList<Boardgame> searchBoardGames(String name, String size, String category, int amountPlayers) throws SQLException
	{
		
		ArrayList<Boardgame> boardGameList = new ArrayList<Boardgame>();
		ResultSet results;
		int BoardGameID;
		int maxPlayers;
		int minPlayers;

		String sql = "SELECT * FROM Boardgame";
		if(name == null && size == null && category == null && amountPlayers <= 0)
		{
			
		}			
		else
		{
			sql = sql + " Where ";
		
		if(name != null)
		{
			sql = sql + "name = '" + name + "'";
		}
		if(name != null && category != null)
		{
			sql = sql + " AND Categories = '" + category + "'";
		}
		else if(category != null)
		{
			sql = sql + "Categories = '" + category + "'";
		}
		if(size != null && name != null || size != null && category != null)
		{
			sql = sql + " AND size = '" + size + "'"; 
		}
		else if (size != null)
		{
			sql = sql + "size = '" + size + "'";
		}
		
		if(amountPlayers > 0 && size != null || amountPlayers > 0 && name != null || amountPlayers > 0 && category != null)
		{
			sql = sql + " AND AmountOfPlayersMax >= " + amountPlayers; 
			sql = sql + " AND AmountOfPlayerMin <= " + amountPlayers; 
		}
		else if (amountPlayers > 0)
		{
			sql = sql + "AmountOfPlayersMax >= " + amountPlayers;
			sql = sql + "AND AmountOfPlayerMin <= " + amountPlayers;
		}
		}
		
		results = ConnectionDB.showInfo(sql);
		
		while (results.next())
		{
			name = results.getString(1);
			size = results.getString(2);
			maxPlayers = results.getInt(3); 
			minPlayers = results.getInt(4); 
			category = results.getString(5); 
			BoardGameID = results.getInt(6);
			
			boardgame = new Boardgame(name, size, maxPlayers, minPlayers, category, BoardGameID);
			boardGameList.add(boardgame);
			System.out.print(boardgame.getName() + "\n");
		}
		
		return boardGameList;
	}
	
	

}
