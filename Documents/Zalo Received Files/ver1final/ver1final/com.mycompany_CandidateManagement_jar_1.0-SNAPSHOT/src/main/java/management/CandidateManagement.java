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
    static FileHandler fileHandler = new FileHandler();

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

//    private void add() {
//        Candidate candidate = candidateView.inputCandidateInformation(listCandidates);
//        listCandidates.add(candidate.saveFormat());
//        for (Skill skill : candidate.getListSkills()) {
//            listCandidateSkills.add(candidate.getId() + "|" + skill.getSkillName() + "|" + skill.getYearOfExp());
//        }
//        writeFile(SystemConstraint.CANDIDATE, listCandidates);
//        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkills);
//        messageSuccess("Add");
//    }
    public int checkAdd(String firseName, String lastName, int yearOfBirth, String address, String phone, String email, int numberOfSkill, ArrayList<String> lsSkill, int type, int gradYear, String gradRank, int yearExp) {

        List<String> listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
        int age = Calendar.getInstance().get(Calendar.YEAR) - yearOfBirth;
//        
//      listCandidates.add(new Candidate((listCandidates.size() +1), firseName, lastName, yearOfBirth, address, phone, email, type, lsSkill).saveFormat());

        if (!firseName.matches(SystemConstraint.NAME_VALID)) {
            return listCandidates.size();
        }

        if (!lastName.matches(SystemConstraint.NAME_VALID)) {
            return listCandidates.size();
        }

//          if (!lastName.matches(SystemConstraint.NAME_VALID)) {
//            return listCandidates.size();
//        }
        if (yearOfBirth < 1900 || yearOfBirth > Calendar.getInstance().get(Calendar.YEAR)) {
            return listCandidates.size();
        }

        if (!phone.matches(SystemConstraint.PHONE_VALID)) {
            return listCandidates.size();
        }
        if (!email.matches(SystemConstraint.EMAIL_VALID)) {
            return listCandidates.size();
        }

        if (numberOfSkill != lsSkill.size()) {
            return listCandidates.size();
        }
        int count = 0;
        for (String s : lsSkill) {
            List<String> listSkills = new FileHandler().getAllDataInFile("skill.txt");
            for (String skill : listSkills) {
                if (s.equals(skill.split("\\|")[1])) {
                    count++;
                }
            }

        }

        if (count == 0) {
            return listCandidates.size();
        }

        if (type == 1) {
            if (gradYear < yearOfBirth || gradYear > Calendar.getInstance().get(Calendar.YEAR)) {
                return listCandidates.size();
            }
        }
        if (!(gradRank.equals("Excellence") || gradRank.equals("Good") || gradRank.equals("Fair") || gradRank.equals("Poor"))) {
            return listCandidates.size();
        }

        if (type == 2) {
            if (yearExp > age) {
                return listCandidates.size();
            }
        }

        if (type != 0 && type != 1 & type != 2) {
            return listCandidates.size();
        }

        return listCandidates.size() + 1;
    }

    public int newAdd(Candidate c) {

        List<String> listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
        int age = Calendar.getInstance().get(Calendar.YEAR) - c.getYearOfBirth();

//        listCandidates.add(new Candidate((listCandidates.size() + 1), c.firseName, c.lastName, yearOfBirth, address, phone, email, type, lsSkill).saveFormat());
        if (c.getId() != listCandidates.size() + 1) {
            return listCandidates.size();
        }
        if (!c.getFirstName().matches(SystemConstraint.NAME_VALID)) {
            return listCandidates.size();
        }

        if (!c.getLastName().matches(SystemConstraint.NAME_VALID)) {
            return listCandidates.size();
        }

        if (c.getYearOfBirth() < 1900 || c.getYearOfBirth() > Calendar.getInstance().get(Calendar.YEAR)) {
            return listCandidates.size();
        }

        if (!c.getPhone().matches(SystemConstraint.PHONE_VALID)) {
            return listCandidates.size();
        }
        if (!c.getEmail().matches(SystemConstraint.EMAIL_VALID)) {
            return listCandidates.size();
        }

        if (c.getListSkills().size() != c.getListSkills().size()) {
            return listCandidates.size();
        }

        int count = 0;
        for (Skill s : c.getListSkills()) {
            List<String> listSkillsInFile = new FileHandler().getAllDataInFile("skill.txt");
            for (String skill : listSkillsInFile) {
                if (s.getSkillName().equals(skill.split("\\|")[1])) {
                    count++;
                }
            }

        }

        if (count != c.getListSkills().size()) {
            return listCandidates.size();
        }

//        c = new Fresher();
//
//        if (c.getType() == 1) {
//            if ((Fresher) c.get < yearOfBirth || gradYear > Calendar.getInstance().get(Calendar.YEAR)) {
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
        if (c.getType() != 0 && c.getType() != 1 & c.getType() != 2) {
            return listCandidates.size();
        }

        listCandidates.add(c.saveFormat());
        for (Skill skill : c.getListSkills()) {
            listCandidateSkills.add(c.getId() + "|" + skill.getSkillName() + "|" + skill.getYearOfExp());
        }
        writeFile(SystemConstraint.CANDIDATE, listCandidates);
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkills);
        messageSuccess("Add");

        return listCandidates.size() + 1;
    }

    public boolean newUpdate(int idUpdate) {

        if (idUpdate < 1 || idUpdate > listCandidates.size()) {
            System.out.println("Id entered doesn't exist!");
            return false;
        } else {
            return true;
        }
    }

    public void update(int idUpdate) {
        listCandidateSkillsUpdated = new ArrayList<>();
//        int idUpdate = dataInput.checkInputIntLimit("Enter candidate ID to update: ", 1, listCandidates.size());
        for (String candidate : listCandidates) {
            if (Integer.parseInt(candidate.split("\\|")[0]) == idUpdate) {
                Candidate candidateInfo = candidateView.inputCandidateInformation(listCandidates);
                String candidateUpdated = idUpdate + candidateInfo.saveFormat().substring(candidate.indexOf("|"));
                listCandidates.set(idUpdate - 1, candidateUpdated);
                for (String candidateSkill : listCandidateSkills) {
                    if (Integer.parseInt(candidateSkill.split("\\|")[0]) != idUpdate) {
                        listCandidateSkillsUpdated.add(candidateSkill);
                    }
                }
                for (Skill skill : candidateInfo.getListSkills()) {
                    listCandidateSkillsUpdated.add(idUpdate + "|" + skill.getSkillName() + "|" + skill.getYearOfExp());
                }
                break;
            }
        }
        writeFile(SystemConstraint.CANDIDATE, listCandidates);
        writeFile(SystemConstraint.SKILL_CANDIDATE, listCandidateSkillsUpdated);
        messageSuccess("update");
    }

    public int newDelete(int idDelete) {

        if (idDelete < 1 || idDelete > listCandidates.size()) {
            System.out.println("Id entered doesn't exist!");
            return 0;
        }
        return 1;
    }

    public void delete(int idDelete) {
        listCandidateSkillsUpdated = new ArrayList<>();
//        int candidateID = dataInput.checkInputIntLimit("Enter candidate ID to delete: ", 1, listCandidates.size());
        for (String candidate : listCandidates) {
            if (Integer.parseInt(candidate.split("\\|")[0]) == idDelete) {
                listCandidates.remove(idDelete - 1);
                break;
            }
        }
        for (int i = idDelete - 1; i < listCandidates.size(); i++) {
            String candidateUpdated = (i + 1) + listCandidates.get(i).substring(listCandidates.get(i).indexOf("|"));
            listCandidates.set(i, candidateUpdated);
        }
        for (String candidateSkill : listCandidateSkills) {
            int nextCandidateID = Integer.parseInt(candidateSkill.split("\\|")[0]);
            if (nextCandidateID != idDelete) {
                if (nextCandidateID > idDelete) {
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

//    public int deleteTest(int id, List<String> listCandidates) {
//        if (id < 1 || id > listCandidates.size()) {
//            return listCandidates.size();
//        } else {
//            return listCandidates.size() - 1;
//        }
//    }
//
//    public int updateTest(int id, List<String> listCandidates) {
//        if (id < 1 || id > listCandidates.size()) {
//            return listCandidates.size();
//        } else {
//            return listCandidates.size() - 1;
//        }
//    }
    public void candidateManagementController() {
        int candidateManagementChoice = view.candidateManagement(); //change variable name
        switch (candidateManagementChoice) {
            case 1:
                list();
                break;
            case 2:
                Candidate candidate = candidateView.inputCandidateInformation(listCandidates);
                newAdd(candidate);
                break;
            case 3:
                int idUpdate = dataInput.checkInputIntLimit("Enter candidate ID to update: ", 1, listCandidates.size());
                if (newUpdate(idUpdate)) {
                    update(idUpdate);
                }
                break;
            case 4:
                int idDelete = dataInput.checkInputIntLimit("Enter candidate ID to delete: ", 1, listCandidates.size());
                if (newDelete(idDelete)==1) {
                    delete(idDelete);
                }
            default:
                break;
        }
    }
//

    public static void main(String[] args) {

        List<String> listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
        System.out.println(listCandidates.size());

    }
}
