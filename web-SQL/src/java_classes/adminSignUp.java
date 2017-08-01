package java_classes;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;


public class adminSignUp 
{
  private static int id;
  private static String username;
  private static String name;
  private static String password;
  
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL ="jdbc:mysql://localhost/buy-sell-repeat";
  private static Statement statement = null;
  private static ResultSet resultSet = null;
  
  static final String User = "root";
  static final String pass ="password";
  public static adminSignUp ConnectedAdmin;
  
  public adminSignUp(String user, String namein, String pass)
  {
	  this.username = user;
	  this.name = namein;
	  this.password = pass;
  }
  public adminSignUp(int idin, String userin, String Name, String pass)
  {
	 this.id = idin;
	 this.username = userin;
	 this.name = name;
	 this.password = pass;
  }
  public adminSignUp()
  {
	  
  }
  private void SetUsername(String userin)
  {
	  this.username = userin;
  }
  private void SetName(String namein)
  {
	  this.name = namein;
  }
  private void SetPassword(String passIn)
  {
	  this.password = passIn;
  }
  public int getID()
  {
	  return id;
  }
  public String getUsername()
  {
	  return username;
  }
  private String getName()
  {
	  return name;
  }
  public String getPassword()
  {
	  return password;
  }
  public static int add(adminSignUp signin)
  {
	  int status = 0;
	  
	  String sqlString ="insert into admin( username, name,password) values(\'"+signin.getUsername()+ 
				"\', \'"+signin.getName()+ 
				"\', \'"+signin.getPassword()+"\')";
	  status = databaseUpdate(sqlString);
	  System.out.print(sqlString);  
	  return status;
  }
  public static ArrayList<adminSignUp> viewAllAdmins()
  {
	  ArrayList<adminSignUp> AllAdmins = new ArrayList<adminSignUp>();
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection conn = DriverManager.getConnection(DB_URL,User, pass);
		  statement = conn.createStatement();
		  resultSet = statement.executeQuery("select * FROM admin");
		  
		  while(resultSet.next ())
		  {
			  adminSignUp nextadmin = new adminSignUp(resultSet.getInt("id"),
					               
							              resultSet.getString("username"),
							              resultSet.getString("name"),
							              resultSet.getString("password"));
							     
			  AllAdmins.add(nextadmin);
		  }
	  conn.close();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	   return AllAdmins; 
	}
  public int updateAdmin(int IDNoIn, String usernamein, String passwordin)   
  {
      int status = 0;
      String sqlString="update admin set username=\'"+usernamein+"',password = '"+passwordin+"\' where id="+IDNoIn;
      status = databaseUpdate(sqlString);                                            
      return status;

    
   }
public static int databaseUpdate(String sqlUpdate)
  {
		int status = 0;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, User, pass);
			statement=conn.createStatement();
			status=statement.executeUpdate(sqlUpdate);
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return status;
  }
  public static void main(String []args)
  {
	
    Scanner keyIn = new Scanner(System.in);

	
	System.out.println("Userername: ");
	String user=keyIn.nextLine();
	System.out.println("Password: ");
	String pass=keyIn.nextLine();
	ArrayList<adminSignUp> ars= new ArrayList<adminSignUp>();
	ars=adminSignUp.viewAllAdmins();
	for(adminSignUp s:ars){
		if(user.equals(s.getUsername())&& pass.equals(s.getPassword()))
	    {
			
			adminSignUp.ConnectedAdmin=s;
	    	System.out.println("connected");

	    }

	}
  }
  

  }
