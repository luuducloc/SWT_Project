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
public class SKillView {

    DataInput dataInput = new DataInput();

    public String inputSkillName() {
        return dataInput.checkInputString("Enter skill name: ");
    }

    public int getSkillIdToUpdate(List<String> listSkills) {
        return dataInput.checkInputIntLimit("Enter skill ID to update: ", 1, listSkills.size());
    }

    public int getSkillIdToDelete(List<String> listSkills) {
        return dataInput.checkInputIntLimit("Enter skill ID to delete: ", 1, listSkills.size());
    }
}
