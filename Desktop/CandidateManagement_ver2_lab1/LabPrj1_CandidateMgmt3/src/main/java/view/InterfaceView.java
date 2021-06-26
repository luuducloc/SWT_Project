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
public class InterfaceView {
    DataInput dataInput = new DataInput();
    
    private final String ENTER_CHOICE = "Enter your choice: ";
    
    public int menu(){
        System.out.println("1. Login");
        System.out.println("2. Exit");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 2);       
    }

    public int adminMenu() {
        System.out.println("1. View list, add, update, delete candidate");
        System.out.println("2. Add, update, delele skill information");
        System.out.println("3. Add, update, delete candidate skill information");
        System.out.println("4. View list, add, update, delete user; change user's password");
        System.out.println("5. Search & print out the list of candidates who have a specific skill");
        System.out.println("6. Log out, Change password");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 7);       
    }

    public int memberMenu() {
        System.out.println("1. Add, update, delete candidate skill information");
        System.out.println("2. Search & print out the list of candidates who have a specific skill");
        System.out.println("3. Log out, Change password");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 4);        
    }

    public int candidateManagement() {
        System.out.println("1. View list candidate");
        System.out.println("2. Add candidate");
        System.out.println("3. Update candidate");
        System.out.println("4. Delete candidate");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 4);   
    }

    public int skillInformationManagement() {
        System.out.println("1. Add skill information");
        System.out.println("2. Update skill information");
        System.out.println("3. Delete skill information");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 3);        
    }

    public int candidateSkillInformationManagement() {
        System.out.println("1. Add candidate skill information");
        System.out.println("2. Update candidate skill information");
        System.out.println("3. Delete candidate skill information");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 3);        
    }

    public int userManagement() {
        System.out.println("1. View list user");
        System.out.println("2. Add user");
        System.out.println("3. Update user");
        System.out.println("4. Delete user");
        System.out.println("5. Change user's password");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 5);     
    }

    public String searchBySkill(List<String> listSkills) {
        while (true) {
            String skillName = dataInput.checkInputString("Enter skill name: ");
            for (String skill : listSkills) {
                if (skillName.equalsIgnoreCase(skill.split("\\|")[1])) {
                    return skillName;
                }
            }
            return null;
        }
    }

    public int accountManagement() {
        System.out.println("1. Change password");
        System.out.println("2. Log out");
        return dataInput.checkInputIntLimit(ENTER_CHOICE, 1, 2);       
    }

    public User getUserInfo() {
        System.out.println("\n===== Login =====");
        User user = new User();
        user.setUserName(dataInput.checkInputString("Username: "));
        user.setPassword(dataInput.checkInputString("Password: "));
        return user;
    }

    public String changePassword(User user) {
        String password;
        while (true) {
            String oldPassord = dataInput.checkInputString("Enter old password: ");
            if (!oldPassord.equals(user.getPassword())) {
                System.out.println("Old passwords do not match! Please enter again!");
            } else {
                password = dataInput.checkPassword("Enter new password: ");
                String confirmPassword = dataInput.checkInputString("Confirm new password: ");
                if (password.equals(confirmPassword)) {
                    break;
                } else {
                    System.out.println("Passwords do not match! Please enter again!");
                }
            }
        }
        return password;
    }

}
