
package domain;

public class Reservation {
    private int reservation_no;
    private String client_arrival;
    private String client_departure;
    private int client_no;
    private int room_no;
    public Reservation(int reservation_no, String client_arrival, String client_departure, int client_no, int room_no) {
        this.reservation_no = reservation_no;
        this.client_arrival = client_arrival;
        this.client_departure = client_departure;
        this.client_no = client_no;
        this.room_no = room_no;
    }
    public int getReservation_no() {
        return reservation_no;
    }
    public void setReservation_no(int reservation_no) {
        this.reservation_no = reservation_no;
    }
    public String getClient_arrival() {
        return client_arrival;
    }
    public void setClient_arrival(String client_arrival) {
        this.client_arrival = client_arrival;
    }
    public String getClient_departure() {
        return client_departure;
    }
    public void setClient_departure(String client_departure) {
        this.client_departure = client_departure;
    }
    public int getClient_no() {
        return client_no;
    }
    public void setClient_no(int client_no) {
        this.client_no = client_no;
    }
    public int getRoom_no() {
        return room_no;
    }
    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }
    @Override
    public String toString() {
        return reservation_no + ", " + client_arrival + ", " + client_departure + ", " + client_no + ", " + room_no;
    }
    
    
}
