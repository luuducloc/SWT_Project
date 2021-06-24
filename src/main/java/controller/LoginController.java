/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.FileHandler;
import model.User;
import view.InterfaceView;

/**
 *
 * @author Dang Phat
 */
public class LoginController {

    private InterfaceView view = new InterfaceView();

    public User login() {
        while (true) {
            User user = view.getUserInfo();
            try {
                if (new FileHandler().checkLogin("account.dat", user)) {
                    return user;
                } else {
                    System.err.println("Username or password is incorrect! Please enter again!");
                }
            } catch (Exception ex) {
                System.err.println("Error!");
                System.exit(0);
            }
        }
    }

    
}
