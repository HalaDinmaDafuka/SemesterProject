package domain;

import dataSource.DataBaseFacade;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private Client currectClient;
    private Reservation currentReservation;
    private ArrayList freeRooms;
    private HashMap<Integer, Reservation> allReservations;
    DataBaseFacade dbf = new DataBaseFacade();
    private int registerno = 0;

    public Controller() {
        currectClient = null;
        currentReservation = null;
    }

    public void startTheProcessOfEditingDB() {
        dbf.initializeUnitOfWorkWithOrderMapper();
    }

    public Client creatingNewClient(String client_name, String client_surname, String client_address) {

        int newClientNo = dbf.getNextClientNo();
        if (newClientNo != 0) {
            currectClient = new Client(newClientNo, client_name, client_surname, client_address);
            dbf.registerNewClientifUnitOfWorkIsInitialized(currectClient);
            registerno = newClientNo;
        } else {
            currectClient = null;
        }
        return currectClient;
    }

    public Client creatingSecondaryClients(String client_name, String client_surname, String client_address) {
        int newClientNo = dbf.getNextClientNo();
        if (registerno != 0) {
            currectClient = new Client(newClientNo, client_name, client_surname, client_address, registerno);
            System.out.println("cyrrent"+ currectClient.toString3());
            dbf.registerNewClientifUnitOfWorkIsInitialized(currectClient);
        } else {
            currectClient = null;
        }
        return currectClient;
    }

    public Client creatingNewClientPrvInf(int client_passport, String client_country, int client_phone, String client_email, String client_agency) {
        if (currectClient.getClient_no() != 0) {
            currectClient = new Client(currectClient.getClient_no(), client_passport, client_country, client_phone, client_email, client_agency);
            dbf.registerNewClientPrvInfIfUnitOfWorkIsInitialized(currectClient);
        } else {
            currectClient = null;
        }
        return currectClient;
    }

    public Reservation creatingNewReservation(String client_arrival, String client_departure, int room_no) {
        int newReservationNo = dbf.getReservationNo();
        if (newReservationNo != 0) {
            currentReservation = new Reservation(newReservationNo, client_arrival, client_departure, currectClient.getClient_no(), room_no);
            dbf.registerNewReservationIfUnitOfWorkIsInitialized(currentReservation);
        } else {
            currentReservation = null;
        }
        return currentReservation;
    }

    public Client getParticularClient(int client_no) {
        currectClient = dbf.getParticularClient(client_no);

        return currectClient;
    }

    public Client getParticularClientPrivateInf(int client_no) {
        currectClient = dbf.getParticularClientPrivateInf(client_no);

        return currectClient;
    }

    public Reservation getReservationInfo(int reservation_no) {
        currentReservation = dbf.getReservationInfo(reservation_no);
        return currentReservation;
    }

    public ArrayList<Room> getFreeRooms(String room_type) {
        freeRooms = dbf.getFreeRooms(room_type);
        //freeRooms.toString(); 
        return freeRooms;
    }

    public HashMap<Integer, Reservation> getAllReservations() //THAT ONE IS WORKING, BUT I Don't know how to implement it!
    {
        allReservations = dbf.getAllReservations();
        //allReservations.toString();
        return allReservations;

    }

    public void updateParticularClient(int client_no, String client_name, String client_surname, String client_address) {

        currectClient = dbf.getParticularClient(client_no);
        dbf.updateParticularClient(client_no, client_name, client_surname, client_address);
    }

    public void updateClientPersonalInformation(int client_no, int client_passport, String client_country, int client_phone, String client_email, String client_agency) {
        currectClient = dbf.getParticularClientPrivateInf(client_no);
        dbf.updateClientPersonalInformation(client_no, client_passport, client_country, client_phone, client_email, client_agency);
    }

    public void updateReservationInformation(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no) {
        currentReservation = dbf.getReservationInfo(reservation_no);
        dbf.updateReservationInformation(reservation_no, client_arrival, client_departure, client_no, room_no);
    }

    //Delete
    public void deleteReservation(int reservation_no) {
        dbf.deleteReservation(reservation_no);
    }

    //Saving order
    public void endTheProcessOfEditingDB() {
//        boolean status = false;
//        if (processingOrder)
//        {
        //== ends ongoing business transaction

        dbf.startTheCommitMetods();
//            processingOrder = false;
//            currentOrder = null;
//        }
//        return status;
    }
}
