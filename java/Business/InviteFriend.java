/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author student
 */
import Helper.FriendInvited;
import Persistence.Friend_Invited_CRUD;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



/**
 *
 * @author student
 */
public class InviteFriend {

    public FriendInvited getFriend(String code, String friendName) {
        FriendInvited friend = Friend_Invited_CRUD.getInvitedFriend(code, friendName);
        return (friend);
    }

    public boolean Invite(String code, String friendName) throws ClassNotFoundException, SQLException, ServerAddressNotSuppliedException, IOException, InterruptedException {
        boolean success = false;
        success = Friend_Invited_CRUD.addInvited(code, friendName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();

        Messaging.sendmessage("INVITE:" + code + ":" + friendName + ":" + date.format(formatter));
        return success;

    }

}
