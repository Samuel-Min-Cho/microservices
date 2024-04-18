/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontEnd.helper;

import java.util.ArrayList;

/**
 *
 * @author student
 */
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
public class Friend {

    String code;
    String friendName;
    ArrayList<Item> items = new ArrayList<>();

    boolean acceptance;
    String date;
    int numInv;

    public Friend() {

    }

    public int getNumInv() {
        return numInv;
    }

    public void setNumInv (int numInv) {
        this.numInv = numInv;
    }

    public String getCode() {
        return code;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setUser(String friendName) {
        this.friendName = friendName;
    }

    public String getUser() {
        return friendName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItems(ArrayList<Item> items) {
        for (Item i : items) {
            this.items.add(new Item(i.getCode(), i.getName(), i.getPrice(), i.getFriendName()));
        }
    }

    public void setFirends(ArrayList<Item> items) {
        this.items = items;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public String getAccepatnceString() {
        String a;
        if (acceptance == true) {
            a = "Invited";

        } else {
            a = "Not invited";
        }
        return a;
    }

    public boolean getAccepatnce() {
        return acceptance;
    }

    public Friend(String code, String friendName, ArrayList<Item> items) {
        this.code = code;
        this.friendName = friendName;
        this.items = items;

    }
}
