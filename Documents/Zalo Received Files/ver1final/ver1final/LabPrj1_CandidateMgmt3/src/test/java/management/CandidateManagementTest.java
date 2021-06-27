/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import view.InterfaceView;

/**
 *
 * @author Hiep Nino
 */
public class CandidateManagementTest {

    private static CandidateManagement canMng;
     private static InterfaceView view;

    public CandidateManagementTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("BeforeClass method is called!");
        canMng = new CandidateManagement();
        view = new InterfaceView();
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("");
    }

    @BeforeEach
    public static void setUp() {
        System.out.println("");
    }

    @AfterEach
    public static void tearDown() {
        System.out.println("");
    }

    /**
     * Test of candidateManagementController method, of class
     * CandidateManagement.
     */
    @Test
    public static void testCandidateManagementController() {
        System.out.println("candidateManagementController");
        CandidateManagement instance = new CandidateManagement();
        instance.candidateManagementController();
        1 = view.candidateManagement();
        assertEquals( 1, s);

        // TODO review the generated test code and remove the default call to fail.
    }

}
