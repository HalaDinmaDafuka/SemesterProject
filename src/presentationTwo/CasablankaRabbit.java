/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationTwo;

import control.Controller;
import entity.Client;

/**
 *
 * @author Desting
 */
public class CasablankaRabbit
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
       Controller nik = new Controller();
       nik.startTheProcessOfEditingDB();
       nik.creatingNewClient("Nik", "Des", "Skodvej");
       nik.creatingNewClientPrvInf(666, "DK", 777, "Lol", "DetSeje");
       nik.creatingNewReservation("01-MAR-14", "02-MAR-14", 1);
       nik.creatingSecondaryClients("Teo", "Dum", "Madafaka");
       nik.creatingNewClientPrvInf(234, "DK", 234, "JegerSej", "Duforlækker");
        nik.endTheProcessOfCreatingDB();
        
//        System.out.println(nik.getParticularClient(10000));
//        nik.updateParticularClient(10001, "Boyko", "Eats", "Dick");
//        nik.updateClientPersonalInformation(10001, 453, "Vala Hates us", 9876, "But Weeeeee Don't", " give a Shiiiiiit");
//       nik.updateReservationInformation(10000, "01-FEB-14", "02-FEB-14", 10000, 1);
//        System.out.println(nik.getAllReservations());
//        System.out.println(nik.getFreeRooms("Single Room"));
//       nik.deleteReservation(10000);
//       nik.endTheProcessOfDeletingDB();
//       nik.endTheProcessOfUpdatingDB();
//        System.out.println(nik.getParticularClientPrivateInf(10000));
       
      
       
        
    }
  
}
