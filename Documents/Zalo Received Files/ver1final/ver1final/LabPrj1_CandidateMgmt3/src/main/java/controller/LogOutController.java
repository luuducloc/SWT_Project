/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.DataInput;

/**
 *
 * @author Dang Phat
 */
public class LogOutController {

    public boolean logOut() {        
        return !new DataInput().checkInputYN("Do you want to log out? (Y/N): ");
    }
}
