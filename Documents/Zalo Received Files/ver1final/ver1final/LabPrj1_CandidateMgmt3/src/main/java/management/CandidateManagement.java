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
import java.util.Calendar;
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
        Candidate candidate = candidateView.inputCandidateInformation(listCandidates);
        listCandidates.add(candidate.saveFormat());
        for (Skill skill : candidate.getListSkills()) {
            listCandidateSkills.add(candidate.getId() + "|" + skill.getSkillName() + "|" + skill.getYearOfExp());
        }
        writeFile(SystemConstraint.CANDIDATE, listCandidates);
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkills);
        messageSuccess("Add");
    }

    public int addTest(String firseName, String lastName, int yearOfBirth, String address, String phone, String email, int numberOfSkill, ArrayList<Skill> lsSkill, int type, int gradYear, String gradRank, int yearExp) {

        List<String> listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
        int age = Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth;
        
      listCandidates.add(new Candidate((listCandidates.size() +1), firseName, lastName, yearOfBirth, address, phone, email, type, lsSkill).saveFormat());

//        if (!name.matches(SystemConstraint.NAME_VALID)) {
//            return listCandidates.size();
//        }
//
//        if (yearOfBirth < 1900 || yearOfBirth > Calendar.getInstance().get(Calendar.YEAR)) {
//            return listCandidates.size();
//        }
//
//        if (!phone.matches(SystemConstraint.PHONE_VALID)) {
//            return listCandidates.size();
//        }
//        if (!email.matches(SystemConstraint.EMAIL_VALID)) {
//            return listCandidates.size();
//        }
//
//        if (numberOfSkill != lsSkill.size()) {
//            return listCandidates.size();
//        }
//        int count = 0;
//        for (String s : lsSkill) {
//            List<String> listSkills = new FileHandler().getAllDataInFile("skill.txt");
//            for (String skill : listSkills) {
//                if (s.equals(skill.split("\\|")[1])) {
//                    count++;
//                }
//            }
//
//        }
//
//        if (count == 0) {
//            return listCandidates.size();
//        }
//
//        if (type == 1) {
//            if (gradYear < yearOfBirth || gradYear > Calendar.getInstance().get(Calendar.YEAR)) {
//                return listCandidates.size();
//            }
//        }
//        if (!(gradRank.equals("Excellence") || gradRank.equals("Good") || gradRank.equals("Fair") || gradRank.equals("Poor"))) {
//            return listCandidates.size();
//        }
//
//        if (type == 2) {
//            if (yearExp > age) {
//                return listCandidates.size();
//            }
//        }
//
//        if (type != 0 && type != 1 & type != 2) {
//            return listCandidates.size();
//        }

        return listCandidates.size() + 1;
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

    public int deleteTest(int id, List<String> listCandidates) {
        if (id < 1 || id > listCandidates.size()) {
            return listCandidates.size();
        } else {
            return listCandidates.size() - 1;
        }
    }
    
        public int updateTest(int id, List<String> listCandidates) {
        if (id < 1 || id > listCandidates.size()) {
            return listCandidates.size();
        } else {
            return listCandidates.size() - 1;
        }
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

    public static void main(String[] args) {
        String email = "vuhiep@fpt";
        if (!email.matches(SystemConstraint.EMAIL_VALID)) {
            System.out.println("false");
        } else {
            System.out.println("aa");
        }

    }

}