package dataSource;

import domain.*;

public class DataBaseFacade {

    private static UpdatingOrderMapperInterface om; 
    private static DataBaseFacade instance;
    
    public DataBaseFacade() {
        om = new UpdatingOrderMapper(DataBaseConnection.getConnection());
    }

    public DataBaseFacade(UpdatingOrderMapperInterface orm){
      om = orm;
    }

    public boolean saveNewClient(Client client) {
        return om.saveNewClient(client);
    }


}
