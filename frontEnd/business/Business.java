/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontEnd.business;

import frontEnd.helper.Event;
import frontEnd.helper.EventsXML;
import frontEnd.helper.Friend;
import frontEnd.helper.FriendXML;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javassist.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String passwrod) {
        return true;
    }

    public static String invitePost(String code, String friendName) {
        System.out.println("InvitePost");
        Client updateClient = ClientBuilder.newClient();
        String inviteService = System.getenv("inviteService");
        Entity<String> requestBody = Entity.entity("code=" + code + "&friendName=" + friendName, MediaType.APPLICATION_FORM_URLENCODED);
        WebTarget inviteWeb = updateClient.target("http://" + inviteService + "/InvitedFriends/webresources/invite").path("update");
        //WebTarget inviteWeb = updateClient.target("http://localhost:8080/InvitedFriends/webresources/invite").path("update");
        InputStream responseStream = inviteWeb.request(MediaType.TEXT_HTML).post(requestBody, InputStream.class);
        String output = responseStream.toString();
        System.out.println(output);
        return output;

//        WebTarget updateWeb
//                = updateClient.target("http://localhost:8080/InvitedFriends/webresources/invite/isInvited");
//         InputStream is
//                = updateWeb.path("update").request(MediaType.APPLICATION_XML).post(requestBody);
    }

    public static EventsXML getServices(String query, String token, String hiddenParam) throws IOException {
        System.out.println(query);
        Client searchclient = ClientBuilder.newClient();
        String searchService = System.getenv("searchService");
        String inviteService = System.getenv("inviteService");

        WebTarget searchwebTarget = searchclient.target("http://" + searchService + "/SearchEvent/webresources/search");
        //WebTarget searchwebTarget = searchclient.target("http://localhost:8080/SearchEvent/webresources/search");
        InputStream is
                = searchwebTarget.path(query).request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        System.out.println(xml);
        EventsXML events = eventxmltoObjects(xml);
        System.out.println(events.getEvents().size());
        System.out.println(events.getEvents().get(0).getFriends().size());
        System.out.println(events.getEvents().get(0).getFriends().get(0).getUser());

        if (token != null) {
            Client holdclient = ClientBuilder.newClient();
            WebTarget holdwebTarget = holdclient.target("http://" + inviteService + "/InvitedFriends/webresources/invite/isInvited");
            //WebTarget holdwebTarget = holdclient.target("http://localhost:8080/InvitedFriends/webresources/invite/isInvited");
            for (Event event : events.getEvents()) {
                System.out.println(event.getEventName());
                for (Friend friend : event.getFriends()) {
                    String code = event.getCode();
                    String friendName = friend.getUser();
                    System.out.println("CODE : " + code + " Name : " + friendName);
                    String path = code + "/" + friendName;
                    System.out.println(path);

                    FriendXML a = null;
                    try {
                        InputStream holddata = holdwebTarget
                                .path(path)
                                .queryParam("token", token)
                                .request(MediaType.APPLICATION_XML)
                                .get(InputStream.class);
                        a = friendInvitedXmltoObjects(IOUtils.toString(holddata, "utf-8"));
                        if (a != null) {
                            friend.setAcceptance(true);
                            friend.setDate(a.getDate());
                            System.out.println(friend.getAccepatnce());
                        } else {
                            friend.setAcceptance(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        friend.setAcceptance(false);
                    }
                    // Handle 404 error

                }
            }
        }
        return (events);

    }

    private static EventsXML eventxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(EventsXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            EventsXML events = (EventsXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));

            return events;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static FriendXML friendInvitedXmltoObjects(String xml) {
        if (xml.isEmpty()) {
            return null;
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(FriendXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            FriendXML friend = (FriendXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return friend;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
