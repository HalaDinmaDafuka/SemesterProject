
import domain.Controller;


public class Main {

    public static void main(String[] args) {
        Controller valia = new Controller();
        
        valia.creatingNewClient("Boyko", "Gonko", "Shailqllq");
        valia.addPrivateInfo(5324, "Babyland", 99999, "zomg@Haladin.com", "MylovelyAgency");
        
        valia.getParticularClient(0);
        valia.getParticularClientPrivateInf(0);
    }
}
