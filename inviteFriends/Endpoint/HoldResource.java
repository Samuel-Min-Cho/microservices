/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Endpoint;


import java.io.StringWriter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import Business.InviteFriend;
import Helper.FriendInvited;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author student
 */
@Path("invite")
public class HoldResource {

    @Context
    private UriInfo context;
    

    /**
     * Creates a new instance of SearchResource
     */
    public HoldResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("isInvited/{code}/{friendName}")
    public String getXml(@PathParam("code") String code, @PathParam("friendName") String friendName) {
        //@PathParam("friendName") String friendName
        System.out.println(code);
        InviteFriend invite = new InviteFriend();
        FriendInvited friend = invite.getFriend(code, friendName);
        if (friend == null) {
            return (null);
        }
        JAXBContext jaxbContext;
        try {
            System.out.println("MARSHALLIZING");
            jaxbContext = JAXBContext.newInstance(FriendInvited.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(friend, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(HoldResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("Errrrorororororor");
        }
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("update")
    public String update(@FormParam("code") String code, @FormParam("friendName") String friendName) throws ClassNotFoundException, SQLException, ServerAddressNotSuppliedException, IOException, InterruptedException {
        System.out.println("POST : code : " + code + ", friendName : " + friendName);
        InviteFriend invite = new InviteFriend();
        boolean bs = invite.Invite(code, friendName);
        if (bs) {
            return ("Inserted");
        } else {
            return ("Not inserted");
        }

    }
}
