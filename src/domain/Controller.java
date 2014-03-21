
package domain;

import dataSource.DataBaseFacade;

public class Controller {
    private Client currectClient;
    private Reservation currentReservation;
    DataBaseFacade dbf = new DataBaseFacade();
    
    public Controller(){
        currectClient = null;
    }
    
    public Client creatingNewClient(String client_name, String client_surname, String client_address)
    {
        currectClient = new Client(0, client_name, client_surname, client_address);
        
        boolean sent = dbf.creatingNewClient(currectClient);
        if(!sent)
        {
            currectClient = null;
        }
        return currectClient;
    }
    
    public Client creatingClientPrivateInformation(int client_passport, String client_country, int client_phone, String client_email, String client_agency)
    {
        currectClient = new Client(0, client_passport, client_country, client_phone, client_email, client_agency);
        
        boolean sent = dbf.creatingClientPrivateInformation(currectClient);
        if(!sent)
        {
            currectClient = null;
        }
        return currectClient;
    }
    
    public Reservation addNewReservation(String client_arrival, String client_departure,int client_no, int room_no)
    {
        currentReservation = new Reservation(0, client_arrival, client_departure, client_no, room_no);
        boolean sent = dbf.creatingNewReservation(currentReservation);
        if(!sent)
        {
            currentReservation = null;
        }
        return currentReservation;
    }
    
    public Client getParticularClient(int client_no)
    {
        currectClient = dbf.getParticularClient(client_no);
        
        return currectClient;
    }
    
    public Client getParticularClientPrivateInf(int client_no)
    {
        currectClient = dbf.getParticularClientPrivateInf(client_no);
        
        return currectClient;
    }
    
    public Reservation getReservationInfo(int reservation_no)
    {
        currentReservation =dbf.getReservationInfo(reservation_no);
        return currentReservation;
    }
    
        public void updateParticularClient(int client_no, String client_name, String client_surname, String client_address)
    {
        
        currectClient = dbf.getParticularClient(client_no);
        dbf.updateParticularClient(client_no, client_name, client_surname, client_address);
    }
        
        public void updateClientPersonalInformation(int client_no, int client_passport, String client_country, int client_phone, String client_email, String client_agency)
        {
            currectClient = dbf.getParticularClientPrivateInf(client_no);
            dbf.updateClientPersonalInformation(client_no, client_passport, client_country, client_phone, client_email, client_agency);
        }
        
        public void updateReservationInformation(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no)
        {
            currentReservation = dbf.getReservationInfo(reservation_no);
            dbf.updateReservationInformation(reservation_no, client_arrival, client_departure, client_no, room_no);
        }
}
