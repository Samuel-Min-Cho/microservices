/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

/**
 *
 * @author student
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Friend_Invited_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connection = System.getenv("DB_URL");
             con = DriverManager.getConnection("jdbc:mysql://" + connection +"/reinvite_friends?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reinvite_friends?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");

            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static boolean isInvited(String code, String friendName) {
        boolean result;
        try {
            Connection con = getCon();
            String q = "select * from inviteFriend "
                    + " WHERE code LIKE '" + code + "'" + "friendName LIKE '" + friendName + "';";
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
            con.close();
        } catch (Exception e) {
            return false;
        }
        return result;
    }

    public static boolean addInvited(String code, String friendName, String date) {
        try {
            Connection con = getCon();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);
            String q = "insert into inviteFriend "
                    + "(code, friendName, invitedDate) values "
                    + "('" + code + "', "
                    + "'" + friendName + "', "
                    + "'" + date + "');";
            Statement stmt = con.createStatement();
            System.out.println(q);
            stmt.execute(q);
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
