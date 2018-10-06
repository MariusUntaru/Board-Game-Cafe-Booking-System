package Database;

public class connectionChecker{

	
public static int checkconnection() throws InterruptedException
{

	int connected = 0;
		if(ConnectionDB.checkConnection())
		{
			connected = 1;
		}

		else
		{
			connected = 0;
		}
		
		return connected;
}


}