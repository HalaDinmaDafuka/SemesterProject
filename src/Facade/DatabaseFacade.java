/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Client;
import Entity.Reservation;
import Entity.Room;
import dataSource.ClientMapper;
import dataSource.DatabaseConnection;
import dataSource.ReservationMapper;
import dataSource.RoomMapper;
import dataSource.UnitofWork;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Desting
 */
public class DatabaseFacade
{
    private static ClientMapper cMapper;
    private static ReservationMapper reservationMapper;
    private static RoomMapper roomMapper;
    private UnitofWork unitOfWork;
    private Connection con;

    private static DatabaseFacade dbf;

    public DatabaseFacade()
    {
        cMapper = new ClientMapper(DatabaseConnection.getConnection());
        reservationMapper = new ReservationMapper(DatabaseConnection.getConnection());
        roomMapper = new RoomMapper(DatabaseConnection.getConnection());
    }
    
    public static DatabaseFacade getFacade() {

        if (dbf == null) {
            dbf = new DatabaseFacade();
        }
        return dbf;
    }
    
    //Numbers
    public int getNextClientNo() {
        return cMapper.getNextClientNo();
    }
    
    public int getReservationNo() {
        System.out.println("before");
        return reservationMapper.getNextRegistrationNo(); 
    }
    public int getCurrentClientNo(){
        return cMapper.getCurrentClientNo();
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
            unitOfWork.addingNewReservationToArrayListOfReservationForReservationTBL(reservation);
        }
    }
    
    
    //Retrieving
    public Client getParticularClient(int client_no) {
        return cMapper.getInformationFromClientTable(client_no);
    }
    
    public Client getParticularClientPrivateInf(int client_no) {
        return cMapper.getInformationFromClientPrivateInformationTable(client_no);
    }
    
    public Reservation getReservationInfo(int reservation_no) {
        return reservationMapper.getInformationFromReservationTable(reservation_no);
    }
    
    public HashMap<Integer, Reservation> getAllReservations() {
        return reservationMapper.getAllInformationFromReservationTable();
    }
    
    public ArrayList<Room> getFreeRooms(String room_type) {
        return roomMapper.getFreeRoomsFromRoomTable(room_type);
    }
    
    //Update
    public void updateParticularClient(Client client) {
        unitOfWork.updatingClientToArrayListOfClientsForClientTBL(client);
    }

    public void updateClientPersonalInformation(Client client) {
        unitOfWork.updatingClientPrvInfToArrayListOfClientsForClientPrvInfTBL(client);
    }

    public void updateReservationInformation(Reservation reserve) {
        unitOfWork.updatingReservationToArrayListOfReservationsForReservationTBL(reserve);
    }
    
    //Delete
    public void deleteReservation(int reservation_no) {
        unitOfWork.deletingReservationbyaddingResNoNoToArrayListOfInt(reservation_no);
    }
    
    
    
    //Pass all changes to Unitofwork
    public void initializeUnitOfWorkWithOrderMapper() {
        unitOfWork = new UnitofWork(cMapper,reservationMapper);
    }

    public boolean startTheCreationCommit() {
        boolean status = false;
        if (unitOfWork != null) {
            status = unitOfWork.creationCommit(con);
            unitOfWork = null;
        }
        return status;
    }
    
    public boolean startTheDeletionCommit() {
        boolean status = false;
        if (unitOfWork != null) {
            status = unitOfWork.deletionCommit(con);
            unitOfWork = null;
        }
        return status;
    }
    
    public boolean startTheUpdatingCommit() {
        boolean status = false;
        if (unitOfWork != null) {
            status = unitOfWork.updatingCommit(con);
            unitOfWork = null;
        }
        return status;
    }
    
    
    
    
    
    
    
}
