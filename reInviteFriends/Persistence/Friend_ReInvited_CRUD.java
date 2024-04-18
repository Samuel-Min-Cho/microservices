/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Helper.ReInvited;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author student
 */
public class Friend_ReInvited_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connection = System.getenv("DB_URL");
            con = DriverManager.getConnection("jdbc:mysql://" + connection + "/reinvite_friends?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");
           // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reinvite_friends?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");

            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static ReInvited getReInvited(String code, String friendName) {
        ReInvited friend = null;
        try {
            Connection con = getCon();

            String q = "select * from inviteFriend"
                    + " WHERE "
                    + "code = '" + code + "' AND friendName = '" + friendName + "';";
            System.out.println(q);

            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String date = rs.getDate("invitedDate").toString();
                friend = new ReInvited(code, friendName, date);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return friend;

    }

    public static boolean reInvite(String code, String friendName) {

        try {
            Connection con = getCon();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            LocalDate date = LocalDate.now();
            System.out.println(date.format(formatter));
            String q = "insert into reinviteFriend"
                    + "(code, friendName, num) values "
                    + "('" + code + "', '" + friendName + "',1);";
            Statement stmt = con.createStatement();
            stmt.execute(q);

            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
