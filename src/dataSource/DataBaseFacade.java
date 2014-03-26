package dataSource;

import domain.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBaseFacade {

    private static OrderMapper order;
    private UnitOfWork unitOfWork;
    private Connection con;

    private static DataBaseFacade dbf;
    //private static DataBaseFacade instance; Useless for now.

    public DataBaseFacade() {
        order = new OrderMapper(DataBaseConnection.getConnection());

    }

    public static DataBaseFacade getFacade() {

        if (dbf == null) {
            dbf = new DataBaseFacade();
        }
        return dbf;
    }

    //Numbers
    public int getNextClientNo() {
        return order.getNextClientNo();
    }
    
    public int getReservationNo() {
        return order.getNextRegistrationNo();
    }

    //Creating
    public void registerNewClientifUnitOfWorkIsInitialized(Client client) {
        if (unitOfWork != null) {
            unitOfWork.addingNewClientToArrayListOfClientsForClientTBL(client);

        }
    }

    public void registerNewClientPrvInfIfUnitOfWorkIsInitialized(Client client) {
        if (unitOfWork != null) {
            unitOfWork.addingNewClientPrvInfToArrayListOfClientPrvInfForCLientPrvTBL(client);
        }
    }

    public void registerNewReservationIfUnitOfWorkIsInitialized(Reservation reservation) {
        if (unitOfWork != null) {
            //order.saveInformationIntoReservationTable(reservation);
            unitOfWork.addingNewRegisterToArrayListOfRegistersForRegisterTBL(reservation);
        }
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

    //Delete
    public void deleteReservation(int reservation_no) {
        order.deleteReservation(reservation_no);
    }

    //Save all changes Unitofwork
    public void initializeUnitOfWorkWithOrderMapper() {
        unitOfWork = new UnitOfWork(order);
    }

    public boolean startTheCommitMetods() {
        boolean status = false;
        if (unitOfWork != null) {
            status = unitOfWork.actualCommitStatements(con);
            unitOfWork = null;
        }
        return status;
    }
}
