
package dataSource;
import domain.Client;

public interface PullOrderMapperInterface {
    
    public Client getClientTBLinfo(int client_no);
    
    public Client getClientprivinfTBLinfo(int client_no);
}
