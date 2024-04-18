/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchEvent.endpoint;

import SearchEvent.business.SearchBusiness;
import SearchEvent.helper.EventsXML;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author student
 */
@Path("search/{query}")
public class SearchResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public SearchResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {
        System.out.println(query);
        SearchBusiness search = new SearchBusiness();
        EventsXML events = search.getEventsByQuery(query);
        System.out.println(events.getEvents().get(0).getFriends().get(0).getCode());
        System.out.println(">>>>>>>>>>>>>>>>>>" + events);

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(EventsXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(events, sw);
            
            System.out.println(sw.toString());

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(SearchResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }

    /**
     * PUT method for updating or creating an instance of SearchResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
