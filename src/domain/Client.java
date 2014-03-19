package domain;

public class Client {

    private int client_no;
    private String client_name;
    private String client_surname;
    private String client_address;

    public Client(int client_no, String client_name, String client_surname, String client_address) {
        this.client_no = client_no;
        this.client_name = client_name;
        this.client_surname = client_surname;
        this.client_address = client_address;
    }

    public int getClient_no() {
        return client_no;
    }

    public void setClient_no(int client_no) {
        this.client_no = client_no;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    @Override
    public String toString() {
        return "Client{" + "client_no=" + client_no + ", client_name=" + client_name + ", client_surname=" + client_surname + ", client_address=" + client_address + '}';
    }
}
