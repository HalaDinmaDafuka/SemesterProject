package dataSource;

import domain.Client;
import domain.Reservation;
import domain.Room;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public interface OrderMapperInterface {

    public int getNextClientNo();
    
    public int getNextRegistrationNo();
    
    public Client getInformationFromClientTable(int client_no);

    public Client getInformationFromClientPrivateInformationTable(int client_no);

    public ArrayList<Room> getFreeRoomsFromRoomTable(String room_type);

    public Reservation getInformationFromReservationTable(int reservation_no);
    
    public HashMap<Integer, Reservation> getAllInformationFromReservationTable();

    public boolean saveInformationIntoClientTable(ArrayList<Client> clientList);

    public boolean saveInformationIntoClientPrivateInformationTable(ArrayList<Client> clientList);

    public boolean saveInformationIntoReservationTable(ArrayList<Reservation> clientList);

    public void updateInformationIntoClientTable(int client_no, String client_name, String client_surname, String client_address);

    public Client updateInformationIntoClientPrivateInformationTable(int client_no, int client_passport, String client_country, int client_phone, String client_email, String client_agency);

    public Client updateInformationIntoReservationTable(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no);

    public void deleteReservation(int reservation_no);
    
    
}
