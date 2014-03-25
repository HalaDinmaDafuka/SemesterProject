package dataSource;

import domain.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseFacade {

    private static OrderMapperInterface order;
    //private static DataBaseFacade instance; Useless for now.

    public DataBaseFacade() {
        order = new OrderMapper(DataBaseConnection.getConnection());
    }

    //Creating
    public boolean creatingNewClient(Client client) {
        return order.saveInformationIntoClientTable(client);
    }

    public boolean creatingClientPrivateInformation(Client client) {
        return order.saveInformationIntoClientPrivateInformationTable(client);
    }

    public boolean creatingNewReservation(Reservation reservation) {
        return order.saveInformationIntoReservationTable(reservation);
    }

    //Getting
    public Client getParticularClient(int client_no) {
        return order.getInformationFromClientTable(client_no);
    }

    public Client getParticularClientPrivateInf(int client_no) {
        return order.getInformationFromClientPrivateInformationTable(client_no);
    }

    public Reservation getReservationInfo(int reservation_no) {
        return order.getInformationFromReservationTable(reservation_no);
    }

    public HashMap<Integer, Reservation> getAllReservations() {
        return order.getAllInformationFromReservationTable();
    }

    //Free Rooms
    public ArrayList<Room> getFreeRooms(String room_type) {
        return order.getFreeRoomsFromRoomTable(room_type);
    }

    //Update
    public void updateParticularClient(int client_no, String client_name, String client_surname, String client_address) {
        order.updateInformationIntoClientTable(client_no, client_name, client_surname, client_address);
    }

    public void updateClientPersonalInformation(int client_no, int client_passport, String client_country, int client_phone, String client_email, String client_agency) {
        order.updateInformationIntoClientPrivateInformationTable(client_no, client_passport, client_country, client_phone, client_email, client_agency);
    }

    public void updateReservationInformation(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no) {
        order.updateInformationIntoReservationTable(reservation_no, client_arrival, client_departure, client_no, room_no);
    }

     public void deleteReservation(int reservation_no){
      order.deleteReservation(reservation_no);
    }
}
