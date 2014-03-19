package dataSource;

import domain.*;

public class DataBaseFacade {

    private static PushOrderMapperInterface pushOrdMap;
    private static PullOrderMapperInterface pullOrdMap;
    //private static DataBaseFacade instance; Useless for now.
    
    public DataBaseFacade() {
        pushOrdMap = new PushOrderMapper(DataBaseConnection.getConnection());
        pullOrdMap = new PullOrderMapper(DataBaseConnection.getConnection());
    }

    public boolean creatingNewClient(Client client) {
        return pushOrdMap.saveNewClient(client);
    }
    
    public boolean addPrivateInfo(Client client) {
        return pushOrdMap.addPrivateInfo(client);
    }

    public Client getParticularClient(int client_no)
    {
        return pullOrdMap.getClientTBLinfo(client_no);
    }
    
    public Client getParticularClientPrivateInf(int client_no)
    {
        return pullOrdMap.getClientprivinfTBLinfo(client_no);
    }
}
