/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchEvent.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import SearchEvent.helper.Event;
import SearchEvent.helper.Friend;
import SearchEvent.helper.Item;

/**
 *
 * @author student
 */
public class Friend_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connection = System.getenv("DB_URL");
            con = DriverManager.getConnection("jdbc:mysql://" + connection + "/LMS?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");
            // con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");
            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static ArrayList<Friend> searchForFriends(String code) {
//        Item it = Item_CRUD.read("userA");
//        Friends bean = new Friends("userA",eventname,it,false);

        Friend bean = null;
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<Friend> friends = new ArrayList<Friend>();
        try {
            Connection con = getCon();
            String q = "select * from Friends WHERE code LIKE \'" + code + "\'";
            System.out.println(q);

            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String friend = rs.getString("friendName");
                System.out.println(friend);
                // Getting Item List
                items = Item_CRUD.searchForItem(code, friend);
                // Creating New Friends
                bean = new Friend(code, friend, items);
                friends.add(bean);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return friends;
    }
}
