package dataSource;

import domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PullOrderMapper implements PullOrderMapperInterface {
    
    private final Connection con;
    
    public PullOrderMapper(Connection con) {
        this.con = con;
    }
    
    @Override
    public Client getClientTBLinfo(int client_no) {
        Client client = null;
        String SQLString1 = // get order
                "select * "
                + "from Client_TBL "
                + "where client_no = ?";
        
        PreparedStatement statement = null;
        try {
            //=== get order
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, client_no);     // primary key value
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client(client_no,
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
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
    
    @Override
    public Client getClientprivinfTBLinfo(int client_no) {
        Client client = null;
        String SQLString1 = // get order
                "select * "
                + "from Client_PrivateInf_TBL "
                + "where client_no = ?";
        PreparedStatement statement = null;
        try {
            //=== get order
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, client_no);     // primary key value
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                client = new Client(client_no,
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));
                //lqlq
                System.out.println(client.toString2());
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
}
