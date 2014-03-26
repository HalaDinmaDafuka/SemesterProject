
package dataSource;

import domain.Reservation;
import domain.Client;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnitOfWork {
    private final OrderMapper orderMapper;
    private final ArrayList<Client> newClientAL;
    private final ArrayList<Client> newClientPrvInfAL;
    private final ArrayList<Reservation> newReservationAL;
    private final ArrayList<Client> updateNewClientALakaDirtyClient;
    

    public UnitOfWork(OrderMapper orderMapper)
    {
        this.orderMapper = orderMapper;
        newClientAL = new ArrayList<>(); // will never exceed 1 element
        newClientPrvInfAL = new ArrayList<>();
        newReservationAL = new ArrayList<>();
        
        updateNewClientALakaDirtyClient = new ArrayList<>(); // will never exceed 1 element
    }
  //Adding to DATABAZEE<33

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
    
        public void addingNewRegisterToArrayListOfRegistersForRegisterTBL(Reservation reservation)
    {
        if (!newReservationAL.contains(reservation))  
        {
            newReservationAL.add(reservation);
        }
    }

//    public void registerDirtyClient(Client client)
//    {
//        if (!newOrders.contains(client) && // if not all ready registered in any list
//                !dirtyOrders.contains(client))
//        {
//            dirtyOrders.add(client);
//        }
//    }
//


  //====== Method to save changes to DB ===============================
    public boolean actualCommitStatements(Connection connection)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            
            //=== system transaction - starts
            //connection.setAutoCommit(false);
            status = status && orderMapper.saveInformationIntoClientTable(newClientAL);
            status = status && orderMapper.saveInformationIntoClientPrivateInformationTable(newClientPrvInfAL);
            status = status && orderMapper.saveInformationIntoReservationTable(newReservationAL);
            
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
