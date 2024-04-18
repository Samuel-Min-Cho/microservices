/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Persistence.Friend_ReInvited_CRUD;
import Helper.ReInvited;
/**
 *
 * @author student
 */
public class ReInviteFriend {
      public ReInvited getFriend (String code, String friendName) {
        ReInvited friend = Friend_ReInvited_CRUD.getReInvited(code, friendName);
        return (friend);
    }

    public boolean ReInvite (String code, String friendName) {
        return (Friend_ReInvited_CRUD.reInvite(code, friendName));
    }
}
