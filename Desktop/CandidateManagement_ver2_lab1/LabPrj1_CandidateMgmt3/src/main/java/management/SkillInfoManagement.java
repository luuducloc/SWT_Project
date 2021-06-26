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

    private void writeFile(String URL, List<String> list) {
        fileHandler.writeFile(URL, list);
    }

    private void showMess(String mess) {
        System.out.println(mess);
    }

    private void add() {
        List<String> listSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL);
        String skillName = "";
        boolean frag = true;
        while (frag) {
            skillName = new SKillView().inputSkillName();
            if (checkExistSkillOnId(listSkills, skillName) == true) {
                frag = false;
            }
            if (checkExistSkillOnId(listSkills, skillName) == true) {
                Skill skill = new Skill();
                skill.setSkillID(listSkills.size() + 1);
                skill.setSkillName(skillName);
                listSkills.add(skill.saveFormat());
                writeFile(SystemConstraint.SKILL, listSkills);
            }
        }

    }

    public boolean checkExistSkillOnId(List<String> listSkills, String skillName) {//****
        String regex = "^[0-9]+$";
        int count = 0;
        for (String l : listSkills) {
            if (l.split("\\|")[1].equalsIgnoreCase(skillName)) {

                showMess("Name has in list");
                return false;
            }
            count++;
        }
        if (count == listSkills.size()) {
            if (skillName.matches(regex)) {

                showMess("Name skill phải là chữ");

                return false;
            } else {
                showMess("OK!");
                return true;
            }
        }
        return false;
    }

    private void update() {
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
                break;
            }
        }
        writeFile(SystemConstraint.SKILL, listSkills);
        showMess("Update successfully!");
    }

    private void delete() {
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
}
