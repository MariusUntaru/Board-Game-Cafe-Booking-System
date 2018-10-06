package ModelLayer;
import java.sql.SQLException;
import Database.ConnectionDB;

public class Person {

String Name;
String Address; 
String Email;
String PhoneNumber;
String PersonType;	
static int PersonID;
Person person;

public Person(String name, String address, String email, String phoneNumber, String personType, int personID) {
	Name = name;
	Address = address;
	Email = email;
	PhoneNumber = phoneNumber;
	PersonType = personType;
	PersonID = personID;
}

public Person(String name, String address, String email, String phoneNumber, String personType) throws SQLException {
	Name = name;
	Address = address;
	Email = email;
	PhoneNumber = phoneNumber;
	PersonType = personType;
	PersonID = ConnectionDB.getID("SELECT max(PersonID) FROM Person");
}


public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getPhoneNumber() {
	return PhoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	PhoneNumber = phoneNumber;
}
public String getPersonType() {
	return PersonType;
}
public void setPersonType(String personType) {
	PersonType = personType;
}
public int getPersonID() {
	return PersonID;
}
public void setPersonID(int personID) {
	PersonID = personID;
}



}
