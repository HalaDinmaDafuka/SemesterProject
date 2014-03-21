
import domain.Controller;

public class Main {

    public static void main(String[] args) {
        Controller valia = new Controller();

//      valia.creatingNewClient("Peter", "Gsadasdssd", "asdasdss");
//      valia.creatingClientPrivateInformation(5324, "Country", 993, "sadsadsd@Haladin.com", "Masdasgency");
//
//      valia.getParticularClient(10000);
//      valia.getParticularClientPrivateInf(10000);
//        
//      valia.addNewReservation("01-MAR-14", "03-MAR-14",10000, 043);
//      valia.getReservationInfo(10000);
        
        //valia.updateParticularClient(10002, "Mada", "Faka", "Odorovci");
        //valia.updateClientPersonalInformation(10002, 12321, "Bulgaria", 213123, "boyko@abv.bg", "ahahahcahx");
        valia.updateReservationInformation(10000, "05-MAR-13", "10-APR-13", 10004, 23);
    }
}
