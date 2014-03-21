package dataSource;

import domain.Client;
import domain.Reservation;

public interface PushOrderMapperInterface {

    public boolean saveNewClient(Client client);
    
    public boolean addPrivateInfo(Client client);
}
