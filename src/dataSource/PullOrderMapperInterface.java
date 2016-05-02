
package dataSource;
import domain.Client;
import domain.Reservation;
import domain.Room;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public interface PullOrderMapperInterface {
    
    public Client getClientTBLinfo(int client_no);
    
    public Client getClientprivinfTBLinfo(int client_no);
    
    public ArrayList<Room> getRoomsfromType(String room_type);
}
