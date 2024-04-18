/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchEvent.helper;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */

@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

    String code;
    String userName;
    String eventName;
    Date dateCreated;
    ArrayList<Friend> friends = new ArrayList<>();
    
    public Event(){
        
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String EventName) {
        this.eventName = EventName;
    }

    public Date getDate() {
        return dateCreated;
    }

    public void setDate(Date date) {
        this.dateCreated = date;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void addFreinds(ArrayList<Friend> friends) {
        for (Friend f : friends) {
            this.friends.add(new Friend(f.getCode(), f.getUser(), f.getItems()));
        }
    }

    public void setFirends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public Event(String code, String userName, String eventName, Date dateCreated, ArrayList <Friend> friends) {
        this.code = code;
        this.userName = userName;
        this.eventName = eventName;
        this.dateCreated = dateCreated;
        this.friends = friends;
    }

}
