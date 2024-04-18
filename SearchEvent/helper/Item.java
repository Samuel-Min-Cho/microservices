/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchEvent.helper;

/**
 *
 * @author student
 */
public class Item {
    String code;
    String name;
    Double price ;
    String friendname;
    
    public String getCode (){
        return code;
    }
    
    public String getFriendName(){
        return friendname; 
    }
        
    public void setName (String name){
        this.name = name;
    }
    public String getName (){
        return name;
    }
    
    public void setPrice (double price){
        this.price = price;
    }
    public double getPrice (){
        return price;
    }
    
    public Item (String code, String name, double price, String friendname){
        this.code = code; 
        this.name = name;
        this.price = price;
        this.friendname = friendname;
    }
}