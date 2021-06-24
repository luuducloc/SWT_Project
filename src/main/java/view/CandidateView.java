/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.DataInput;
import common.FileHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Candidate;
import model.Experience;
import model.Fresher;
import model.Intern;
import model.Skill;

/**
 *
 * @author Dang Phat
 */
public class CandidateView {
    DataInput dataInput = new DataInput();
    public Candidate inputCandidateInformation(List<String> listCandidate) {
        List<String> listSkills = new FileHandler().getAllDataInFile("skill.txt");
        String firstName = dataInput.checkNameInput("Enter first name: "); //change function called
        String lastName = dataInput.checkNameInput("Enter last name: "); //change function called
        int birthDate = dataInput.checkInputIntLimit("Enter birth date: ", 1900,
                Calendar.getInstance().get(Calendar.YEAR));
        String address = dataInput.checkInputString("Enter address: ");
        String phone = dataInput.checkInputPhone("Enter phone: ");
        String email = dataInput.checkInputEmail("Enter email: ");
        int numOfProSkills = dataInput.checkInputIntLimit("Enter the number of Professional Skills: ", 1, listSkills.size());
        ArrayList<Skill> listProSkill = new ArrayList<>();
        for (int i = 0; i < numOfProSkills; i++) {
            Skill skill = new Skill();
            skill.setSkillName(dataInput.checkInputSkill("Enter skill name " + (i + 1) + ": "));
            skill.setYearOfExp(dataInput.checkInputExprience("Enter year of experience: ", birthDate));
            listProSkill.add(skill);
        }
        int type = dataInput.checkInputIntLimit("Enter candidate type (0 for Intern, 1 for Fresher, 2 for Experience): ", 0, 2);
        Candidate candidate = new Candidate(listCandidate.size() + 1, firstName, lastName,
                birthDate, address, phone, email, type, listProSkill);        
        //check id exist or not
        switch (type) {
            case 0:
                return createInternship(candidate);                
            case 1:
                return createFresher(candidate);
            default: //change case 2 to default
                return createExperience(candidate);
        }
    }

    private Candidate createExperience(Candidate candidate) {
        int yearExperience = dataInput.checkInputExprience("Enter year of experience: ", candidate.getYearOfBirth());
        String softSkill = dataInput.checkInputString("Enter soft skill: ");
        return new Experience(yearExperience, softSkill,
                candidate.getId(), candidate.getFirstName(), candidate.getLastName(),
                candidate.getYearOfBirth(), candidate.getAddress(),
                candidate.getPhone(), candidate.getEmail(), candidate.getType(), candidate.getListSkills());        
    }

    //allow user create fresher
    private Candidate createFresher(Candidate candidate) {
        int graduationDate = dataInput.checkInputIntLimit("Enter graduation date: ", candidate.getYearOfBirth(), Calendar.getInstance().get(Calendar.YEAR));
        String graduationRank = dataInput.checkInputGraduationRank("Enter graduation rank: ");
        String education = dataInput.checkInputString("Enter education: ");
        return new Fresher(graduationDate, graduationRank, education, candidate.getId(),
                candidate.getFirstName(), candidate.getLastName(),
                candidate.getYearOfBirth(), candidate.getAddress(),
                candidate.getPhone(), candidate.getEmail(),
                candidate.getType(), candidate.getListSkills());
    }

    //allow user create internship
    private Candidate createInternship(Candidate candidate) {
        String major = dataInput.checkInputString("Enter major: ");
        String semester = dataInput.checkInputString("Enter semester: ");
        String university = dataInput.checkInputString("Enter university: ");
        return new Intern(major, semester, university, candidate.getId(),
                candidate.getFirstName(), candidate.getLastName(),
                candidate.getYearOfBirth(), candidate.getAddress(),
                candidate.getPhone(), candidate.getEmail(),
                candidate.getType(), candidate.getListSkills());
    }
    
    public void listExperiences(List<Experience> list){
        System.out.println("====== EXPERIENCE CANDIDATE =====");
        for (Experience experience : list) {
            System.out.println(experience.saveFormat());
        }
        System.out.println("");
    }
    
    public void listFreshers(List<Fresher> list){
        System.out.println("====== FRESHER CANDIDATE ====="); //fix text
        for (Fresher fresher : list) {
            System.out.println(fresher.saveFormat());
        }
        System.out.println("");
    }
    
    public void listInterns(List<Intern> list){
        System.out.println("====== INTERNSHIP CANDIDATE ====="); //fix text
        for (Intern intern : list) {
            System.out.println(intern.saveFormat());
        }
        System.out.println("");
    }
}
