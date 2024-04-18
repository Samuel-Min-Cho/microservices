/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchEvent.persistence;

import SearchEvent.helper.Event;
import SearchEvent.helper.Friend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author student
 */
public class Event_CRUD {

    private static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String connection = System.getenv("DB_URL");
             con = DriverManager.getConnection("jdbc:mysql://" + connection +"/LMS?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "student");

            System.out.println("Connection established.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static Set<Event> searchForEvents(String eventName) {
//        Date date = new Date();
//        Event bean = new Event(userName, "GameNight", date);
//        Friends nf = Friends_CRUD.read("GameNight");
//        Event.friends.add(nf);

        Set<Event> events = new HashSet<Event>();
        ArrayList<Friend> friends = new ArrayList<Friend>();
        try {
            Connection con = getCon();
            String q = "select * from Event WHERE eventName LIKE \'" + eventName + "\'";
            System.out.println(q);
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String c = rs.getString("code");
                String userName = rs.getString("userName");
                Date d = rs.getDate("dateCreated");

                // Getting Friends ArrayList
                friends = Friend_CRUD.searchForFriends(c);

                // Creating New Event
                Event bean = new Event(c, userName, eventName, d, friends);
                events.add(bean);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return events;
    }
}
