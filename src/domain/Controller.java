
package domain;

import dataSource.DataBaseFacade;

public class Controller {
    private Client currectClient;
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
    
    public Client addPrivateInfo(int client_passport, String client_country, int client_phone, String client_email, String client_agency)
    {
        currectClient = new Client(0, client_passport, client_country, client_phone, client_email, client_agency);
        
        boolean sent = dbf.addPrivateInfo(currectClient);
        if(!sent)
        {
            currectClient = null;
        }
        return currectClient;
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
}
