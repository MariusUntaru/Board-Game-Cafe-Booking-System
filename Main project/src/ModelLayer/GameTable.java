package ModelLayer;

public class GameTable {
	
String Size;
String Area;
int GameTableID;

public int getGameTableID() {
	return GameTableID;
}

public void setGameTableID(int gameTableID) {
	GameTableID = gameTableID;
}

public GameTable(String size, String area) {
	this.Size = size;
	this.Area = area;
}

public GameTable(String Size, String Area, int GameTableID)
{
	this.Size = Size;
	this.Area = Area;
	this.GameTableID = GameTableID;
}

public String getSize() {
	return Size;
}
public void setSize(String size) {
	Size = size;
}
public String getArea() {
	return Area;
}
public void setArea(String area) {
	Area = area;
}





}
