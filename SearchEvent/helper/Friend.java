/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchEvent.helper;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Friend {

    String code;
    String friendName;
    ArrayList<Item> items = new ArrayList<>();
    Boolean acceptance;

    public String getCode() {
        return code;
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

    public void setAcceptance(Boolean acceptance) {
        this.acceptance = acceptance;
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
