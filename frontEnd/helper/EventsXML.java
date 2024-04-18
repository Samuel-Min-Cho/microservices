/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontEnd.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "events")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventsXML {

    @XmlElement(name = "event")
    private ArrayList<Event> events;

    public List<Event> getEvents() {
        return events;

    }

    public EventsXML() {

    }

    public void setBook(ArrayList<Event> ev) {
        events = ev;

    }

}
