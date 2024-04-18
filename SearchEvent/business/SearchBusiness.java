package SearchEvent.business;

import SearchEvent.helper.EventsXML;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import SearchEvent.helper.Event;
import SearchEvent.persistence.Event_CRUD;

/**
 *
 * @author student
 */
public class SearchBusiness {

    public EventsXML getEventsByQuery(String query) {
        System.out.println(query);
        Set<Event> events = Event_CRUD.searchForEvents(query);
        Map<String, Event> allUsersEvents = new HashMap();      
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&" + events.size());
        for (Event event : events) {
            allUsersEvents.put(event.getCode(), event);
        }
        System.out.println("**********************" + allUsersEvents.size());
        EventsXML ev = new EventsXML();
        ev.setEvent(new ArrayList(allUsersEvents.values()));
        System.out.println("SEARCHBUSINESS : " + ev.getEvents().get(0).getFriends().get(0).getCode());
        return (ev);
    }

}
