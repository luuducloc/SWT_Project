/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import common.DataInput;
import common.FileHandler;
import constraint.SystemConstraint;
import java.util.ArrayList;
import java.util.List;
import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import model.Skill;
import view.CandidateView;
import view.InterfaceView;

/**
 *
 * @author Dang Phat
 */
public class CandidateManagement {

    private InterfaceView view = new InterfaceView();
    private CandidateView candidateView = new CandidateView();
    private DataInput dataInput = new DataInput();
    FileHandler fileHandler = new FileHandler();

    ArrayList<String> listCandidateSkillsUpdated = null;

    List<String> listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
    List<String> listCandidateSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL_CANDIDATE);
    Candidate candidate = candidateView.inputCandidateInformation(listCandidates);

    private void writeFile(String URL, List<String> list) {
        fileHandler.writeFile(URL, list);
    }

    //new function avoid duplicate
    private void messageSuccess(String action) {
        System.out.println(action + " successfully!");
    }

    private void list() {
        List<Experience> listExperiences = fileHandler.getExperience();
        List<Fresher> listFreshers = fileHandler.getFresher();
        List<Intern> listInterns = fileHandler.getIntern();
        candidateView.listExperiences(listExperiences);
        candidateView.listFreshers(listFreshers);
        candidateView.listInterns(listInterns);
    }

    private void add() {
        listCandidates.add(candidate.saveFormat());
        for (Skill skill : candidate.getListSkills()) {
            listCandidateSkills.add(candidate.getId() + "|" + skill.getSkillName() + "|" + skill.getYearOfExp());
        }
        writeFile(SystemConstraint.CANDIDATE, listCandidates);
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkills);
        messageSuccess("Add");
    }

    private void upadte() {
        listCandidateSkillsUpdated = new ArrayList<>();
        int candidateID = dataInput.checkInputIntLimit("Enter candidate ID to update: ", 1, listCandidates.size());
        for (String candidate : listCandidates) {
            if (Integer.parseInt(candidate.split("\\|")[0]) == candidateID) {
                Candidate candidateInfo = candidateView.inputCandidateInformation(listCandidates);
                String candidateUpdated = candidateID + candidateInfo.saveFormat().substring(candidate.indexOf("|"));
                listCandidates.set(candidateID - 1, candidateUpdated);
                for (String candidateSkill : listCandidateSkills) {
                    if (Integer.parseInt(candidateSkill.split("\\|")[0]) != candidateID) {
                        listCandidateSkillsUpdated.add(candidateSkill);
                    }
                }
                for (Skill skill : candidateInfo.getListSkills()) {
                    listCandidateSkillsUpdated.add(candidateID + "|" + skill.getSkillName() + "|" + skill.getYearOfExp());
                }
                break;
            }
        }
        writeFile(SystemConstraint.CANDIDATE, listCandidates);
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkillsUpdated);
        messageSuccess("Update");
    }

    private void delete() {
        listCandidateSkillsUpdated = new ArrayList<>();
        int candidateID = dataInput.checkInputIntLimit("Enter candidate ID to delete: ", 1, listCandidates.size());
        for (String candidate : listCandidates) {
            if (Integer.parseInt(candidate.split("\\|")[0]) == candidateID) {
                listCandidates.remove(candidateID - 1);
                break;
            }
        }
        for (int i = candidateID - 1; i < listCandidates.size(); i++) {
            String candidateUpdated = (i + 1) + listCandidates.get(i).substring(listCandidates.get(i).indexOf("|"));
            listCandidates.set(i, candidateUpdated);
        }
        for (String candidateSkill : listCandidateSkills) {
            int nextCandidateID = Integer.parseInt(candidateSkill.split("\\|")[0]);
            if (nextCandidateID != candidateID) {
                if (nextCandidateID > candidateID) {
                    listCandidateSkillsUpdated.add((nextCandidateID - 1) + "|" + candidateSkill.split("\\|")[1] + "|" + candidateSkill.split("\\|")[2]);
                } else {
                    listCandidateSkillsUpdated.add(candidateSkill);
                }
            }
        }
        writeFile(SystemConstraint.CANDIDATE, listCandidates);
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkillsUpdated);
        messageSuccess("Delete");
    }

    public void candidateManagementController() {
        int candidateManagementChoice = view.candidateManagement(); //change variable name
        switch (candidateManagementChoice) {
            case 1:
                list();
                break;
            case 2:
                add();
                break;
            case 3:
                upadte();
                break;
            case 4:
                delete();
                break;
            default:
                break;
        }
    }
}
