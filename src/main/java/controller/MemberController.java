/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import management.CandidateSkillInfoManagement;
import management.SearchCandidateManagement;

import model.User;
import view.CandidateSkillView;
import view.InterfaceView;

/**
 *
 * @author Dang Phat
 */
public class MemberController {

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
            new CandidateSkillView().mainDisplay(0, user.getUserName());
            int choice = view.memberMenu();
            switch (choice) {
                case 1:
                    new CandidateSkillInfoManagement().candidateSkill();
                    break;
                case 2:
                    new SearchCandidateManagement().searchBySkillController();
                    break;
                case 3:
                    isContinue = chooseFunction(user);
                    break;
                default:
                    break;
            }
        }
    }
}
