/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import common.FileHandler;
import constraint.SystemConstraint;
import java.util.ArrayList;
import java.util.List;
import model.Skill;
import view.InterfaceView;
import view.SKillView;

/**
 *
 * @author Dang Phat
 */
public class SkillInfoManagement {
    
    private InterfaceView view = new InterfaceView();
    FileHandler fileHandler = new FileHandler();
    
    List<String> listSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL);
    SKillView skillView=new SKillView();
    private void writeFile(String uel, List<String> list) {
        fileHandler.writeFile(uel, list);
    }

    private void showMess(String mess) {
        System.out.println(mess);
    }

    public void add() {
        String skillName = "";
        boolean frag = true;
        while (frag) {
            skillName = new SKillView().inputSkillName();
            if (checkExistSkillOnId(listSkills, skillName) == true) {
                Skill skill = new Skill();
                skill.setSkillID(listSkills.size() + 1);
                skill.setSkillName(skillName);
                listSkills.add(skill.saveFormat());
                writeFile(SystemConstraint.SKILL, listSkills);
                System.out.println("Add Succesful");
                break;
            }else{
                showMess("Name cannot be a number Or Name is Already!!");
            }
        }
    }
    
    public int addTest(String skillName,ArrayList<String>listSkill){
        String regex = "^[0-9]+$";
       
        for (String s : listSkill) {
            if(s.split("\\|")[1].equalsIgnoreCase(skillName)){
                return listSkill.size();
            }if (skillName.matches(regex)) {
                return listSkills.size();
            }
            if(skillName.isEmpty()){
                return listSkills.size();
            }
        }
      
        
        return listSkills.size();
    }
    
    private boolean checkExistSkillOnId(List<String> listSkills, String skillName) {//****
        String regex = "^[0-9]+$";
        int count = 0;
        for (String l : listSkills) {
            if (l.split("\\|")[1].equalsIgnoreCase(skillName)) {
                return false;
            }
            count++;
        }
        if (count == listSkills.size()) {
            if (skillName.matches(regex)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    public void update() {
        int skillID = new SKillView().getSkillIdToUpdate(listSkills);
        String oldSkill = "";
        String newSkill = "";
        for (String skill : listSkills) {
            if (Integer.parseInt(skill.split("\\|")[0]) == skillID) {
                oldSkill = skill.split("\\|")[1];
                newSkill = new SKillView().inputSkillName();
                Skill skillUpdated = new Skill();
                skillUpdated.setSkillID(skillID);
                skillUpdated.setSkillName(newSkill);
                listSkills.set(skillID - 1, skillUpdated.saveFormat());
               
            
            }
        }
        writeFile(SystemConstraint.SKILL, listSkills);
        showMess("Update successfully!");
    }
    //Test
//    public int update(int skillId,String skillName){
//        if(skillId!=skillView.getSkillIdToUpdate(listSkills, skillId)){
//            return listSkills.size();
//        }
//        String oldSkill = "";
//     
//        for (String skill : listSkills) {
//            if (Integer.parseInt(skill.split("\\|")[0]) == skillId) {
//                oldSkill = skill.split("\\|")[1];
//                Skill skillUpdated = new Skill();
//                skillUpdated.setSkillID(skillId);
//                skillUpdated.setSkillName(skillName);
//                listSkills.set(skillId - 1, skillUpdated.saveFormat());
//                return listSkills.size();
//            
//            }
//        }
//        return listSkills.size();
//    }
    public void delete() {
        ArrayList<String> listCandidateSkillsUpdated = new ArrayList<>();
        int skillID = new SKillView().getSkillIdToDelete(listSkills);
        String skillNameRemove = "";
        for (String skill : listSkills) {
            if (Integer.parseInt(skill.split("\\|")[0]) == skillID) {
                skillNameRemove = skill.split("\\|")[1];
                listSkills.remove(skillID - 1);
                break;
            }
        }
        for (int i = skillID - 1; i < listSkills.size(); i++) {
            String skillUpdated = (i + 1) + listSkills.get(i).substring(listSkills.get(i).indexOf("|"));
            listSkills.set(i, skillUpdated);
        }
        writeFile(SystemConstraint.SKILL, listSkills);
        showMess("Delete successfully!");
    }

    public void skillInformationManagementController() {
        int skillInfoManagementChoice = view.skillInformationManagement();
        switch (skillInfoManagementChoice) {
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
}
