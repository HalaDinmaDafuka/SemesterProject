package dataSource;

import domain.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PushOrderMapper implements PushOrderMapperInterface {
    //DoN'T KNOW WHAT RESULT SET MEANS NOR ROWSINSERTED
    //== Insert new order (tuple)

    private final Connection con;
    private int temp_no;

    public PushOrderMapper(Connection con) {
        this.con = con;
    }

    @Override
    public boolean saveNewClient(Client client) {
        int rowsInserted = 0;
        String SQLString1
                = "select client_no_seq.nextval  "
                + "from dual";
        String SQLString2
                = "insert into Client_TBL "
                + "values (?,?,?,?)";
        PreparedStatement statement = null;

        try {
            //== get unique ono
            statement = con.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                temp_no = rs.getInt(1);
                client.setClient_no(temp_no);
            }
            //== insert tuple
            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, client.getClient_no());
            statement.setString(2, client.getClient_name());
            statement.setString(3, client.getClient_surname());
            statement.setString(4, client.getClient_address());
            rowsInserted = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - saveNewClient");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - saveNewClient");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }

    @Override
    public boolean addPrivateInfo(Client client) {
        int rowsInserted = 0;
        String SQLString1
                = "insert into CLIENT_PRIVATEINF_TBL "
                + "values (?,?,?,?,?,?)";
        PreparedStatement statement = null;

        try {
            //== insert tuple
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, temp_no);
            statement.setInt(2, client.getClient_passport());
            statement.setString(3, client.getClient_country());
            statement.setInt(4, client.getClient_phone());
            statement.setString(5, client.getClient_email());
            statement.setString(6, client.getClient_agency());
            rowsInserted = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - addPrivateInfo");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in OrderMapper - addPrivateInfo");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }


}
