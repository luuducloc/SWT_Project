/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.DataInput;
import java.util.List;
import model.User;

/**
 *
 * @author Dang Phat
 */
public class AccountView {

    DataInput dataInput = new DataInput();

    public User createUserInfo(List<String> listAccounts) {
        User user = new User();
        user.setUserName(dataInput.checkUserName("Enter username: ", listAccounts));
        String password;
        while (true) {
            password = dataInput.checkPassword("Enter password: ");
            String confirmPassword = dataInput.checkInputString("Confirm new password: ");
            if (password.equals(confirmPassword)) {
                break;
            } else {
                System.out.println("Passwords do not match! Please enter again!");
            }
        }
        user.setPassword(password);
        int type = dataInput.checkInputIntLimit("Enter type account (1 for Admin, 2 for Memeber): ", 1, 2);
        user.setType(type);
        user.setUserCode(listAccounts.size() + 1);
        return user;
    }

    public User createUser(List<String> listAccounts, String userName, String pass, String confirmPassword, int type) {
        User user = new User();
        String name = dataInput.checkUserName1(listAccounts, userName);
        String passWord = dataInput.checkPassword1(pass);
        String confirmPassword1 = dataInput.checkPassword1(confirmPassword);
        int typeUser = dataInput.checkInputIntLimit1(type, 1, 2);

        if (name != null && passWord != null && confirmPassword1 != null && typeUser != -1) {
            System.out.println(dataInput.checkUserName1(listAccounts, userName));
            user.setUserName(dataInput.checkUserName1(listAccounts, userName));
            System.out.println(passWord);
            System.out.println("confirm pass: " + confirmPassword1);
            if (passWord.equals(confirmPassword1)) {
                System.out.println(pass);
                user.setPassword(pass);
            } else {
                return null;
            }

            System.out.println(typeUser);
            user.setType(type);
            user.setUserCode(listAccounts.size() + 1);
            return user;
        }

        return null;
    }

    public User updateUser(List<String> listAccounts) {
        User user = new User();
        user.setUserCode(dataInput.checkInputIntLimit("Enter user code: ", 1, listAccounts.size()));
        int type = dataInput.checkInputIntLimit("Set new type account (1 for Admin, 2 for Memeber): ", 1, 2);
        user.setType(type);
        return user;
    }

    public User changePassword(List<String> listAccounts) {
        User user = new User();
        user.setUserCode(dataInput.checkInputIntLimit("Enter user code: ", 1, listAccounts.size()));
        String password;
        while (true) {
            password = dataInput.checkPassword("Enter password: ");
            String confirmPassword = dataInput.checkInputString("Confirm new password: ");
            if (password.equals(confirmPassword)) {
                break;
            } else {
                System.out.println("Passwords do not match! Please enter again!");
            }
        }
        user.setPassword(password);
        return user;
    }

}
