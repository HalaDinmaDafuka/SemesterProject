
package dataSource;
import domain.Client;
import domain.Reservation;

public interface PullOrderMapperInterface {
    
    public Client getClientTBLinfo(int client_no);
    
    public Client getClientprivinfTBLinfo(int client_no);
}
