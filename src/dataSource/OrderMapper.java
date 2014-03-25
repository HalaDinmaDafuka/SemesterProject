package dataSource;

import domain.Client;
import domain.Reservation;
import domain.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderMapper implements OrderMapperInterface {

    private final Connection connect;
    private int temp_no;

    public OrderMapper(Connection con) {
        this.connect = con;
    }
    //Getting Information (PULL)
    @Override
    public Client getInformationFromClientTable(int client_no) {
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
                //lqlq
                //System.out.println(client.toString2());
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

    @Override
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
    
    @Override 
    public HashMap<Integer, Reservation> getAllInformationFromReservationTable()
    {
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

    //Adding Information (PUSH)
    @Override
    public boolean saveInformationIntoClientTable(Client client) {
        int rowsInserted = 0;
        String SQLString1
                = "select client_no_seq.nextval  "
                + "from dual";
        String SQLString2
                = "insert into Client_TBL "
                + "values (?,?,?,?)";
        PreparedStatement statement = null;

        try {
            //== get unique ono
            statement = connect.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                temp_no = rs.getInt(1);
                client.setClient_no(temp_no);
            }
            //== insert tuple
            statement = connect.prepareStatement(SQLString2);
            statement.setInt(1, client.getClient_no());
            statement.setString(2, client.getClient_name());
            statement.setString(3, client.getClient_surname());
            statement.setString(4, client.getClient_address());
            rowsInserted = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - saveNewClient");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - saveNewClient");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }

    @Override
    public boolean saveInformationIntoClientPrivateInformationTable(Client client) {
        int rowsInserted = 0;
        String SQLString1
                = "insert into CLIENT_PRIVATEINF_TBL "
                + "values (?,?,?,?,?,?)";
        PreparedStatement statement = null;

        try {
            //== insert tuple
            statement = connect.prepareStatement(SQLString1);
            statement.setInt(1, temp_no);
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
        }
        return rowsInserted == 1;
    }

    @Override
    public boolean saveInformationIntoReservationTable(Reservation reservation) {
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
            statement = connect.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                reservation_no = rs.getInt(1);
                reservation.setReservation_no(reservation_no);
            }
            //== insert tuple
            System.out.println("res no:" + reservation_no);
            statement = connect.prepareStatement(SQLString2);
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

    //Updating Information (UPDATE)
    @Override
    public void updateInformationIntoClientTable(int client_no, String client_name, String client_surname, String client_address) {
        Client updateClient;
        String SQLString1
                = "update Client_TBL "
                + "set client_name = ?, client_surname = ?, client_address = ? "
                + "where client_no = ?";
        PreparedStatement statement = null;
        try {
            statement = connect.prepareStatement(SQLString1);

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

    @Override
    public Client updateInformationIntoClientPrivateInformationTable(int client_no, int client_passport, String client_country, int client_phone, String client_email, String client_agency) {
        Client updateClientPrivateInfo = null; //this is not correct 
        int rowsUpdated = 0;
        String SQLString
                = "update Client_PrivateInf_TBL "
                + "set client_passport=?, client_country=?, client_phone=?, client_email=?, client_agency=? "
                + "where client_no=?";
        PreparedStatement statement = null;
        try {
            //== insert tuple
            statement = connect.prepareStatement(SQLString);

            // statement.setInt    (1, client.getClient_no());
            statement.setInt(1, client_passport);
            statement.setString(2, client_country);
            statement.setInt(3, client_phone);
            statement.setString(4, client_email);
            statement.setString(5, client_agency);
            statement.setInt(6, client_no);
            rowsUpdated = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in UpdateOrderMapper - updateClientPersonalInformation");
            System.out.println(e.getMessage());
        } finally // must close statement
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

    @Override
    public Client updateInformationIntoReservationTable(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no) {
        Client updateClientPrivateInfo = null; //this is not correct 
        int rowsUpdated = 0;
        String SQLString
                = "update Reservation_TBL "
                + "set client_arrival=?, client_departure=?, client_no=?, room_no=? "
                + "where reservation_no=?";
        PreparedStatement statement = null;
        try {
            //== insert tuple
            statement = connect.prepareStatement(SQLString);

            // statement.setInt    (1, client.getClient_no());
            statement.setString(1, client_arrival);
            statement.setString(2, client_departure);
            statement.setInt(3, client_no);
            statement.setInt(4, room_no);
            statement.setInt(5, reservation_no);
            rowsUpdated = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in UpdateOrderMapper - updateReservationInformation");
            System.out.println(e.getMessage());
        } finally // must close statement
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
    
     @Override
    public void deleteReservation(int reservation_no) {
        Reservation reservation = getInformationFromReservationTable(reservation_no);

        int rowsDeleted = 0;
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
        //return rowsDeleted == 1;
    }
}
