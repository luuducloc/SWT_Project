/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import constraint.SystemConstraint;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.InterfaceView;
import view.SKillView;

/**
 *
 * @author CHU NAM
 */
public class SkillInfoManagementTest {

    static SkillInfoManagement skillManager = null;
    static InterfaceView view = null;
    static common.FileHandler fileHandler = null;

    static ArrayList<String> listSkills = null;
    static SKillView skillView = null;
    static int skillID;
    static String skillName;
    static int result;
    int expResult;

    public SkillInfoManagementTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        skillManager = new SkillInfoManagement();
        view = new InterfaceView();

        listSkills = (ArrayList<String>) new common.FileHandler().getAllDataInFile(SystemConstraint.SKILL);
        skillView = new SKillView();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class SkillInfoManagement.
     */
    @Test
    public void testAdd() {
        //TC1
        expResult = 6;
        String skillName = "Python";
        result = skillManager.addTest(skillName, listSkills);
        assertEquals("Test cas #1: ", new Double(expResult), new Double(result));

        //TC2
        expResult = 6;
        skillName = "1231231231";
        result = skillManager.addTest(skillName, listSkills);
        assertEquals("Test cas #2: ", new Double(expResult), new Double(result));
        //TC3
        expResult = 6;
        skillName = "#######";
        result = skillManager.addTest(skillName, listSkills);
        assertEquals("Test cas #3: ", new Double(expResult), new Double(result));
        //TC4
        expResult = 6;
        skillName = "";
        result = skillManager.addTest(skillName, listSkills);
        assertEquals("Test cas #4: ", new Double(expResult), new Double(result));
        //TC5
        expResult = 6;
        skillName = "Pascal";
        result = skillManager.addTest(skillName, listSkills);
        assertEquals("Test cas #5: ", new Double(expResult), new Double(result));
    }

    /**
     * Test of addTest method, of class SkillInfoManagement.
     */
    /**
     * Test of update method, of class SkillInfoManagement.
     */
    @Test
    public void testUpdate() {

        //TC6 : id cần update không tồn tại
        skillID = 10;
        result = skillManager.updateTest(skillID, listSkills);
        int expResult = 6;
        assertEquals("Test cas #6: ", new Double(expResult), new Double(result));

        //TC7 : id cần update tồn tại, xóa thành công
        skillID = 1;
        result = skillManager.updateTest(skillID, listSkills);
        expResult = 5;
        assertEquals("Test cas #7: ", new Double(expResult), new Double(result));
    }

    /**
     * Test of delete method, of class SkillInfoManagement.
     */
    @Test
    public void testDelete() {
//      
        //TC8 : id cần xóa không tồn tại
        skillID = 10;
        result = skillManager.deleteTest(skillID, listSkills);
        int expResult = 6;
        assertEquals("Test cas #8: ", new Double(expResult), new Double(result));

        //TC9 : id cần xóa tồn tại, xóa thành công
        skillID = 1;
        result = skillManager.deleteTest(skillID, listSkills);
        expResult = 5;
        assertEquals("Test cas #9: ", new Double(expResult), new Double(result));
    }

    /**
     * Test of skillInformationManagementController method, of class
     * SkillInfoManagement.
     */
    

}
