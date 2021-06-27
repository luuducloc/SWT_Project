/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import common.FileHandler;
import constraint.SystemConstraint;
import java.util.List;
import view.InterfaceView;
import view.CandidateSkillView;

/**
 *
 * @author Dang Phat
 */
public class CandidateSkillInfoManagement {

    private InterfaceView view = new InterfaceView();
    FileHandler fileHandler = new FileHandler();

    List<String> listCandidateSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL_CANDIDATE);
    List<String> listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
    List<String> listSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL);
    CandidateSkillView candidateSkillView = new CandidateSkillView();

    private void writeFile(String URL, List<String> list) {
        fileHandler.writeFile(URL, list);
    }

    private void add() {
        int candidateID = candidateSkillView.getCandidateId(listCandidates); //***
        boolean isContinue = true;
        while (isContinue) {
            boolean errorCheck = false;
            String skillName = candidateSkillView.getSkillName(listSkills);
            for (int i = 0; i < listSkills.size(); i++) {
                if (skillName.equalsIgnoreCase(listSkills.get(i).split("\\|")[1])) {
                    if (!candidateSkillView.checkExistSkillOnId(listCandidateSkills, candidateID, skillName)) {//**
                        int birthDate = candidateSkillView.getBirthDate(listCandidates, candidateID); //****
                        int yearOfExp = candidateSkillView.getExperience(birthDate);
                        listCandidateSkills.add(candidateID + "|" + skillName + "|" + yearOfExp);
                        isContinue = false;
                    } else {
                        candidateSkillView.candidateSkillInfoMessage(2, "Add");
                        errorCheck = true;
                    }
                }
            }
            if (isContinue && !errorCheck) {
                candidateSkillView.candidateSkillInfoMessage(1, "Add");
            }
        }
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkills);
        candidateSkillView.candidateSkillInfoMessage(3, "Add");
    }

    private void update() {
        int candidateID = candidateSkillView.getCandidateId(listCandidates);
        boolean isContinue = true;
        while (isContinue) {
            String skillName = candidateSkillView.getSkillName(listSkills);
            for (int i = 0; i < listCandidateSkills.size(); i++) {
                if (candidateID == Integer.parseInt(listCandidateSkills.get(i).split("\\|")[0])
                        && skillName.equalsIgnoreCase(listCandidateSkills.get(i).split("\\|")[1])) {
                    int birthDate = candidateSkillView.getBirthDate(listCandidates, candidateID);
                    int yearOfExp = candidateSkillView.getExperience(birthDate);
                    listCandidateSkills.set(i, candidateID + "|" + skillName + "|" + yearOfExp);
                    isContinue = false;
                }
            }
            if (isContinue) {
                candidateSkillView.candidateSkillInfoMessage(1, "Update");
            }
        }
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkills);
        candidateSkillView.candidateSkillInfoMessage(3, "Update");
    }

    private void delete() {
        int candidateID = candidateSkillView.getCandidateId(listCandidates);
        boolean isContinue = true;
        while (isContinue) {
            String skillName = candidateSkillView.getSkillName(listSkills);
            for (int i = 0; i < listCandidateSkills.size(); i++) {
                if (candidateID == Integer.parseInt(listCandidateSkills.get(i).split("\\|")[0])
                        && skillName.equalsIgnoreCase(listCandidateSkills.get(i).split("\\|")[1])) {
                    listCandidateSkills.remove(listCandidateSkills.get(i));
                    isContinue = false;
                    break;
                }
            }
            if (isContinue) {
                candidateSkillView.candidateSkillInfoMessage(1, "Delete");
            }
        }
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkills);
        candidateSkillView.candidateSkillInfoMessage(3, "Delete");
    }

    public void candidateSkill() {
        int candidateSkillInfoChoice = view.candidateSkillInformationManagement();
        switch (candidateSkillInfoChoice) {
            case 1:
                add();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
                break;
            default:
                break;
        }

    }
}
