package test;

import javax.imageio.stream.ImageInputStream;
import java.sql.*;

public class DB {

    //public static void main(String[] args) {
    public double euro() {
        double conversation_rate = 0;
        try {
            String url = "jdbc:mysql://localhost:3306/prestashopDB";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM `ps_currency` where  id_currency = 2");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String iso_code = resultSet.getString(3);
                    int numeric = resultSet.getInt(4);
                    int pre = resultSet.getInt(5);
                    conversation_rate = resultSet.getDouble(6);
                    int deleted = resultSet.getInt(7);
                    int active = resultSet.getInt(8);
                }
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
        return conversation_rate;
    }

}


