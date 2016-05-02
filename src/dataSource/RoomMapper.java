/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import Entity.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Desting
 */
public class RoomMapper
{
    private final Connection connect;
    private int temp_no;

    public RoomMapper(Connection con)
    {
        this.connect = con;
    }
    
    public ArrayList<Room> getFreeRoomsFromRoomTable(String room_type) {
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
            statement = connect.prepareStatement(SQLString1);
            statement.setString(1, room_type);     // primary key value
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                room = new Room(rs.getInt(1),
                        rs.getString(2));
                //lqlq
                freerooms.add(room);
                //System.out.println(room.toString());

            }
            //System.out.println(freerooms);
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
