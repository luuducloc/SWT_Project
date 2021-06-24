/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.DataInput;
import java.util.List;

/**
 *
 * @author Dang Phat
 */
public class CandidateSkillView {
    DataInput dataInput = new DataInput();

    public int getCandidateId(List<String> listCandidates) {
        return dataInput.checkInputIntLimit("Enter a candidate ID: ", 1, listCandidates.size());
    }

    public String getSkillName(List<String> listSkills) {
        while (true) {
            String skillName = dataInput.checkInputString("Enter skill name: ");
            for (String skill : listSkills) {
                if (skillName.equalsIgnoreCase(skill.split("\\|")[1])) {
                    return skillName;
                }
            }
            System.out.println("Skill does not exist! Please enter again!");
        }
    }

    public int getExperience(int birthday) {
        return dataInput.checkInputExprience("Enter year of experience: ", birthday);
    }

    public int getBirthDate(List<String> listCandidates, int candidateID) { //*****
        int birthDate = 0;
        for (String l : listCandidates) {
            if (Integer.parseInt(l.split("\\|")[0]) == candidateID) {
                birthDate = Integer.parseInt(l.split("\\|")[3]);
            }
        }
        return birthDate;
    }

    public boolean checkExistSkillOnId(List<String> listCandidateSkill, int candidateID, String skillName) {//****
        for (String l : listCandidateSkill) {
            if (Integer.parseInt(l.split("\\|")[0]) == candidateID) {
                if (l.split("\\|")[1].equalsIgnoreCase(skillName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void candidateSkillInfoMessage(int type, String function) {
        String message = "";
        switch (type) {
            case 1:
                message = "Candidate does not have this skill! Please enter again!";
                break;
            case 2:
                message = "Duplidate skill with that CandidateID";
                break;
            case 3:
                message = function + " Successfully!";
                break;
            case 4:
                message = function + " Fail";
                break;
            default:
                message = "";
                break;
        }
        System.out.println(message);
    }

    public void mainDisplay(int role, String userName) {
        String type = "MEMBER";
        if (role == 1) {
            type = "ADMIN";
        }
        System.out.println("\nWelcome: " + userName);
        System.out.println("********** CANDIDATE MANAGEMENT - " + type + " **********");
    }
}
