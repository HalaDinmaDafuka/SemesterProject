/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import Entity.Client;
import Entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Desting
 */
public class ReservationMapper
{
    private final Connection connect;
    private int temp_no;

    public ReservationMapper(Connection con)
    {
        this.connect = con;
    }
    
    
    //Number
    public int getNextRegistrationNo() {
        System.out.println("after");
        int nextOrderNumber = 0;
        String SQLString
                = "select reservation_no_seq.nextval  "
                + "from dual";
        PreparedStatement statement = null;
        try {
            statement = connect.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                nextOrderNumber = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextOrderNo");
            System.out.println(e.getMessage());
        }
        return nextOrderNumber;
    }
    
    //Save
    public boolean saveInformationIntoReservationTable(ArrayList<Reservation> reservationList) {
        int rowsInserted = 0;

        String sqlStringInsert
                = "insert into Reservation_TBL "
                + "values (?,?,?,?,?)";
        PreparedStatement statement = null;

        try {
            for (int i = 0; i < reservationList.size(); i++) {
                Reservation reservation = reservationList.get(i);
                statement = connect.prepareStatement(sqlStringInsert);
                statement.setInt(1, reservation.getReservation_no());
                statement.setString(2, reservation.getClient_arrival());
                statement.setString(3, reservation.getClient_departure());
                statement.setInt(4, reservation.getClient_no());
                statement.setInt(5, reservation.getRoom_no());
                rowsInserted = statement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - addNewReservation324");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - addNewReservation");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }
    
    //Load 
    //Single reservation
    public Reservation getInformationFromReservationTable(int reservation_no) {

        Reservation reservation = null;
        String SQLString1 = // get order
                "select * "
                + "from Reservation_TBL "
                + "where reservation_no = ?";

        PreparedStatement statement = null;
        try {
            statement = connect.prepareStatement(SQLString1);
            statement.setInt(1, reservation_no);     // primary key value
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                reservation = new Reservation(reservation_no,
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                //lqlq
                System.out.println(reservation.toString());
            }
        } catch (SQLException e) {
            System.out.println("Fail in OrderMapper - getReservationInfo");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - getReservationInfo");
                System.out.println(e.getMessage());
            }
        }
        return reservation;
    }
    
    //All Reservations
    public HashMap<Integer, Reservation> getAllInformationFromReservationTable() {
        Reservation reservation = null;
        HashMap<Integer, Reservation> reservationsMap = new HashMap<Integer, Reservation>();
        String SQLString1 = // get order
                "SELECT * "
                + "FROM Reservation_TBL ";
        PreparedStatement statement = null;
        try {
            //=== get order
            statement = connect.prepareStatement(SQLString1);
            //statement.setInt(1, reservation.getReservation_no());     // primary key value
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                reservation = new Reservation(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                //lqlq
                //reservationsArray.clear();
                reservationsMap.put(rs.getInt(1), reservation);

            }
            System.out.println(reservationsMap.toString());
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
        return reservationsMap;
    }
    
    //Update
    public boolean updateInformationIntoReservationTable(ArrayList<Reservation> reserveUpdateList) {
        boolean updated = true;
        int rowsUpdated = 0;
        String SQLString
                = "update Reservation_TBL "
                + "set client_arrival=?, client_departure=?, client_no=?, room_no=? "
                + "where reservation_no=?";
        PreparedStatement statement = null;
        try {
            for (int i = 0; i < reserveUpdateList.size(); i++) {
            Reservation reserve = reserveUpdateList.get(i);
            
            statement = connect.prepareStatement(SQLString);
            statement.setString(1, reserve.getClient_arrival());
            statement.setString(2, reserve.getClient_departure());
            statement.setInt(3, reserve.getClient_no());
            statement.setInt(4, reserve.getRoom_no());
            statement.setInt(5, reserve.getReservation_no());
            rowsUpdated = statement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Fail in UpdateOrderMapper - updateReservationInformation");
            System.out.println(e.getMessage());
            updated = false;
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - updateReservationInformation");
                System.out.println(e.getMessage());
                updated = false;
            }
        }
        return updated;
    }
    
    
    
    //Delete
    public boolean deleteReservation(ArrayList<Integer> reservationArray) {
        int rowsDeleted = 0;
        for (int i = 0; i < reservationArray.size(); i++) {

            System.out.println("ressssssssss>>>>>>>>>>>>>>" + reservationArray.get(i));
            Reservation reservation = getInformationFromReservationTable(reservationArray.get(i));
            String SQLString1 = "delete from Reservation_TBL "
                    + "where reservation_no= ?";
            PreparedStatement statement = null;

            try {
                //== get unique ono
                statement = connect.prepareStatement(SQLString1);
                statement.setInt(1, reservation.getReservation_no());

                //== insert tuple
                rowsDeleted = statement.executeUpdate();

            } catch (Exception e) {
                System.out.println("Fail in DeleteReservationOrderMapper - deleteReservation");
                System.out.println(e.getMessage());
            } finally // must close statement
            {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Fail in DeleteReservationOrderMapper, closing the connection - deleteReservation");
                    System.out.println(e.getMessage());
                }
            }
        }
        return rowsDeleted == reservationArray.size();
    }
        
        //return rowsDeleted == 1;
    
}

