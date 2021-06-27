/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import controller.AdminController;
import controller.LoginController;
import controller.MemberController;
import model.User;

/**
 *
 * @author Dang Phat
 */
public class MainManagement {

    public void management() {
        LoginController loginController = new LoginController();
        User user = loginController.login();
        int getTypeLogin = user.getType();
        switch (getTypeLogin) {
            case 1:
                AdminController adminController = new AdminController(); //only initialize in each case
                adminController.manage(user);
                break;
            case 2:
                MemberController memberController = new MemberController(); //only initialize in each case
                memberController.manage(user);
                break;
            default:
                break;
        }
    }
}
