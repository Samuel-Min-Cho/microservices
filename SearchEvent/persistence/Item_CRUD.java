/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchEvent.persistence;

import SearchEvent.helper.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import SearchEvent.helper.Event;
import SearchEvent.helper.Friend;

/**
 *
 * @author student
 */
public class Item_CRUD {

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

    public static ArrayList<Item> searchForItem(String code, String friendname) {
        //     Item bean = new Item("Cookie",23.3,friendname);
        ArrayList<Item> items = new ArrayList<Item>();
        Item bean = null;
        try {
            Connection con = getCon();
            String q = "select * from Item WHERE friendName LIKE \'" + friendname + "\' AND code LIKE \'" + code + "\'";
            System.out.println(q);
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("itemName");
                double price = rs.getDouble("itemPrice");
                bean = new Item(code, name, price, friendname);
                items.add(bean);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return items;
    }
}
