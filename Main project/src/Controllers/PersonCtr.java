package Controllers;
import Database.*;
import ModelLayer.*;

public class PersonCtr {
       
    public static Person searchPerson(String phoneNumber) throws Exception
    {
		Person person = PersonDB.searchPerson(phoneNumber);
		return person;
       
    }
    
    
    }