package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import Database.BoardGameDB;
import GUI.BoardGameSearchWindow;
import GUI.TableSearchWindow;
import ModelLayer.Boardgame;



public class BoardGameCtr {

	Boardgame boardgame;
	static ArrayList<Boardgame> boardGameList;
	
	public Boardgame findBoardGameByID(int BoardGameID) throws Exception
	{
		boardgame = BoardGameDB.getBoardGameByID(BoardGameID);
		return boardgame;	
	}
	
	public ArrayList<Boardgame> findBoardGameByName(String name) throws Exception
	{
		ArrayList<Boardgame> boardGameList = BoardGameDB.getBoardGameByName(name);
		return boardGameList;
	}
	
	public static void searchBoardGamesByMultiple(String name, String size, String category, int amountPlayers) throws SQLException
	{
		BoardGameSearchWindow.resetRowNumber();
		boardGameList = BoardGameDB.searchBoardGames(name, size, category, amountPlayers);
		
		
		for(int i = 0; i < boardGameList.size(); i++)
		{
			String boardGameName = boardGameList.get(i).getName();
			String boardGameSize = boardGameList.get(i).getSize(); 
			int maxPlayerAmount = boardGameList.get(i).getMaxAmountOfPlayers();
			int minPlayerAmount = boardGameList.get(i).getMinAmountOfPlayers();
			String boardGameCategory = boardGameList.get(i).getCategory();
			int boardGameID = boardGameList.get(i).getBoardGameID();

			
			BoardGameSearchWindow.addRowsFremBoardGameList(boardGameName, boardGameSize, maxPlayerAmount, minPlayerAmount, boardGameCategory, boardGameID);
		}
		
		
	}
	
	public static void setBoardGame(int rowNumber)
	{
		Boardgame boardgame = boardGameList.get(rowNumber);
		
		BookingCtr.setBoardGame(boardgame);
		TableSearchWindow.setBoardGame(boardgame.getName());

	}

	
	
}
