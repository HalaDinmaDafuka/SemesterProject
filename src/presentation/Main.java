package presentation;


import domain.Controller;

public class Main {
//BETA MAIN FOR TESTING AND OTHER SCARY THINGS (((:
    public static void main(String[] args) {
        Controller valia = new Controller();
        
//      valia.creatingNewClient("Peter", "Gsadasdssd", "asdasdss");
//      valia.creatingClientPrivateInformation(5324, "Country", 993, "sadsadsd@Haladin.com", "Masdasgency");
//
//      valia.getParticularClient(10000);
//      valia.getParticularClientPrivateInf(10000);
//        
//      valia.addNewReservation("01-MAR-14", "03-MAR-14", 91);
//      valia.getReservationInfo(10000);
//        
//      valia.updateParticularClient(10002, "Mada", "Faka", "Odorovci");
//      valia.updateClientPersonalInformation(10002, 12321, "Bulgaria", 213123, "boyko@abv.bg", "ahahahcahx");
//      valia.updateReservationInformation(10000, "05-MAR-13", "10-APR-13", 10004, 23);
        
//        valia.getFreeRooms("Single Room");
        
        //valia.getAllReservations();
        valia.startTheProcessOfEditingDB();
        valia.creatingNewClient("rt", "yj", "ryj");
        valia.creatingNewClientPrvInf(334567890, "Opida", 889904305, "Spasiba@gmail.com", "NoMoreCocaAgency");
        valia.creatingNewReservation("02-MAR-14", "05-MAR-14", 1);
        valia.creatingSecondaryClients("fgsh", "fhdj", "fdhj");
        valia.creatingSecondaryClients("hhj", "hj", "7f");
        valia.creatingNewClientPrvInf(944567890, "Skrid", 889904305, "Spasiba@gmail.com", "NoMoreCocaAgency");
        valia.endTheProcessOfEditingDB();
            
        }
    }

