package dataSource;

import domain.*;
import java.util.ArrayList;

public class DataBaseFacade {

    private static PushOrderMapperInterface pushOrdMap;
    private static PullOrderMapperInterface pullOrdMap;
    private static ReservationFunctionality resf;
    private static UpdateOrderMapper updordmap;
    //private static DataBaseFacade instance; Useless for now.

    public DataBaseFacade() {
        pushOrdMap = new PushOrderMapper(DataBaseConnection.getConnection());
        pullOrdMap = new PullOrderMapper(DataBaseConnection.getConnection());
        resf = new ReservationFunctionality(DataBaseConnection.getConnection());
        updordmap = new UpdateOrderMapper(DataBaseConnection.getConnection());
    }

    //Creating
    public boolean creatingNewClient(Client client) {
        return pushOrdMap.saveNewClient(client);
    }

    public boolean creatingClientPrivateInformation(Client client) {
        return pushOrdMap.addPrivateInfo(client);
    }

    public boolean creatingNewReservation(Reservation reservation) {
        System.out.println("hello from Facad");
        System.out.println(reservation.getClient_arrival() + " " + reservation.getClient_departure() + " "
                + reservation.getClient_no() + " " + reservation.getRoom_no() + " for" + reservation.getReservation_no());
        return resf.addNewReservation(reservation);
    }

    //Getting
    public Client getParticularClient(int client_no) {
        return pullOrdMap.getClientTBLinfo(client_no);
    }

    public Client getParticularClientPrivateInf(int client_no) {
        return pullOrdMap.getClientprivinfTBLinfo(client_no);
    }

    public Reservation getReservationInfo(int reservation_no) {
        return resf.getReservationInfo(reservation_no);

    }

    //Free Rooms
    public ArrayList<Room> getFreeRooms(String room_type) {
        return pullOrdMap.getRoomsfromType(room_type);
    }

    //Update
    public void updateParticularClient(int client_no, String client_name, String client_surname, String client_address) {
        updordmap.updateClientTBLinfo(client_no, client_name, client_surname, client_address);
    }

    public void updateClientPersonalInformation(int client_no, int client_passport, String client_country, int client_phone, String client_email, String client_agency) {
        updordmap.updateClientPersonalInformation(client_no, client_passport, client_country, client_phone, client_email, client_agency);
    }

    public void updateReservationInformation(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no) {
        updordmap.updateReservationInformation(reservation_no, client_arrival, client_departure, client_no, room_no);
    }

}
