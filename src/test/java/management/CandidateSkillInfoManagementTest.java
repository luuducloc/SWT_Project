/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import constraint.SystemConstraint;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import view.CandidateSkillView;
import view.InterfaceView;

/**
 *
 * @author s1tha
 */
public class CandidateSkillInfoManagementTest {

    CandidateSkillInfoManagement candidateManager = null;
    InterfaceView view = null;
    common.FileHandler fileHandler = null;
    List<String> listCandidateSkills = null;
    List<String> listCandidates = null;
    List<String> listSkills = null;
    CandidateSkillView candidateSkillView = null;
    int candidateID;
    String skillName;
    int yearOfExp;
    int result;

    public CandidateSkillInfoManagementTest() {
    }

    @BeforeAll
    public void setUpClass() {
        candidateManager = new CandidateSkillInfoManagement();
        view = new InterfaceView();
        fileHandler = new common.FileHandler();

        listCandidateSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL_CANDIDATE);
        listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
        listSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL);
        candidateSkillView = new CandidateSkillView();
    }

    @AfterAll
    public void tearDownClass() {
        view = null;
        fileHandler = null;
        listCandidateSkills = null;
        listCandidates = null;
        listSkills = null;
        candidateSkillView = null;
    }

    @Before
    public void setUp() {
        System.out.println("SetUpMethod");
        candidateManager = new CandidateSkillInfoManagement();
        view = new InterfaceView();
        fileHandler = new common.FileHandler();

        listCandidateSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL_CANDIDATE);
        listCandidates = fileHandler.getAllDataInFile(SystemConstraint.CANDIDATE);
        listSkills = fileHandler.getAllDataInFile(SystemConstraint.SKILL);
        candidateSkillView = new CandidateSkillView();
    }

    @After
    public void tearDown() {
        System.out.println("EndMethod");
    }

    //List candidate information = 3;
    @Test
    public void add() {
        //TC1
        candidateID = 1;
        skillName = "Java";
        yearOfExp = 1;
        result = candidateManager.add(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #1: ", result, 4);

        //TC2
        candidateID = 0;
        skillName = "Java";
        yearOfExp = 1;
        result = candidateManager.add(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #2: ", result, 4);

        //TC3
        candidateID = 1;
        skillName = "Java";
        yearOfExp = 1;
        result = candidateManager.add(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #3: ", result, 4);

        //TC4
        candidateID = 1;
        skillName = "C#";
        yearOfExp = 0;
        result = candidateManager.add(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #4: ", result, 4);

        //TC5
        candidateID = 1;
        skillName = "Python";
        yearOfExp = 1;
        result = candidateManager.add(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #5: ", result, 4);

        //TC6
        candidateID = 1;
        skillName = "PHP";
        yearOfExp = 1;
        result = candidateManager.add(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #6: ", result, 4);
        
        //TC6
        candidateID = -1;
        skillName = "Python";
        yearOfExp = -1;
        result = candidateManager.add(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #6: ", result, 4);
    }

    @Test
    public void delete() {
        //TC1
        candidateID = 1;
        skillName = "Java";
        result = candidateManager.delete(candidateID, skillName);
        assertEquals("Test Case #1: ", result, 3);

        //TC2
        candidateID = 0;
        skillName = "Java";
        result = candidateManager.delete(candidateID, skillName);
        assertEquals("Test Case #2: ", result, 3);

        //TC3
        candidateID = 1;
        skillName = "Java";
        result = candidateManager.delete(candidateID, skillName);
        assertEquals("Test Case #3: ", result, 3);

        //TC4
        candidateID = 1;
        skillName = "PHP";
        result = candidateManager.delete(candidateID, skillName);
        assertEquals("Test Case #4: ", result, 3);
        
        //TC5
        candidateID = -1;
        skillName = "C#";
        result = candidateManager.delete(candidateID, skillName);
        assertEquals("Test Case #5: ", result, 3);
    }

    public void update() {
        //TC1
        candidateID = 1;
        skillName = "Python";
        yearOfExp = 1;
        result = candidateManager.update(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #1: ", result, -1);

        //TC2
        candidateID = -1;
        skillName = "Python";
        yearOfExp = 1;
        result = candidateManager.update(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #2: ", result, 3);

        //TC3
        candidateID = 1;
        skillName = "Java";
        yearOfExp = 1;
        result = candidateManager.update(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #3: ", result, 3);

        //TC4
        candidateID = 1;
        skillName = "PHP";
        yearOfExp = 1;
        result = candidateManager.update(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #4: ", result, 3);

        //TC5
        candidateID = 1;
        skillName = "Python";
        yearOfExp = -1;
        result = candidateManager.update(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #5: ", result, 3);
        
                //TC6
        candidateID = 0;
        skillName = "C#";
        yearOfExp = 0;
        result = candidateManager.update(candidateID, skillName, yearOfExp);
        assertEquals("Test Case #6: ", result, 3);
    }

    public void candidateSkill() {
        int choice;
        boolean check;

        //TC1
        choice = 1;
        check = candidateManager.candidateSkill(choice);
        assertEquals("Test Case #1: ", check, true);

        //TC2
        choice = 2;
        check = candidateManager.candidateSkill(choice);
        assertEquals("Test Case #2: ", check, true);
        
        //TC3
        choice = 3;
        check = candidateManager.candidateSkill(choice);
        assertEquals("Test Case #3: ", check, true);
        
        //TC4
        choice = -1;
        check = candidateManager.candidateSkill(choice);
        assertEquals("Test Case #4: ", check, false);   
        
        //TC5
        choice = 0;
        check = candidateManager.candidateSkill(choice);
        assertEquals("Test Case #4: ", check, false); 
    }
}
