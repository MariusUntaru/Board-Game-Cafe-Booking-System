package Database;
import java.sql.ResultSet;
import ModelLayer.*;

public abstract class PersonDB {
	
	static Person person;
	
	
     
	public static Person searchPerson(String dPhoneNo) throws Exception{
		
		String Name = null;
		String Address = null;
		String Email = null;
		String PhoneNumber = null;
		String PersonType = null;
		int PersonID = 0;
		
		String sql = "SELECT * FROM Person WHERE PhoneNumber =" + dPhoneNo;
		ResultSet results = ConnectionDB.showInfo(sql);	  
		
		while (results.next())
		{
			Name = results.getString(1);
			Address = results.getString(2);
			Email = results.getString(3); 
			PhoneNumber = results.getString(4); 
			PersonType = results.getString(5); 
			PersonID = results.getInt(6);
		}
		
		person = new Person(Name, Address, Email, PhoneNumber, PersonType, PersonID);
		
		return person;
		
	}
	

}
