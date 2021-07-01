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
import model.Candidate;
import model.Skill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;

/**
 *
 * @author Hiep Nino
 */
public class CandidateSkillInfoManagementTest {

    private static CandidateManagement candidateManagement;
    private static String fName;
    private static String lName;
    private static int yearOfBirth;
    private static String address;
    private static String email;
    private static int numberOfSkill;
    private static String phone;
    private static int type;
    private static int gradYear;
    private static int id;
    private static String gradRank;
    private static int yearExp;
    private static ArrayList<String> lsSkill;
    private static ArrayList<String> lsSkillExisted;
    private static List<String> listCandidates;
    private static Candidate c;

    int idDelete;
    int idUpdate;
    private static int result;
    private static boolean resultBool;

    public CandidateSkillInfoManagementTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("BeforeClass method is called!");

        candidateManagement = new CandidateManagement();
        lsSkill = new ArrayList<>();
        lsSkillExisted = (ArrayList<String>) new FileHandler().getAllDataInFile("skill.txt");
        listCandidates = new FileHandler().getAllDataInFile(SystemConstraint.CANDIDATE);

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of candidateSkill method, of class CandidateSkillInfoManagement.
     */
    @Test
    public void testAdd() {

        //TC1 : tên có kí tự đặc biệt
        fName = "**hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;
        lsSkill.add("Java");
        lsSkill.add("Pascal");
        lsSkill.add("C#");
        phone = "01234567891";
        type = 0;
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        int expResult = 3;
        assertEquals("Test cas #1: ", new Double(expResult), new Double(result));

        //TC2 : add thành công
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;

        phone = "01234567891";
        type = 0;
        gradRank = "Good";
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 4;
        assertEquals("Test cas #2: ", new Double(expResult), new Double(result));

        //TC3 : năm sinh lớn hơn năm hiện tại
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2023;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;

        phone = "01234567891";
        type = 0;
        gradRank = "Good";
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 3;
        assertEquals("Test cas #3: ", new Double(expResult), new Double(result));

        //TC4 : sđt có ít hơn 10 chữ số
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;

        phone = "0123456";
        type = 0;
        gradRank = "Good";
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 3;
        assertEquals("Test cas #4: ", new Double(expResult), new Double(result));

        //TC5 : type candidate khác 0,1 và 2
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;

        phone = "01234567891";
        type = 5;
        gradRank = "Good";
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 3;
        assertEquals("Test cas #5: ", new Double(expResult), new Double(result));

        //TC6 : Graduation Rank khác exellence, good,Fair và Poor
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;

        phone = "01234567891";
        type = 0;
        gradRank = "Khá là được";
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 3;
        assertEquals("Test cas #6: ", new Double(expResult), new Double(result));

        //TC7 : email không đúng format
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt";
        numberOfSkill = 3;

        phone = "01234567891";
        type = 0;
        gradRank = "Good";
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 3;
        assertEquals("Test cas #7: ", new Double(expResult), new Double(result));

        //TC8 : năm kinh nghiệm (type Experience) lớn hơn tuổi
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;

        phone = "01234567891";
        type = 2;
//        gradRank = "Good";
        yearExp = 90;
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 3;
        assertEquals("Test cas #8: ", new Double(expResult), new Double(result));

        //TC9 : năm tốt nghiệp(type Fresher) nhỏ hơn năm  sinh
        fName = "hiep";
        lName = "vu";
        yearOfBirth = 2000;
        address = "Hung Yen";
        email = "vuhiep@fpt.edu";
        numberOfSkill = 3;

        phone = "01234567891";
        type = 1;
//      gradRank = "Good";
        gradYear = 1997;
        result = candidateManagement.checkAdd(fName, lName, yearOfBirth, address, phone, email, numberOfSkill, lsSkill, type, gradYear, gradRank, yearExp);

        expResult = 3;
        assertEquals("Test cas #9: ", new Double(expResult), new Double(result));
    }

    @Test
    public void testDelete() {

        //TC10 : id cần xóa không tồn tại
        idDelete = 10;
        result = candidateManagement.newDelete(idDelete);
        int expResult = 0;
        assertEquals("Test cas #10: ", new Double(expResult), new Double(result));

        //TC10 : id cần xóa tồn tại, xóa thành công
        idDelete = 1;
        result = candidateManagement.newDelete(idDelete);
        expResult = 1;
        assertEquals("Test cas #11: ", new Double(expResult), new Double(result));

    }

    @Test
    public void testUpdate() {

        //TC10 : id cần update không tồn tại
        idUpdate = 10;
        resultBool = candidateManagement.newUpdate(idUpdate);
        boolean expResultBool2 = false;
        assertEquals("Test cas #12: ", expResultBool2, resultBool);

        //TC10 : id cần update tồn tại, xóa thành công
        idUpdate = 1;
        resultBool = candidateManagement.newUpdate(idUpdate);
        expResultBool2 = true;
        assertEquals("Test cas #13: ", expResultBool2, resultBool);

        //các bước update còn lại trong function này có thể testing y hệt function add candidate
    }

}
