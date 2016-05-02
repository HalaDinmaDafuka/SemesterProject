/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import entity.Client;
import entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Desting
 */
public class ClientMapper
{
    private final Connection connect;
    private int temp_no;

    public ClientMapper(Connection con)
    {
        this.connect = con;
    }
    
    //Numbers
    public int getNextClientNo() {
        int nextClientNo = 0;
        String SQLString
                = "select client_no_seq.nextval  "
                + "from dual";
        PreparedStatement statement = null;
        try {
            statement = connect.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                nextClientNo = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextOrderNo");
            System.out.println(e.getMessage());
        }
        return nextClientNo;
    }
    
    public int getCurrentClientNo() {
        int nextClientNo = 0;
        String SQLString
                = "select client_no_seq.currval  "
                + "from dual";
        PreparedStatement statement = null;
        try {
            statement = connect.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                nextClientNo = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextOrderNo");
            System.out.println(e.getMessage());
        }
        return nextClientNo;
    }
    
    //Saving information
    public boolean saveInformationIntoClientTable(ArrayList<Client> clientList) {
        int rowsInserted = 0;
        String SQLString
                = "insert into Client_TBL "
                + "values (?,?,?,?,?)";
        PreparedStatement statement = null;
        //System.out.println("hellloooo");
            for (int i = 0; i < clientList.size(); i++) {
        try {
                Client client = clientList.get(i);
                statement = connect.prepareStatement(SQLString);
                statement.setInt(1, client.getClient_no());
                statement.setString(2, client.getClient_name());
                statement.setString(3, client.getClient_surname());
                statement.setString(4, client.getClient_address());
                statement.setInt(5, client.getRepresentative_no());
                rowsInserted += statement.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - saveNewClient HAHAHAAHAHHAHAAH");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            System.out.println("insertOrders(): " + (rowsInserted == clientList.size()));
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - saveNewClient");
                System.out.println(e.getMessage());
            }
        }
        }
        return rowsInserted == clientList.size();
    }
    
    public boolean saveInformationIntoClientPrivateInformationTable(ArrayList<Client> clientList) {
        int rowsInserted = 0;
        String SQLString
                = "insert into CLIENT_PRIVATEINF_TBL "
                + "values (?,?,?,?,?,?)";
        PreparedStatement statement = null;

            for (int i = 0; i < clientList.size(); i++) {
        try {
                Client client = clientList.get(i);
                statement = connect.prepareStatement(SQLString);
                statement.setInt(1, client.getClient_no());
                statement.setInt(2, client.getClient_passport());
                statement.setString(3, client.getClient_country());
                statement.setInt(4, client.getClient_phone());
                statement.setString(5, client.getClient_email());
                statement.setString(6, client.getClient_agency());
                rowsInserted = statement.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - addPrivateInfo");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - addPrivateInfo");
                System.out.println(e.getMessage());
            }
        }}
        return rowsInserted == 1;
    }
    
    // Loading information
    public Client getInformationFromClientTable(int client_no) { //CHANGEZZZ
        Client client = null;
        String SQLString1 = // get order
                "select * "
                + "from Client_TBL "
                + "where client_no = ?";

        PreparedStatement statement = null;
        try {
            //=== get order
            statement = connect.prepareStatement(SQLString1);
            statement.setInt(1, client_no);     // primary key value
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client(client_no,
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
                //lqlq
                System.out.println(client.toString());
            }
        } catch (SQLException e) {
            System.out.println("Fail in OrderMapper - getOrder");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - getOrder");
                System.out.println(e.getMessage());
            }
        }
        return client;
    }
    
    public Client getInformationFromClientPrivateInformationTable(int client_no) {
        Client client = null;
        String SQLString1 = // get order
                "select * "
                + "from Client_PrivateInf_TBL "
                + "where client_no = ?";
        PreparedStatement statement = null;
        try {
            //=== get order
            statement = connect.prepareStatement(SQLString1);
            statement.setInt(1, client_no);     // primary key value
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client(client_no,
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println("Fail in OrderMapper - getOrder");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - getOrder");
                System.out.println(e.getMessage());
            }
        }
        return client;
    }
    
    //Update
    public boolean updateInformationIntoClientTable(ArrayList<Client> clientUpdateList) {
        boolean updated = true;

        String SQLString1
                = "update Client_TBL "
                + "set client_name = ?, client_surname = ?, client_address = ?, representative_no = ?"
                + "where client_no = ?";
        PreparedStatement statement = null;
        try {
            for (int i = 0; i < clientUpdateList.size(); i++) {
                Client client = clientUpdateList.get(i);

                statement = connect.prepareStatement(SQLString1);

                statement.setInt(5, client.getClient_no());
                statement.setString(1, client.getClient_name());
                statement.setString(2, client.getClient_surname());
                statement.setString(3, client.getClient_address());
                statement.setInt(4, client.getRepresentative_no());

                statement.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - updateClientTBLinfo");
            System.out.println(e.getMessage());
            updated = false;
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - updateClientTBLinfo");
                System.out.println(e.getMessage());
                updated = false;
            }
        }
        return updated;
    }
    
    public boolean updateInformationIntoClientPrivateInformationTable(ArrayList<Client> clientUpdateList) {
        int rowsUpdated = 0;
        boolean updated = true;
        String SQLString
                = "update Client_PrivateInf_TBL "
                + "set client_passport=?, client_country=?, client_phone=?, client_email=?, client_agency=? "
                + "where client_no=?";
        PreparedStatement statement = null;
        try {
            for (int i = 0; i < clientUpdateList.size(); i++) {
                Client client = clientUpdateList.get(i);
                statement = connect.prepareStatement(SQLString);
                statement.setInt(1, client.getClient_passport());
                statement.setString(2, client.getClient_country());
                statement.setInt(3, client.getClient_phone());
                statement.setString(4, client.getClient_email());
                statement.setString(5, client.getClient_agency());
                statement.setInt(6, client.getClient_no());
                rowsUpdated = statement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Fail in UpdateOrderMapper - updateClientPersonalInformation");
            System.out.println(e.getMessage());
            updated = false;
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - updateClientPersonalInformation");
                System.out.println(e.getMessage());
                updated = false;
            }
        }
        return updated;
    }
    
    
    
    
    
}
