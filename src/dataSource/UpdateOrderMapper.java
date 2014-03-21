package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import domain.Client;

public class UpdateOrderMapper {

    private final Connection con;

    public UpdateOrderMapper(Connection con) {
        this.con = con;
    }

    public void updateClientTBLinfo(int client_no, String client_name, String client_surname, String client_address) {
        Client updateClient;
        String SQLString1
                = "update Client_TBL "
                + "set client_name = ?, client_surname = ?, client_address = ? "
                + "where client_no = ?";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString1);
            
            statement.setInt(4, client_no);
            statement.setString(1, client_name);
            statement.setString(2, client_surname);
            statement.setString(3, client_address);
            
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - updateClientTBLinfo");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - updateClientTBLinfo");
                System.out.println(e.getMessage());
            }
        }
    }
    
    public Client updateClientPersonalInformation(int client_no, int client_passport, String client_country, int client_phone, String client_email, String client_agency)
  {
      Client updateClientPrivateInfo=null; //this is not correct 
     int rowsUpdated =0;
    String SQLString = 
        "update Client_PrivateInf_TBL " +
        "set client_passport=?, client_country=?, client_phone=?, client_email=?, client_agency=? " +
            "where client_no=?";  
    PreparedStatement statement=null;
    try{        
        //== insert tuple
        statement = con.prepareStatement(SQLString);
        
       // statement.setInt    (1, client.getClient_no());
        statement.setInt(1, client_passport);
        statement.setString(2, client_country);
        statement.setInt(3, client_phone);
        statement.setString(4, client_email);
        statement.setString(5, client_agency);
        statement.setInt(6, client_no);
        rowsUpdated  = statement.executeUpdate();
    }
    catch (Exception e)
    {   
        System.out.println("Fail in UpdateOrderMapper - updateClientPersonalInformation");
        System.out.println(e.getMessage());
    }
     finally														// must close statement
    {
  	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail in OrderMapper - updateClientPersonalInformation");
			System.out.println(e.getMessage());
		}  
    }
    return updateClientPrivateInfo;
  }
    
    public Client updateReservationInformation(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no)
  {
      Client updateClientPrivateInfo=null; //this is not correct 
     int rowsUpdated =0;
    String SQLString = 
        "update Reservation_TBL " +
        "set client_arrival=?, client_departure=?, client_no=?, room_no=? " +
            "where reservation_no=?";  
    PreparedStatement statement=null;
    try{        
        //== insert tuple
        statement = con.prepareStatement(SQLString);
        
       // statement.setInt    (1, client.getClient_no());
        statement.setString(1, client_arrival);
        statement.setString(2, client_departure);
        statement.setInt(3, client_no);
        statement.setInt(4, room_no);
        statement.setInt(5, reservation_no);
        rowsUpdated  = statement.executeUpdate();
    }
    catch (Exception e)
    {   
        System.out.println("Fail in UpdateOrderMapper - updateReservationInformation");
        System.out.println(e.getMessage());
    }
     finally														// must close statement
    {
  	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail in OrderMapper - updateReservationInformation");
			System.out.println(e.getMessage());
		}  
    }
    return updateClientPrivateInfo;
  }
}
