package dataSource;

import domain.Client;
import domain.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

    @Override
    public ArrayList<Room> getRoomsfromType(String room_type) {
        Room room = null;
        ArrayList<Room> freerooms = new ArrayList<>();
        String SQLString1 = // get order
                "SELECT room.ROOM_NO, room.ROOM_TYPE FROM ROOM_TBL room "
                + "LEFT JOIN RESERVATION_TBL reserve "
                + "ON reserve.ROOM_NO = room.ROOM_NO "
                + "WHERE reserve.ROOM_NO IS NULL "
                + "AND room.ROOM_NO IS NOT NULL "
                + "AND room.ROOM_TYPE = ?";
        PreparedStatement statement = null;
        try {
            //=== get order
            statement = con.prepareStatement(SQLString1);
            statement.setString(1, room_type);     // primary key value
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {

                
                    room = new Room(rs.getInt(1),
                            rs.getString(2));
                    //lqlq
                    freerooms.add(room);
                    //System.out.println(room.toString());
                
            }
            System.out.println(freerooms);
        } catch (SQLException e) {
            System.out.println("Fail in OrderMapper - getRoomsfromType");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - getRoomsfromType");
                System.out.println(e.getMessage());
            }
        }
        return freerooms;
    }

}
