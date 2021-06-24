/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author locluu
 */
public class Message {
    public void AccountManagementMessage(int type, String function) {
        String message = "";
        switch (type) {
            case 1:
                message = function + "account successfully!";
                break;
            case 2: 
                message = "List accounts:";
                break;
            case 3:
                message = "Usercode\tUsername\tType";
                break;
            default:
                
                break;
        }
        System.out.println(message);
    }
    
    public void listAccountDisplay(int type, String userCode, String userName, String Type) {
        String message = "";
        switch (type) {
            case 1: 
                message = userCode + "\t\t" + userName + "\t\t" + type;
                break;
                
        }
        System.out.println(message);
    }
}
