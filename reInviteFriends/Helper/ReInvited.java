/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@XmlRootElement(name = "friendRe")
@XmlAccessorType(XmlAccessType.FIELD)   
public class ReInvited {
 
    String code; 
    String friendName;
    Boolean Acceptance;
    String date;
    
    int num; 


    public ReInvited(String code, String friendName, String date) {
        this.code = code;
        this.date = date;
        this.friendName = friendName;
    }

    public ReInvited() {
        
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getFriendName() {
        return friendName;
    }

}

