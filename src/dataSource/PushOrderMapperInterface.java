package dataSource;

import domain.Client;

public interface PushOrderMapperInterface {

    public boolean saveNewClient(Client client);
    
    public boolean addPrivateInfo(Client client);
}
