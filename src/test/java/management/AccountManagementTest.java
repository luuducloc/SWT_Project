/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import common.FileHandler;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import view.AccountView;
import view.InterfaceView;
import org.junit.After;
import static org.junit.Assert.*;

/**
 *
 * @author locluu
 */
public class AccountManagementTest {
    static FileHandler fileHandler = null;
    static InterfaceView view = null;
    static AccountView accountView = null;
    static AccountManagement account = null;
    static List<String> listAccounts = null;
    int accountID;
    String userName;
    String password;
    String rePassword;
    int type, result;
    boolean resultUpdate;
    
    
    public AccountManagementTest() {
        view = null;
        fileHandler = null;
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        account = new AccountManagement();
        view = new InterfaceView();
        fileHandler = new FileHandler();
        listAccounts = fileHandler.getListAccounts();
        accountView = new AccountView();
    }
    
    @AfterAll
    public static void tearDownClass() {
        view = null;
        fileHandler = null;
        listAccounts = null;
        accountView = null;
        
    }
    
    @Before
    public void setUp() {
        System.out.println("Setup method");
        account = new AccountManagement();
        view = new InterfaceView();
        fileHandler = new FileHandler();
        listAccounts = fileHandler.getListAccounts();
        accountView = new AccountView();
    }
    
    @After
    public void tearDown() {
        System.out.println("End method");
    }

    /**
     * Test of userManagementController method, of class AccountManagement.
     */
    @Test
    public void add() {
        //TC1
        userName = "mrc123";
        password = "c123456";
        rePassword ="c123456";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #1: ", result, 3);
        
        //TC2
        userName = "mra123";
        password = "c123456";
        rePassword ="c123456";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #2: ", result, 2);
        
        //TC3
        userName = "mrc123";
        password = "123456";
        rePassword ="123456";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #3: ", result, 2);
        
        //TC4
        userName = "mrc123";
        password = "c123456";
        rePassword ="c12345";
        type = 1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #4: ", result, 2);
        
        //TC5
        userName = "mrc123";
        password = "c123456";
        rePassword ="c123456";
        type = -1;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #5: ", result, 2);
        
        //TC6
        userName = "mra123";
        password = "c123456";
        rePassword ="c123456";
        type = 0;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #6: ", result, 2);
        
        //TC7
        userName = "mra123";
        password = "c123456";
        rePassword ="c123456";
        type = 5;
        result = account.add(userName, password, rePassword, type);
        assertEquals("Test Case #7: ", result, 2);
        
    }
    
    public void update() {
        //TC01
        accountID = 1;
        type = 1;
        resultUpdate = account.update(accountID, type);
        assertEquals("Test Case #8", result, true);
        
        //TC02
        accountID = 0;
        type = 1;
        resultUpdate = account.update(accountID, type);
        assertEquals("Test Case #9", result, false);
        
        //TC03
        accountID = 1;
        type = 2;
        resultUpdate = account.update(accountID, type);
        assertEquals("Test Case #10", result, true);
        
        //TC04
        accountID = 1;
        type = -1;
        resultUpdate = account.update(accountID, type);
        assertEquals("Test Case #11", result, false);
        
        
        //TC05
        accountID = -1;
        type = 1;
        resultUpdate = account.update(accountID, type);
        assertEquals("Test Case #12", result, false);
        
        //TC06
        accountID = 10;
        type = 1;
        resultUpdate = account.update(accountID, type);
        assertEquals("Test Case #13", result, false);
        
        //TC08
        accountID = 1;
        type = 3;
        resultUpdate = account.update(accountID, type);
        assertEquals("Test Case #14", result, false);
    }
    
    public void delete() {
        //TC1
        accountID = 1;
        result = account.delete(accountID);
        assertEquals("Test case #15", result, 2);
        
        //TC2
        accountID = 2;
        result = account.delete(accountID);
        assertEquals("Test case #16", result, 2);
        
        //TC3
        accountID = 3;
        result = account.delete(accountID);
        assertEquals("Test case #17", result, 2);
        
        //TC4
        accountID = 7;
        result = account.delete(accountID);
        assertEquals("Test case #18", result, 3);
        
        //TC5
        accountID = -1;
        result = account.delete(accountID);
        assertEquals("Test case #19", result, 3);
        
    }
    
    
    
}
