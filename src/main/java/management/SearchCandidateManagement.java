/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import common.FileHandler;
import java.util.List;
import view.InterfaceView;

/**
 *
 * @author Dang Phat
 */
public class SearchCandidateManagement {


    private InterfaceView view = new InterfaceView();
    FileHandler fileHandler = new FileHandler();

    public void searchBySkillController() {
        List<String> listSkills = fileHandler.getAllDataInFile("skill.txt");
        String skillName = null;
        boolean isContinue = true;
        while (isContinue) {
            skillName = view.searchBySkill(listSkills);
            if(skillName != null){
                isContinue = false;
            }else{
                System.out.println("Skill does not exist! Please enter again!");
            }
        }
        List<String> listCandidates = fileHandler.getCandidateBySkillName(skillName);
        System.out.println("List candidates by skill: " + skillName);
        if (listCandidates.isEmpty()) {
            System.out.println("No candidate has this skill!");
        } else {
            for (String candidate : listCandidates) {
                System.out.println(candidate);
            }
        }
    }
}
