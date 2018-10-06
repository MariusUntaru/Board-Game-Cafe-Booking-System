package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ModelLayer.Boardgame;
import ModelLayer.GameTable;

public class GameTableDB {	
	
	static GameTable gametable;


	public static GameTable getGameTableID(int GameTableID) throws Exception{
		String area = null;
		String size = null;
	
		String sql = "SELECT * FROM GameTable WHERE GameTableID =" + GameTableID;
		ResultSet results = ConnectionDB.showInfo(sql);	  
		
		while (results.next())
		{
			area = results.getString(1);
			size = results.getString(2);
			GameTableID = results.getInt(3);
		}
		
		gametable = new GameTable(area, size, GameTableID);
		
		return gametable;

	}

	public static ArrayList<GameTable> searchGameTable(String area, int amountOfPlayers, Boardgame boardgame) throws SQLException {
		ArrayList<GameTable> gameTableList = new ArrayList<GameTable>();
		
		String sql = "SELECT * FROM GameTable";
		if(area == null && amountOfPlayers <= 0 && boardgame.getSize() == null)
		{
			
		}
		else
		{
			sql = sql + " Where ";
		
		if(area != null)
		{
			sql = sql + "Area = '" + area + "'";

		}
		
		if(area == null && boardgame.getSize().compareTo("Big") == 0 || amountOfPlayers > 5 && area == null && amountOfPlayers != 0)
		{
			
			sql = sql + "Size = 'Big'";
		}
		else if(area == null && boardgame.getSize().compareTo("Small") == 0 || amountOfPlayers < 6 && area == null && amountOfPlayers != 0)
		{
			sql = sql + "Size = 'Small'";
		}
		
		if(boardgame.getSize().compareTo("Big") == 0 || amountOfPlayers > 5 && amountOfPlayers != 0)
		{
			
			sql = sql + "and Size = 'Big'";
		}
		else if(boardgame.getSize().compareTo("Small") == 0 || amountOfPlayers < 6 && amountOfPlayers != 0)
		{
			sql = sql + "and Size = 'Small'";
		}

		
		}
			
		ResultSet results = ConnectionDB.showInfo(sql);	  
				
		
			while (results.next())
			{
				String newArea = results.getString(1);
				String size = results.getString(2);
				int GameTableID = results.getInt(3);
				
				gametable = new GameTable(newArea, size, GameTableID);
				
				gameTableList.add(gametable);

			}
			
			return gameTableList;
		
	}

}
	
	
 

	
 
