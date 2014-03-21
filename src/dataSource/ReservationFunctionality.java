
package dataSource;

import domain.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationFunctionality {
    private final Connection con;

    public ReservationFunctionality(Connection con) {
        this.con = con;
    }

    public boolean addNewReservation(Reservation reservation) {
        int rowsInserted = 0;
        int reservation_no = 0;
        System.out.println("hello from reservation");
        String SQLString1
                = "select reservation_no_seq.nextval  "
                + "from dual";
        String SQLString2
                = "insert into Reservation_TBL "
                + "values (?,?,?,?,?)";
        PreparedStatement statement = null;

        try {
            //== get unique ono
            statement = con.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                reservation_no = rs.getInt(1);
                reservation.setReservation_no(reservation_no);
            }
            //== insert tuple
            System.out.println("res no:" + reservation_no);
            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, reservation.getReservation_no());
            statement.setString(2, reservation.getClient_arrival());
            statement.setString(3, reservation.getClient_departure());
            statement.setInt(4, reservation.getClient_no());
            statement.setInt(5, reservation.getRoom_no());
            rowsInserted = statement.executeUpdate();
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
    
    public Reservation getReservationInfo(int reservation_no) {
        
        Reservation reservation  = null;
        String SQLString1 = // get order
                "select * "
                + "from Reservation_TBL "
                + "where reservation_no = ?";
        
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1,reservation_no);     // primary key value
            ResultSet rs = statement.executeQuery();
            System.out.println("sorry");
            if (rs.next()) {
                System.out.println("hello");
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
}
