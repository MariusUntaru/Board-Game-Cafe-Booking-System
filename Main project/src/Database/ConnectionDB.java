package Database;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    private static String dbURL = "jdbc:sqlserver://kraka.ucn.dk\\databaseName=dmaJ0915_2Sem_4:1433";
    private static String user = "dmaJ0915_2Sem_4";
    private static String pass = "IsAllowed";

    private static Connection conn = null;
    private static ConnectionDB instance = null;

    private ConnectionDB() throws SQLException {
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Success");
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
             //   conn.close();
            }
        }

    }

    public static ConnectionDB getInstance() throws SQLException {
        if(instance == null){
            instance = new ConnectionDB();
        }
        return instance;
    }
    
    public static void startTransaction()
    {
    	try
    	{
    		conn.setAutoCommit(false);
    	}
    	
    	catch(Exception e)
    	{
    		System.out.println("Error in startTransaction");
    		System.out.println(e.getMessage());
    	}
    }
    
    public static void commitTransaction()
    {
    	try
    	{
    		conn.setAutoCommit(true);
    	}
    	
    	catch(Exception e)
    	{
    		System.out.println("Error incommitTransaction"); 
    		System.out.println(e.getMessage());
    	} 
    }
    
    public static void rollbackTransaction()
    {
    	try
    	{
    		conn.rollback(); conn.setAutoCommit(true);
    	}
    	
    	catch(Exception e)
    	{
    		System.out.println("Error in rollbackTransaction"); 
    		System.out.println(e.getMessage());
    	} 
    }
    
    public static void addInfo(String query) throws SQLException
    {
        ConnectionDB.getInstance();

    	Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(query);    	
    }
    
    public static boolean checkConnection()
    {
    	boolean connection = conn != null;

    	return connection;
    }
    
    public static void deleteInfo(String query) throws SQLException
	{
    ConnectionDB.getInstance();

	Statement stmt = null;
    stmt = conn.createStatement();
    stmt.executeUpdate(query);    	
	}
	public static ResultSet showInfo(String query) throws SQLException
	{
    ResultSet results = null;	
    getInstance();
	Statement stmt = null;
    stmt = conn.createStatement();
    results = stmt.executeQuery(query);  
    return results;
	}
    
    public static void updateInfo (String query) throws SQLException
    {
    	getInstance();
    	Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }
    
	public static int getID(String query) throws SQLException
    {
        getInstance();
        int finalResult = 0;
    	Statement stmt;
        stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while (result.next()){
			finalResult =  ((Number) result.getObject(1)).intValue();
		}
		
    	    	
    	return finalResult + 1;	
		}
	
	


    public static void Disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return conn;
    }

   	public static void main(String[] args) throws Exception {

        	Thread checkConn = new Thread(new Runnable(){
        		public void run()
        		{
     
    					GUI.connectionViewer frame2 = new GUI.connectionViewer();
    					frame2.setVisible(true);
        	            
        		}
        	}); 
        	
        	
        	Thread allOtherStuff = new Thread(new Runnable(){
        		public void run()
        		{
    				
    				GUI.Menu frame = new GUI.Menu();
    				frame.setVisible(true);
    				
        		}
        	
        		});
        	
        	
        	checkConn.start();
        	allOtherStuff.start();
        	
        
    }
    }
