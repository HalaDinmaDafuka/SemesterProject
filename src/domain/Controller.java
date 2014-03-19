
package domain;

import dataSource.DataBaseFacade;

public class Controller {
    private Client currectClient;
    DataBaseFacade dbf = new DataBaseFacade();
    
//    public Controller(){
//        currectClient = null;
//    }
    
    public Client creatingNewClient(String client_name, String client_surname, String client_address)
    {
        currectClient = new Client(0, client_name, client_surname, client_address);
        
        boolean sent = dbf.saveNewClient(currectClient);
        if(!sent)
        {
            currectClient = null;
        }
        return currectClient;
    }
}
