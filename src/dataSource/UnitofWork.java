/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import Entity.Client;
import Entity.Reservation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Desting
 */
public class UnitofWork
{
   private ClientMapper clientMapper;
   private ReservationMapper reservationMapper;
    private final ArrayList<Client> newClientAL;
    private final ArrayList<Client> newClientPrvInfAL;
    private final ArrayList<Reservation> newReservationAL;
    private final ArrayList<Client> updateCLientAL;
    private final ArrayList<Client> updateClientPrvInfAL;
    private final ArrayList<Reservation> updateReservationAL;
    
    private final ArrayList<Integer> deletingReservations;
    

    public UnitofWork(ClientMapper clientMapper, ReservationMapper reservationMapper)
    {
        this.clientMapper = clientMapper;
        this.reservationMapper = reservationMapper;
        newClientAL = new ArrayList<>();
        newClientPrvInfAL = new ArrayList<>();
        newReservationAL = new ArrayList<>();
        
        updateClientPrvInfAL = new ArrayList<>();
        updateCLientAL = new ArrayList<>(); // will never exceed 1 element
        updateReservationAL = new ArrayList<>();
        
        deletingReservations = new ArrayList<>();
        
    }
    
    //Add to ArrayLists
    
    public void addingNewClientToArrayListOfClientsForClientTBL(Client client)
    {
        if (!newClientAL.contains(client)) 
                
        {
            newClientAL.add(client);
        }
    }
    
    public void addingNewClientPrvInfToArrayListOfClientPrvInfForCLientPrvTBL(Client client)
    {
        if (!newClientPrvInfAL.contains(client))
        {
            newClientPrvInfAL.add(client);
        }
    }
    
    public void addingNewReservationToArrayListOfReservationForReservationTBL(Reservation reservation)
    {
        if (!newReservationAL.contains(reservation))  
        {
            newReservationAL.add(reservation);
        }
    }
    
    
    
    //Updating
    public void updatingClientToArrayListOfClientsForClientTBL(Client client) {
        if (!updateCLientAL.contains(client)) {
            updateCLientAL.add(client);
            System.out.println("registered." + updateCLientAL.toString());
        }
    }
    public void updatingClientPrvInfToArrayListOfClientsForClientPrvInfTBL(Client client) {
        if (!updateClientPrvInfAL.contains(client)) {
            updateClientPrvInfAL.add(client);
        }
    }
    
    public void updatingReservationToArrayListOfReservationsForReservationTBL(Reservation reservation) {
        if (!updateReservationAL.contains(reservation)) {
            updateReservationAL.add(reservation);
        }
    }
    
    //Deleting
    public void deletingReservationbyaddingResNoNoToArrayListOfInt(int reservation_no) {
        if (!deletingReservations.contains(reservation_no)) {
            deletingReservations.add(reservation_no);
            //System.out.println(deletingReservations.toString() + "<<<<<<<<<<<<");
        }
    }
    
    
    //====== Methods to save changes to DB ===============================
    public boolean creationCommit(Connection connection)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            
            //=== system transaction - starts
            //connection.setAutoCommit(false);
            if(!newClientAL.isEmpty())
            {
            status = status && clientMapper.saveInformationIntoClientTable(newClientAL);
            }
              if (!newClientPrvInfAL.isEmpty())
              {
            status = status && clientMapper.saveInformationIntoClientPrivateInformationTable(newClientPrvInfAL);
              }
                  
              if(!newReservationAL.isEmpty())
              {
            status = status && reservationMapper.saveInformationIntoReservationTable(newReservationAL);
              }
            
            if (!status)
            {
                throw new Exception("Process Order Business Transaction aborted");
            }
            //=== system transaction - ends with success
            //connection.commit();
        } catch (Exception e)
        {
            //=== system transaction - ends with roll back
            try
            {
                connection.rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            status = false;
        }
        
        return status;
    }
    
    //Updating
    public boolean updatingCommit(Connection connection)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            
            //=== system transaction - starts
            //connection.setAutoCommit(false);
            if(!updateCLientAL.isEmpty())
            {
            status = status && clientMapper.updateInformationIntoClientTable(updateCLientAL);
            }
                if (!updateClientPrvInfAL.isEmpty())
                {
            status = status && clientMapper.updateInformationIntoClientPrivateInformationTable(updateClientPrvInfAL);
                }
                    if(!updateReservationAL.isEmpty())
                    {
                        status = status && reservationMapper.updateInformationIntoReservationTable(updateReservationAL);
                    }
            
            if (!status)
            {
                throw new Exception("Process Order Business Transaction aborted");
            }
            //=== system transaction - ends with success
            //connection.commit();
        } catch (Exception e)
        {
            //=== system transaction - ends with roll back
            try
            {
                connection.rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            status = false;
        }
        return status;
    }
    
    //Deletion
    public boolean deletionCommit(Connection connection)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            
            //=== system transaction - starts
            //connection.setAutoCommit(false);
            status = status && reservationMapper.deleteReservation(deletingReservations);
            
            if (!status)
            {
                throw new Exception("Process Order Business Transaction aborted");
            }
            //=== system transaction - ends with success
            //connection.commit();
        } catch (Exception e)
        {
            //=== system transaction - ends with roll back
            try
            {
                connection.rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            status = false;
        }
        return status;
    }
    
    
    
    
    
}
