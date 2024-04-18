/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Helper.FriendInvited;

/**
 *
 * @author student
 */
public class Friend_Invited_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connection = System.getenv("DB_URL");
            con = DriverManager.getConnection("jdbc:mysql://" + connection + "/invite_friends?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reinvite_friends?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");

            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static boolean addInvited(String code, String friendName) {
        try {
            Connection con = getCon();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();
            String q = "insert into inviteFriend "
                    + "(code, friendName, invitedDate) values "
                    + "('" + code + "', "
                    + "'" + friendName + "', "
                    + "'" + date.format(formatter) + "');";
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

    public static FriendInvited getInvitedFriend(String code, String friendName) {
        FriendInvited friend = null;
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

                friend = new FriendInvited(code, friendName, date);

            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        return friend;

    }

    public static boolean addInvited(String code, String friendName, String holddate) {
        return (addInvited(code, friendName));
    }
}
