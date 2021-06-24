/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import management.AccountManagement;
import management.CandidateManagement;
import management.CandidateSkillInfoManagement;
import management.SearchCandidateManagement;
import management.SkillInfoManagement;
import model.User;
import view.CandidateSkillView;
import view.InterfaceView;

/**
 *
 * @author Dang Phat
 */
public class AdminController {

    private InterfaceView view = new InterfaceView();

    private boolean chooseFunction(User user) {
        int accountManagementChoice = view.accountManagement();
        switch (accountManagementChoice) {
            case 1:
                new ChangePasswordController().changePassword(user);
                break;
            case 2:
                return new LogOutController().logOut();
            default:
                break;
        }
        return true;
    }

    public void manage(User user) {
        boolean isContinue = true;
        while (isContinue) {
            new CandidateSkillView().mainDisplay(1, user.getUserName());
            int choice = view.adminMenu();
            switch (choice) {
                case 1:
                    new CandidateManagement().candidateManagementController();
                    break;
                case 2:
                    new SkillInfoManagement().skillInformationManagementController();
                    break;
                case 3:
                    new CandidateSkillInfoManagement().candidateSkill();
                    break;
                case 4:
                    new AccountManagement().userManagementController();
                    break;
                case 5:
                    new SearchCandidateManagement().searchBySkillController();

                    break;
                case 6:
                    isContinue = chooseFunction(user);
                    break;
                default:
                    break;
            }
        }
    }

}
